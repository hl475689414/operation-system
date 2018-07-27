package com.wmq.sys.service.impl;

import com.wmq.sys.dao.CommonUserMapper;
import com.wmq.sys.dao.HomeMapper;
import com.wmq.sys.dao.LoginInfoMapper;
import com.wmq.sys.service.OperationsDataService;
import com.wmq.sys.utils.*;
import com.wmq.sys.vo.UserTrendOverviewVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 李怀鹏 on 2018/5/21.
 */
@Service("operationsDataService")
public class OperationsDataServiceImpl extends BaseService implements OperationsDataService {
    @Autowired
    private CommonUserMapper commonUserMapper;
    @Autowired
    private LoginInfoMapper loginInfoMapper;
    @Autowired
    private HomeMapper homeMapper;
    @Autowired
    private ChartDateUtils chartDateUtils;

    /**
     * 获取用户趋势总览
     * @return
     */
    @Override
    public JsonResult getUserTrendOverview() {
        UserTrendOverviewVo userTrendOverviewVo = new UserTrendOverviewVo();
        userTrendOverviewVo.setUserTotal(commonUserMapper.getUserTotal());
        userTrendOverviewVo.setCompanyTotal(commonUserMapper.getCompanyTotal());
        userTrendOverviewVo.setPersonalTotal(commonUserMapper.getPersonalTotal());
        userTrendOverviewVo.setTodayAddCompanyTotal(commonUserMapper.getToadyAddCompanyTotal());
        userTrendOverviewVo.setTodayAddUserTotal(commonUserMapper.getTodayAddUserTotal());
        userTrendOverviewVo.setTodayStartTotal(loginInfoMapper.getTodayStartTotal());
        return new JsonResult(0, "获取成功", 0, userTrendOverviewVo);
    }

