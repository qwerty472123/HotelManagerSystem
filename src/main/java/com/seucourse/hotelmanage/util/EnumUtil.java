package com.seucourse.hotelmanage.util;

import java.util.ArrayList;
import java.util.List;

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
    public static String getOrderStatusCN(Integer id) {
        switch (id) {
            case 0:
                return "已入住";
            case 1:
                return "已预约";
            case 2:
                return "已退房";
            default:
                return "未知";
        }
    }
    public static String getGenderDescCN(Integer id) {
        switch (id) {
            case 0:
                return "男";
            case 1:
                return "女";
            default:
                return "未知";
        }
    }
}
