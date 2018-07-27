package com.wmq.sys.dao;

import com.wmq.sys.entity.CommonAuthOrder;
import com.wmq.sys.vo.CommonAuthOrderInfoVo;
import com.wmq.sys.vo.CommonAuthOrderListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonAuthOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommonAuthOrder record);

    int insertSelective(CommonAuthOrder record);

    CommonAuthOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonAuthOrder record);

    int updateByPrimaryKey(CommonAuthOrder record);

    int getVipAmount();

    int getUpgradeVipAmount();

    int getVipToatlAmountDays(String time);

    int getUpgradeVipToatlAmountDays(String time);

    List<Map> getToadyVipIncomeTotalList();

    List<Map> getYesterdayVipIncomeTotalList();

    List<Map> getDaysVipIncomeTotalList(Integer days);

    List<Map> getCustomizeVipIncomeTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    List<Map> getToadyUpgradeVipIncomeTotalList();

    List<Map> getYesterdayUpgradeVipIncomeTotalList();

    List<Map> getDaysUpgradeVipIncomeTotalList(Integer days);

    List<Map> getCustomizeUpgradeVipIncomeTotalList(@Param(value = "startDate") String startDate, @Param(value = "endDate") String endDate);

    List<Map> getSilverVipDaysDataList();

    List<Map> getVipDaysDataList(Integer authType);

    List<Map> getUpgradeVipDaysDataList(Integer authType);

    List<CommonAuthOrderListVo> getAuthOrderListPage(@Param(value = "key")String key, @Param(value = "payType")String payType, @Param("state")Integer state);

    CommonAuthOrderInfoVo getAuthOrderInfo(String id);
 }