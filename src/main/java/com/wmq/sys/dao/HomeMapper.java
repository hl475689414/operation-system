package com.wmq.sys.dao;

import com.wmq.sys.vo.TodayDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by 程江涛 on 2018/5/16 0016
 */
@Mapper
public interface HomeMapper {

    @Select("select (SELECT IFNULL(COUNT(*),0) FROM common_user WHERE TO_DAYS(registerTime) = TO_DAYS(NOW())) as todayNew,\n" +
            "(SELECT IFNULL(COUNT(*),0) FROM common_user WHERE TO_DAYS(NOW( )) - TO_DAYS(registerTime) <=1) as yesterdayNew,\n" +
            "(SELECT IFNULL(COUNT(DISTINCT li.userId),0) FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE TO_DAYS(li.crateTime) = TO_DAYS(NOW())) as todayActive,\n" +
            "(SELECT IFNULL(COUNT(DISTINCT li.userId),0) FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE TO_DAYS(NOW( )) - TO_DAYS(li.crateTime) <=1) as yesterdayActive,\n" +
            "(SELECT IFNULL(COUNT(*),0) FROM login_info WHERE TO_DAYS(crateTime) = TO_DAYS(NOW())) as todayLogin,\n" +
            "(SELECT IFNULL(COUNT(*),0) FROM login_info WHERE TO_DAYS(NOW( )) - TO_DAYS(crateTime) <=1) as yesterdayLogin,\n" +
            "CONVERT(((SELECT IFNULL(SUM(money),0) FROM common_order WHERE state = 2 AND TO_DAYS(createTime) = TO_DAYS(NOW())) + \n" +
            "(SELECT IFNULL(SUM(money),0) FROM common_auth_order WHERE state = 1 AND TO_DAYS(orderTime) = TO_DAYS(NOW())) -\n" +
            "(SELECT IFNULL(SUM(money),0) FROM common_cash WHERE state = 1 AND TO_DAYS(createTime) = TO_DAYS(NOW())))/100,DECIMAL(10,2)) as todayIncome,\n" +
            "CONVERT(((SELECT IFNULL(SUM(money),0) FROM common_order WHERE state = 2 AND TO_DAYS(NOW( )) - TO_DAYS(createTime) <=1) +\n" +
            "(SELECT IFNULL(SUM(money),0) FROM common_auth_order WHERE state = 1 AND TO_DAYS(NOW( )) - TO_DAYS(orderTime) <=1)*100 -\n" +
            "(SELECT IFNULL(SUM(money),0) FROM common_cash WHERE state = 1 AND TO_DAYS(NOW( )) - TO_DAYS(createTime) <=1))/100,DECIMAL(10,2)) as yesterdayIncome")
    TodayDataVO getTodayData();

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count\n" +
            "        FROM (SELECT count(*) AS count, DATE_FORMAT(registerTime, '%H') AS hour\n" +
            "        FROM common_user WHERE TO_DAYS(registerTime) = TO_DAYS(NOW()) GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "        WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "        ORDER BY dayHour")
    List<Map<String, Integer>> getTodayNewByHours();

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count \n" +
            "        FROM (SELECT COUNT(DISTINCT li.userId) AS count, DATE_FORMAT(li.crateTime,'%H') AS hour  FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE TO_DAYS(li.crateTime) = TO_DAYS(NOW()) GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Integer>> getTodayActiveByHours();

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count \n" +
            "        FROM (SELECT COUNT(*) AS count, DATE_FORMAT(crateTime,'%H') AS hour  FROM login_info WHERE TO_DAYS(crateTime) = TO_DAYS(NOW()) GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Integer>> getTodayLoginByHours();

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count \n" +
            "        FROM (SELECT COUNT(*) AS count, DATE_FORMAT(crateTime,'%H') AS hour  FROM login_info WHERE TO_DAYS(NOW()) - TO_DAYS(registerTime) <= 1) GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Integer>> getYesterdayLoginByHours();

    @Select("SELECT dayHour,CONVERT((IF(A.count IS NULL, 0, A.count) + IF(C.count IS NULL, 0, C.count)*100 - IF(D.count IS NULL, 0, D.count))/100, DECIMAL(10,2)) as money  FROM \n" +
            "(SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%H') AS hour FROM common_order WHERE state = 2 AND TO_DAYS(createTime) = TO_DAYS(NOW()) GROUP BY hour ORDER BY 1) A \n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(orderTime, '%H') AS hour FROM common_auth_order WHERE state = 1 AND TO_DAYS(orderTime) = TO_DAYS(NOW()) GROUP BY hour ORDER BY 1) C ON C.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%H') AS hour FROM common_cash WHERE state = 1 AND TO_DAYS(createTime) = TO_DAYS(NOW()) GROUP BY hour ORDER BY 1) D ON D.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Double>> getTodayIncomeByHours();

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count\n" +
            "        FROM (SELECT count(*) AS count, DATE_FORMAT(registerTime, '%H') AS hour\n" +
            "        FROM common_user WHERE date(registerTime) = #{date} GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "        WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "        ORDER BY dayHour")
    List<Map<String, Integer>> getDateNewByHours(@Param(value = "date")String date);

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count \n" +
            "        FROM (SELECT COUNT(DISTINCT li.userId) AS count, DATE_FORMAT(li.crateTime,'%H') AS hour  FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE date(li.crateTime) = #{date} GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Integer>> getDateActiveByHours(@Param(value = "date")String date);

    @Select("SELECT dayHour,IF(count IS NULL, 0, count) count \n" +
            "        FROM (SELECT COUNT(*) AS count, DATE_FORMAT(crateTime,'%H') AS hour  FROM login_info WHERE date(crateTime) = #{date} GROUP BY hour ORDER BY 1) A\n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Integer>> getDateLoginByHours(@Param(value = "date")String date);

    @Select("SELECT dayHour,CONVERT((IF(A.count IS NULL, 0, A.count) + IF(C.count IS NULL, 0, C.count)*100 - IF(D.count IS NULL, 0, D.count))/100, DECIMAL(10,2)) as money  FROM \n" +
            "(SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%H') AS hour FROM common_order WHERE state = 2 AND date(createTime) = #{date} GROUP BY hour ORDER BY 1) A \n" +
            "        RIGHT JOIN (SELECT one.hours + two.hours AS dayHour\n" +
            "        FROM (SELECT 0 hours\n" +
            "        UNION ALL SELECT 1 hours\n" +
            "        UNION ALL SELECT 2 hours\n" +
            "        UNION ALL SELECT 3 hours\n" +
            "        UNION ALL SELECT 4 hours\n" +
            "        UNION ALL SELECT 5 hours\n" +
            "        UNION ALL SELECT 6 hours\n" +
            "        UNION ALL SELECT 7 hours\n" +
            "        UNION ALL SELECT 8 hours\n" +
            "        UNION ALL SELECT 9 hours) one\n" +
            "        CROSS JOIN (SELECT 0 hours\n" +
            "        UNION ALL SELECT 10 hours\n" +
            "        UNION ALL SELECT 20 hours) two\n" +
            "WHERE (one.hours + two.hours) < 24) B ON A.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(orderTime, '%H') AS hour FROM common_auth_order WHERE state = 1 AND date(orderTime) = #{date} GROUP BY hour ORDER BY 1) C ON C.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%H') AS hour FROM common_cash WHERE state = 1 AND date(createTime) = #{date} GROUP BY hour ORDER BY 1) D ON D.hour = CONVERT(B.dayHour, SIGNED)\n" +
            "ORDER BY dayHour")
    List<Map<String, Double>> getDateIncomeByHours(@Param(value = "date")String date);

    //@Select("SELECT DATE_FORMAT(registerTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM common_user WHERE DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= date(registerTime) AND date(registerTime) < CURDATE() GROUP BY day ORDER BY 1")
    @Select("set @d = #{days};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()));\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from (SELECT DATE_FORMAT(registerTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count \n" +
            "FROM common_user WHERE DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(registerTime) AND date(registerTime) < CURDATE() \n" +
            "GROUP BY day ORDER BY 1) A RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Integer>> getNewByDays(@Param(value = "days") Integer days);

    //@Select("SELECT DATE_FORMAT(li.crateTime,'%Y-%m-%d') AS day, IFNULL(COUNT(DISTINCT li.userId),0) AS count FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= date(li.crateTime) AND date(li.crateTime) < CURDATE() GROUP BY day ORDER BY 1")
    @Select("set @d = #{days};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()));\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from (SELECT DATE_FORMAT(li.crateTime,'%Y-%m-%d') AS day, IFNULL(COUNT(DISTINCT li.userId),0) AS count FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(li.crateTime) AND date(li.crateTime) < CURDATE() GROUP BY day ORDER BY 1) A RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Integer>> getActiveByDays(@Param(value = "days") Integer days);

    //@Select("SELECT DATE_FORMAT(crateTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM login_info WHERE DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= date(crateTime) AND date(crateTime) < CURDATE() GROUP BY day ORDER BY 1")
    @Select("set @d = #{days};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()));\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from (SELECT DATE_FORMAT(crateTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM login_info WHERE DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(crateTime) AND date(crateTime) < CURDATE() GROUP BY day ORDER BY 1) A RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Integer>> getLoginByDays(@Param(value = "days") Integer days);

    //    @Select("SELECT A.day,CONVERT((IF(A.count IS NULL, 0, A.count) + IF(C.count IS NULL, 0, C.count) - IF(D.count IS NULL, 0, D.count))/100, DECIMAL(10,2)) as money  FROM\n" +
//            "(SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_order WHERE state = 2 AND DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= date(createTime) AND date(createTime) < CURDATE() GROUP BY day ORDER BY 1) A \n" +
//            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(orderTime, '%Y-%m-%d') AS day FROM common_auth_order WHERE state = 1 AND DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= date(orderTime) AND date(orderTime) < CURDATE() GROUP BY day ORDER BY 1) C ON C.day=A.day\n" +
//            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_cash WHERE state = 1 AND DATE_SUB(CURDATE(), INTERVAL #{days} DAY) <= date(createTime) AND date(createTime) < CURDATE() GROUP BY day ORDER BY 1) D ON D.day=A.day\n" +
//            "ORDER BY day")
    @Select("set @d = #{days};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()));\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(money IS NULL,0,money) money from \n" +
            "(SELECT E.day,CONVERT((IF(E.count IS NULL, 0, E.count) + IF(C.count IS NULL, 0, C.count)*100 - IF(D.count IS NULL, 0, D.count))/100, DECIMAL(10,2)) as money  FROM\n" +
            "(SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_order WHERE state = 2 AND DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(createTime) AND date(createTime) < CURDATE() GROUP BY day ORDER BY 1) E \n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(orderTime, '%Y-%m-%d') AS day FROM common_auth_order WHERE state = 1 AND DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(orderTime) AND date(orderTime) < CURDATE() GROUP BY day ORDER BY 1) C ON C.day=E.day\n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_cash WHERE state = 1 AND DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(createTime) AND date(createTime) < CURDATE() GROUP BY day ORDER BY 1) D ON D.day=E.day\n" +
            "ORDER BY day) A \n" +
            "RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Double>> getIncomeByDays(@Param(value = "days") Integer days);

    //@Select("SELECT DATE_FORMAT(registerTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM common_user WHERE date(registerTime) BETWEEN #{start} AND #{end} GROUP BY day ORDER BY 1")
    @Select("set @d1 = #{start};\n" +
            "set @d2 = #{end};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(@d1,@d2)+1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from \n" +
            "(SELECT DATE_FORMAT(registerTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM common_user WHERE date(registerTime) BETWEEN @d1 AND @d2 GROUP BY day ORDER BY 1) A \n" +
            "RIGHT JOIN (select date_add(@d1,interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Integer>> getNewByInterval(@Param(value = "start") String start, @Param(value = "end") String end);

    //@Select("SELECT DATE_FORMAT(li.crateTime,'%Y-%m-%d') AS day, IFNULL(COUNT(DISTINCT li.userId),0) AS count FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE date(li.crateTime) BETWEEN #{start} AND #{end} GROUP BY day ORDER BY 1")
    @Select("set @d1 = #{start};\n" +
            "set @d2 = #{end};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(@d1,@d2)+1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from \n" +
            "(SELECT DATE_FORMAT(li.crateTime,'%Y-%m-%d') AS day, IFNULL(COUNT(DISTINCT li.userId),0) AS count FROM login_info li LEFT JOIN common_user cu on cu.id = li.userId WHERE date(li.crateTime) BETWEEN @d1 AND @d2 GROUP BY day ORDER BY 1) A \n" +
            "RIGHT JOIN (select date_add(@d1,interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Integer>> getActiveByInterval(@Param(value = "start") String start, @Param(value = "end") String end);

    //@Select("SELECT DATE_FORMAT(crateTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM login_info WHERE date(crateTime) BETWEEN #{start} AND #{end} GROUP BY day ORDER BY 1")
    @Select("set @d1 = #{start};\n" +
            "set @d2 = #{end};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(@d1,@d2)+1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from \n" +
            "(SELECT DATE_FORMAT(crateTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count FROM login_info WHERE date(crateTime) BETWEEN @d1 AND @d2 GROUP BY day ORDER BY 1) A \n" +
            "RIGHT JOIN (select date_add(@d1,interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Integer>> getLoginByInterval(@Param(value = "start") String start, @Param(value = "end") String end);

    //    @Select("SELECT A.day,CONVERT((IF(A.count IS NULL, 0, A.count) + IF(C.count IS NULL, 0, C.count) - IF(D.count IS NULL, 0, D.count))/100, DECIMAL(10,2)) as money  FROM\n" +
//            "(SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_order WHERE state = 2 AND date(createTime) BETWEEN #{start} AND #{end} GROUP BY day ORDER BY 1) A \n" +
//            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(orderTime, '%Y-%m-%d') AS day FROM common_auth_order WHERE state = 1 AND date(orderTime) BETWEEN #{start} AND #{end} GROUP BY day ORDER BY 1) C ON C.day=A.day\n" +
//            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_cash WHERE state = 1 AND date(createTime) BETWEEN #{start} AND #{end} GROUP BY day ORDER BY 1) D ON D.day=A.day\n" +
//            "ORDER BY day")
    @Select("set @d1 = #{start};\n" +
            "set @d2 = #{end};\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(@d1,@d2)+1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(money IS NULL,0,money) money from \n" +
            "(SELECT E.day,CONVERT((IF(E.count IS NULL, 0, E.count) + IF(C.count IS NULL, 0, C.count)*100 - IF(D.count IS NULL, 0, D.count))/100, DECIMAL(10,2)) as money  FROM\n" +
            "(SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_order WHERE state = 2 AND date(createTime) BETWEEN @d1 AND @d2 GROUP BY day ORDER BY 1) E \n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(orderTime, '%Y-%m-%d') AS day FROM common_auth_order WHERE state = 1 AND date(orderTime) BETWEEN @d1 AND @d2 GROUP BY day ORDER BY 1) C ON C.day=E.day\n" +
            "LEFT JOIN (SELECT IFNULL(SUM(money),0) AS count, DATE_FORMAT(createTime, '%Y-%m-%d') AS day FROM common_cash WHERE state = 1 AND date(createTime) BETWEEN @d1 AND @d2 GROUP BY day ORDER BY 1) D ON D.day=E.day\n" +
            "ORDER BY day) A \n" +
            "RIGHT JOIN (select date_add(@d1,interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<Map<String, Double>> getIncomeByInterval(@Param(value = "start") String start, @Param(value = "end") String end);
}
