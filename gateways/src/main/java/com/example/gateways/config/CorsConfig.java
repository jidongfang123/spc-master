package com.example.gateways.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * 跨域配置
 */
@Configuration
public class CorsConfig implements WebFluxConfigurer{
        /**
         * 开启跨域
         */
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            // 设置允许跨域的路由
            registry.addMapping("/**")
                    // 设置允许跨域请求的域名
                    .allowedOrigins("*")
                    // 是否允许证书（cookies）
                    .allowCredentials(true)
                    // 设置允许的方法
                    .allowedMethods("*")
                    // 跨域允许时间
                    .maxAge(3600);
        }


    /**
         * 跨域过滤器
         * @return
         */
        @Bean
        public CorsWebFilter corsWebFilter() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.addAllowedOrigin("*");
            corsConfiguration.addExposedHeader(HttpHeaders.SET_COOKIE);
            UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
            corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
            return new CorsWebFilter(corsConfigurationSource);
        }
}
