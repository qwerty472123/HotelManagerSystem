package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.Room;

import java.util.Date;
import java.util.List;

public interface RoomService {
    void addRoom(Room room);

    List<Room> listRoom(Room room);

    void updateRoom(Room room);

    List<String> listRoomTypes();

    Room getRoomByTypeAndTime(String type, Date startDate, Date endDate);

    Room getRoomByCheckTime(Integer id, Date checkStart, Date checkEnd);

    void deleteRoom(Integer roomId);
}
