package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.SearchService;
import com.example.triponezidoapi.service.SnsLoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SnsLoginController.class)
class SnsLoginControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SnsLoginService snsLoginService;

    @Test
    @DisplayName("네이버 연동")
    void naverLogin() throws Exception {

        String code = "네이버의 임의의 인증코드";
        String state = "네이버의 무작위의 상태값";
        String id = "testNaver";

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(snsLoginService.naverLogin(code, state)).willReturn(id);

        mockMvc.perform(get("/api/social/naver_callback")
                        .param("code", code)
                        .param("state", state))
                //응답코드 3xx대로 리디렉션 관련. 응답OK인 200과는 다름
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:3000/social/naver/" + id))
                .andDo(print());

        verify(snsLoginService).naverLogin(eq(code),eq(state));
    }

    @Test
    @DisplayName("카카오 연동")
    void kakoLogin() throws Exception {

        String code = "카카오의 임의의 인가코드";
        String id = "testKakao";

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(snsLoginService.kakaoLogin(code)).willReturn(id);

        mockMvc.perform(get("/api/social/kakao_login")
                        .param("code", code))
                //응답코드 3xx대로 리디렉션 관련. 응답OK인 200과는 다름
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:3000/social/kakao/" + id))
                .andDo(print());

        verify(snsLoginService).kakaoLogin(eq(code));
    }
}