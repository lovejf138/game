package com.webpos.tools;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;


public class DateUtils {
	private static Logger logger = Logger.getLogger(DateUtils.class);

    public static final String C_TIME_PATTON_DEFAULT = "yyyy-MM-dd HH:mm:ss";

    public static String formatDateTime(Date date, String format) {
        if (date == null)
            return null;
        if (format == null)
            return date.toString();
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String formatY0M0D(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyyMMdd");
    }
    
    public static String formatYM(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyy-MM");
    }
    
    public static String formatYMD(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyy-MM-dd");
    }

    public static String formatYMD_(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyy_MM_dd");
    }

    public static String formatYMDHM(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyy-MM-dd HH:mm");
    }

    public static String formatDateTimeByDate(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static String formatDateTimeByDateOther(Date date) {
        return date == null ? "" : formatDateTime(date, "yyyy-MM-dd_HH:mm:ss");
    }

    public static String formatDateTimeByDateTime(Date date) {
        return date == null ? "" : formatDateTime(date, "HH:mm:ss");
    }

    public static String formatDateTimeByDateTimeHms(Date date) {
        return date == null ? "" : formatDateTime(date, "HH-mm-ss");
    }
    
    public static boolean showNew(Date time) {  
        if (time == null)   
            return false;
        return DateUtils.addDays(time, 3).compareTo(new Date()) >= 0;
    }

    public static Date addDays(Date srcDate, int addDays) {
        return getNextDayCurrDay(srcDate, addDays);
    }

    public static Date addMinutes(Date srcDate, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(srcDate);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    public static Date getNextDayCurrDay(Date currDate, int i) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(currDate);
        gc.add(GregorianCalendar.DATE, i);
        return gc.getTime();
    }

    public static int getCurrDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_WEEK);
    }


    public static long getNumOfDays(String date1, String date2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d1;
        try {
            d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            long diff = Math.abs(d2.getTime() - d1.getTime());
            return (long) (diff / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
        	logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean compare(Date d1, Date d2) {
        if ((d2.getTime() - d1.getTime()) > 600000l) {
            return true;
        }
        return false;
    }

    public static String[] weekDate(int year, int week) {
        if (week < 1 || week > 52)
            return null;
        String s[] = new String[2];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.WEEK_OF_YEAR, week);
        c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        s[0] = formatYMD(c.getTime());
        c.add(Calendar.DATE, 6);
        s[1] = formatYMD(c.getTime());
        return s;

    }

    public static String[] monthDate(int year, int month) {
        if (month < 1 || month > 12)
            return null;
        String s[] = new String[2];
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        s[0] = formatYMD(c.getTime());
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DATE, -1);
        s[1] = formatYMD(c.getTime());
        return s;

    }

    public static String[] seasonDate(int year, int season) {
        if (season < 1 || season > 4)
            return null;
        @SuppressWarnings("unused")
        Calendar c = Calendar.getInstance();
        String y = String.valueOf(year);
        String[] s = new String[2];
        switch (season) {
            case 1:
                s[0] = y + "-01-01";
                s[1] = y + "-03-31";
                break;
            case 2:
                s[0] = y + "-04-01";
                s[1] = y + "-06-30";
                break;
            case 3:
                s[0] = y + "-07-01";
                s[1] = y + "-09-30";
                break;
            case 4:
                s[0] = y + "-10-01";
                s[1] = y + "-12-31";
                break;
        }
        return s;

    }

    public String transDate(String year, String season, String month,
                            String week) {

        int yearstr = Integer.parseInt(year);
        int seasonstr = 0;
        int monthstr = 0;
        int weekstr = 0;

        if (!"".equals(year.trim())) {
            if (!"".equals(season.trim())) {
                seasonstr = Integer.parseInt(season);
                String[] temp = DateUtils.seasonDate(yearstr, seasonstr);
                return temp[0] + "," + temp[1];
            }
            if (!"".equals(month.trim())) {
                monthstr = Integer.parseInt(month);
                String[] temp = DateUtils.monthDate(yearstr, monthstr);
                return temp[0] + "," + temp[1];
            }
            if (!"".equals(week.trim())) {
                weekstr = Integer.parseInt(week);
                String[] temp = DateUtils.weekDate(yearstr, weekstr);
                return temp[0] + "," + temp[1];
            }
        }

        return null;
    }

    public static final Date parseDate(String source) throws ParseException {
        if (source == null || source.length() == 0) {
            return null;
        }
        try {
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat1.parse(source);
        } catch (ParseException e) {
        	logger.error(e.getMessage(),e);
            try {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                return dateFormat.parse(source);
            } catch (Exception e1) {
            	logger.error(e.getMessage(),e);
                DateFormat dateFormat = new SimpleDateFormat("yyyy��MM��dd��");
                return dateFormat.parse(source);
            }
        }

    }

    /**
     * 判断是否闰年
     *
     * @param date
     * @return
     */
    public boolean judgeLeapYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        int year;
        gc.setTime(date);
        year = gc.get(Calendar.YEAR);// Integer.parseInt(null,
        // gc.get(Calendar.YEAR));
        GregorianCalendar cal = new GregorianCalendar();
        boolean b = false;
        b = cal.isLeapYear(year); // true
        return b;
    }

