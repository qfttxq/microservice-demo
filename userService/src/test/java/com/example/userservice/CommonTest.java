package com.example.userservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Slf4j
public class CommonTest {
    @Test
    public void test1(){
       long l= 1L ^ (1L << 5L);
       log.info("v:{}",l);
       log.info("v1:{}",-1 ^ 32);
    }
    @Test
    public void test2(){
        log.info("v:{}", new Random().nextInt(31));
        log.info("v:{}", new Random().nextInt(31));
    }
}
