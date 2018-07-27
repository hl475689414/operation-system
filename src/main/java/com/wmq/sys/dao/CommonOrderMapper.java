package com.wmq.sys.dao;

import com.wmq.sys.entity.CommonOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonOrder record);

    int insertSelective(CommonOrder record);

    CommonOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonOrder record);

    int updateByPrimaryKey(CommonOrder record);

    int getCourseAmount();

    int getCourseToatlAmountDays(String time);

    List<Map> getTodayOrderIncomeTotalList();

    List<Map> getYesterdayOrderIncomeTotalList();

    List<Map> getDaysOrderIncomeTotalList(Integer days);

    List<Map> getCustomizeOrderIncomeTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    List<Map> getToadyCourseIncomeTotalList();

    List<Map> getYesterdayCourseIncomeTotalList();

    List<Map> getDaysCourseIncomeTotalList(Integer days);

    List<Map> getCustomizeCourseIncomeTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    List<Map> getCourseDaysDataList();
}