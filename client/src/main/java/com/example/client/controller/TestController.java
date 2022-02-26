package com.example.client.controller;

import com.example.client.service.TestServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

  @Autowired
  private TestServices testServices;

  @RequestMapping("/get")
  public String get(@RequestParam("name") String name) {
    System.out.println("参数"+name);
    return testServices.get(name);
  }
}
