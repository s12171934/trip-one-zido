package com.example.triponezidoapi;

import com.example.triponezidoapi.member.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

    @SpringBootTest
    class TripOneZidoApiApplicationTests {
        @Autowired
        MemberMapper memberMapper;

        @Test
    void contextLoads() {
    }
}
