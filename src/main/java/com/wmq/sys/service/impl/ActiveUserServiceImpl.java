package com.wmq.sys.service.impl;

import com.wmq.sys.dao.ActiveUserMapper;
import com.wmq.sys.service.ActiveUserService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.HomeCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 程江涛 on 2018/5/22 0022
 */
@Service
public class ActiveUserServiceImpl implements ActiveUserService{

    @Autowired
    private ActiveUserMapper activeUserMapper;

    @Override
    public JsonResult getActiveUserData() {
        List<HomeCountVO> weekActiveOriginal = activeUserMapper.getWeekActiveByDays();
        List<HomeCountVO> weekActive = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            int sum = 0;
            for(int j = 0; j < 7; j++){
                sum += weekActiveOriginal.get(i + j).getCount();
            }
            weekActive.add(new HomeCountVO(weekActiveOriginal.get(i + 7).getDay(), sum));
        }
        int totalUser = activeUserMapper.getTotalUser();
        List<HomeCountVO> dayActive = activeUserMapper.getDayActiveByDays();
        List<HomeCountVO> monthLost = activeUserMapper.getMonthLostByDays();
        Map<String, Object> activeUser = new HashMap<>(); // 活跃用户，全部数据封装
        Map<String, Object> todayData = new HashMap<>();  // 今天数据封装
        HomeCountVO todayDayActive = dayActive.remove(7);
        HomeCountVO todayWeekActive = weekActive.remove(7);
        HomeCountVO todayMonthLost = monthLost.remove(7);
        todayData.put("dayActive", todayDayActive.getCount());
        todayData.put("weekActive", todayWeekActive.getCount());
        todayData.put("monthLost", todayMonthLost.getCount());
        todayData.put("dayActiveness", getActiveness(todayDayActive.getCount(),totalUser));
        todayData.put("weekActiveness", getActiveness(todayWeekActive.getCount(),totalUser));
        todayData.put("monthLostRatio", getActiveness(todayMonthLost.getCount(),totalUser));
        activeUser.put("todayData", todayData);
        //日活跃度计算
        List<Map<String, String>> weeklyDayActiveness = getWeeklyActiveness(dayActive, totalUser);
        //周活跃度计算
        List<Map<String, String>> weeklyWeekActiveness = getWeeklyActiveness(weekActive, totalUser);
        //月流失率计算
        List<Map<String, String>> weeklyMonthLostRatio = getWeeklyActiveness(monthLost, totalUser);
        activeUser.put("weeklyDayActive", dayActive);
        activeUser.put("weeklyDayActiveness", weeklyDayActiveness);
        activeUser.put("weeklyWeekActive", weekActive);
        activeUser.put("weeklyWeekActiveness", weeklyWeekActiveness);
        activeUser.put("weeklyMonthLost", monthLost);
        activeUser.put("weeklyMonthLostRatio", weeklyMonthLostRatio);
        return new JsonResult(0, "获取成功", activeUser.size(), activeUser);
    }

    /**
     * 计算活跃度
     * @param count
     * @param total
     * @return
     */
    private String getActiveness(double count, double total) {
        if(count == 0 || total == 0) {
            return "0.00%";
        }
        double d = count/total;
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(d);
    }

    /**
     * 计算活跃度和流失率
     * @param originalList 原始数据集合
     * @param total 总用户数
     * @return
     */
    private List<Map<String, String>> getWeeklyActiveness(List<HomeCountVO> originalList, int total){
        List<Map<String, String>> resultList = new ArrayList<>();
        for(HomeCountVO homeCountVO: originalList) {
            Map<String, String> map = new HashMap<>();
            String date = homeCountVO.getDay();
            String ratio = getActiveness(homeCountVO.getCount(), total);
            map.put("day", date);
            map.put("ratio", ratio);
            resultList.add(map);
        }
        return resultList;
    }
}