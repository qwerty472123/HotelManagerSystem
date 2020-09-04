package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Conflict;
import com.seucourse.hotelmanage.entity.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConflictMapper {
    @Select("SELECT COUNT(*)" +
            " FROM conflict WHERE date = #{date} AND roomId = #{roomId}")
    int countConflict(Conflict conflict);

    @Delete("DELETE FROM conflict WHERE date = #{date} AND roomId = #{roomId}")
    void deleteConflict(Conflict conflict);

    @Insert("INSERT INTO conflict(roomid, date) VALUES (#{roomId}, #{date})")
    void insertConflict(Conflict conflict);
}
