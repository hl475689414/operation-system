package com.wmq.sys.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by 李怀鹏 on 2018/5/21.
 */
@Component
public class ChartDateUtils {
    /**
     * 获取24小时每小时数字并赋值
     */
    public List<Map> hourslist() {
        List<Map> list = new ArrayList<>();
        Map map = null;
        for (int i = 0; i < 24; i++){
            map = new HashMap();
            if(i < 10) {
                map.put("hours", "0"+i);
                map.put("count", "0");
            }else {
                map.put("hours", ""+i);
                map.put("count", "0");
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 获取N天的天列表并赋值
     * 由于时间只有天，时间跨月时易显示不正常，为防止天重复，此处先返回具体年月日
     * @param days
     * @return
     * @throws ParseException
     */
    public List<Map> getDaysLlist(int days) throws ParseException {
        List<Map> list = new ArrayList<>();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());//获取当天时间
        Map map = null;
        for(int i = 0; i < days; i++){
            map = new HashMap();
            rightNow.add(Calendar.DAY_OF_YEAR,-1);
            String time = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd");
            map.put("day", time);
            map.put("count", "0");
            list.add(map);
        }
        //将数据顺序倒序
        Collections.reverse(list);
        return list;
    }

    /**
     * 获取N天的天列表并赋值
     * 由于时间只有天，时间跨月时易显示不正常，为防止天重复，此处先返回具体年月日
     * @param days
     * @return
     * @throws ParseException
     */
    public List<Map> getDaysKanBanLlist(int days) throws ParseException {
        List<Map> list = new ArrayList<>();
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(new Date());//获取当天时间
        Map map = null;
        for(int i = 0; i < days; i++){
            map = new HashMap();
            rightNow.add(Calendar.DAY_OF_YEAR,-1);
            String time = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd");
            map.put("day", time);
            map.put("count", "0");
            map.put("money", "0");
            list.add(map);
        }
        //将数据顺序倒序
        Collections.reverse(list);
        return list;
    }

    /**
     * 自定义天数的天列表
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public List<Map> customDays(String startDate,String endDate) throws ParseException {
        List<Map> customDays = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int number_day = DateUtil.DayBetweenNew(endDate,startDate)+1; //计算相差天数
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(sdf.parse(startDate));
        Map map = null;
        for(int i = 0; i < number_day; i++){
            map = new HashMap();
            String time = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd");
            map.put("day", time);
            map.put("count", "0");
            rightNow.add(Calendar.DATE,1);
            customDays.add(map);
        }
        return customDays;
    }
}
