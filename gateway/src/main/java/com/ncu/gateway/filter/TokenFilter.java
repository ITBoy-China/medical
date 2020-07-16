package com.ncu.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.ncu.common.utils.JwtUtil;
import com.ncu.gateway.config.FilterWhiteList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * @author : 城南有梦
 * @date : 2020-07-10 15:24:17
 * @description:
 * token拦截器
 */
@Component
public class TokenFilter implements GlobalFilter, Ordered {
    @Autowired
    private FilterWhiteList filterWhiteList;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取request和response对象
        ServerHttpRequest request =  exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //从gateway过的 请求头带一个值
        exchange.getRequest().mutate().header("from","gateway").build();

        //遍历白名单
        for(String item:filterWhiteList.getAllowPaths()){
            if(request.getURI().getPath().startsWith(item)){
                //白名单直接放行
                return chain.filter(exchange);
            }
        }

        //如果是 /out/api/patients请求 同时是post请求 放行
        String path = request.getURI().getPath();
        HttpMethod method = request.getMethod();
        if( method!=null && path.startsWith("/out/api/patients") && method.matches("POST")){
            //放行
            return chain.filter(exchange);
        }

        //从header中拿到token
        String token = request.getHeaders().getFirst("access_token");
        JSONObject jsonObject = new JSONObject();
        //验证
        try {
            //验证token 拿到 token中的用户名和id
            String idAndName = JwtUtil.validToken(token);
            //将id和name 放入请求的header里
            Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                try {
                    httpHeader.set("user_id", URLEncoder.encode(idAndName.split("-")[0],"UTF-8").toString());
                    httpHeader.set("user_name",URLEncoder.encode(idAndName.split("-")[1],"UTF-8").toString());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            };
            ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
            exchange.mutate().request(serverHttpRequest).build();
        } catch (Exception e) {
            //验证失败
            jsonObject.put("code","401");
            jsonObject.put("flag",false);
            jsonObject.put("message", "请求非法，不允许直接访问");
            jsonObject.put("data","");
            byte[] data = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(data);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
        //放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
