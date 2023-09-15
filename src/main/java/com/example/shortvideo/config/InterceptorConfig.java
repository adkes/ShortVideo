package com.example.shortvideo.config;

import com.example.shortvideo.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * .
 *
 * @author 985892345
 * 2022/12/12 23:27
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new TokenInterceptor())
      .addPathPatterns("/**") // 拦截所有请求
      .excludePathPatterns("/error")
      .excludePathPatterns("/user/**"); // 排除 /user/** 以下的请求
  }
}
