package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("회원가입시 보안질문 조회")
    void questionsInfo() {
    }

    @Test
    @DisplayName("회원가입")
    void signupMember() {
    }

    @Test
    @DisplayName("아이디 중복확인")
    void signupCheckLoginId() {
    }

    @Test
    @DisplayName("로그인")
    void loginMember() {
    }

    @Test
    @DisplayName("아이디 찾기")
    void checkId() {
    }

    @Test
    @DisplayName("비밀번호 찾기 - 비밀번호를 찾을 회원번호 조회")
    void checkPw() {
    }

    @Test
    @DisplayName("비밀번호 찾기 - 선택한 보안 질문 조회")
    void checkQuestion() {
    }

    @Test
    @DisplayName("비밀번호 찾기 - 보안 답변 전송")
    void answerQuestion() {
    }

    @Test
    @DisplayName("비밀번호 찾기 - 비밀번호 재설정")
    void setNewPassword() {
    }

    @Test
    @DisplayName("회원 정보 조회")
    void showMemberInfo() {
    }

    @Test
    @DisplayName("회원 정보 수정")
    void updateMemberInfo() {
    }

    @Test
    @DisplayName("프로필 사진 변경")
    void putProfile() {
    }

    @Test
    @DisplayName("비밀번호 변경")
    void updatePassword() {
    }

    @Test
    @DisplayName("회원 탈퇴")
    void removeMember() {
    }
}