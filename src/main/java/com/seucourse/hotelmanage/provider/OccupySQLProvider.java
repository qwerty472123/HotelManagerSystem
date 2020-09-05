package com.seucourse.hotelmanage.provider;

import com.seucourse.hotelmanage.entity.Occupy;
import com.seucourse.hotelmanage.entity.User;
import org.apache.ibatis.jdbc.SQL;

public class OccupySQLProvider {
    public String createUpdateSQL(Occupy occupy) {
        return new SQL(){{
            UPDATE("occupy");
            if (null != occupy.getOrderId()) {
                SET("orderId = #{orderId}");
            }
            if(null != occupy.getName() && !occupy.getName().equals("")) {
                SET("username = #{username}");
            }
            if(null != occupy.getCertId() && !occupy.getCertId().equals("")) {
                SET("password = #{password}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }
    public String createSelectSQL(Occupy occupy) {
        return new SQL(){{
            SELECT("id, roomId, name, certId");
            FROM("occupy");
            if(occupy != null) {
                if (null != occupy.getOrderId()) {
                    WHERE("orderId = #{orderId}");
                }
                if(null != occupy.getName() && !occupy.getName().equals("")) {
                    WHERE("username = #{username}");
                }
                if(null != occupy.getCertId() && !occupy.getCertId().equals("")) {
                    WHERE("password = #{password}");
                }
            }
        }}.toString();
    }
}
