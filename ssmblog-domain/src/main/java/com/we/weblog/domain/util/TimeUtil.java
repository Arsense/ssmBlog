package com.we.weblog.domain.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    private static final Calendar CALENDAR =  Calendar.getInstance();

    public  static  int  getYear(Date  date)  {
        CALENDAR.setTime(date);
        return  CALENDAR.get(Calendar.YEAR);
    }


    public static String getEdate(Date date){
        SimpleDateFormat df = new SimpleDateFormat("MMM dd", Locale.UK);
        df.setTimeZone(new java.util.SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }


    public static  String getCurrentTime(){
        CALENDAR.setTime(new Date());
        int year = CALENDAR.get(Calendar.YEAR);
        int month = CALENDAR.get(Calendar.MONTH);
        return year+""+month;

    }


    /**
     * 获取格式化的时间
     * 输出格式：2015-08-04 20:55:35
     */
    public static String getFormatClearToDay(Date date){
//        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        return formatter.format(date);
    }


    public static String getFormatClearToSecond(Date date){
//        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }


}
