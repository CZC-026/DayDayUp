package com.example._2023_04_20;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;

@RunWith(SpringRunner.class)
@SpringBootTest
class ApplicationTests {
    @Autowired
    private StaticService staticService;
    @Autowired
    private DynamciService dynamciService;

    @Test
    public void testStaticProxy() {
        staticService.execute();
    }

    @Test
    public void testDynamicProxy() {
        dynamciService.execute();
    }

}
