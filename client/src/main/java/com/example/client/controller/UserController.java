package com.example.client.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.client.model.User;
import com.example.client.service.UserService;
import com.example.gateways.uitl.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("addUser")
    public String createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            User userDb = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("name", userDb.getUsername());
            payload.put("password", userDb.getPassword());
            String token = JwtUtil.getToken(payload);
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }


    @PostMapping("selectUserList")
    public Map<String, Object> selectUserList(@RequestBody String  token) {
        System.out.println(token);
        Map<String, Object> map = new HashMap<>();
        try {
            JwtUtil.verify(token);
            map.put("state", true);
            map.put("msg", "验证通过");
        } catch (SignatureVerificationException e) {
            map.put("msg", "无效签名");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token以过期");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法验证失败");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "验证异常");
        }
        map.put("state", false);
        return map;
    }
}
