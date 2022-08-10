package com.example.client.model;

import lombok.Data;

import java.util.Date;
/**
 * @author jidongfang
 */
@Data
public class User {
    private Integer userid;

    private String username;

    private String password;

    private Date createTime;

    private Date updateTime;

    private String phone;

    private String verificationCode;

    private String token;
}