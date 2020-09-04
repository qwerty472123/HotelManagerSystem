package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Room;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface RoomMapper {
    @Select("SELECT id, name, clean, type FROM room WHERE id NOT IN (" +
            "SELECT roomId FROM conflict WHERE date BETWEEN #{startDate} AND #{endDate}" +
            ") AND type = #{type}")
    Room getRoomByTypeAndTime(String type, Date startDate, Date endDate);
}
