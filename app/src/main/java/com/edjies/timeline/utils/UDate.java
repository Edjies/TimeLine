package com.edjies.timeline.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理工具
 * @author hubble
 *
 */
public class UDate {
	@SuppressLint("SimpleDateFormat")
	private static SimpleDateFormat sDateFormat = new SimpleDateFormat();
	
	/**
	 * 获取格式化日期时间
	 * @param calendar
	 * @param pattern  格式化pattern   "yyyyMMdd", "yyyy-MM-dd HH:mm:ss" 
	 * @return
	 */
	public static String getFormatDate(Calendar calendar, String pattern) {
		sDateFormat.applyPattern(pattern);
		return sDateFormat.format(calendar.getTime());
	}
	
	/**
	 * 获取格式化日期时间
	 * @param date
	 * @param pattern  格式化pattern   "yyyyMMdd", "yyyy-MM-dd HH:mm:ss" 
	 * @return
	 */
	public static String getFormatDate(Date date, String pattern) {
		sDateFormat.applyPattern(pattern);
		return sDateFormat.format(date);
	}

	
	/**
	 * 
	 * @param date    yyyy-MM-dd HH:mm:ss
	 * @param dstPattern 目标pattern
	 * @return
	 */
	public static String getFormatDate(String date, String srcPattern, String dstPattern) {
		try {
			sDateFormat.applyPattern(srcPattern);
			Date d = sDateFormat.parse(date);
			sDateFormat.applyPattern(dstPattern);
			return sDateFormat.format(d);
		} catch (Exception e) {
			return String.valueOf(date);
		}
	}


	/**时间提示：早上，晚上*/
	public static String getSimpleTimeTip(long time) {
		if(time < 0) {
			return "";
		}

		Date d = new Date(time);
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int hour =c.get(Calendar.HOUR_OF_DAY);
		if(hour >= 6 && hour < 12) {
			return " 早上 ";
		}

		else if(hour >=12 &&  hour < 18) {
			return " 下午 ";
		}

		else {
			return " 晚上 ";
		}
	}
	
	/***
	 * 将毫秒数转化为日期格式
	 * @param time 毫秒数
	 * @param dstPattern 日期目标格式yyyy-mm-dd
	 * @return
	 */
	public static String getFormatDate(long time, String dstPattern) {
		if(time < 0) {
			return "";
		}
		Date date = new Date(time);
		sDateFormat.applyPattern(dstPattern);
		return sDateFormat.format(date);
	}
	

	private static long dateTimeToMillis (String datetime) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			calendar.setTime(sdf.parse(datetime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendar.getTimeInMillis();	
	}
	
	
}
