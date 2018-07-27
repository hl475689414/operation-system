package com.wmq.sys.dao;

import com.wmq.sys.vo.HomeCountVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * Created by 程江涛 on 2018/5/22 0022
 */
@Mapper
public interface ActiveUserMapper {
    @Select("set @d = 7;\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()) + 1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from \n" +
            "(SELECT DATE_FORMAT(li.crateTime,'%Y-%m-%d') AS day, IFNULL(COUNT(DISTINCT li.userId),0) AS count \n" +
            "FROM login_info li \n" +
            "LEFT JOIN common_user cu on cu.id = li.userId WHERE DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= date(li.crateTime) GROUP BY day ORDER BY 1) A\n" +
            "RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day \n" +
            "GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<HomeCountVO> getDayActiveByDays();

    @Select("set @d = 14;\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()) + 1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from \n" +
            "(SELECT DATE_FORMAT(loginTime,'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count from common_user \n" +
            "WHERE runDay > 2 AND DATE_SUB(CURDATE(), INTERVAL @d DAY) <= date(loginTime) GROUP BY day ORDER BY 1 desc) A\n" +
            "RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day \n" +
            "GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<HomeCountVO> getWeekActiveByDays();

    @Select("set @d = 7;\n" +
            "set @i = -1;\n" +
            "set @sql = repeat(\" select 1 union all\",-datediff(SUBDATE(CURDATE(),INTERVAL @d DAY),CURDATE()) + 1);\n" +
            "set @sql = left(@sql,length(@sql)-length(\" union all\"));\n" +
            "set @sql = concat(\"select B.day, IF(count IS NULL,0,count) count from \n" +
            "(SELECT DATE_FORMAT(ADDDATE(loginTime,30),'%Y-%m-%d') AS day, IFNULL(count(*),0) AS count\n" +
            "FROM common_user WHERE DATE(loginTime) BETWEEN DATE_SUB(CURDATE(), INTERVAL 37 DAY) AND DATE_SUB(CURDATE(), INTERVAL 30 DAY)\n" +
            "GROUP BY day ORDER BY 1) A\n" +
            "RIGHT JOIN (select date_add(SUBDATE(CURDATE(),INTERVAL @d DAY),interval @i:=@i+1 day) as day from (\",@sql,\") as tmp ) B ON B.day = A.day \n" +
            "GROUP BY day ORDER BY 1\");\n" +
            "prepare stmt from @sql;\n" +
            "execute stmt;")
    List<HomeCountVO> getMonthLostByDays();

    @Select("SELECT count(*) from common_user")
    int getTotalUser();
}
