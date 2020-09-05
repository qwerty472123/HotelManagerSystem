package com.seucourse.hotelmanage.provider;

import com.seucourse.hotelmanage.entity.Order;
import org.apache.ibatis.jdbc.SQL;

public class OrderSQLProvider {
    public String createSelectSQL(Order order){
        return new SQL(){{
            SELECT("id, roomId, userId, startDate, endDate, status");
            FROM("`order`");
            if (null !=order.getId()){
                WHERE("id = #{id}");
            }
            if (null != order.getRoomId()){
                WHERE("roomId = #{roomId}");
            }
            if (null != order.getStartDate()){
                WHERE("startDate = #{startDate}");
            }
            if (null != order.getEndDate()){
                WHERE("endDate = #{endDate}");
            }
            if (null != order.getUserId()){
                WHERE("userId = #{userId}");
            }
            if (null != order.getStatus()){
                WHERE("status = #{status}");
            }
            ORDER_BY("status, startDate");
        }}.toString();
    }

    public String createUpdateSQL(Order order){
        return new SQL(){{
            UPDATE("`order`");
            if (null != order.getRoomId()){
                SET("roomId = #{roomId}");
            }
            if (null != order.getUserId()){
                SET("userId = #{userId}");
            }
            if (null != order.getStartDate()){
                SET("startDate = #{startDate}");
            }
            if (null != order.getEndDate()){
                SET("endDate = #{endDate}");
            }
            if (null != order.getStatus()){
                SET("status = #{status}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
}
