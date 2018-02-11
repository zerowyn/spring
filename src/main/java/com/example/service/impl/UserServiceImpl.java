package com.example.service.impl;

import com.example.mapper.pojo.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUser() {
        return new User("zhangsan",19);
    }
}
