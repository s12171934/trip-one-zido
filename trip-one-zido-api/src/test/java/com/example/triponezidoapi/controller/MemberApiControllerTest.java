package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

class MemberApiControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberApiController memberApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberApiController).build();
    }

    @Test
    void questionsInfo() {
    }

    @Test
    void signupMember() {
    }

    @Test
    void signupCheckLoginId() {
    }

    @Test
    void loginMember() {
    }

    @Test
    void checkId() {
    }

    @Test
    void checkPw() {
    }

    @Test
    void checkQuestion() {
    }

    @Test
    void answerQuestion() {
    }

    @Test
    void setNewPassword() {
    }

    @Test
    void showMemberInfo() {
    }

    @Test
    void updateMemberInfo() {
    }

    @Test
    void putProfile() {
    }

    @Test
    void updatePassword() {
    }

    @Test
    void removeMember() {
    }
}