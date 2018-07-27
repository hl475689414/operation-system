package com.wmq.sys.controller;

import com.wmq.sys.service.HomeService;
import com.wmq.sys.utils.DateUtils;
import com.wmq.sys.utils.JsonResult;
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