package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Login;
import com.example.triponezidoapi.dto.Member;
import com.example.triponezidoapi.dto.request.RequestFind;
import com.example.triponezidoapi.dto.request.RequestFindId;
import com.example.triponezidoapi.dto.request.RequestNewPassword;
import com.example.triponezidoapi.dto.response.ResponseQuestions;
import com.example.triponezidoapi.dto.response.ResponseTour;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(MemberApiController.class)
class MemberApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    //LocalDateTime을 직렬화 및 역직렬화하기 위한 코드
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    @DisplayName("회원가입시 보안질문 조회")
    void questionsInfo() throws Exception {
        List<ResponseQuestions> questions = new ArrayList<>();
        questions.add(new ResponseQuestions(1L, "당신이 태어난 곳은?"));
        questions.add(new ResponseQuestions(2L, "졸업한 초등학교 이름은?"));
        questions.add(new ResponseQuestions(3L, "당신의 별명은?"));
        questions.add(new ResponseQuestions(4L, "부모님의 고향은?"));
        questions.add(new ResponseQuestions(5L, "가족구성원은?"));

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(memberService.getSecurityQuestions()).willReturn(
                questions
        );

        mockMvc.perform(get("/api/member/signup"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[2].id").exists())
                .andExpect(jsonPath("$[3].id").exists())
                .andExpect(jsonPath("$[4].id").exists())
                .andExpect(jsonPath("$[5].id").doesNotExist())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        //응답 객체 확인
        for (ResponseQuestions question : questions) {
            System.out.println(question);
        }
    }
    @Test
    @DisplayName("회원가입")
    void signupMember() throws Exception {
        Member member = new Member(0L, "테스트", "test02","testkitri!123","testkitri!123",
                5L,"테스트","kitri@kitri.com","010-1111-1111","테스트용주소",
                LocalDateTime.of(1991,1,12,8,24),"남");

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

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(memberService.isUsingMemberId("test01")).willReturn(
                true
        );

        mockMvc.perform(post("/api/member/signup/{loginId}", "test01"))
                .andExpect(status().isOk())
                //Body = true 일때 세팅
                .andExpect(content().string("true"))
                .andDo(print());

        verify(memberService).isUsingMemberId("test01");
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