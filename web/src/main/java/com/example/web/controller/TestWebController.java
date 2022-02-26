package com.example.web.controller;

import com.example.client.api.TestServicesApis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestWebController {

  @Autowired
  private TestServicesApis testServicesApis;

  @RequestMapping("/getTest")
  public String getTest(@RequestParam("name") String name) {
    return testServicesApis.get(name);
  }
}
