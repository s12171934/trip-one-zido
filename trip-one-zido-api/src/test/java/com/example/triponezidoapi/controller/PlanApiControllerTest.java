package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestPlan;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.PlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@WebMvcTest(PlanApiController.class)
class PlanApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    PlanService planService;

    //LocalDateTime을 직렬화 및 역직렬화하기 위한 코드
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Test
    @DisplayName("일정 게시글 상세 조회 테스트")
    void showPlanDetail() throws Exception{

        List<ResponseSpotPlan> SpotPlans = new ArrayList<>();
        ResponseSpotPlan spotPlan = new ResponseSpotPlan();
        spotPlan.setId(1L);
        spotPlan.setTitle("덕수궁");
        spotPlan.setStartDate(LocalDateTime.of(2024, 3, 14, 0, 0));
        spotPlan.setEndDate(LocalDateTime.of(2024, 3, 14, 23, 59));
        SpotPlans.add(spotPlan);

        List<ResponseMember> members = new ArrayList<>();
        ResponseMember member = new ResponseMember();
        member.setId(9L);
        member.setLoginId("member");
        member.setOwn("writer");
        member.setProfile(null);
        member.setFollow(true);
        members.add(member);

        List<ResponseComment> comments = new ArrayList<>();
        ResponseComment responseComment = new ResponseComment();
        responseComment.setId(1L);
        responseComment.setComment("굿");
        responseComment.setMemberId(11L);
        responseComment.setContentId(1L);
        responseComment.setType("comment");
        responseComment.setCreatedAt(LocalDateTime.of(2024, 3, 14, 23, 59));

        ResponsePlanDetail planDetail = new ResponsePlanDetail();
        planDetail.setId(1L);
        planDetail.setStartDate(LocalDate.of(2024, 3, 14));
        planDetail.setEndDate(LocalDate.of(2024, 3, 14));
        planDetail.setLocCategory("서울");
        planDetail.setStatus("여행중");
        planDetail.setReview("후기");
        planDetail.setGrade(1);
        planDetail.setViewCount(2);
        planDetail.setGoodCount(1);
        planDetail.setMyGood(true);
        planDetail.setBookmarkCount(1);
        planDetail.setMyBookmark(1);
        planDetail.setTitle("서울여행");
        planDetail.setVisibility(true);
        planDetail.setCreatedAt(LocalDateTime.of(2024, 3, 14, 0, 0));
        planDetail.setMembers(members);
        planDetail.setComments(comments);
        planDetail.setMine(true);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(planService.getPlan(1L, 9L )).willReturn(
                planDetail
        );


        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        mockMvc.perform(get("/api/plan/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                .andExpect(jsonPath("$.status").exists())
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
                .andExpect(jsonPath("$.spotPlans").exists())
                .andExpect(jsonPath("$.members").exists())
                .andExpect(jsonPath("$.comments").exists())
                .andExpect(jsonPath("$.mine").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());
        //verify : 해당 객체의 메소드가 실행됐는지 체크
        verify(planService).getPlan(1L, 9L);
    }

    @Test
    @DisplayName("일정 게시글 등록 테스트")
    void postPlan() throws Exception {
        RequestPlan requestPlan = new RequestPlan();
        requestPlan.setId(1L);
        requestPlan.setTitle("제목");
        requestPlan.setVisibility(true);
        requestPlan.setStartDate(LocalDate.of(2024, 3, 14));
        requestPlan.setEndDate(LocalDate.of(2024, 3, 14));
        requestPlan.setLocCategory("서울특별시");
        requestPlan.setStatus(0);
        requestPlan.setReview("후기");
        requestPlan.setGrade(5);
        requestPlan.setProfile(new byte[]{0, 0});

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // requestPlan 객체를 JSON 문자열로 변환
        String jsonPlan = objectMapper.writeValueAsString(requestPlan);

        mockMvc.perform(post("/api/plan/")
                        .sessionAttr("id", 9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPlan)
                       )
                .andExpect(status().isOk())
                .andDo(print());

        verify(planService).addPlan(9L, requestPlan);
    }

    @Test
    @DisplayName("일정 게시물 수정 테스트")
    void putPlan() throws Exception {
        RequestPlan requestPlan = new RequestPlan();
        requestPlan.setId(1L);
        requestPlan.setTitle("제목");
        requestPlan.setVisibility(true);
        requestPlan.setStartDate(LocalDate.of(2024, 3, 14));
        requestPlan.setEndDate(LocalDate.of(2024, 3, 14));
        requestPlan.setLocCategory("서울특별시");
        requestPlan.setStatus(0);
        requestPlan.setReview("후기");
        requestPlan.setGrade(5);
        requestPlan.setProfile(new byte[]{0, 0});
//        requestPlan.setSpots(new ArrayList<>(){{add(1L); add(2L);}});
//        requestPlan.setMembers(new ArrayList<>(){{add(1L); add(2L);}});

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // requestPlan 객체를 JSON 문자열로 변환
        String jsonPlan = objectMapper.writeValueAsString(requestPlan);

        mockMvc.perform(put("/api/plan/{id}", 9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonPlan)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(planService).updatePlan(9L, requestPlan);
    }

    @Test
    @DisplayName("일정 게시글 삭제 테스트")
    void deletePlan() throws Exception {
        mockMvc.perform(delete("/api/plan/{id}", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(planService).deletePlan(9L);
    }
}