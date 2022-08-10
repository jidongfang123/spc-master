package com.example.client.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
  /**
   * 允许跨域
   * @param registry registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        //.allowedOrigins("*")  //注意这个变动项，这个是旧版
        .allowedOriginPatterns("*")//这个才是新版的要求
        .allowedMethods("GET","HEAD","POST","PUT","DELETE","OPTIONS")
        .allowCredentials(true)
        .maxAge(3600)
        .allowedHeaders("*");
  }
}
