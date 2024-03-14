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
        ResponsePhoto photo = new ResponsePhoto();
        photo.setContentId(1L);
        photo.setPhoto(new byte[]{0,0});
        photos.add(photo);

        List<ResponseMember> members = new ArrayList<>();
        ResponseMember member = new ResponseMember();
        member.setId(9L);
        member.setLoginId("member");
        member.setOwn("writer");
        member.setProfile(null);
        member.setFollow(true);
        members.add(member);

        List<ResponseComment> comments = new ArrayList<>();
        ResponseComment comment = new ResponseComment();
        comment.setId(1L);
        comment.setComment("굿");
        comment.setMemberId(11L);
        comment.setContentId(1L);
        comment.setType("comment");
        comment.setCreatedAt(LocalDateTime.of(2024, 3, 14, 23, 59));
        comments.add(comment);

        ResponseSpotDetail spotDetail = new ResponseSpotDetail();
        spotDetail.setId(1L);
        spotDetail.setCategory("음식점");
        spotDetail.setStartDate(LocalDateTime.of(2024, 3, 14, 0, 0));
        spotDetail.setEndDate(LocalDateTime.of(2024, 3, 14, 23, 59));
        spotDetail.setLocCategory("서울");
        spotDetail.setAddress("서울");
        spotDetail.setReview("후기");
        spotDetail.setGrade(5);
        spotDetail.setViewCount(1);
        spotDetail.setGoodCount(1);
        spotDetail.setMyGood(1);
        spotDetail.setBookmarkCount(1);
        spotDetail.setMyBookmark(1);
        spotDetail.setTitle("장소");
        spotDetail.setPublic(true);
        spotDetail.setCreatedAt(LocalDateTime.of(2024, 3, 14, 0, 0));
        spotDetail.setProfile(new byte[]{0, 0});
        spotDetail.setPhotos(photos);
        spotDetail.setMembers(members);
        spotDetail.setComments(comments);
        spotDetail.setMine(true);

        given(spotService.spotDetail(1L, 9L )).willReturn(
                spotDetail
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
        RequestSpot requestSpot = new RequestSpot();
        requestSpot.setId(1L);
        requestSpot.setTitle("제목");
        requestSpot.setPublic(true);
        requestSpot.setStartDate(LocalDateTime.of(2024, 3, 14, 0, 0));
        requestSpot.setEndDate(LocalDateTime.of(2024, 3, 14, 23, 59));
        requestSpot.setCategory("음식점");
        requestSpot.setLocCategory("서울특별시");
        requestSpot.setAddress("서울시");
        requestSpot.setReview("후기");
        requestSpot.setGrade(5);
        requestSpot.setProfile(1);
        requestSpot.setPhotos(null);
        requestSpot.setMembers(new ArrayList<>(){{add(1L); add(2L);}});

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
        RequestSpot requestSpot = new RequestSpot();
        requestSpot.setId(1L);
        requestSpot.setTitle("제목");
        requestSpot.setPublic(true);
        requestSpot.setStartDate(LocalDateTime.of(2024, 3, 14, 0, 0));
        requestSpot.setEndDate(LocalDateTime.of(2024, 3, 14, 23, 59));
        requestSpot.setCategory("음식점");
        requestSpot.setLocCategory("서울특별시");
        requestSpot.setAddress("서울시");
        requestSpot.setReview("후기");
        requestSpot.setGrade(5);
        requestSpot.setProfile(1);
        requestSpot.setPhotos(null);
        requestSpot.setMembers(new ArrayList<>(){{add(1L); add(2L);}});

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // requestSpot 객체를 JSON 문자열로 변환
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
