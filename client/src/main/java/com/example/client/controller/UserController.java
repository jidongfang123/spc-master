package com.example.client.controller;

import com.example.client.model.User;
import com.example.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public String createUser(@RequestBody User user){
       return userService.addUser(user);
    }
}
