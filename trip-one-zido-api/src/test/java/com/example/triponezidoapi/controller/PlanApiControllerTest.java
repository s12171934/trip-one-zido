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

    // Controller에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
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

        List<ResponseSpotPlan> responseSpotPlans = new ArrayList<>();
        responseSpotPlans.add(new ResponseSpotPlan(1L, "덕수궁", LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59)));
        responseSpotPlans.add(new ResponseSpotPlan(2L, "광화문", LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59)));

        List<ResponseMember> members = new ArrayList<>();
        members.add(new ResponseMember(9L, "member1", "writer", null, true));
        members.add(new ResponseMember(10L, "member2", "with", null, true));

        List<ResponseComment> comments = new ArrayList<>();
        comments.add(new ResponseComment(1L, "멋지네요", 11L, 1L, "comment", LocalDateTime.of(2024, 3, 14, 23, 59)));
        comments.add(new ResponseComment(2L, "굿", 12L, 1L, "comment", LocalDateTime.of(2024, 3, 14, 23, 59)));

        ResponsePlanDetail responsePlanDetail = new ResponsePlanDetail(1L, LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59), "서울", "여행중", "",
                5, 1, 1, 1, 1, 1, "서울여행", true, LocalDateTime.of(2024, 3, 14, 0, 0),
                responseSpotPlans, members, comments, true);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(planService.getPlan(1L, 9L )).willReturn(
                responsePlanDetail
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
                .andExpect(jsonPath("$.responseSpotPlans").exists())
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
        RequestPlan requestPlan = new RequestPlan(1L, "제목", true, LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59), "서울특별시", "계획", "후기", 5, null, null, null);

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
        RequestPlan requestPlan = new RequestPlan(1L, "제목", true, LocalDateTime.of(2024, 3, 14, 0, 0),
                LocalDateTime.of(2024, 3, 14, 23, 59), "서울특별시", "계획", "후기", 5, null, null, null);

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

