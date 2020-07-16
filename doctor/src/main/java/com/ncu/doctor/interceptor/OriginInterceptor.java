package com.ncu.doctor.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : 城南有梦
 * @date : 2020-07-12 17:06:36
 * @description:
 * 自定义拦截器 拦截不是来自网关的
 */
@Component
public class OriginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取头部信息
        String origin = request.getHeader("from");
        if(origin==null){
            return false;
        }else if(origin.equals("gateway")){
            return true;
        }else{
            return false;
        }
    }
}
