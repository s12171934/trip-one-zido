package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestGood;
import com.example.triponezidoapi.service.ContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContentApiController.class)
class ContentApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContentService contentService;

    @Test
    @DisplayName("게시글 고정 등록")
    void postPin() throws Exception {
        mockMvc.perform(post("/api/content/pin/{id}", 9L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(contentService).addPin(9L, 9L);
    }

    @Test
    @DisplayName("게시글 고정 취소")
    void deletePin() throws Exception {
        mockMvc.perform(delete("/api/content/pin/{id}", 9L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(contentService).deletePin(9L, 9L);
    }

    @Test
    @DisplayName("좋아요 여부 조회")
    void getGood() throws Exception {
        mockMvc.perform(get("/api/content/good/{id}", 9L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());
        verify(contentService).isGood(9L, 9L);
    }

    @Test
    @DisplayName("좋아요 등록/수정/취소")
    void postGood() throws Exception {
        RequestGood requestGood = new RequestGood();
        requestGood.setGood(true);
        requestGood.setMemberId(9L);
        requestGood.setContentId(1L);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // requestGood 객체를 JSON 문자열로 변환
        String jsonGood = objectMapper.writeValueAsString(requestGood);

        mockMvc.perform(post("/api/content/good/{id}", 9L)
                        .sessionAttr("id", 9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonGood)
                )
                .andExpect(status().isOk())
                .andDo(print());
        verify(contentService).addGood(9L, 9L, requestGood);
    }

    @Test
    @DisplayName("최근 본 게시물 조회")
    void showRecentView() throws Exception {
        mockMvc.perform(get("/api/content/recent-view/{page}", 0)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());
        verify(contentService).getRecentView(9L, 0);
    }
}