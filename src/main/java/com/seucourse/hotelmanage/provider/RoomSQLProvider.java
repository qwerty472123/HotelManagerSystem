package com.seucourse.hotelmanage.provider;

import com.seucourse.hotelmanage.entity.Room;
import org.apache.ibatis.jdbc.SQL;

public class RoomSQLProvider {
    public String createUpdateSQL(Room room) {
        return new SQL() {{
            UPDATE("room");
            if (null != room.getName() && !room.getName().equals("")) {
                SET("name = #{name}");
            }
            if (null != room.getClean()) {
                SET("clean = #{clean}");
            }
            if (null != room.getType() && !room.getType().equals("")) {
                SET("type = #{type}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    public String createSelectSQL(Room room) {
        return new SQL() {{
            SELECT("id, name, clean, type");
            FROM("room");
            if (room != null) {
                if (null != room.getId()) {
                    WHERE("id = #{id}");
                }
                if (null != room.getName() && !room.getName().equals("")) {
                    WHERE("name = #{name}");
                }
                if (null != room.getClean()) {
                    WHERE("clean = #{clean}");
                }
                if (null != room.getType() && !room.getType().equals("")) {
                    WHERE("type = #{type}");
                }
            }
        }}.toString();
    }
}
