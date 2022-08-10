package com.example.client.controller;

import com.example.client.model.User;
import com.example.client.util.MailUtil;
import com.example.client.util.RedisUtil;
import com.example.client.util.ResponseVo;
import com.example.client.util.SendSms;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jidongfang
 */
@CrossOrigin
@RestController
@RequestMapping("send")
public class SendMsg {
  @Autowired
  RedisUtil redisUtil;
  @Autowired
  SendSms sendSms;
  @PostMapping("sendEmail")
  public ResponseVo sendEmail(@RequestBody User user){
    //创建6位随机验证码
    String code = Random();
    redisUtil.set(user.getPhone(),code,60);
    MailUtil.sendMail("807733833@qq.com","测试给你发送一下","你的手机号"+
        user.getPhone()+",验证码" +code);
    return new ResponseVo();
  }

  @PostMapping("sendMsg")
  public ResponseVo sendMsg(@RequestBody User user){
    //创建6位随机验证码
    String code = Random();
    redisUtil.set(user.getPhone(),code,300);
    sendSms.sendTextMessage(user.getPhone(),code);
    MailUtil.sendMail("807733833@qq.com","测试给你发送一下","你的手机号"+
        user.getPhone()+",验证码" +code);
    return new ResponseVo();
  }

  public static String Random() {
    Random random = new Random();
    String rad = String.valueOf(random.nextInt(Integer.MAX_VALUE));
    return rad.substring(rad.length() - 6);
  }
}
