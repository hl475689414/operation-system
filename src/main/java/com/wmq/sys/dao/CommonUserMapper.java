package com.wmq.sys.dao;

import com.wmq.sys.entity.CommonUser;
import com.wmq.sys.vo.CommonUserListVo;
import com.wmq.sys.vo.PersonalUserInfoVo;
import com.wmq.sys.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonUser record);

    int insertSelective(CommonUser record);

    CommonUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonUser record);

    int updateByPrimaryKey(CommonUser record);

    List<CommonUserListVo> getUserListPage(@Param(value = "keys") String keys, @Param(value = "sex") Integer sex, @Param(value = "state") Integer state, @Param(value = "isCompany") Integer isCompany);

    UserInfoVo getUserInfo(Integer userId);

    int getUserTotal();

    int getCompanyTotal();

    int getPersonalTotal();

    int getToadyAddCompanyTotal();

    int getTodayAddUserTotal();

    int getUserToatlDays(String time);

    List<Map> getToadyUserTotalList();

    List<Map> getYesterdayUserTotalList();

    List<Map> getDaysUserTotalList(Integer days);

    List<Map> getCustomizeUserTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    List<Map> getToadyNewRegisteredCompanyTotalList();

    List<Map> getYesterdayNewRegisteredCompanyTotalList();

    List<Map> getDaysNewRegisteredCompanyTotalList(Integer days);

    List<Map> getCustomizeNewRegisteredCompanyTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    List<Map> getToadyNewRegisteredPersonalTotalList();

    List<Map> getYesterdayNewRegisteredPersonalTotalList();

    List<Map> getDaysNewRegisteredPersonalTotalList(Integer days);

    List<Map> getCustomizeNewRegisteredPersonalTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    int getCompanyUserToatlDays(String time);

    List<Map> getDaysCompanyUserTotalList(Integer days);

    int getPersonalUserToatlDays(String time);

    List<Map> getDaysPersonalUserTotalList(Integer days);
}