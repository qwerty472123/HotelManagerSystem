package com.seucourse.hotelmanage.provider;

import com.seucourse.hotelmanage.entity.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSQLProvider {
    public String createUpdateSQL(User user) {
        return new SQL() {{
            UPDATE("user");
            if (null != user.getName() && !user.getName().equals("")) {
                SET("name = #{name}");
            }
            if (null != user.getUsername() && !user.getUsername().equals("")) {
                SET("username = #{username}");
            }
            if (null != user.getPassword() && !user.getPassword().equals("")) {
                SET("password = #{password}");
            }
            if (null != user.getRole()) {
                SET("role = #{role}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    public String createSelectSQL(User user) {
        return new SQL() {{
            SELECT("id, username, password, role, name");
            FROM("user");
            if (user != null) {
                if (null != user.getId()) {
                    WHERE("id = #{id}");
                }
                if (null != user.getName() && !user.getName().equals("")) {
                    WHERE("name = #{name}");
                }
                if (null != user.getUsername() && !user.getUsername().equals("")) {
                    WHERE("username = #{username}");
                }
                if (null != user.getPassword() && !user.getPassword().equals("")) {
                    WHERE("password = #{password}");
                }
                if (null != user.getRole()) {
                    WHERE("role = #{role}");
                }
            }
        }}.toString();
    }
}
