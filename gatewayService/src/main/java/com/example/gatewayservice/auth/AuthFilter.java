package com.example.gatewayservice.auth;


import com.example.gatewayservice.common.Result;
import com.example.gatewayservice.config.GateWayConfig;
import com.example.gatewayservice.util.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String ACCESS_TOKEN = "access_token";
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GateWayConfig config;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        List<String> whiteList = config.getWhiteList();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        String path = request.getURI().getPath();
        for (String uri : whiteList) {
            boolean match = pathMatcher.match(uri, path);
            if (match) {
                return chain.filter(exchange);
            }
        }
        //进行session验证
        try {
            WebSession session = exchange.getSession().toFuture().get();
            Object user = session.getAttribute("user");
            if (user != null) {
                return chain.filter(exchange);
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
        }


        //进行token验证
        String accessToken = request.getHeaders().getFirst(ACCESS_TOKEN);
        Result result = JwtUtils.verify2(accessToken);
        if (result.getCode() == 200) {
            return chain.filter(exchange);
        } else {
            DataBuffer data = null;
            try {
                data = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(result));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
            return response.writeWith(Mono.just(data));
        }

    }

    @Override
    public int getOrder() {
        return 99;
    }
}
