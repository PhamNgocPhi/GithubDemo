package com.systena.githupdemo.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateUtils {


    /**
     * convert time with pattern to milliseconds
     * ex : 2018/12/20 to 123456789
     */
    public static long convertTimeToMilliseconds(String time, String pattern) {
        try {
            Calendar c = Calendar.getInstance(Locale.getDefault());
            c.setTime(new SimpleDateFormat(pattern, Locale.getDefault()).parse(time));
            return c.getTimeInMillis();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

//    public static int updatedWithinLast30Days(String time, String pattern) {
//        Calendar c = Calendar.getInstance();
//        long currentTimeMillis = c.getTimeInMillis();
//        long oneMonthInMillis = 2592000000l;
//        long updatedInMillis = convertTimeToMilliseconds(time, pattern);
//
//        return currentTimeMillis - oneMonthInMillis <= createdInMillis && createdInMillis <= currentTimeMillis;
//    }
}
