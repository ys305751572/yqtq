package com.leoman.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestUtil {

    //当前0点
    public static Timestamp getDayBegin() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 001);
        return new Timestamp(cal.getTimeInMillis());
    }

    // 当天0点
    public static Timestamp getDayBeginTimestamp() {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        Date date2 = new Date(date.getTime() - gc.get(gc.HOUR_OF_DAY) * 60 * 60
                * 1000 - gc.get(gc.MINUTE) * 60 * 1000 - gc.get(gc.SECOND)
                * 1000);
        return new Timestamp(date2.getTime());
    }
    //获得当天0点时间
    public static Long getTimesmorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (Long) (cal.getTimeInMillis());
    }
    //获得当天24点时间
    public static Long getTimesnight(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 24);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (Long) (cal.getTimeInMillis());
    }
    public static void main(String[] args) throws ParseException {
        //当前0点
        System.out.println(new TestUtil().getTimesmorning());
        //当前24点
        System.out.println(new TestUtil().getTimesnight());
        // 当前时间
        System.out.println(System.currentTimeMillis());
        //当前0点
        System.out.println(new TestUtil().getDayBegin());
        // 当天0点
        System.out.println(new TestUtil().getDayBeginTimestamp());
        // 当前时间
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date()));
        System.out.println(Calendar.getInstance().getTime());
        // 当前时间
        System.out.println(new Timestamp(System.currentTimeMillis()));

    }

}