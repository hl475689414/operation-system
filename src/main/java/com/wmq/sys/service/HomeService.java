package com.wmq.sys.service;

import com.wmq.sys.utils.JsonResult;

/**
 * Created by 程江涛 on 2018/5/16 0016
 */
public interface HomeService {

    /**
     * 首页-获取今日数据总览
     * @return
     */
    JsonResult getTodayData();

    /**
     * 按小时获取今日新增用户数据
     * @return
     */
    JsonResult getTodayNewByHours();

    /**
     * 按小时获取今日活跃用户数据
     * @return
     */
    JsonResult getTodayActiveByHours();

    /**
     * 按小时获取今日启动次数数据
     * @return
     */
    JsonResult getTodayLoginByHours();

    /**
     * 按小时获取今日营业收入数据
     * @return
     */
    JsonResult getTodayIncomeByHours();

    /**
     * 按小时获取所选日期新增用户数据
     * @return
     */
    JsonResult getDateNewByHours(String date);

    /**
     * 按小时获取所选日期活跃用户数据
     * @return
     */
    JsonResult getDateActiveByHours(String date);

    /**
     * 按小时获取所选日期启动次数数据
     * @return
     */
    JsonResult getDateLoginByHours(String date);

    /**
     * 按小时获取所选日期营业收入数据
     * @return
     */
    JsonResult getDateIncomeByHours(String date);


    /**
     * 获取最近 n 天新增用户-按天
     * @param days
     * @return
     */
    JsonResult getNewByDays(Integer days);

    /**
     * 获取最近 n 天活跃用户-按天
     * @param days
     * @return
     */
    JsonResult getActiveByDays(Integer days);

    /**
     * 获取最近 n 天启动次数-按天
     * @param days
     * @return
     */
    JsonResult getLoginByDays(Integer days);

    /**
     * 获取最近 n 天营业收入-按天
     * @param days
     * @return
     */
    JsonResult getIncomeByDays(Integer days);

    /**
     * 获取时间区间内新增用户-按天
     * @param start
     * @param end
     * @return
     */
    JsonResult getNewByInterval(String start, String end);

    /**
     * 获取时间区间内活跃用户-按天
     * @param start
     * @param end
     * @return
     */
    JsonResult getActiveByInterval(String start, String end);

    /**
     * 获取时间区间内启动次数-按天
     * @param start
     * @param end
     * @return
     */
    JsonResult getLoginByInterval(String start, String end);

    /**
     * 获取时间区间内营业收入-按天
     * @param start
     * @param end
     * @return
     */
    JsonResult getIncomeByInterval(String start, String end);
}