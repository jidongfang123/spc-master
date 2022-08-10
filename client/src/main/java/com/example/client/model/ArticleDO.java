package com.example.client.model;

import java.util.Date;
import lombok.Data;

@Data
public class ArticleDO {
    private Integer id;

    private String text;

    private String createUser;

    private Integer createUserid;

    private Integer falg;

    private Date cerateTime;

    private Date updateTime;

    private Integer currentPage =1;

    private Integer pageSize =5;


    /**
     * 该字段为非业务字段。Mybatis配置文件需要要到该字段，方便进行insertOrUpdate操作
     */
    private int count;

}