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
            if (null != order.getUserId()){
                WHERE("userId = #{userId}");
            }
            if (null != order.getStatus()){
                WHERE("status = #{status}");
            }
            ORDER_BY("status, startDate");
        }}.toString();
    }
}
