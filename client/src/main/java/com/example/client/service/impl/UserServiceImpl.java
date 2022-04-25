package com.example.client.service.impl;

import com.example.client.enums.Message;
import com.example.client.mapper.UserMapper;
import com.example.client.model.User;
import com.example.client.model.UserExample;
import com.example.client.service.UserService;
import com.example.client.util.MD5Util;
import com.example.client.util.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @Override
    public ResponseVo createUser(User user) {
        ResponseVo responseVo = new ResponseVo();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPhoneEqualTo(user.getPhone());
        int count = userMapper.countByExample(userExample);
        if (count > 0) {
            responseVo.setCode(Message.FAIL.getCode());
            responseVo.setMessage(Message.ERROR_PHONE_OCCUPY.getMessage());
            return responseVo;
        }
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setPassword(MD5Util.md5(user.getPassword()));
        try {
            userMapper.insert(user);
            responseVo.setCode(Message.OK.getCode());
            responseVo.setMessage(Message.OK.getMessage());
        } catch (Exception e) {
            responseVo.setCode(Message.FAIL.getCode());
            responseVo.setMessage(Message.ERROR_DB_EXECTION.getMessage());
        }

        return responseVo;
    }

    @Override
    public User login(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andPasswordEqualTo(MD5Util.md5(user.getPassword()))
                .andPhoneEqualTo(user.getPhone());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() > 0) {
            return users.get(0);
        }
        throw new RuntimeException(Message.FAIL.getMessage());
    }
}
