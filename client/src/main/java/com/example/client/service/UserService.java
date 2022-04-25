package com.example.client.service;

import com.example.client.model.User;
import com.example.client.util.ResponseVo;

public interface UserService {
    ResponseVo createUser(User user);

    User login(User user);
}
