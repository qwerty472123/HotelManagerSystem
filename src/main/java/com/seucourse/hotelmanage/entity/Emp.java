package com.seucourse.hotelmanage.entity;

import com.seucourse.hotelmanage.util.EnumUtil;
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

    public String getGenderDesc() {
        return EnumUtil.getGenderDescCN(gender);
    }
}
