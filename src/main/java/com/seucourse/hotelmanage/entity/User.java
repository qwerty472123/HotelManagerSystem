package com.seucourse.hotelmanage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer role; // guest / frontEmp / backEmp / manager
    private String name;
}
