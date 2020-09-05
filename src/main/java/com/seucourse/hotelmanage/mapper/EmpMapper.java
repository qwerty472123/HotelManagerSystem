package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.provider.EmpSQLProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Insert("INSERT INTO emp(userid, hiredate, birthday, gender, phone) " +
            "VALUES (#{userId}, #{hireDate} ,#{birthday} ,#{gender} ,#{phone}")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertEmp(Emp emp);

    @Delete("UPDATE emp WHERE id = #{id} ")
    void deleteEmpByEmpId(Integer id);

    @SelectProvider(type = EmpSQLProvider.class,  method = "createSelectAllSQL")
    List<Emp> selectAllEmps(Emp emp);

    @UpdateProvider(type = EmpSQLProvider.class, method = "createUpdateSQL")
    void updateEmp(Emp emp);

    @Select("SELECT id, userid, hiredate, birthday, gender, phone" +
            " FROM emp WHERE id = #{id}")
    @Results(id = "withUsers", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "userid", property = "userId"),
            @Result(column = "hiredate", property = "hireDate"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "id", property = "user", one = @One(select = "com.seucourse.hotelmanage.mapper.UserMapper.selectIdByUserId",
                    fetchType = FetchType.LAZY))
    })
    Emp selectEmpByEmpId(Integer id);
}