    /**
     * 计算两个时间之间的间隔数
     *
     * @param starttime 开始时间
     * @param endtime   结束时间
     * @param s         返回类型0，年；1，月；2，周；3，天；4，小时；5，分；6，秒。
     * @param n         返回类型的单位基数年（365/366）；月（28/29/30/31）
     * @return 注：为保证计算准确，starttime、endtime应保证是datetime型的，即：年月日时分秒都全;另，考虑到闰年和月份的天数不定，故设计为用户设定。
     */
    public static double getIntervaltime(Date starttime, Date endtime, int s, int n) {
        double val = 0;
        // 确保startime在endtime之前
        if (starttime.after(endtime)) {
            Date cal = starttime;
            starttime = endtime;
            endtime = cal;
        }
        // 分别取得两个时间的毫秒数
        long ll_star = starttime.getTime();
        long ll_end = endtime.getTime();

        long ll_Intervaltime = ll_end - ll_star;
        // 根据S参数返回不同的单位值
        switch (s) {
            case 0:// 返回年
                // val = ll_Intervaltime/(1000*60*60*24*n);
            case 1:// 返回月
                val = ll_Intervaltime / (1000 * 60 * 60 * 24 * n);
                break;
            case 2:// 返回星期
                val = ll_Intervaltime / (1000 * 60 * 60 * 24 * 7);
                break;
            case 3:// 返回天
                val = ll_Intervaltime / (1000 * 60 * 60 * 24);
                break;
            case 4:// 返回时
                val = ll_Intervaltime / (1000 * 60 * 60);
                break;
            case 5:// 返回分
                val = ll_Intervaltime / (1000 * 60);
                break;
            case 6:// 返回秒月
                val = ll_Intervaltime / 1000;
                break;
        }
        return val;
    }

    /**
     * 计算某年的天数
     *
     * @param date
     * @return
     */
    public int getAllDaysForYear(Date date) {
        int days;
        Calendar time = Calendar.getInstance();
        time.clear();
        time.setTime(date);
        days = time.getActualMaximum(Calendar.DAY_OF_YEAR);
        // if (judgeLeapYear(date)){
        // days = 366;
        // }else{
        // days = 365;
        // }

        return days;
    }

