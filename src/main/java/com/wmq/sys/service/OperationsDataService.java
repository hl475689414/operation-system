package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/5/21.
 */
public interface OperationsDataService {
    /**
     * 获取用户趋势总览
     * @return
     */
    JsonResult getUserTrendOverview();

    /**
     * 获取用户总量数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getUserTotalDataList(int days, int order, String  startDate, String endDate);

    /**
     * 获取新入驻企业数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getNewRegisteredCompanyDataList(int days, int order, String  startDate, String endDate);

    /**
     * 获取新注册个人用户数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getNewPersonalDataList(int days, int order, String  startDate, String endDate);

    /**
     * 获取启动次数数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getStartupDataList(int days, int order, String  startDate, String endDate);

    /**
     * 获取用户趋势数据看板
     * @return
     */
    JsonResult getDataList();
}
