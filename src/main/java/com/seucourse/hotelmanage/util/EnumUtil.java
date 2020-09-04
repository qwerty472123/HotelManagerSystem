package com.seucourse.hotelmanage.util;

public class EnumUtil {
    public static String getRoleDesc(Integer id) {
        switch (id) {
            case 0:
                return "guest";
            case 1:
                return "front";
            case 2:
                return "back";
            case 3:
                return "manager";
            default:
                return "error";
        }
    }
}
