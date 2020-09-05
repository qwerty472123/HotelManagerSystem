package com.seucourse.hotelmanage.mapper;

import com.seucourse.hotelmanage.entity.Room;
import com.seucourse.hotelmanage.provider.RoomSQLProvider;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RoomMapper {
    @Select("SELECT id, name, clean, type FROM room WHERE id NOT IN (" +
            "SELECT roomId FROM conflict WHERE date BETWEEN #{startDate} AND #{endDate}" +
            ") AND type = #{type} LIMIT 1")
    Room selectRoomByTypeAndTime(String type, Date startDate, Date endDate);

    @Insert("INSERT INTO room(name, clean, type) VALUES (#{name}, #{clean}, #{type})")
    @SelectKey(keyColumn = "id", keyProperty = "id", before = false,
            statement = "SELECT LAST_INSERT_ID()", resultType = Integer.class)
    void insertRoom(Room room);

    @SelectProvider(type = RoomSQLProvider.class, method = "createSelectSQL")
    List<Room> selectRoom(Room room);

    @UpdateProvider(type = RoomSQLProvider.class, method = "createUpdateSQL")
    void updateRoom(Room room);

    @Delete("DELETE FROM room WHERE id = #{roomId}")
    void deleteRoom(Integer roomId);

    @Select("SELECT DISTINCT type FROM room")
    List<String> selectTypes();

    @Select("SELECT id, name, clean, type FROM room WHERE id = #{roomId}")
    Room selectRoomByRoomId(Integer roomId);
}
