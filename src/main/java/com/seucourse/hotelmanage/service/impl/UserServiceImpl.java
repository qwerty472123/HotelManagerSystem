package com.seucourse.hotelmanage.service.impl;

import com.seucourse.hotelmanage.entity.User;
import com.seucourse.hotelmanage.mapper.UserMapper;
import com.seucourse.hotelmanage.service.UserService;
import com.seucourse.hotelmanage.util.PasswordEncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer login(User user) {
        User userInDB = userMapper.selectUser(User.builder().username(user.getUsername()).build());
        if (userInDB == null) return -1; // no such user
        if (!PasswordEncryptUtil.check(user.getPassword(), userInDB.getPassword())) return -2; // error pwd
        user.setRole(userInDB.getRole());
        user.setId(userInDB.getId());
        user.setName(userInDB.getName());
        return 0;
    }

    /**
     * register
     * @param user role must set by server
     * @return status code
     */
    @Override
    public Integer register(User user) {
        User tmp = userMapper.selectUser(User.builder().username(user.getUsername()).build());
        if (tmp != null) return -1; // has same name
        if (user.getName() == null || user.getName().length() == 0) return -2; // No name
        if (user.getUsername() == null || user.getUsername().length() < 3) return -3; // Too short username
        if (user.getPassword() == null || user.getPassword().length() < 3) return -4; // Too short password
        user.setPassword(PasswordEncryptUtil.encrypt(user.getPassword()));
        userMapper.insertUser(user);
        return 0;
    }

    @Override
    public User getUser(Integer userId) {
        return userMapper.selectUser(User.builder().id(userId).build());
    }
}
