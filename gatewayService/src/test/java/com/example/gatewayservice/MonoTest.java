package com.example.gatewayservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

@Slf4j
public class MonoTest {

    @Test
    public void test1(){
        Mono<String> abc = Mono.just("abc");
        log.info("abc:{}",abc);
    }
}
