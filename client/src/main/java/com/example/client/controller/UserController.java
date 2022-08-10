package com.example.client.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.client.enums.Message;
import com.example.client.model.User;
import com.example.client.service.UserService;
import com.example.client.util.JwtUtil;
import com.example.client.util.RedisUtil;
import com.example.client.util.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("createUser")
    public ResponseVo createUser(@RequestBody User user) {
        ResponseVo responseVo = new ResponseVo();
        String code = (String) redisUtil.get(user.getPhone());
        if (StringUtils.isBlank(code)){
            responseVo.setMessage(Message.ERROR_NOT_VERIFICATION.getMessage());
            responseVo.setCode(Message.ERROR_NOT_VERIFICATION.getCode());
            return responseVo;
        }else if(!code.equals(user.getVerificationCode())){
            responseVo.setMessage(Message.ERROR_VERIFICATION_CODE.getMessage());
            responseVo.setCode(Message.ERROR_VERIFICATION_CODE.getCode());
            return responseVo;
        }
        return userService.createUser(user);
    }

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("login")
    public ResponseVo login(@RequestBody User user) {
        ResponseVo responseVo = new ResponseVo();
        try {
            User userDb = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("name", userDb.getUsername());
            payload.put("password", userDb.getPassword());
            String token = JwtUtil.getToken(payload);
            responseVo.setCode(Message.OK.getCode());
            responseVo.setMessage(Message.OK.getMessage());
            User loginUser = new User();
            loginUser.setToken(token);
            loginUser.setUserid(userDb.getUserid());
            loginUser.setUsername(userDb.getUsername());
            responseVo.setData(loginUser);
        } catch (Exception e) {
            responseVo.setCode(Message.FAIL.getCode());
            responseVo.setMessage(e.getMessage());
        }
        return responseVo;
    }


    @PostMapping("selectUserList")
    public ResponseVo selectUserList(@RequestBody String token) {
        ResponseVo responseVo = new ResponseVo();
        try {
            JwtUtil.verify(token);
            responseVo.setCode(Message.OK.getCode());
            responseVo.setMessage(Message.OK.getMessage());
        } catch (SignatureVerificationException e) {
            responseVo.setMessage(Message.ERROR_TOKEN_SIGN.getMessage());
        } catch (TokenExpiredException e) {
            responseVo.setMessage(Message.ERROR_TOKEN_EXPIRE.getMessage());
        } catch (AlgorithmMismatchException e) {
            responseVo.setMessage(Message.ERROR_TOKEN_ALGORITHMMISMATCH.getMessage());
        } catch (Exception e) {
            responseVo.setMessage(Message.ERROR_TOKEN_EXECTION.getMessage());
        }
        responseVo.setCode(Message.FAIL.getCode());
        return responseVo;
    }
}
