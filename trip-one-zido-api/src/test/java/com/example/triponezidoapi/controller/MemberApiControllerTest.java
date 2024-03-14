package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.Login;
import com.example.triponezidoapi.dto.Member;
import com.example.triponezidoapi.dto.request.RequestFind;
import com.example.triponezidoapi.dto.request.RequestFindId;
import com.example.triponezidoapi.dto.request.RequestNewPassword;
import com.example.triponezidoapi.dto.request.RequestPhoto;
import com.example.triponezidoapi.dto.response.ResponseQuestions;
import com.example.triponezidoapi.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

        verify(memberService).getSecurityQuestions();

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

        verify(memberService).signUp(eq(member));
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

        verify(memberService).isUsingMemberId(eq("test01"));
    }

    @Test
    @DisplayName("로그인")
    void loginMember() throws Exception {

        Login login = new Login("test01","string1");
        //request에 담긴 세션정보를 서비스에서 확인하여 처리
        MockHttpServletRequest request = new MockHttpServletRequest();

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(memberService.login(login,request)).willReturn(
                false
        );

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(login);

        mockMvc.perform(post("/api/member/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andExpect(content().string("false"))
                .andDo(print());

        verify(memberService).login(eq(login), ArgumentMatchers.any());
    }

    @Test
    @DisplayName("아이디 찾기")
    void checkId() throws Exception {
        RequestFindId requestFindid = new RequestFindId("박준혁","kitri@kitri.com");

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(memberService.getLoginId(requestFindid)).willReturn(
                "jhpark"
        );

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestFindid);

        mockMvc.perform(post("/api/member/check/id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                //Body에 String
                .andExpect(content().string("jhpark"))
                .andDo(print());

        verify(memberService).getLoginId(eq(requestFindid));
    }


    @Test
    @DisplayName("비밀번호 찾기 - 비밀번호를 찾을 회원번호 조회")
    void checkPw() throws Exception {
        RequestFind requestFind = new RequestFind("박준혁","kitri@kitri.com","test02");

        given(memberService.getId(requestFind)).willReturn(
                7L
        );

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestFind);

        mockMvc.perform(post("/api/member/check/pw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                //Body에 Long
                .andExpect(content().string("7"))
                .andDo(print());

        verify(memberService).getId(eq(requestFind));
    }

    @Test
    @DisplayName("비밀번호 찾기 - 선택한 보안 질문 조회")
    void checkQuestion() throws Exception {

        given(memberService.getQuestion(9L)).willReturn(
                "졸업한 초등학교는?"
        );

        mockMvc.perform(get("/api/member/check/{id}", 9L))
                .andExpect(status().isOk())
                .andExpect(content().string("졸업한 초등학교는?"))
                .andDo(print());

        verify(memberService).getQuestion(eq(9L));
    }

    @Test
    @DisplayName("비밀번호 찾기 - 보안 답변 전송")
    void answerQuestion() throws Exception {

        given(memberService.checkAnswer(9L,"화정초")).willReturn(
                true
        );

        mockMvc.perform(post("/api/member/check/{id}",9L)
                        .param("answer","화정초"))
                .andExpect(status().isOk())
                //Body에 ture 혹은 false
                .andExpect(content().string("true"))
                .andDo(print());

        verify(memberService).checkAnswer(eq(9L),eq("화정초"));

    }

    @Test
    @DisplayName("비밀번호 찾기 - 비밀번호 재설정")
    void setNewPassword() throws Exception {
        RequestNewPassword requestNewPassword = new RequestNewPassword("Test123!@","Test123!@new","Test123!@new");

        given(memberService.updatePw(9L,requestNewPassword)).willReturn(
                true
        );

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestNewPassword);

        mockMvc.perform(put("/api/member/passwd/{id}",9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());

        verify(memberService).updatePw(eq(9L),eq(requestNewPassword));
    }

    @Test
    @DisplayName("회원 정보 조회")
    void showMemberInfo() throws Exception {
        Member member = new Member(9L,"회원1","mmmm1234","Mmmm123!",null,4L,"서울",
                "m1@naver.com","010-0000-0000","경기도",
                LocalDateTime.of(2020,02,02,0,0,0),"여");

        given(memberService.getMember(9L)).willReturn(
                member
        );

        mockMvc.perform(get("/api/member/")
                        .sessionAttr("id",9L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.loginId").exists())
                .andExpect(jsonPath("$.password").exists())
                .andExpect(jsonPath("$.passwordCheck").doesNotExist())
                .andExpect(jsonPath("$.question").exists())
                .andExpect(jsonPath("$.answer").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.phoneNumber").exists())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.birth").exists())
                .andExpect(jsonPath("$.gender").exists())
                .andDo(print());

        verify(memberService).getMember(eq(9L));

    }

    @Test
    @DisplayName("회원 정보 수정")
    void updateMemberInfo() throws Exception {

        Member member = new Member(9L,"회원변경","mmmm1234","Mmmm123!",null,4L,"정보수정",
                "m1@naver.com","010-1234-5678","경기도",
                LocalDateTime.of(2020,02,02,0,0,0),"남");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(member);

        mockMvc.perform(put("/api/member/")
                        .sessionAttr("id",9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andDo(print());

        verify(memberService).updateMember(eq(9L),eq(member));
    }

    @Test
    @DisplayName("프로필 사진 변경")
    void putProfile() throws Exception {
        //생성자 작성시, 실제 service 코드에서 빨간줄
        RequestPhoto requestPhoto = new RequestPhoto();
        requestPhoto.setContentId(125);
        requestPhoto.setPhoto(null);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestPhoto);

        mockMvc.perform(put("/api/member/profile")
                        .sessionAttr("id",9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andDo(print());

        verify(memberService).updateProfile(eq(9L),eq(requestPhoto));
    }

    @Test
    @DisplayName("비밀번호 변경")
    void updatePassword() throws Exception {
        RequestNewPassword requestNewPassword = new RequestNewPassword("Test123!@","Test123!@new","Test123!@new");

        given(memberService.updatePw(9L,requestNewPassword)).willReturn(
                true
        );

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestNewPassword);

        mockMvc.perform(put("/api/member/password")
                        .sessionAttr("id",9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());

        verify(memberService).updatePw(eq(9L),eq(requestNewPassword));
    }

    @Test
    @DisplayName("회원 탈퇴")
    void removeMember() throws Exception {

        mockMvc.perform(delete("/api/member/")
                        .sessionAttr("id",9L))
                .andExpect(status().isOk())
                .andDo(print());

        verify(memberService).deleteMember(eq(9L));

    }
}