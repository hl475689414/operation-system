package com.wmq.sys.service.impl;

import com.wmq.sys.dao.HomeMapper;
import com.wmq.sys.service.HomeService;
import com.wmq.sys.utils.JsonResult;
import com.wmq.sys.vo.TodayDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 程江涛 on 2018/5/16 0016
 */
@Service
public class HomeServiceImpl implements HomeService{

    @Autowired
    private HomeMapper homeMapper;

    @Override
    public JsonResult getTodayData() {
        TodayDataVO result = homeMapper.getTodayData();
        String ratioNew = getRatio(result.getTodayNew(), result.getYesterdayNew());
        String ratioActive = getRatio(result.getTodayActive(), result.getYesterdayActive());
        String ratioLogin = getRatio(result.getTodayLogin(), result.getYesterdayLogin());
        String ratioIncome = getRatio(result.getTodayIncome(), result.getYesterdayIncom());
        Map<String, Object>  map = new HashMap<>();
        map.put("todayNew", result.getTodayNew());
        map.put("yesterdayNew", result.getYesterdayNew());
        map.put("todayActive", result.getTodayActive());
        map.put("yesterdayActive" ,result.getYesterdayActive());
        map.put("todayLogin", result.getTodayLogin());
        map.put("yesterdayLogin", result.getYesterdayLogin());
        map.put("todayIncome", result.getTodayIncome());
        map.put("yesterdayIncome", result.getYesterdayIncom());
        map.put("ratioNew", ratioNew);
        map.put("ratioActive", ratioActive);
        map.put("ratioLogin", ratioLogin);
        map.put("ratioIncome", ratioIncome);
        return new JsonResult(0, "获取成功", map.size(), map);
    }

    @Override
    public JsonResult getTodayNewByHours() {
        List<Map<String, Integer>> list = homeMapper.getTodayNewByHours();
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getTodayActiveByHours() {
        List<Map<String, Integer>> list = homeMapper.getTodayActiveByHours();
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getTodayLoginByHours() {
        List<Map<String, Integer>> list = homeMapper.getTodayLoginByHours();
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getTodayIncomeByHours() {
        List<Map<String, Double>> list = homeMapper.getTodayIncomeByHours();
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getDateNewByHours(String date) {
        List<Map<String, Integer>> list = homeMapper.getDateNewByHours(date);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getDateActiveByHours(String date) {
        List<Map<String, Integer>> list = homeMapper.getDateActiveByHours(date);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getDateLoginByHours(String date) {
        List<Map<String, Integer>> list = homeMapper.getDateLoginByHours(date);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getDateIncomeByHours(String date) {
        List<Map<String, Double>> list = homeMapper.getDateIncomeByHours(date);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getNewByDays(Integer days) {
        List<Map<String, Integer>> list = homeMapper.getNewByDays(days);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getActiveByDays(Integer days) {
        List<Map<String, Integer>> list = homeMapper.getActiveByDays(days);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getLoginByDays(Integer days) {
        List<Map<String, Integer>> list = homeMapper.getLoginByDays(days);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getIncomeByDays(Integer days) {
        List<Map<String, Double>> list = homeMapper.getIncomeByDays(days);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getNewByInterval(String start, String end) {
        List<Map<String, Integer>> list = homeMapper.getNewByInterval(start, end);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getActiveByInterval(String start, String end) {
        List<Map<String, Integer>> list = homeMapper.getActiveByInterval(start, end);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getLoginByInterval(String start, String end) {
        List<Map<String, Integer>> list = homeMapper.getLoginByInterval(start, end);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    @Override
    public JsonResult getIncomeByInterval(String start, String end) {
        List<Map<String, Double>> list = homeMapper.getIncomeByInterval(start, end);
        if(list != null && list.size() > 0) {
            return new JsonResult(0, "获取成功", list.size(), list);
        }
        return new JsonResult(1, "获取失败");
    }

    /**
     * 计算日环比
     * @param today
     * @param yesterday
     * @return
     */
    private String getRatio(double today, double yesterday) {
        if(today == 0 && yesterday == 0 || today == yesterday ) {
            return "0.00%";
        }
        double d = (today - yesterday)/yesterday;
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(d);
    }

}