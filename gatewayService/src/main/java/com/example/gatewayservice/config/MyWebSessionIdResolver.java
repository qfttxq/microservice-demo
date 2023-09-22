package com.example.gatewayservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.session.CookieWebSessionIdResolver;

import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
public class MyWebSessionIdResolver extends CookieWebSessionIdResolver {
    @Override
    public List<String> resolveSessionIds(ServerWebExchange exchange) {
        MultiValueMap<String, HttpCookie> cookieMap = exchange.getRequest().getCookies();
        List<HttpCookie> cookies = cookieMap.get(getCookieName());
        if (cookies == null) {
            return Collections.emptyList();
        }
        return cookies.stream().map(HttpCookie::getValue).map(this::base64Decode).collect(Collectors.toList());
    }

    private String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        } catch (Exception ex) {
            log.debug("Unable to Base64 decode value: " + base64Value);
            return null;
        }
    }
}
