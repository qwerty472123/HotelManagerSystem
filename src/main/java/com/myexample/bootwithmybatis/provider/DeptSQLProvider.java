package com.myexample.bootwithmybatis.provider;

import com.myexample.bootwithmybatis.entity.Dept;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

public class DeptSQLProvider {
    public String createUpdateSQL(Dept dept) {
        return new SQL(){{
            UPDATE("dept");
            if(null != dept.getDeptName() || !dept.getDeptName().equals("")) {
                SET("deptName = #{deptName}");
            }
            if(null != dept.getStatus()) {
                SET("status = #{status}");
            }
            WHERE("deptid = #{deptId}");
        }}.toString();
    }
    public String createSelectSQL(Dept dept) {
        return new SQL(){{
            SELECT("deptId, deptName, status");
            FROM("dept");
            if(dept != null) {
                if(null != dept.getDeptId()) {
                    WHERE("deptId = #{deptId}");
                }
                if(null != dept.getDeptName() && !dept.getDeptName().equals("")) {
                    WHERE("deptName = #{deptName}");
                }
                if(null != dept.getStatus()) {
                    WHERE("status = #{status}");
                }
            }
        }}.toString();
    }
}
