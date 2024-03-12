package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.BookmarkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BookmarkApiControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BookmarkService bookmarkService;

    @InjectMocks
    private BookmarkApiController bookmarkApiController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookmarkApiController).build();
    }

    @Test
    void showBookmarkAll() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/bookmark/9")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(bookmarkService).getAllBookmark(eq(9L), eq(9L));
    }

    @Test
    void showSpotPlanListByPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/bookmark/8/SpotPlan/1")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(bookmarkService).getPlanSpotBookmark(eq(8L), eq(9L),eq(1L));
    }

    @Test
    void showTourListByPage() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(get("/api/bookmark/5/tour/3")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(bookmarkService).getTourBookmark(eq(5L), eq(9L),eq(3L));
    }

    @Test
    void postBookmark() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(post("/api/bookmark/5")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(bookmarkService).addBookMark(eq(5L), eq(9L));
    }

    @Test
    void deleteBookmark() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("id", 9L); // 세션 속성 설정

        mockMvc.perform(delete("/api/bookmark/5")
                        .session(session) // MockMvc에 세션 설정
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isOk());

        verify(bookmarkService).deleteBookmark(eq(5L), eq(9L));
    }
}