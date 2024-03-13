package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Login;
import com.example.triponezidoapi.dto.Member;
import com.example.triponezidoapi.dto.request.RequestFind;
import com.example.triponezidoapi.dto.request.RequestFindId;
import com.example.triponezidoapi.dto.request.RequestNewPassword;
import com.example.triponezidoapi.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberApiControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberApiController memberApiController;

    //LocalDateTime을 직렬화 및 역직렬화하기 위한 코드
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberApiController).build();
    }

    @Test
    @DisplayName("회원가입시 보안질문 조회")
    void questionsInfo() throws Exception {

        mockMvc.perform(get("/api/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(memberService).getSecurityQuestions();
    }
    @Test
    @DisplayName("회원가입")
    void signupMember() throws Exception {
        Member member = new Member();
        member.setId(0L); // 예시로 0으로 설정
        member.setName("테스트");
        member.setLoginId("test02");
        member.setPassword("testkitir!123");
        member.setPasswordCheck("testkitir!123");
        member.setQuestion(5L);
        member.setAnswer("테스트");
        member.setEmail("kitri@kitri.com");
        member.setPhoneNumber("010-1111-1111");
        member.setAddress("테스트용 주소");
        member.setBirth(LocalDateTime.of(1991,1,12,8,24));
        member.setGender("남");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(member);

        mockMvc.perform(post("/api/member/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andDo(print())
                .andExpect(status().isOk());

        verify(memberService).signUp(any(Member.class));
    }

    @Test
    @DisplayName("아이디 중복확인")
    void signupCheckLoginId() throws Exception {

        mockMvc.perform(post("/api/member/signup/test")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andDo(print())
                .andExpect(status().isOk());

        verify(memberService).isUsingMemberId(eq("test"));
    }

    @Test
    @DisplayName("로그인")
    void loginMember() throws Exception {
        Login login = new Login();
        login.setLoginId("username");
        login.setPassword("password");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/api/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk());
//                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("아이디 찾기")
    void checkId() throws Exception {
        RequestFindId requestFindid = new RequestFindId();
        requestFindid.setName("박준혁");
        requestFindid.setEmail("kitri@kitri.com");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestFindid);

        mockMvc.perform(post("/api/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("비밀번호 찾기 - 비밀번호를 찾을 회원번호 조회")
    void checkPw() throws Exception {
        RequestFind requestFind = new RequestFind();
        requestFind.setName("박준혁");
        requestFind.setEmail("kitri@kitri.com");
        requestFind.setLoginId("test02");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestFind);

        mockMvc.perform(post("/api/member/check/pw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("비밀번호 찾기 - 선택한 보안 질문 조회")
    void checkQuestion() throws Exception {

        mockMvc.perform(get("/api/member/check/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(memberService).getQuestion(eq(9L));
    }

    @Test
    @DisplayName("비밀번호 찾기 - 보안 답변 전송")
    void answerQuestion() throws Exception {

        mockMvc.perform(get("/api/member/check/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(memberService).checkAnswer(eq(9L),eq("보안질문 답변 테스트"));

    }

    @Test
    @DisplayName("비밀번호 찾기 - 비밀번호 재설정")
    void setNewPassword() throws Exception {
        RequestNewPassword requestNewPassword = new RequestNewPassword();
        requestNewPassword.setNowPassword("Test123!@");
        requestNewPassword.setChangePassword("Test123!@new");
        requestNewPassword.setChangePasswordCheck("Test123!@new");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestNewPassword);

        mockMvc.perform(put("/api/member/passwd/9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk());

        verify(memberService).updatePw(9L,requestNewPassword);
    }

    @Test
    @DisplayName("회원 정보 조회")
    void showMemberInfo() throws Exception {
    }

    @Test
    @DisplayName("회원 정보 수정")
    void updateMemberInfo() throws Exception {
    }

    @Test
    @DisplayName("프로필 사진 변경")
    void putProfile() throws Exception {
    }

    @Test
    @DisplayName("비밀번호 변경")
    void updatePassword() throws Exception {
    }

    @Test
    @DisplayName("회원 탈퇴")
    void removeMember() throws Exception {
    }
}