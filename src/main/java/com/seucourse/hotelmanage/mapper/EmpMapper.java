package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMapper {
    @Insert("INSERT INTO emp(empname, gender, birthday, hiredate, deptid, job, status, empphoto) " +
            "VALUES (#{empName}, #{gender} ,#{birthday} ,#{hireDate} ,#{deptId} ,#{job} ,#{status} ,#{empPhoto} ")
    @SelectKey(keyColumn = "empid", keyProperty = "empId", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertEmp(Emp emp);

    @Select("SELECT empid, empname, gender, birthday, hiredate, deptid, job, status, empphoto" +
            " FROM emp WHERE deptid = #{deptId}")
    List<Emp> selectEmpsByDeptId(Integer deptId);
}
