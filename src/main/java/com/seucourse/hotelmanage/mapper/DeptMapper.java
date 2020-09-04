package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Dept;
import com.seucourse.hotelmanage.provider.DeptSQLProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Insert("INSERT INTO dept(deptname, status) VALUES (#{deptName}, #{status})")
    void insertDept(Dept dept);

    @Select("SELECT deptid, deptname, status FROM dept WHERE deptid = #{deptId}")
    @Results(id = "withEmps", value = {
            @Result(id = true, column = "deptid", property = "deptId"),
            @Result(column = "deptname", property = "deptName"),
            @Result(column = "status", property = "status"),
            @Result(column = "deptId", property = "empList",
                    many = @Many(select = "com.seucourse.hotelmanage.mapper.EmpMapper.selectEmpsByDeptId",
                            fetchType = FetchType.LAZY))
    })
    Dept selectDeptByDeptId(Integer deptId);

    @SelectProvider(type = DeptSQLProvider.class, method = "createSelectSQL")
    List<Dept> selectAllDepts(Dept dept);

    @UpdateProvider(type = DeptSQLProvider.class, method = "createUpdateSQL")
    void updateDept(Dept dept);

    @Delete("DELETE FROM dept WHERE deptid = #{deptId}")
    void deleteDeptByDeptId(Integer deptId);
}
