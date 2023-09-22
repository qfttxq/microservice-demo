package com.example.warehouseservice;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class MyTest {
    @Test
    public void test1(){
        //Assert.isTrue(1==2,"fail");
        Assert.hasText("1","no message");
    }
}
