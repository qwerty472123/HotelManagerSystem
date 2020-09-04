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

    @Select("SELECT id FROM User WHERE id = #{UserId} ")
    void selectIdByUserId(Integer UserId);

    @Insert("INSERT INTO user(username, password, role, name) VALUES (#{username}, #{password}, #{role}, #{name})")
    void insertUser(User user);

    @SelectProvider(type = UserSQLProvider.class, method = "createUpdateSQL")
    void updateUser(User user);
}
