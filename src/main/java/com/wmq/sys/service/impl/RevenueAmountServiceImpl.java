package com.wmq.sys.service.impl;

import com.wmq.sys.dao.CommonAuthOrderMapper;
import com.wmq.sys.dao.CommonOrderMapper;
import com.wmq.sys.service.RevenueAmountService;
import com.wmq.sys.utils.ChartDateUtils;
import com.wmq.sys.utils.DateUtil;
import com.wmq.sys.utils.DateUtils;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.RevenueAmountOverviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by 李怀鹏 on 2018/5/24.
 */
@Service("/revenueAmountService")
public class RevenueAmountServiceImpl extends BaseService implements RevenueAmountService {
    @Autowired
    private CommonOrderMapper commonOrderMapper;
    @Autowired
    private CommonAuthOrderMapper commonAuthOrderMapper;
    @Autowired
    private ChartDateUtils chartDateUtils;

    /**
     * 获取营收分析总览
     * @return
     */
    @Override
    public JsonResult getRevenueAmountOverview() {
        RevenueAmountOverviewVo revenueAmountOverviewVo = new RevenueAmountOverviewVo();
        int courseAmount = commonOrderMapper.getCourseAmount(); //订阅课程收入
        int vipAmount = commonAuthOrderMapper.getVipAmount() * 100; //购买VIP收入
        int upgradeVipAmount = commonAuthOrderMapper.getUpgradeVipAmount() * 100; //升级VIP收入
        int totalAmount = courseAmount + vipAmount + upgradeVipAmount; //总收入
        revenueAmountOverviewVo.setTotalAmount(toYuan(totalAmount));
        revenueAmountOverviewVo.setCourseAmount(toYuan(courseAmount));
        revenueAmountOverviewVo.setCourseAmountProportion(getProportion(toYuan(courseAmount),toYuan(totalAmount)));
        revenueAmountOverviewVo.setVipAmount(toYuan(vipAmount));
        revenueAmountOverviewVo.setVipAmountProportion(getProportion(toYuan(vipAmount),toYuan(totalAmount)));
        revenueAmountOverviewVo.setUpgradeVipAmount(toYuan(upgradeVipAmount));
        revenueAmountOverviewVo.setUpgradeVipProportion(getProportion(toYuan(upgradeVipAmount),toYuan(totalAmount)));
        return new JsonResult(0, "获取成功", 0, revenueAmountOverviewVo);
    }

    /**
     * 计算百分比
     * @param amount
     * @param totalAmount
     * @return
     */
    private String getProportion(double amount, double totalAmount) {
        if(totalAmount == 0) {
            return "0.00%";
        }
        double d = amount / totalAmount;
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(d);
    }

