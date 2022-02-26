package com.example.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "server-client", contextId = "TestServicesApis")
public interface TestServicesApis {

  @PostMapping("/test/get")
  public String get(@RequestParam("name") String name);
}