    /**
     * 获取用户总量数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getUserTotalDataList(int days, int order, String  startDate, String endDate) {
        try {
            List<Map> list = new ArrayList<>(); //接收小时或天列表
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            String time = getMinimalTime(order, days, startDate); //获取最早那个日期
            int total = commonUserMapper.getUserToatlDays(time); //获取小于指定日期为止的用户总数

            if(order == 1 || order == 2) { //小时
                list = chartDateUtils.hourslist(); //获取小时数列表
                if(order == 1) {
                    listData = commonUserMapper.getToadyUserTotalList(); //获取今天用户总量数据
                    //补全小时数据
                    completionHoursList(list, listData);
                    //累加用户总数
                    accumulateTotalUserList(total, list);
                }else {
                    listData = commonUserMapper.getYesterdayUserTotalList(); //获取昨天用户总量数据
                    //补全小时数据
                    completionHoursList(list, listData);
                    //累加用户总数
                    accumulateTotalUserList(total, list);
                }
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = chartDateUtils.getDaysLlist(days); //获取N天时间列表
                listData = commonUserMapper.getDaysUserTotalList(days); //获取最近N天用户总量数据
                //补全天数据
                completionDaysList(list, listData);
                //累加用户总数
                accumulateTotalUserList(total, list);
            }else { //自定义日期
                list = chartDateUtils.customDays(startDate, endDate);
                listData = commonUserMapper.getCustomizeUserTotalList(startDate + " 00:00:01", endDate + " 23:59:59");
                //补全天数据
                completionDaysList(list, listData);
                //累加用户总数
                accumulateTotalUserList(total, list);
            }
            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e) {
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
     * 累加用户总数
     * @param total
     * @param list
     * @return
     */
    public List<Map> accumulateTotalUserList(int total, List<Map> list) {
        list.get(0).put("count", total + Integer.parseInt(list.get(0).get("count").toString()));
        for(int i = 1; i < list.size(); i++) {
            list.get(i).put("count", Integer.parseInt(list.get(i-1).get("count").toString()) + Integer.parseInt(list.get(i).get("count").toString()));
        }
        return list;
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
                    maphours.put("count", map.get("count").toString());
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
                    mapdays.put("count", map.get("count").toString());
                }
            }
        }
        return listDays;
    }

    /**
     * 获取新入驻企业数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getNewRegisteredCompanyDataList(int days, int order, String  startDate, String endDate) {
        try {
            List<Map> list = new ArrayList<>(); //接收小时或天列表
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            if(order == 1 || order == 2) { //小时
                list = chartDateUtils.hourslist(); //获取小时数列表
                if(order == 1) {
                    listData = commonUserMapper.getToadyNewRegisteredCompanyTotalList(); //获取今天新入驻企业总量数据
                }else {
                    listData = commonUserMapper.getYesterdayNewRegisteredCompanyTotalList(); //获取昨天新入驻企业用户总量数据
                }
                //补全小时数据
                completionHoursList(list, listData);
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = chartDateUtils.getDaysLlist(days); //获取N天时间列表
                listData = commonUserMapper.getDaysNewRegisteredCompanyTotalList(days); //获取最近N天新入驻企业用户总量数据
                //补全天数据
                completionDaysList(list, listData);
            }else { //自定义日期
                list = chartDateUtils.customDays(startDate, endDate);
                listData = commonUserMapper.getCustomizeNewRegisteredCompanyTotalList(startDate + " 00:00:01", endDate + " 23:59:59");
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
     * 获取新注册个人用户数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getNewPersonalDataList(int days, int order, String  startDate, String endDate) {
        try {
            List<Map> list = new ArrayList<>(); //接收小时或天列表
            List<Map> listData = new ArrayList<>(); //接收查询结果数据列表
            if(order == 1 || order == 2) { //小时
                list = chartDateUtils.hourslist(); //获取小时数列表
                if(order == 1) {
                    listData = commonUserMapper.getToadyNewRegisteredPersonalTotalList(); //获取今天新入驻个人用户总量数据
                }else {
                    listData = commonUserMapper.getYesterdayNewRegisteredPersonalTotalList(); //获取昨天新入驻个人用户总量数据
                }
                //补全小时数据
                completionHoursList(list, listData);
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = chartDateUtils.getDaysLlist(days); //获取N天时间列表
                listData = commonUserMapper.getDaysNewRegisteredPersonalTotalList(days); //获取最近N天用户总量数据
                //补全天数据
                completionDaysList(list, listData);
            }else { //自定义日期
                list = chartDateUtils.customDays(startDate, endDate);
                listData = commonUserMapper.getCustomizeNewRegisteredPersonalTotalList(startDate + " 00:00:01", endDate + " 23:59:59");
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
     * 获取启动次数数据列表 -- 天
     * @param days
     * @param order
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public JsonResult getStartupDataList(int days, int order, String  startDate, String endDate) {
        try {
            List<Map<String, Integer>> list = new ArrayList<>();
            if(order == 1 || order == 2) { //小时
                if(order == 1) { //今天
                    list = homeMapper.getTodayLoginByHours();
                }else { //昨天
                    list = homeMapper.getYesterdayLoginByHours();
                }
            }else if(order == 3 || order == 4 || order == 5) { //天
                list = homeMapper.getLoginByDays(days);
            }else { //自定义日期
                list = homeMapper.getLoginByInterval(startDate, endDate);
            }
            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e) {
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }

    /**
     * 获取用户趋势数据看板
     * @return
     */
    @Override
    public JsonResult getDataList() {
        try {
            List<Map> list = new ArrayList<>();

            //入驻企业总数
            int companyTotal = commonUserMapper.getCompanyUserToatlDays(DateUtils.formatDate(DateUtil.subtract(new Date(), 7), "yyyy-MM-dd"));//获取小于指定日期为止的企业用户总数
            List<Map> listCompanyDays = chartDateUtils.getDaysLlist(7); //获取7天时间列表
            List<Map> listCompanyData = commonUserMapper.getDaysCompanyUserTotalList(7); //获取最近7天用户总量数据
            //补全天数据
            completionDaysList(listCompanyDays, listCompanyData);
            //累加用户总数
            accumulateTotalUserList(companyTotal, listCompanyDays);
            Map companyMap = new HashMap();
            companyMap.put("companyTotalDaysData", listCompanyDays);
            list.add(companyMap);

            //个人用户总数
            int personalTotal = commonUserMapper.getPersonalUserToatlDays(DateUtils.formatDate(DateUtil.subtract(new Date(), 7), "yyyy-MM-dd"));//获取小于指定日期为止的个人用户总数
            List<Map> listPersonalDays = chartDateUtils.getDaysLlist(7); //获取7天时间列表
            List<Map> listPersonalData = commonUserMapper.getDaysPersonalUserTotalList(7); //获取最近7天用户总量数据
            //补全天数据
            completionDaysList(listPersonalDays, listPersonalData);
            //累加用户总数
            accumulateTotalUserList(personalTotal, listPersonalDays);
            Map personalMap = new HashMap();
            personalMap.put("personalTotalDaysData", listPersonalDays);
            list.add(personalMap);
            return new JsonResult(0, "获取成功", 0, list);
        }catch (Exception e){
            logger.error(e);
            return new JsonResult(1, "获取失败");
        }
    }
}