package com.yunpan.base.tool;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateTool {
	public static String getCurrentDateStr() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
	}
	/**
	 * @param args
	 */
	public static String formatFullDate(Date date) {
		return DateTool.getDateToStr(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getDateToStr(Date date, String formart) {
		String result = DateFormatUtils.format(date, formart, Locale.ENGLISH);
		return result;
	}

	/**
	 * 获取当前系统前一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAgoDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * 获取N天前一天日期
	 * 
	 * @param date
	 * @return
	 */
	public static Date getAgoDay(Date date,int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -num);
		date = calendar.getTime();
		return date;
	}

	public static Date strToDate(String strDate) {
		return DateTool.strToDate(strDate, "yyyy-MM-dd HH:mm:ss");
	}

	public static Long stringToLongDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		long timeStart = 0;
		try {
			timeStart = sdf.parse(strDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeStart;
	}

	public static Long stringToLongDate(String strDate, String strDateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

		long timeStart = 0;
		try {
			timeStart = sdf.parse(strDate).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timeStart;
	}

	public static Date strToDate(String strDate, String formartStr) {
		SimpleDateFormat formatter = new SimpleDateFormat(formartStr,
				Locale.ENGLISH);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	// long类型转换为String类型
	// currentTime要转换的long类型的时间
	// formatType要转换的string类型的时间格式
	public static String longToString(long currentTime, String formatType)
			throws ParseException {
		Date date = longToDate(currentTime, formatType); // long类型转成Date类型
		String strTime = dateToString(date, formatType); // date类型转成String
		return strTime;
	}

	// date类型转换为String类型
	// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	// data Date类型的时间
	public static String dateToString(Date data, String formatType) {
		return new SimpleDateFormat(formatType).format(data);
	}

	// long转换为Date类型
	// currentTime要转换的long类型的时间
	// formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	public static Date longToDate(long currentTime, String formatType)
			throws ParseException {
		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
		String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
		Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
		return date;
	}

	// string类型转换为date类型
	// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
	// HH时mm分ss秒，
	// strTime的时间格式必须要与formatType的时间格式相同
	public static Date stringToDate(String strTime, String formatType)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(formatType);
		Date date = null;
		date = formatter.parse(strTime);
		return date;
	}

	/**
	 * 获得收益时间(获取当前天+1天，周末不算).
	 * 
	 * @param date
	 *            任意日期
	 * @return the income date
	 * @throws NullPointerException
	 *             if null == date
	 */
	private static Date getIncomeDate(Date date) throws NullPointerException {
		return DateTool.getIncomeDate(date, +1);
	}

	/**
	 * 获得收益时间(获取当前天+N天，周末不算, 节假日不算).
	 * 
	 * @param date
	 *            任意日期
	 * @param position
	 *            位置 +1
	 * @return the income date
	 * @throws NullPointerException
	 *             if null == date
	 */
	public static Date getIncomeDate(Date date, int position)
			throws NullPointerException {
		if (null == date) {
			throw new NullPointerException("the date is null or empty!");
		}

		// 对日期的操作,我们需要使用 Calendar 对象
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		// +1天
		calendar.add(Calendar.DATE, position);

		Date incomeDate = calendar.getTime();

		if (isWeekend(calendar) || isHoliday(calendar)) {
			// 递归
			return getIncomeDate(incomeDate);
		}
		return incomeDate;
	}

	/**
	 * 判断一个日历是不是周末.
	 * 
	 * @param calendar
	 *            the calendar
	 * @return true, if checks if is weekend
	 */
	private static boolean isWeekend(Calendar calendar) {
		// 判断是星期几
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfWeek == 1 || dayOfWeek == 7) {
			return true;
		}
		return false;
	}

	/**
	 * 一个日历是不是节假日.
	 * 
	 * @param calendar
	 *            the calendar
	 * @return true, if checks if is holiday
	 */
	private static boolean isHoliday(Calendar calendar) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateString = simpleDateFormat.format(calendar.getTime());

		// 节假日 这个可能不同地区,不同年份 都有可能不一样,所以需要有个地方配置, 可以放数据库, 配置文件,环境变量 等等地方
		// 这里以配置文件 为例子
		ResourceBundle resourceBundle = ResourceBundle.getBundle("holidayConfig");
		String holidays = resourceBundle.getString("holiday");

		String[] holidayArray = holidays.split(",");

		boolean isHoliday = ArrayUtils.contains(holidayArray, dateString);
		return isHoliday;
	}

	public static void main(String args[]) throws Exception {
		Date nowDate = new Date();
		Date startDate = getAgoDay(nowDate,7);
		Date endDate = getAgoDay(nowDate,1);
		System.out.println("当前日期："+nowDate);
		
		System.out.println("开始日期1："+dateToString(startDate,"yyyy-MM-dd"));
		System.out.println("截止日期2："+dateToString(endDate,"yyyy-MM-dd"));
		
//		System.out.println("a: "
//				+ DateTool.dateToString(nowDate, "yyyy-MM-dd HH:mm:ss"));
//		String strDate = "2016-05-19 12:00:00";
//		System.out.println("b: " + strDate);
//		Date a = DateTool.strToDate(strDate);
//		System.out.println(DateTool.cheDanDateValid(strDate));

		// System.out.println(DateTool.getDateToStr(a,
		// "EEE MMM dd HH:mm:ss yyyy"));

		// String str1 = "Tue Jan 12 14:59:18 2016";
		// // String str1 ="Jan 12 14:59:18 2016";
		// Date d1 = DateTool.strToDate(str1, "EEE MMM dd HH:mm:ss yyyy");
		// System.out.println(d1);
		// System.out.println(DateTool.formatFullDate(d1));

		// String pattern = "yyyy-MM-dd HH:mm:ss";
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		//
		// System.out.println(compareTime(simpleDateFormat.parse("2015-08-25 13:33:05"),
		// simpleDateFormat.parse("2015-08-22 13:33:05")));

		// System.out.println(simpleDateFormat.format(getIncomeDate(new
		// Date())));

		// System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2015-08-21 13:33:05"),
		// +1)));

		// System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2016-08-01 13:33:05"))));
		// System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2016-08-02 13:33:05"))));
		// System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2016-08-03 13:33:05"))));
		// System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2016-09-30 13:33:05"))));
		// System.out.println(simpleDateFormat.format(getIncomeDate(simpleDateFormat.parse("2016-10-02 13:33:05"))));

	}

	/**
	 * 比较时间大小 at>bt= true
	 * 
	 * @param at
	 * @param bt
	 * @return
	 */
	public static boolean compareTime(Date at, Date bt) {
		if ((at.getTime() - bt.getTime()) > 0)
			return true;
		return false;
	}

	public static boolean cheDanDateValid(String applyDate) throws Exception {
		Date appDate = DateTool.stringToDate(applyDate, "yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		// 今天三点
		Date nowDate3 = DateTool.stringToDate(
				DateTool.dateToString(nowDate, "yyyy-MM-dd") + " 15:00:00",
				"yyyy-MM-dd HH:mm:ss");
		// 昨天三点
		Date zuoDate3 = DateTool.stringToDate(
				DateTool.dateToString(DateTool.getIncomeDate(nowDate, -1),"yyyy-MM-dd") + " 15:00:00", "yyyy-MM-dd HH:mm:ss");
		System.out.println("今天三点=="+ DateTool.dateToString(nowDate3, "yyyy-MM-dd HH:mm:ss"));
		System.out.println("昨天三点=="+ DateTool.dateToString(zuoDate3, "yyyy-MM-dd HH:mm:ss"));

		// 1、判断当前时间是否是三点以后

		// 三点以后
		if (nowDate.getHours() >= 15) {
			// 当天三点以前的单子不能撤了
			if ((appDate.getTime() - nowDate3.getTime()) <= 0) {
				return false;
			}

		}
		// 三点以前
		else {
			// 昨日三点以前不可以撤单
			// 当天三点以前的单子不能撤了
			if ((appDate.getTime() - zuoDate3.getTime()) <= 0) {
				return false;
			}

		}

		return true;
	}

	/*
	 * 这样你就可以对当前时间进行年份的加减，比如求一年后i=1，取1年前i=-1 如果是月份加减cal.add(2, i);
	 * 如果是星期加减cal.add(3, i); 如果是每日加减cal.add(5, i); 如果是小时加减cal.add(10, i);
	 * 如果是分钟加减cal.add(12, i); 如果是秒的加减cal.add(13, i);
	 */

	public static Date addOrMinusYear(int i) throws Exception {
		Date rtn = null;
		GregorianCalendar cal = new GregorianCalendar();
		Date date = new Date();
		cal.setTime(date);
		cal.add(12, i);
		rtn = cal.getTime();
		return rtn;
	}

}
