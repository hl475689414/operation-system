package com.wmq.sys.controller;

import com.wmq.sys.service.HomeService;
import com.wmq.sys.utils.DateUtils;
import com.wmq.sys.utils.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 程江涛 on 2018/5/16 0016
 */
@RestController
@RequestMapping("/home")
public class HomeController  extends BaseController {
    @Autowired
    private HomeService homeService;

    /**
     * 获取今日数据总览
     * @return
     */
    @PostMapping(value = "/getTodayData")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getTodayData() {
        try{
            return homeService.getTodayData();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取今日新增用户数据-每小时
     * @return
     */
    @PostMapping(value = "/getTodayNewByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getTodayNewByHours() {
        try{
            return homeService.getTodayNewByHours();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取今日活跃用户数据-每小时
     * @return
     */
    @PostMapping(value = "/getTodayActiveByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getTodayActiveByHours() {
        try{
            return homeService.getTodayActiveByHours();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取今日启动次数数据-每小时
     * @return
     */
    @PostMapping(value = "/getTodayLoginByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getTodayLoginByHours() {
        try{
            return homeService.getTodayLoginByHours();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取今日营业收入数据-每小时
     * @return
     */
    @PostMapping(value = "/getTodayIncomeByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getTodayIncomeByHours() {
        try{
            return homeService.getTodayIncomeByHours();
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取所选日期新增用户数据-每小时
     * @return
     */
    @PostMapping(value = "/getDateNewByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getDateNewByHours() {
        try{
            String date = getParams().getString("date");
            if(!DateUtils.isValidDate(date)) {
                return new JsonResult(1, "日期不合法");
            }
            return homeService.getDateNewByHours(date);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取所选日期活跃用户数据-每小时
     * @return
     */
    @PostMapping(value = "/getDateActiveByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getDateActiveByHours() {
        try{
            String date = getParams().getString("date");
            if(!DateUtils.isValidDate(date)) {
                return new JsonResult(1, "日期不合法");
            }
            return homeService.getDateActiveByHours(date);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取所选日期启动次数数据-每小时
     * @return
     */
    @PostMapping(value = "/getDateLoginByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getDateLoginByHours() {
        try{
            String date = getParams().getString("date");
            if(!DateUtils.isValidDate(date)) {
                return new JsonResult(1, "日期不合法");
            }
            return homeService.getDateLoginByHours(date);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取所选日期营业收入数据-每小时
     * @return
     */
    @PostMapping(value = "/getDateIncomeByHours")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getDateIncomeByHours() {
        try{
            String date = getParams().getString("date");
            if(!DateUtils.isValidDate(date)) {
                return new JsonResult(1, "日期不合法");
            }
            return homeService.getDateIncomeByHours(date);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取最近 n 天新增用户数据-按天获取
     * @return
     */
    @PostMapping(value = "/getNewByDays")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getNewByDays() {
        try{
            Integer days = getParams().getInt("days");
            return homeService.getNewByDays(days);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取最近 n 天活跃用户数据-按天获取
     * @return
     */
    @PostMapping(value = "/getActiveByDays")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getActiveByDays() {
        try{
            Integer days = getParams().getInt("days");
            return homeService.getActiveByDays(days);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取最近 n 天启动次数数据-按天获取
     * @return
     */
    @PostMapping(value = "/getLoginByDays")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getLoginByDays() {
        try{
            Integer days = getParams().getInt("days");
            return homeService.getLoginByDays(days);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取最近 n 天营业收入数据-按天获取
     * @return
     */
    @PostMapping(value = "/getIncomeByDays")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getIncomeByDays() {
        try{
            Integer days = getParams().getInt("days");
            return homeService.getIncomeByDays(days);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取时间区间内新增用户-按天获取
     * @return
     */
    @PostMapping(value = "/getNewByInterval")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getNewByInterval() {
        try{
            String start = getParams().getString("start");
            String end = getParams().getString("end");
            if(!DateUtils.isValidDate(start) || !DateUtils.isValidDate(end)) {
                return new JsonResult(1, "日期不合法");
            }
            if(DateUtils.daysBetween(start, end) >= 60) {
                return new JsonResult(1, "日期区间不能大于60天");
            }
            return homeService.getNewByInterval(start, end);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取时间区间内活跃用户-按天获取
     * @return
     */
    @PostMapping(value = "/getActiveByInterval")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getActiveByInterval() {
        try{
            String start = getParams().getString("start");
            String end = getParams().getString("end");
            if(!DateUtils.isValidDate(start) || !DateUtils.isValidDate(end)) {
                return new JsonResult(1, "日期不合法");
            }
            if(DateUtils.daysBetween(start, end) >= 60) {
                return new JsonResult(1, "日期区间不能大于60天");
            }
            return homeService.getActiveByInterval(start, end);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取时间区间内启动次数-按天获取
     * @return
     */
    @PostMapping(value = "/getLoginByInterval")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getLoginByInterval() {
        try{
            String start = getParams().getString("start");
            String end = getParams().getString("end");
            if(!DateUtils.isValidDate(start) || !DateUtils.isValidDate(end)) {
                return new JsonResult(1, "日期不合法");
            }
            if(DateUtils.daysBetween(start, end) >= 60) {
                return new JsonResult(1, "日期区间不能大于60天");
            }
            return homeService.getLoginByInterval(start, end);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }

    /**
     * 获取时间区间内营业收入-按天获取
     * @return
     */
    @PostMapping(value = "/getIncomeByInterval")
    @RequiresPermissions("sys:home:seeData")
    public JsonResult getIncomeByInterval() {
        try{
            String start = getParams().getString("start");
            String end = getParams().getString("end");
            if(!DateUtils.isValidDate(start) || !DateUtils.isValidDate(end)) {
                return new JsonResult(1, "日期不合法");
            }
            if(DateUtils.daysBetween(start, end) >= 60) {
                return new JsonResult(1, "日期区间不能大于60天");
            }
            return homeService.getIncomeByInterval(start, end);
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1, "获取数据失败");
        }
    }
}