package com.seucourse.hotelmanage.util;

import java.util.Date;

public class TimeUtil {
    private static Date date = new Date();
    public static Date getCurrentDate() {
        return date;
    }
    public static void changeDate(int delta) {
        date = new Date(date.getTime() + 3600 * 24 * 1000 * delta);
    }
}