    /**
     * 计算某年某月的天数
     *
     * @param date
     * @return
     */
    @SuppressWarnings("static-access")
    public int getAllDaysForMonth(Date date) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.setTime(date);
        time.set(Calendar.MONTH, time.get(time.MONTH) - 1);
        int days = time.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    /**
     * 计算某日期是该年的第多少天
     *
     * @param date
     * @return
     */
    public int getDayOfYear(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        int days = 0, months = 0, year;
        gc.setTime(date);
        days = gc.get(Calendar.DATE);
        months = gc.get(Calendar.MONTH);
        year = gc.get(Calendar.YEAR);
        for (int i = 0; i < (months - 1); i++) {
            gc.set(year, gc.get(Calendar.MONTH) - 1, gc.get(Calendar.DATE));
            days = days + getAllDaysForMonth(gc.getTime());
        }
        // //精确计算
        // int days;
        // Calendar time = Calendar.getInstance();
        // time.clear();
        // time.setTime(date);
        // time.set(Calendar.MONTH, 1);
        // time.set(Calendar.DATE, 1);
        // Date date1 = time.getTime();
        // days = (int) (getIntervaltime(date1, date,3,30)+1);

        return days;
    }

    /**
     * 计算某日期是该月的第多少天
     *
     * @param date
     * @return
     */
    public int getDayOfMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        int days;
        gc.setTime(date);
        days = gc.get(Calendar.DATE);
        return days;
    }

    /**
     * 计算某日期是该年中的第几个星期
     *
     * @param date
     * @return
     */
    public int getWeekOfyear(Date date) {
        int weekno;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        weekno = cal.get(Calendar.WEEK_OF_YEAR);
        return weekno;
    }

    /**
     * 计算某年中的第几个星期是什么日期
     *
     * @param year,weekno
     * @return
     */
    public Date getDateOfWeek(int year, int weekno) {
        Date date;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, weekno);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        date = cal.getTime();
        return date;
    }

    public static String formatCronExpression(String cronExpression) {
        String[] arryLightOn = cronExpression.split(" ");
        if (arryLightOn[0].length() < 2) {
            arryLightOn[0] = "0" + arryLightOn[0];
        }
        if (arryLightOn[1].length() < 2) {
            arryLightOn[1] = "0" + arryLightOn[1];
        }
        if (arryLightOn[2].length() < 2) {
            arryLightOn[2] = "0" + arryLightOn[2];
        }
        String timer = arryLightOn[2] + " : " + arryLightOn[1] + " : "
                + arryLightOn[0];
        return timer;
    }

    /**
	 * 将Date类型的日期转换为系统参数定义的格式的字符串。
	 *
	 * @param aTs_Datetime
	 * @param as_Pattern
	 * @return
	 */
	public static String format(Date aTs_Datetime, String as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;

		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern);

		return dateFromat.format(aTs_Datetime);
	}

    	/**
	 * Parse a string and return the date value in the specified format
	 *
	 * @param strFormat
	 * @param dateValue
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public static Date parseDate(String strFormat, String dateValue) {
		if (dateValue == null || "".equals(dateValue))
			return null;

		if (strFormat == null)
			strFormat = C_TIME_PATTON_DEFAULT;

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;

		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			logger.error(pe.getMessage(),pe);
			newDate = null;
		}

		return newDate;
	}
	 
	/**
	 *  获取所有日期,距当天几天前的所有日期  
	 * @return
	 */
	public static List<String> getBeforeDates(int num) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		long nowTime = date.getTime();
		List<String> dateList = new ArrayList<String>();
		for (int i = num; i > 0; i--) {
			long numTime = nowTime - (1000L * 60 * 60 * 24 * i);
			Date numDate = new Date(numTime);
			dateList.add(df.format(numDate));
		}
		return dateList;
	}
	
	/**
	 * 获取指定天数前的日期
	 * @param args
	 */
	public static String getNumDate(int num) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		long nowTime = date.getTime();
		long numTime = nowTime - (1000L * 60 * 60 * 24 * num);
		Date numDate = new Date(numTime);  
		return df.format(numDate);
	}
	/**
	 * 获取指定天数前的日期时间
	 * @param args
	 */
	public static String getNumDateWithTime(int num) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		long nowTime = date.getTime();
		long numTime = nowTime - (1000L * 60 * 60 * 24 * num);
		Date numDate = new Date(numTime);  
		return df.format(numDate);
	}
	/**
	 *  默认查询开始时间(月)
	 */
	public static String selectDefaultStartTimeWithMonth(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, -1);//获取上个月的日期
		
		String lastMonthDay = simpleDateFormat.format(calendar.getTime());
		String start = lastMonthDay+" 00:00:00";
		return start;
	}
	/**
	 *  默认查询结束时间(月)
	 */
	public static String selectDefaultEndTimeWithMonth(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new Date());
		
		String start = today+" 23:59:59";
		return start;
	}
	/**
	 *  默认查询开始时间(当天)
	 */
	public static String selectDefaultStartTimeWithDay(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new Date());
		
		String start = today+" 00:00:00";
		return start;
	}
	
	/**
	 *  默认查询开始时间(7天前)
	 */
	public static String selectDefaultStartTimeWithDayBefore7Day(){
		Calendar   c   =   Calendar.getInstance(); 
		c.add(Calendar.DAY_OF_MONTH,-7);//关键是这个7天前.... 
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(c.getTime());
		
		String start = today+" 00:00:00";
		return start;
	}
	
	/**
	 * 判断字符串是否为指定格式  DateUtils.isRightDateStr("2017-05-15","yyyy-MM-dd")
	 * @param dateStr
	 * @param datePattern
	 * @return
	 */
	public static boolean isRightDateStr(String dateStr,String datePattern){
        DateFormat dateFormat  = new SimpleDateFormat(datePattern);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = (Date)dateFormat.parse(dateStr);
            //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if(dateStr.equals(newDateStr)){
                return true;
            }else {
               // LOGGER.error("字符串dateStr:{}， 不是严格的 datePattern:{} 格式的字符串",dateStr,datePattern);
                return false;
            }
        } catch (ParseException e) {
           // LOGGER.error("字符串dateStr:{}，不能按照 datePattern:{} 样式转换",dateStr,datePattern);
            return false;
        }
    }
	
	
	/*
    *获取当前时间之前或之后几分钟 minute
    */
    public static String getTimeByMinute(String time,int minute) {

    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date newDate = null;
		try {
			newDate = dateFormat.parse(time);
		} catch (ParseException pe) {
			//logger.error(pe.getMessage(),pe);
			newDate = null;
		}
		
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(newDate);
        
        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

    }
    
	/**
	 *  默认查询结束时间(日)
	 */
	public static String selectDefaultEndTimeWithDay(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = simpleDateFormat.format(new Date());
		
		String start = today+" 23:59:59";
		return start;
	}
	public static void main(String[] args) {
		
//		int num = 30;
//		String numDate = getNumDate(0);
//		System.out.println(numDate);
//		System.out.println("*******");
//		String numDate7 = getNumDate(7);
//		System.out.println(numDate7);
//		System.out.println("*******");
//		String numDate15 = getNumDate(15);
//		System.out.println(numDate15);
//		System.out.println("*******");
//		
//		String numDate30 = getNumDate(30);
//		System.out.println(numDate30);
//		System.out.println("*******");
		/*List<String> dateList = getBeforeDates(num);
		for (String str : dateList) {
			System.out.println(str);
		}*/
		
		 System.out.println(isRightDateStr("2017-01-08 00:00:00","yyyy-MM-dd HH:mm:ss"));
		
	}
	
	public static String getYesterdayDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}
	
	public static String getYesterdayStartDateStr (){
		return getYesterdayDate() + " 00:00:00";
	}
	
	public static String getYesterdayEndDateStr (){
		return getYesterdayDate() + " 23:59:59";
	}
	
	public static Date getYesterdayStartDate(){
		String yesterdayStr = getYesterdayDate() + " 00:00:00";
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date getYesterdayEndDate(){
		String yesterdayStr = getYesterdayDate() + " 23:59:59";
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yesterdayStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将date转成jdk8的LocalDate类
	 * 
	 * @param date 日期
	 */
	public static LocalDate convertFromDate(Date date){
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
