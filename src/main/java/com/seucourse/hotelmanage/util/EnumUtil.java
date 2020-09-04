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
    public static String getRoleDescCN(Integer id) {
        switch (id) {
            case 0:
                return "来宾";
            case 1:
                return "前台";
            case 2:
                return "后勤";
            case 3:
                return "经理";
            default:
                return "未知";
        }
    }
}