    /**
     * 分转元
     * @param fen
     * @return
     */
    private double toYuan(int fen) {
        return new BigDecimal(fen / (float) 100).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 营收总金额数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getRevenueAmountDataList(int days, int order, String startDate, String endDate) {
        try {
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            String time = getMinimalTime(order, days, startDate); //获取最早那个日期
            double courseTotalAmount = toYuan(commonOrderMapper.getCourseToatlAmountDays(time)); //获取小于指定日期为止的课程收入
            double vipTotalAmount = toYuan(commonAuthOrderMapper.getVipToatlAmountDays(time) * 100); //获取小于指定日期为止的购买VIP收入
            double upgradeVipTotalAmount = toYuan(commonAuthOrderMapper.getUpgradeVipToatlAmountDays(time) * 100); //获取小于指定日期为止的升级VIP收入

            if(order == 1 || order == 2) { //小时
                if(order == 1) {
                    listData = commonOrderMapper.getTodayOrderIncomeTotalList(); //获取今天课程收入、VIP购买、VIP升级数据列表
                    //累加金额总数
                    accumulateTotalIncomeList(courseTotalAmount+vipTotalAmount+upgradeVipTotalAmount, listData);
                }else {
                    listData = commonOrderMapper.getYesterdayOrderIncomeTotalList(); //获取昨天课程收入、VIP购买、VIP升级数据列表
                    //累加金额总数
                    accumulateTotalIncomeList(courseTotalAmount+vipTotalAmount+upgradeVipTotalAmount, listData);
                }
            }else if(order == 3 || order == 4 || order == 5) { //天
                listData = commonOrderMapper.getDaysOrderIncomeTotalList(days); //获取最近N天课程收入、VIP购买、VIP升级数据列表
                //累加金额总数
                accumulateTotalIncomeList(courseTotalAmount+vipTotalAmount+upgradeVipTotalAmount, listData);
            }else { //自定义
                listData = commonOrderMapper.getCustomizeOrderIncomeTotalList(startDate, endDate);
                //累加金额总数
                accumulateTotalIncomeList(courseTotalAmount+vipTotalAmount+upgradeVipTotalAmount, listData);
            }
            return new JsonResult(0, "获取成功", 0, listData);
        }catch (Exception e){
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }

    /**
     * 获取最小日期
     * @param order
     * @param days
     * @param startDate
     * @return
     */
    public String getMinimalTime(int order, int days, String startDate) {
        String time = "";
        switch (order){
            case 1 : time = DateUtils.getDate();
                break;
            case 2 : time = DateUtils.formatDate(DateUtil.subtract(new Date(),1), "yyyy-MM-dd");
                break;
            case 3 : case 4 : case 5 : time = DateUtils.formatDate(DateUtil.subtract(new Date(), days), "yyyy-MM-dd");
                break;
            case 6 : time = startDate;
                break;
        }
        return time;
    }

    /**
     * 累加金额总数
     * @param total
     * @param list
     * @return
     */
    public List<Map> accumulateTotalIncomeList(double total, List<Map> list) {
        DecimalFormat df = new DecimalFormat("#.00");
        list.get(0).put("money", df.format(total +  Double.valueOf(list.get(0).get("money").toString())));
        for(int i = 1; i < list.size(); i++) {
            list.get(i).put("money", df.format(Double.valueOf(list.get(i-1).get("money").toString()) + Double.valueOf(list.get(i).get("money").toString())));
        }
        return list;
    }

    /**
     * 订阅课程收入数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getCourseAmountDataList(int days, int order, String startDate, String endDate) {
        try {
            List<Map> list = new ArrayList<>(); //接收小时或天列表
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            if(order == 1 || order == 2) { //小时
                list = chartDateUtils.hourslist(); //获取小时数列表
                if(order == 1) {
                    listData = commonOrderMapper.getToadyCourseIncomeTotalList(); //获取今天订阅课程总量数据
                }else {
                    listData = commonOrderMapper.getYesterdayCourseIncomeTotalList(); //获取昨天订阅课程总量数据
                }
                //补全小时数据
                completionHoursList(list, listData);
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = chartDateUtils.getDaysLlist(days); //获取N天时间列表
                listData = commonOrderMapper.getDaysCourseIncomeTotalList(days); //获取最近N天订阅课程总量数据
                //补全天数据
                completionDaysList(list, listData);
            }else { //自定义日期
                list = chartDateUtils.customDays(startDate, endDate);
                listData = commonOrderMapper.getCustomizeCourseIncomeTotalList(startDate + " 00:00:01", endDate + " 23:59:59");
                //补全天数据
                completionDaysList(list, listData);
            }
            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e) {
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }

    /**
     * 补全小时数据
     * @param listHours
     * @param listData
     * @return
     */
    public List<Map> completionHoursList(List<Map> listHours, List<Map> listData) {
        for(Map maphours : listHours) {
            for(Map map : listData) {
                if(maphours.get("hours").toString().equals(map.get("hours").toString())) {
                    maphours.put("money", map.get("money").toString());
                }
            }
        }
        return listHours;
    }

    /**
     * 补全天数据
     * @param listDays
     * @param listData
     * @return
     */
    public List<Map> completionDaysList(List<Map> listDays, List<Map> listData) {
        for(Map mapdays : listDays) {
            for(Map map : listData) {
                if(mapdays.get("day").toString().equals(map.get("days").toString())) {
                    mapdays.put("money", map.get("money").toString());
                }
            }
        }
        return listDays;
    }

    /**
     * 新增企业VIP收入数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getVipAmountDataList(int days, int order, String startDate, String endDate) {
        try {
            List<Map> list = new ArrayList<>(); //接收小时或天列表
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            if(order == 1 || order == 2) { //小时
                list = chartDateUtils.hourslist(); //获取小时数列表
                if(order == 1) {
                    listData = commonAuthOrderMapper.getToadyVipIncomeTotalList(); //获取今天购买VIP总量数据
                }else {
                    listData = commonAuthOrderMapper.getYesterdayVipIncomeTotalList(); //获取昨天购买VIP总量数据
                }
                //补全小时数据
                completionHoursList(list, listData);
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = chartDateUtils.getDaysLlist(days); //获取N天时间列表
                listData = commonAuthOrderMapper.getDaysVipIncomeTotalList(days); //获取最近N天购买VIP总量数据
                //补全天数据
                completionDaysList(list, listData);
            }else { //自定义日期
                list = chartDateUtils.customDays(startDate, endDate);
                listData = commonAuthOrderMapper.getCustomizeVipIncomeTotalList(startDate + " 00:00:01", endDate + " 23:59:59");
                //补全天数据
                completionDaysList(list, listData);
            }
            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e) {
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }

    /**
     * 升级企业VIP收入数据列表
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getUpgradeVipAmountDataList(int days, int order, String startDate, String endDate) {
        try {
            List<Map> list = new ArrayList<>(); //接收小时或天列表
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            if(order == 1 || order == 2) { //小时
                list = chartDateUtils.hourslist(); //获取小时数列表
                if(order == 1) {
                    listData = commonAuthOrderMapper.getToadyUpgradeVipIncomeTotalList(); //获取今天购买VIP总量数据
                }else {
                    listData = commonAuthOrderMapper.getYesterdayUpgradeVipIncomeTotalList(); //获取昨天购买VIP总量数据
                }
                //补全小时数据
                completionHoursList(list, listData);
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = chartDateUtils.getDaysLlist(days); //获取N天时间列表
                listData = commonAuthOrderMapper.getDaysUpgradeVipIncomeTotalList(days); //获取最近N天购买VIP总量数据
                //补全天数据
                completionDaysList(list, listData);
            }else { //自定义日期
                list = chartDateUtils.customDays(startDate, endDate);
                listData = commonAuthOrderMapper.getCustomizeUpgradeVipIncomeTotalList(startDate + " 00:00:01", endDate + " 23:59:59");
                //补全天数据
                completionDaysList(list, listData);
            }
            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e) {
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }

    /**
     * 营收分析数据看板
     * @return
     */
    @Override
    public JsonResult getRevenueDataList() {
        try {
            List<Map> list = new ArrayList<>();

            //银卡会员
            Map silverVipMap = new HashMap();
            List<Map> silverList = commonAuthOrderMapper.getSilverVipDaysDataList();
            List<Map> silverViplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            completionDaysKanBanList(silverViplistDays, silverList); //补全数据
            silverVipMap.put("silverVipList", silverViplistDays);
            list.add(silverVipMap);

            //金卡会员
            Map goldVipMap = new HashMap();
            Map vipMap = new HashMap();
            List<Map> vipList = commonAuthOrderMapper.getVipDaysDataList(2);
            List<Map> upgradeVipList = commonAuthOrderMapper.getUpgradeVipDaysDataList(2);
            List<Map> viplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            List<Map> upgradeViplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            completionDaysKanBanList(viplistDays, vipList); //补全数据
            vipMap.put("goldVipList", viplistDays);
            completionDaysKanBanList(upgradeViplistDays, upgradeVipList); //补全数据
            vipMap.put("goldUpgradeVipList", upgradeViplistDays);
            goldVipMap.put("goldVipDataList", vipMap);
            list.add(goldVipMap);

            //铂金会员
            Map platinumVipMap = new HashMap();
            Map vipMap2 = new HashMap();
            List<Map> platinumVipList = commonAuthOrderMapper.getVipDaysDataList(3);
            List<Map> platinumUpgradeVipLis = commonAuthOrderMapper.getUpgradeVipDaysDataList(3);
            List<Map> platinumViplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            List<Map> platinumUpgradeViplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            completionDaysKanBanList(platinumViplistDays, platinumVipList); //补全数据
            vipMap2.put("goldVipList", platinumViplistDays);
            completionDaysKanBanList(platinumUpgradeViplistDays, platinumUpgradeVipLis); //补全数据
            vipMap2.put("goldUpgradeVipList", platinumUpgradeViplistDays);
            platinumVipMap.put("platinumVipDataList", vipMap2);
            list.add(platinumVipMap);

            //钻石会员
            Map diamondVipMap = new HashMap();
            Map vipMap3 = new HashMap();
            List<Map> diamondVipList = commonAuthOrderMapper.getVipDaysDataList(4);
            List<Map> diamondUpgradeVipList = commonAuthOrderMapper.getUpgradeVipDaysDataList(4);
            List<Map> diamondViplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            List<Map> diamondUpgradeViplistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            completionDaysKanBanList(diamondViplistDays, diamondVipList); //补全数据
            vipMap3.put("goldVipList", diamondViplistDays);
            completionDaysKanBanList(diamondUpgradeViplistDays, diamondUpgradeVipList); //补全数据
            vipMap3.put("goldUpgradeVipList", diamondUpgradeViplistDays);
            diamondVipMap.put("diamondVipDataList", vipMap3);
            list.add(diamondVipMap);

            //课程订阅
            Map courseMap = new HashMap();
            List<Map> courseList = commonOrderMapper.getCourseDaysDataList();
            List<Map> courselistDays = chartDateUtils.getDaysKanBanLlist(7); //获取N天时间列表
            completionDaysKanBanList(courselistDays, courseList); //补全数据
            courseMap.put("courseList", courselistDays);
            list.add(courseMap);

            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e){
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }

    /**
     * 补全天数据
     * @param listDays
     * @param listData
     * @return
     */
    public List<Map> completionDaysKanBanList(List<Map> listDays, List<Map> listData) {
        for(Map mapdays : listDays) {
            for(Map map : listData) {
                if(mapdays.get("day").toString().equals(map.get("days").toString())) {
                    mapdays.put("count", map.get("count").toString());
                    mapdays.put("money", map.get("money").toString());
                }
            }
        }
        return listDays;
    }
}