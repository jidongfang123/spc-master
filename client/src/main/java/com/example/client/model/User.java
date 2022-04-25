package com.example.client.model;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    private Integer useid;

    private String username;

    private String password;

    private Date createTime;

    private Date updateTime;

    private String phone;
}