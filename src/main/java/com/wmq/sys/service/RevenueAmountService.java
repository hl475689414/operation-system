package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 李怀鹏 on 2018/5/24.
 */
public interface RevenueAmountService {
    /**
     * 获取营收分析总览
     * @return
     */
    JsonResult getRevenueAmountOverview();

    /**
     * 营收总金额数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getRevenueAmountDataList(int days, int order, String startDate, String endDate);

    /**
     * 订阅课程收入数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getCourseAmountDataList(int days, int order, String startDate, String endDate);

    /**
     * 新增企业VIP收入数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getVipAmountDataList(int days, int order, String startDate, String endDate);

    /**
     * 升级企业VIP收入数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    JsonResult getUpgradeVipAmountDataList(int days, int order, String startDate, String endDate);

    /**
     * 营收分析数据看板
     * @return
     */
    JsonResult getRevenueDataList();
}
