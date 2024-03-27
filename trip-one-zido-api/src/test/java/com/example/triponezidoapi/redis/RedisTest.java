package com.example.triponezidoapi.redis;

import com.example.triponezidoapi.search.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisService redisService;

    @BeforeEach
    void shutDown() {
        redisService.setValues("success");
        redisService.setValues("success");
        redisService.setValues("success");
        redisService.setValues("abc");
        redisService.setValues("abc");
        redisService.setValues("123");
    }
    @Test
    void getKey() {
        ArrayList<Object> set = redisService.getValues();

        Assertions.assertEquals(new ArrayList<>(List.of("success","abc","123")), set);
    }
    @Test
    void expire() throws InterruptedException {
        ArrayList<Object> set = redisService.getValues();
        Assertions.assertEquals(new ArrayList<>(List.of("success","abc","123")),set);
        Thread.sleep(4000);
        set = redisService.getValues();
        Assertions.assertEquals(new ArrayList<>(List.of("success","abc","123")),set);
    }

}
