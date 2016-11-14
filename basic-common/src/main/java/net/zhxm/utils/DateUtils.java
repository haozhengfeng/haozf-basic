package net.zhxm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * DateUtils
 * @author zhengxingmiao
 * @date Aug 10, 2013
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	/**
	 * 英文简写（默认）如：2010-12
	 */
	public static String FORMAT_SHORTYM= "yyyy-MM";
	
	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";

	/**
	 * 英文全称  如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
	
	/**
	 * 中文简写  如：2010年12月
	 */
	public static String FORMAT_SHORTYM_CN = "yyyy年MM月";
	
	/**
	 * 中文简写  如：2010年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	/**
	 * 中文全称  如：2010年12月01日  23时15分06秒
	 */
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	/**
	 * 精确到毫秒的完整中文时间  如：2010年12月01日  23时15分06秒775毫秒
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	/**
	 * 小时、分钟、秒 如：12:24:14
	 */
	public final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";


	/**
	 * 获得默认的 date pattern
	 * @Title: getDatePattern 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:16:13 AM
	 */
	public static String getDatePattern() {
		return FORMAT_LONG;
	}

	/**
	 * 根据预设格式返回当前日期
	 * @Title: getNow 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:15:33 AM
	 */
	public static String getNow() {
		return format(new Date());
	}

	/**
	 * 使用预设格式格式化日期
	 * @Title: format 
	 * @param date
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:15:59 AM
	 */
	public static String format(Date date) {
		return format(date, getDatePattern());
	}

	/**
	 * 根据用户格式返回当前日期
	 * @Title: getNow 
	 * @param format
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:16:46 AM
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}

	/**
	 * 使用用户格式格式化日期
	 * @Title: format 
	 * @param date
	 * @param pattern
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:16:53 AM
	 */
	public static String format(Date date, String pattern) {
		String returnValue = "";
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			returnValue = df.format(date);
		}
		return (returnValue);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * @Title: parse 
	 * @param strDate
	 * @param pattern
	 * @return Date
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:17:49 AM
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 格式化日期
	 * @Title: format 
	 * @param date
	 * @param type
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:18:45 AM
	 */
	public static String format(String date, String type) {
		if (date == null) {
			return "";
		} else if (date.length() == 0) {
			return "";
		} else {
			String newdate = "";
			try {
				SimpleDateFormat df = new SimpleDateFormat(type);
				newdate = df.format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
			} catch (ParseException e) {
				e.printStackTrace();
				return "";
			}
			return newdate;
		}

	}

	/**
	 * 获取2位年份
	 * @Title: getYear 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:05 AM
	 */
	public static String getYear() {
		SimpleDateFormat df = new SimpleDateFormat("yy");
		return df.format(new Date());
	}

	/**
	 * 获取4位年份
	 * @Title: getYearYYYY 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:11 AM
	 */
	public static String getYearYYYY() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date());
	}

	/**
	 * 获取当前月份
	 * @Title: getMonth 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:18 AM
	 */
	public static String getMonth() {
		SimpleDateFormat df = new SimpleDateFormat("MM");
		return df.format(new Date());
	}

	/**
	 * 获取当前是那一天
	 * @Title: getDay 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:23 AM
	 */
	public static String getDay() {
		SimpleDateFormat df = new SimpleDateFormat("dd");
		return df.format(new Date());
	}

	/**
	 * 获取当前小时
	 * @Title: getHour 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:29 AM
	 */
	public static String getHour() {
		SimpleDateFormat df = new SimpleDateFormat("HH");
		return df.format(new Date());
	}

	/**
	 * 获取当前分钟
	 * @Title: getMinute 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:36 AM
	 */
	public static String getMinute() {
		SimpleDateFormat df = new SimpleDateFormat("mm");
		return df.format(new Date());
	}

	/**
	 * 获取当前秒数
	 * @Title: getSecond 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:45 AM
	 */
	public static String getSecond() {
		SimpleDateFormat df = new SimpleDateFormat("ss");
		return df.format(new Date());
	}

	/**
	 * 获取当前毫秒数
	 * @Title: getMillisecond 
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:50 AM
	 */
	public static String getMillisecond() {
		SimpleDateFormat df = new SimpleDateFormat("SSS");
		return df.format(new Date());
	}

	/**
	 * 按照格式比较两个日期
	 * @Title: compareTimstampStr 
	 * @param str2
	 * @param str1
	 * @param type
	 * @return int
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:20:58 AM
	 */
	public static int compareTimstampStr(String str2, String str1, String type) {
		int i = 10;
		SimpleDateFormat df = new SimpleDateFormat(type);
		long lm = 10L;
		try {
			Date d_str2 = df.parse(str2);
			Date d_str1 = df.parse(str1);
			lm = d_str2.getTime() - d_str1.getTime();
		} catch (ParseException e) {
			i = 2147483647;
			e.printStackTrace();
		}

		if (lm > 0L) {
			i = 1;
		} else if (lm == 0L) {
			i = 0;
		} else if (lm < 0L) {
			i = -1;
		}
		return i;
	}

	/**
	 * 获得本年有多少天
	 * @Title: getMaxYear 
	 * @return int
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:24:04 AM
	 */
	public static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);//把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);//把日期回滚一天
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	/**
	 * 计算两个日期之间的毫秒数
	 * @Title: getHaoMiao 
	 * @param end_date
	 * @param begin_date
	 * @return long
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:26:15 AM
	 */
	public static long getHaoMiao(String end_date, String begin_date) {
		long l = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date endDate = df.parse(end_date);
			Date beginDate = df.parse(begin_date);
			l = (endDate.getTime() - beginDate.getTime());
		} catch (ParseException e) {
			l = 0;
		}
		return l;
	}

	/**
	 * 获取某个时间前num小时的时间
	 * @Title: getRollHoursTime 
	 * @param f_type
	 * @param time
	 * @param num
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:28:47 AM
	 */
	public static String getRollHoursTime(String f_type, String time, String num) {
		SimpleDateFormat df = new SimpleDateFormat(f_type);
		Date date = null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int ls_num = StrUtils.getNotNullIntValue(num, 0);
		Date return_date = new Date(date.getTime() - ls_num * 3600 * 1000);
		return df.format(return_date).toString();
	}

	/**
	 * 获取某个时间前几天的日期
	 * @Title: getRollDayTime 
	 * @param f_type
	 * @param time
	 * @param num
	 * @return String
	 * @author：zhengxingmiao
	 * @time: Nov 27, 2011 11:29:15 AM
	 */
	public static String getRollDayTime(String f_type, String time, int num) {
		SimpleDateFormat df = new SimpleDateFormat(f_type);
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(df.parse(time));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		c.add(5, num);

		Date return_date = new Date(c.getTimeInMillis());

		return df.format(return_date).toString();
	}

	/**
	 * 某个时间和当前时间比较
	 * @Title: compareTotime 
	 * @param str
	 * @return boolean
	 * @author：zhengxingmiao
	 * @time: Dec 17, 2011 4:06:57 PM
	 */
	public static boolean compareTotime(String str) {
		boolean b = false;
		long l = 0L;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDay = getNow("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse(currentDay);
			Date d2 = df.parse(str);
			l = d1.getTime() - d2.getTime();
		} catch (ParseException e) {
			l = 0L;
			e.printStackTrace();
		}
		if (l > 0L) {
			b = true;
		}
		return b;
	}

	/**
	 * 获取前几分钟的时间
	 * 方法描述 : 
	 * 创建者：kgj 
	 * 项目名称： CommonUtil
	 * 类名： DateUtil.java
	 * 版本： v1.0
	 * 创建时间： Oct 17, 2012 5:27:27 PM
	 * @param f_type
	 * @param time
	 * @param num
	 * @return String
	 */
	public static String getRollMinteTime(String f_type, String time, String num) {
		SimpleDateFormat df = new SimpleDateFormat(f_type);
		Date date = null;
		try {
			date = df.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int ls_num = StrUtils.getNotNullIntValue(num, 0);
		Date return_date = new Date(date.getTime() - ls_num * 60 * 1000);
		return df.format(return_date).toString();
	}

	/**
	 * 计算两个时间差
	 * 方法描述 : 
	 * 创建者：kanggj 
	 * 项目名称： d1cm_admin
	 * 类名： DateUtil.java
	 * 版本： v1.0
	 * 创建时间： Mar 22, 2013 9:38:37 AM
	 * @param startTime
	 * @param endTime
	 * @param format void
	 */
	public void dateDiff(String startTime, String endTime, String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒。");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 计算时间差
	 * 方法描述 : 
	 * 创建者：kanggj 
	 * 项目名称： d1cm_admin
	 * 类名： DateUtil.java
	 * 版本： v1.0
	 * 创建时间： Mar 22, 2013 9:41:01 AM
	 * @param startTime
	 * @param endTime void
	 */
	public static String dateDiff(long startTime, long endTime) {
		// 按照传入的格式生成一个simpledateformate对象	
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long mns = 1;// 一秒钟的毫秒数
		long diff;
		try {
			// 获得两个时间的毫秒时间差异
			diff = endTime - startTime;
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff % nd % nh % nm / ns;// 计算差多少秒
			long msec = diff % nd % nh % nm % ns / mns;// 计算差多少秒
			// 输出结果
			return "时间相差：" + day + "天" + hour + "小时" + min + "分钟" + sec + "秒" + msec + "毫秒。";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * 转换日期字符串得到指定格式的日期类型
	 * </br>zhengxingmiao  Mar 10, 2014
	 * </br>修改者名字 修改日期
	 * </br>修改内容
	 * @param formatString	需要转换的格式字符串
	 * @param targetDate	需要转换的时间
	 * @return
	 * @throws ParseException
	 */
	public static final Date convertString2Date(String formatString, String targetDate)
			throws ParseException {
		if (StrUtils.isBlank(targetDate)) return null;
		SimpleDateFormat format = null;
		Date result = null;
		format = new SimpleDateFormat(formatString);
		try {
			result = format.parse(targetDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return result;
	}
	
	/**
	 * 转换字符串得到默认格式的日期类型
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date convertString2Date(String strDate) throws ParseException {
		Date result = null;
		try {
			result = convertString2Date(FORMAT_SHORT, strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return result;
	}
	
	/**
	 * 
	 * main函数
	 * </br>zhengxingmiao  Mar 6, 2014
	 * </br>修改者名字 修改日期
	 * </br>修改内容
	 * @param args
	 */
	public static void main(String[] args) {

	}
	/**
	 * 根据用户格式返回当前月份的上个月
	 * @author zhangsimu
	 * @date 2015-9-10 下午03:42:07
	 * @param format
	 * @return
	 */
	public static String getLastYMDate(String format){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		return format(cal.getTime(), format);
	}

}