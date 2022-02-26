package com.example.client.service.impl;

import com.example.client.service.TestServices;
import org.springframework.stereotype.Service;

/**
 * @author jidongfang
 */
@Service

public class TestServiceImpl implements TestServices {
  @Override
  public String get(String name) {
    System.out.println("进来了");
    return "参数name：" + name;
  }
}
