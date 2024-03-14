package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestSpot;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.SpotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SpotApiController.class)
class SpotApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SpotService spotService;

    //LocalDateTime을 직렬화 및 역직렬화하기 위한 코드
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    @DisplayName("장소 게시글 조회 테스트")
    void showSpotDetail() throws Exception {
        List<ResponsePhoto> photos = new ArrayList<>();
        photos.add(new ResponsePhoto(1L, new byte[]{0,0}));
        photos.add(new ResponsePhoto(1L, new byte[]{1,1}));

        List<ResponseMember> members = new ArrayList<>();
        members.add(new ResponseMember(9L, "member1", "writer", null, true));
        members.add(new ResponseMember(10L, "member2", "with", null, true));

        List<ResponseComment> comments = new ArrayList<>();
        comments.add(new ResponseComment(1L, "멋지네요", 11L, 1L, "comment", LocalDateTime.of(2024, 3, 14, 23, 59)));
        comments.add(new ResponseComment(2L, "굿", 12L, 1L, "comment", LocalDateTime.of(2024, 3, 14, 23, 59)));

        ResponseSpotDetail responseSpotDetail = new ResponseSpotDetail(1L, LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59), "서울", "후기", 5, 1,
        1, 1, 1, 1, "장소", true, LocalDateTime.of(2024, 3, 14, 0, 0),
        new byte[]{0, 0},  photos,  members, comments, true);

        given(spotService.spotDetail(1L, 9L )).willReturn(
                responseSpotDetail
        );

        mockMvc.perform(get("/api/spot/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                .andExpect(jsonPath("$.review").exists())
                .andExpect(jsonPath("$.grade").exists())
                .andExpect(jsonPath("$.viewCount").exists())
                .andExpect(jsonPath("$.goodCount").exists())
                .andExpect(jsonPath("$.myGood").exists())
                .andExpect(jsonPath("$.bookmarkCount").exists())
                .andExpect(jsonPath("$.myBookmark").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.public").exists())
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.profile").exists())
                .andExpect(jsonPath("$.photos").exists())
                .andExpect(jsonPath("$.members").exists())
                .andExpect(jsonPath("$.comments").exists())
                .andExpect(jsonPath("$.mine").exists())
                .andDo(print());

        verify(spotService).spotDetail(1L, 9L);
    }

    @Test
    @DisplayName("장소 게시글 등록 테스트")
    void postSpot() throws Exception {
        RequestSpot requestSpot = new RequestSpot(1L, "제목", true, LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59), "음식점", "서울특별시", "서울시", "후기", 5, 1, null, null);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // requestPlan 객체를 JSON 문자열로 변환
        String jsonSpot = objectMapper.writeValueAsString(requestSpot);

        mockMvc.perform(post("/api/spot/")
                        .sessionAttr("id", 9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSpot)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(spotService).addSpot(requestSpot, 9L);
    }

    @Test
    @DisplayName("장소 게시글 수정 테스트")
    void putSpot() throws Exception {
        RequestSpot requestSpot = new RequestSpot(1L, "제목", true, LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59), "음식점", "서울특별시", "서울시", "후기", 5, 1, null, null);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // requestPlan 객체를 JSON 문자열로 변환
        String jsonSpot = objectMapper.writeValueAsString(requestSpot);

        mockMvc.perform(put("/api/spot/{id}", 1L)
                        .sessionAttr("id", 9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonSpot)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(spotService).updateSpot(1L, requestSpot, 9L);
    }

    @Test
    @DisplayName("장소 게시글 삭제 테스트")
    void deleteSpot() throws Exception {
        mockMvc.perform(delete("/api/spot/{id}", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(spotService).deleteSpot(9L);
    }
}