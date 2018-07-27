package com.wmq.sys.utils.mybatis.paginatc;

import com.wmq.sys.utils.mybatis.paginatc.dialect.Dialect;
import com.wmq.sys.utils.mybatis.paginatc.domain.PageBounds;
import com.wmq.sys.utils.mybatis.paginatc.domain.Paginator;
import com.wmq.sys.utils.mybatis.paginatc.support.PropertiesHelper;
import com.wmq.sys.utils.mybatis.paginatc.support.SQLHelp;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.*;


/**
 * 为MyBatis提供基于方言(Dialect)的分页查询的插件
 * <p>
 * 将拦截Executor.query()方法实现分页方言的插入.
 *
 * @author badqiu
 * @author miemiedev
 */

@Intercepts({@Signature(
        type = Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@Component      //不加这个注解的话, 使用@Autowired 就不能注入进去了
@ConfigurationProperties(prefix = "offsetLimitInterceptor")  // 配置文件中的前缀
public class OffsetLimitInterceptor implements Interceptor {
    private static Logger logger = LoggerFactory.getLogger(OffsetLimitInterceptor.class);
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;
    private String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)

    static ExecutorService Pool;
    String dialectClass;
    boolean asyncTotalCount = false;

    public Object intercept(final Invocation invocation) throws Throwable {
        final Executor executor = (Executor) invocation.getTarget();
        final Object[] queryArgs = invocation.getArgs();
        final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
        final Object parameter = queryArgs[PARAMETER_INDEX];
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的
        if (!ms.getId().matches(pageSqlId)) {
            return invocation.proceed();
        }

      /*  final RowBounds rowBounds = (RowBounds)queryArgs[ROWBOUNDS_INDEX];
        final PageBounds pageBounds = new PageBounds(rowBounds);*/

        final PageBounds pageBounds = PageContext.getPage();
        if (pageBounds.getOffset() == RowBounds.NO_ROW_OFFSET
                && pageBounds.getLimit() == RowBounds.NO_ROW_LIMIT
                && pageBounds.getOrders().isEmpty()) {
            return invocation.proceed();
        }

        final Dialect dialect;
        try {
            Class clazz = Class.forName(dialectClass);
            Constructor constructor = clazz.getConstructor(MappedStatement.class, Object.class, PageBounds.class);
            dialect = (Dialect) constructor.newInstance(new Object[]{ms, parameter, pageBounds});
        } catch (Exception e) {
            throw new ClassNotFoundException("Cannot create dialect instance: " + dialectClass, e);
        }

        final BoundSql boundSql = ms.getBoundSql(parameter);

        queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, dialect.getPageSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
        queryArgs[PARAMETER_INDEX] = dialect.getParameterObject();
        queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

        Boolean async = pageBounds.getAsyncTotalCount() == null ? asyncTotalCount : pageBounds.getAsyncTotalCount();
        Future<List> listFuture = call(new Callable<List>() {
            public List call() throws Exception {
                return (List) invocation.proceed();
            }
        }, async);


        if (pageBounds.isContainsTotalCount()) {
            Callable<Paginator> countTask = new Callable() {
                public Object call() throws Exception {
                    Integer count;
                    Cache cache = ms.getCache();
                    if (cache != null && ms.isUseCache() && ms.getConfiguration().isCacheEnabled()) {
                        CacheKey cacheKey = executor.createCacheKey(ms, parameter, new PageBounds(), copyFromBoundSql(ms, boundSql, dialect.getCountSQL(), boundSql.getParameterMappings(), boundSql.getParameterObject()));
                        count = (Integer) cache.getObject(cacheKey);
                        if (count == null) {
                            count = SQLHelp.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
                            cache.putObject(cacheKey, count);
                        }
                    } else {
                        count = SQLHelp.getCount(ms, executor.getTransaction(), parameter, boundSql, dialect);
                    }
                    return new Paginator(pageBounds.getPage(), pageBounds.getLimit(), count);
                }
            };
            Future<Paginator> countFutrue = call(countTask, async);
            // return new PageList(listFuture.get(),countFutrue.get());
            PageContext.getPage().setPaginator(countFutrue.get());
            return listFuture.get();
        }

        return listFuture.get();
    }

    private <T> Future<T> call(Callable callable, boolean async) {
        if (async) {
            return Pool.submit(callable);
        } else {
            FutureTask<T> future = new FutureTask(callable);
            future.run();
            return future;
        }
    }

    private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql,
                                           String sql, List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, parameterMappings, parameter);
        return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

    private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
                                      String sql, List<ParameterMapping> parameterMappings, Object parameter) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, parameterMappings, parameter);
        for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }
        return newBoundSql;
    }

    //see: MapperBuilderAssistant
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuffer keyProperties = new StringBuffer();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
        PropertiesHelper propertiesHelper = new PropertiesHelper(properties);
        String dialectClass = propertiesHelper.getRequiredString("dialectClass");
        setDialectClass(dialectClass);

        setAsyncTotalCount(propertiesHelper.getBoolean("asyncTotalCount", false));

        setPoolMaxSize(propertiesHelper.getInt("poolMaxSize", 0));

    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    public void setDialectClass(String dialectClass) {
        logger.debug("dialectClass: {} ", dialectClass);
        this.dialectClass = dialectClass;
    }

    public void setAsyncTotalCount(boolean asyncTotalCount) {
        logger.debug("asyncTotalCount: {} ", asyncTotalCount);
        this.asyncTotalCount = asyncTotalCount;
    }

    public void setPageSqlId(String pageSqlId) {
        this.pageSqlId = pageSqlId;
    }

    public void setPoolMaxSize(int poolMaxSize) {

        if (poolMaxSize > 0) {
            logger.debug("poolMaxSize: {} ", poolMaxSize);
            Pool = Executors.newFixedThreadPool(poolMaxSize);
        } else {
            Pool = Executors.newCachedThreadPool();
        }


    }
}
