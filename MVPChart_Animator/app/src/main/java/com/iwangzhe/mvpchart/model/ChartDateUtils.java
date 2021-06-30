package com.iwangzhe.mvpchart.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 类：DateUtils 数据层工具类
 * 1.日期的处理
 * 2.
 * 作者： qxc
 * 日期：2018/4/18.
 */
public class ChartDateUtils {
    public static long getDateNow(){
        Date date = new Date();
        return date.getTime();
    }

    public static String getDate(int day){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        calendar.add(Calendar.DATE, -day);
        String date = sdf.format(calendar.getTime());
        return date;
    }
}
