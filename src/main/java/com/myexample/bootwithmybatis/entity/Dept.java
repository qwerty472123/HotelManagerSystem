package com.myexample.bootwithmybatis.entity;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dept {

    private Integer deptId;

    private String deptName;

    private Integer status;

    private List<Emp> empList;
}
