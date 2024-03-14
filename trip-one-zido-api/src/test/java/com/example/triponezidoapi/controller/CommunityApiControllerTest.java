package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestCommunity;
import com.example.triponezidoapi.dto.request.RequestCommunitySearch;
import com.example.triponezidoapi.service.CommunityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommunityApiController.class)
class CommunityApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    @Test
    @DisplayName("커뮤니티 목록 조회")
    void showCommunityAll() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/community/list/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(communityService).getCommunityList(eq(9L));
    }

    @Test
    @DisplayName("커뮤니티 상세 조회")
    void showCommunityDetail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/community/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(communityService).getCommunity(eq(9L), eq(9L));
    }

//    @Test
//    @DisplayName("커뮤니티 게시물 등록")
//    void postCommunity() throws Exception {
//        MockHttpSession session = new MockHttpSession();
//        session.setAttribute("id", 9L); // 세션 속성 설정
//
//        RequestCommunity requestCommunity = new RequestCommunity();
//        requestCommunity.setId(9L);
//        requestCommunity.setTitle("될꺼야");
//        requestCommunity.setStartDate(null);
//        requestCommunity.setEndDate(null);
//        requestCommunity.setNotice("테스트");
//        requestCommunity.setTotal(2);
//        requestCommunity.setLocCategory("서울특별시");
//        requestCommunity.setDeadline(null);
//        requestCommunity.setStatus("모집중");
//
//        // ObjectMapper 객체 생성
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // member 객체를 JSON 문자열로 변환
//        String jsonMember = objectMapper.writeValueAsString(requestCommunity);
//
//        mockMvc.perform(post("/api/community/")
//                        .session(session) // MockMvc에 세션 설정
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonMember))
//                .andExpect(status().isOk());
//
//        verify(communityService).addCommunity(any(RequestCommunity.class), eq(9L));
//    }

    @Test
    @DisplayName("커뮤니티 게시물 삭제")
    void deleteCommunity() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(delete("/api/community/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(communityService).deleteCommunity(eq(9L));
    }

    @Test
    @DisplayName("검색한 커뮤니티 게시물 목록")
    void searchCommunity() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        RequestCommunitySearch requestCommunitySearch = new RequestCommunitySearch();
        requestCommunitySearch.setPage(0);
        requestCommunitySearch.setType("writer");
        requestCommunitySearch.setKeyword("mshan");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestCommunitySearch);

        mockMvc.perform(post("/api/community/search/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk());

        verify(communityService).getCommunityListWithSearch(any(), eq(9L));
    }

    @Test
    @DisplayName("커뮤니티 참여하기")
    void entryCommunity() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(post("/api/community/member/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(communityService).addOwner(eq(9L), eq(9L));
    }

    @Test
    @DisplayName("커뮤니티 참여 취소하기")
    void departureCommunity() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(delete("/api/community/member/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(communityService).deleteOwner(eq(9L), eq(9L));
    }
}