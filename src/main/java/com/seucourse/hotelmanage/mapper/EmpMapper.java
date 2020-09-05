package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Emp;
import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.provider.EmpSQLProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface EmpMapper {

    @Insert("INSERT INTO emp(userId, hireDate, birthday, gender, phone) " +
            "VALUES (#{userId}, #{hireDate} ,#{birthday} ,#{gender} ,#{phone})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertEmp(Emp emp);

    @Delete("DELETE FROM emp WHERE id = #{id} ")
    void deleteEmp(Integer id);

    @SelectProvider(type = EmpSQLProvider.class,  method = "createSelectSQL")
    @Results(id = "withUsers", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "userId", property = "userId"),
            @Result(column = "hireDate", property = "hireDate"),
            @Result(column = "birthday", property = "birthday"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "phone", property = "phone"),
            @Result(column = "userId", property = "user", one = @One(select = "com.seucourse.hotelmanage.mapper.UserMapper.selectUserByUserId",
                    fetchType = FetchType.LAZY))
    })
    List<Emp> selectEmp(Emp emp);

    @UpdateProvider(type = EmpSQLProvider.class, method = "createUpdateSQL")
    void updateEmp(Emp emp);

}
