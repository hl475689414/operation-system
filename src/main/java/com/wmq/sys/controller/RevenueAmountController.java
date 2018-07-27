package com.wmq.sys.controller;

import com.wmq.sys.service.RevenueAmountService;
import com.wmq.sys.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 李怀鹏 on 2018/5/24.
 */
@RestController
@RequestMapping("/revenue")
public class RevenueAmountController extends BaseController {
    @Autowired
    private RevenueAmountService revenueAmountService;

    /**
     * 获取营收分析总览
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getRevenueAmountOverview")
    public JsonResult getRevenueAmountOverview(HttpServletRequest request, HttpServletResponse response) {
        return revenueAmountService.getRevenueAmountOverview();
    }

    /**
     * 营收总金额数据列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getRevenueAmountDataList")
    public JsonResult getRevenueAmountDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return revenueAmountService.getRevenueAmountDataList(days, order, startDate, endDate);
    }

    /**
     * 订阅课程收入数据列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getCourseAmountDataList")
    public JsonResult getCourseAmountDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return revenueAmountService.getCourseAmountDataList(days, order, startDate, endDate);
    }

    /**
     * 新增企业VIP收入数据列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getVipAmountDataList")
    public JsonResult getVipAmountDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return revenueAmountService.getVipAmountDataList(days, order, startDate, endDate);
    }

    /**
     * 升级企业VIP收入数据列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUpgradeVipAmountDataList")
    public JsonResult getUpgradeVipAmountDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return revenueAmountService.getUpgradeVipAmountDataList(days, order, startDate, endDate);
    }

    /**
     * 营收分析数据看板
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getRevenueDataList")
    public JsonResult getRevenueDataList(HttpServletRequest request, HttpServletResponse response) {
        return revenueAmountService.getRevenueDataList();
    }
}
