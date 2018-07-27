package com.wmq.sys.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author 李怀鹏
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm）
	 */
	public static String formatDateTimeBranch(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm）
	 */
	public static String getTimeHours() {
		return formatDate(new Date(), "HH");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm）
	 */
	public static String getTimeBranch() {
		return formatDate(new Date(), "HH:mm");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm）
	 */
	public static String getDateTimeBranch() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm",
	 *   "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDates(Object str, String parrern) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parrern);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * 获取过去的小时
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * 获取过去的分钟
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		return (day>0?day+",":"")+hour+":"+min+":"+s+"."+sss;
    }
	
	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}
	
	/**
	 * 对比当前时间，大于返回TRUE，小于返回FALSE
	 * @return
	 */
	public static boolean compareDate(Date date){
		Date nowDate = new Date();
		return date.getTime()>nowDate.getTime();
	}
	
	/**
     * 获取现在时间
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }
 
    /**
     * 两个时间之间的天数
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || date1.equals(""))
            return 0;
        if (date2 == null || date2.equals(""))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return Math.abs(day);
    }
	
	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
//		System.out.println(formatDate(parseDate("2010/3/6")));
//		System.out.println(getDate("yyyy年MM月dd日 E"));
//		long time = new Date().getTime()-parseDate("2012-11-19").getTime();
//		System.out.println(time/(24*60*60*1000));
	}

	/**
	 * 将时间转为年月
	 * @param time
	 * @return
	 */
	public static String timeYears(String time){
		String stingtime = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
			Date date = dateFormat.parse(time);
			stingtime = dateFormat.format(date).replace("-","/");
		}catch (Exception e){
			e.printStackTrace();
		}
		return stingtime;
	}

	/**
	 * 将时间转为年月日
	 * @param time
	 * @return
	 */
	public static String timeYears(String time, String parrern){
		String stingtime = "";
		try {
			DateFormat dateFormat = new SimpleDateFormat(parrern);
			Date date = dateFormat.parse(time);
			stingtime = dateFormat.format(date).replace("-","/");
		}catch (Exception e){
			e.printStackTrace();
		}
		return stingtime;
	}

	/**
	 * 获取时间段--30天
	 * @return
	 */
	public static Map getTimeSlot() {
		Map map = new HashMap();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());//获取当天时间
		String endTime = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd 23:59:59");
		String startTime = "";
		for(int i = 0; i < 29; i++){
			rightNow.add(Calendar.DAY_OF_YEAR,-1);
			startTime = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd 00:00:01");
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	/**
	 * 获取今天的开始及结束日期
	 * @return
	 */
	public static Map<String, Object> todayDate() {
		Map<String, Object> map = new HashMap<>();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String stateTime = sdf.format(date)+" 00:00:01";
		String endTime = sdf.format(date)+" 23:59:59";
		map.put("startTime",stateTime);
		map.put("endTime",endTime);
		return map;
	}

	/**
	 * 获取时间段--30天
	 * @return
	 */
	public static Map getThreeTimeSlot() {
		Map map = new HashMap();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());//获取当天时间
		String endTime = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd 23:59:59");
		String startTime = "";
		for(int i = 0; i < 2; i++){
			rightNow.add(Calendar.DAY_OF_YEAR,-1);
			startTime = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd 00:00:01");
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	/*
     * 将时间戳转换为时间
     */
	public static String stampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 获取开始到结束时间
	 * @return
	 */
	public static Map getStartAndEndTimeSlot(int day) {
		Map map = new HashMap();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(new Date());//获取当天时间
		String endTime = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd 23:59:59");
		String startTime = "";
		for(int i = 0; i < day-1; i++){
			rightNow.add(Calendar.DAY_OF_YEAR,-1);
			startTime = DateUtils.formatDate(rightNow.getTime(), "yyyy-MM-dd 00:00:01");
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return map;
	}

	/**
	 * 判断VIP是否过期
	 * @param expireTime
	 * @return
     */
	public static boolean isVipExpire(Date expireTime) {
		long num = DateUtil.getDaySub(formatDate(new Date(), "yyyy-MM-dd"),getVipExpireTime(expireTime),"yyyy-MM-dd");
		if(num > 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 判断邀请观影时间是否过期
	 * @param expireTime
	 * @return
     */
	public static boolean isInvitationTimeExpire(String expireTime) {
		long num = DateUtil.getDaySub(formatDate(new Date(), "yyyy-MM-dd"),expireTime,"yyyy-MM-dd");
		if(num > 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * 获取VIP过期时间
	 * @param expireTime //数据库过期时间
	 * @return
     */
	public static String getVipExpireTime(Date expireTime) {
		Date time = null;
		try {
			time = parseDate(formatDate(expireTime, "yyyy-MM-dd"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		Date date = DateUtil.add(time,1); //数据库过期时间加一天，因为不判断到时分秒
		return DateUtils.formatDate(date,"yyyy-MM-dd");
	}

	/**
	 * 判断一个字符串是不是日期
	 * @param date 日期
	 * @return
	 */
	public static boolean isValidDate(String date) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			Date parseDate = format.parse(date);
			if(parseDate.after(new Date())) {
				convertSuccess = false;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * @param smallDate 较小的时间
	 * @param bigDate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smallDate,Date bigDate) throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		smallDate = sdf.parse(sdf.format(smallDate));
		bigDate=sdf.parse(sdf.format(bigDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smallDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bigDate);
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(betweenDays));
	}

	/**
	 * 计算两个日期之间相差的天数-字符串
	 * @param smallDate 较小的时间
	 * @param bigDate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(String smallDate,String bigDate) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smallDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bigDate));
		long time2 = cal.getTimeInMillis();
		long betweenDays = (time2 - time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(betweenDays));
	}
}
