package com.ncu.drug.config;

import com.ncu.drug.interceptor.OriginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : 城南有梦
 * @date : 2020-07-12 17:14:43
 * @description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private OriginInterceptor originInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(originInterceptor)
                .addPathPatterns("/**");
    }
}
