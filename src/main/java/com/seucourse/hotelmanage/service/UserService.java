package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.User;

public interface UserService {
    Integer login(User user);
    Integer register(User user);
}
