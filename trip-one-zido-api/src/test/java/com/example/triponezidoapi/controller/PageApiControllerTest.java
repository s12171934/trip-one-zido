package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.PageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageApiController.class)
class PageApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PageService pageService;

    @Test
    @DisplayName("회원 페이지 조회")
    void showMemberPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/page/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).getMemberPage(eq(9L), eq(9L));
    }

    @Test
    @DisplayName("회원 페이지 일정 게시물 더보기 조회")
    void showPlanListByPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/page/9/plan/0")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).getPlanListByPage(eq(9L), eq(9L), eq(0L));
    }

    @Test
    @DisplayName("회원 페이지 장소 게시물 더보기 조회")
    void showSpotListByPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/page/9/spot/0")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).getSpotListByPage(eq(9L), eq(9L), eq(0L));
    }

    @Test
    @DisplayName("팔로잉 명단")
    void listFollowing() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/page/following/9/0")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).getFollowingList(eq(9L), eq(9L), eq(0L));
    }

    @Test
    @DisplayName("팔로워 명단")
    void listFollower() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/page/follower/9/0")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).getFollowerList(eq(9L), eq(9L), eq(0L));
    }

    @Test
    @DisplayName("팔로우")
    void followingMember() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(post("/api/page/follow/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).follow(eq(9L), eq(9L));
    }

    @Test
    @DisplayName("언팔로우")
    void unfollowMember() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(delete("/api/page/follow/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(pageService).unFollow(eq(9L), eq(9L));
    }
}