package com.example.client.service.impl;

import com.example.client.mapper.UserMapper;
import com.example.client.model.User;
import com.example.client.model.UserExample;
import com.example.client.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public String addUser(User user) {
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        userMapper.insert(user);
        return null;
    }
}
