package com.example.triponezidoapi.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setValues(String data) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyMMddHH");
        String key = LocalDateTime.now().format(timeFormatter);
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        zSetOperations.incrementScore(key,data,1);
        redisTemplate.expire(key,6, TimeUnit.HOURS);
    }

    public ArrayList<Object> getValues() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyMMddHH");
        String key = LocalDateTime.now().minusHours(1).format(timeFormatter);
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        return new ArrayList<>(zSetOperations.reverseRange(key,0,9));
    }
}
