package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.provider.UserSQLProvider;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @SelectProvider(type = UserSQLProvider.class, method = "createSelectSQL")
    User selectUser(User user);

    @Delete("DELETE FROM user WHERE id = #{id}")
    void deleteUser(User user);

    @Select("SELECT id, username, password, role, name FROM user WHERE id = #{userId} ")
    User selectUserByUserId(Integer userId);

    @Insert("INSERT INTO user(username, password, role, name) VALUES (#{username}, #{password}, #{role}, #{name})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertUser(User user);

    @SelectProvider(type = UserSQLProvider.class, method = "createUpdateSQL")
    void updateUser(User user);
}
