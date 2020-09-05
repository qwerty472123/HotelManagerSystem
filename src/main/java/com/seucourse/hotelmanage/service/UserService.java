package com.seucourse.hotelmanage.service;

import com.seucourse.hotelmanage.entity.User;

import java.util.List;

public interface UserService {
    Integer login(User user);
    Integer register(User user);
    User getUser(Integer userId);
    List<User> listUsers(User user);

    String updateUser(User user);
    String deleteUserByUserId(Integer userId);
}
