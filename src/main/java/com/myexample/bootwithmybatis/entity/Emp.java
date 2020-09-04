package com.myexample.bootwithmybatis.entity;

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

    private Integer empId;

    private String empName;

    private String gender;

    private Date birthday;

    private Date hireDate;

    private Integer deptId;

    private Integer job;

    private Integer status;

    private Integer empPhoto;
}
