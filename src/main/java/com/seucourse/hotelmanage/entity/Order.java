package com.seucourse.hotelmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Integer id;
    private Integer roomId;
    private Integer userId;
    private Date startDate, endDate;
    private Integer status; //0-occupy 1-被预定 2-空闲

    private Room room;
    private String username;
}
