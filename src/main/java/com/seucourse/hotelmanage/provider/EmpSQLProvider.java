package com.seucourse.hotelmanage.provider;

import com.seucourse.hotelmanage.entity.Emp;
import org.apache.ibatis.jdbc.SQL;

public class EmpSQLProvider {
    public String createUpdateSQL(Emp emp) {
        // userid, hiredate, birthday, gender, phone
        return new SQL() {{
            UPDATE("emp");
            if (null != emp.getUserId()) {
                SET("userid = #{userId}");
            }
            if (null != emp.getBirthday()) {
                SET("birthday = #{birthday}");
            }
            if (null != emp.getGender()) {
                SET("gender = #{gender}");
            }
            if (null != emp.getHireDate()) {
                SET("hiredate = #{hireDate}");
            }
            if (null != emp.getPhone()) {
                SET("phone = #{phone}");
            }
            WHERE("id = #{id}");
        }}.toString();
    }

    public String createSelectSQL(Emp emp) {
        return new SQL() {{
            SELECT("id, userId, hireDate, birthday, gender, phone");
            FROM("emp");
            if (null != emp.getId()) {
                WHERE("id = #{id}");
            }
            if (null != emp.getUserId()) {
                WHERE("userid = #{userId}");
            }
            if (null != emp.getBirthday()) {
                WHERE("birthday = #{birthday}");
            }
            if (null != emp.getHireDate()) {
                WHERE("hireDate = #{hireDate}");
            }
            if (null != emp.getGender()) {
                WHERE("gender = #{gender}");
            }
            if (null != emp.getPhone()) {
                WHERE("phone = #{phone}");
            }
        }}.toString();
    }
}
