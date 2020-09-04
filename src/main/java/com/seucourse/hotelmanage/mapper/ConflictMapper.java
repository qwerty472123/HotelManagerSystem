package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Conflict;
import com.seucourse.hotelmanage.entity.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConflictMapper {
    @Select("SELECT COUNT(*)" +
            " FROM conflict WHERE date = #{date} AND roomId = #{roomId}")
    int countConflict(Conflict conflict);

    @Delete("DELETE FROM conflict WHERE date = #{date} AND roomId = #{roomId}")
    void deleteConflict(Conflict conflict);

    @Insert("INSERT INTO conflict(roomId, date) VALUES (#{roomId}, #{date})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertConflict(Conflict conflict);
}
