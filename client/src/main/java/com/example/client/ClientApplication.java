package com.example.client;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author jidongfang
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.example.client.mapper")
public class ClientApplication extends WebMvcConfigurerAdapter {

  public static void main(String[] args) {
    SpringApplication.run(ClientApplication.class, args);
  }
  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**")
        .allowCredentials(true)
        .allowedHeaders("*")    //允许任何头
        .allowedOrigins("*")    //允许任何域名
        .allowedMethods("*");   //允许任何方法
  }

}
