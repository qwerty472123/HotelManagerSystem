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
public class Emp {
    private Integer id;
    private Integer userId;
    private Date hireDate;
    private Date birthday;
    private Integer gender; // 0 - male; 1 - female
    private String phone;
    private User user;
}
