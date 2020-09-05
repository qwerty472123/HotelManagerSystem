package com.seucourse.hotelmanage.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class TimeUtil {
    private static Date date = null;
    public static Date getCurrentDate() {
        if(date == null ){
            try {
                date = DateFormat.getDateInstance().parse(DateFormat.getDateInstance().format(new Date()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Date(date.getTime());
    }
    public static void changeDate(int delta) {
        date = new Date(date.getTime() + 3600 * 24 * 1000 * delta);
    }
}
