package com.wmq.sys.controller;

import com.wmq.sys.service.OperationsDataService;
import com.wmq.sys.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 李怀鹏 on 2018/5/21.
 */
@RestController
@RequestMapping("/operations")
public class OperationsDataController extends BaseController {
    @Autowired
    private OperationsDataService operationsDataService;

    /**
     * 获取用户趋势总览
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUserTrendOverview")
    public JsonResult getUserTrendOverview(HttpServletRequest request, HttpServletResponse response) {
        return operationsDataService.getUserTrendOverview();
    }

    /**
     * 获取用户总量数据列表 -- 天
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getUserTotalDataList")
    public JsonResult getUserTotalDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return operationsDataService.getUserTotalDataList(days,order, startDate, endDate);
    }

    /**
     * 获取新入驻企业数据列表 -- 天
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getNewRegisteredCompanyDataList")
    public JsonResult getNewRegisteredCompanyDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return operationsDataService.getNewRegisteredCompanyDataList(days,order, startDate, endDate);
    }

    /**
     * 获取新注册个人用户数据列表 -- 天
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getNewPersonalDataList")
    public JsonResult getNewPersonalDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return operationsDataService.getNewPersonalDataList(days,order, startDate, endDate);
    }

    /**
     * 获取启动次数数据列表 -- 天
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getStartupDataList")
    public JsonResult getStartupDataList(HttpServletRequest request, HttpServletResponse response) {
        int days = getParams().getInt("days");
        int order = getParams().getInt("order");
        String startDate = null;
        String endDate = null;
        if(order == 6) {
            startDate = getParams().getString("startDate");
            endDate = getParams().getString("endDate");
        }
        return operationsDataService.getStartupDataList(days,order, startDate, endDate);
    }

    /**
     * 获取用户趋势数据看板
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/getDataList")
    public JsonResult getDataList(HttpServletRequest request, HttpServletResponse response) {
        return operationsDataService.getDataList();
    }
}
