/*
package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestPlan;
import com.example.triponezidoapi.dto.response.ResponsePlanDetail;
import com.example.triponezidoapi.service.PlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanApiController.class)
class PlanApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // Controller에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
    @MockBean
    PlanService planService;

    @Test
    @DisplayName("일정 게시글 상세 조회 테스트")
    void showPlanDetail() throws Exception{
        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(planService.getPlan(9L, 9L )).willReturn(
                new ResponsePlanDetail()
        );

        Long contentId = 9L;

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        mockMvc.perform(
                get("/api/plan" + contentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                .andExpect(jsonPath("$.status").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());
        //verify : 해당 객체의 메소드가 실행됐는지 체크
        verify(planService).getPlan(9L, 9L);
    }

    @Test
    @DisplayName("일정 게시글 등록 테스트")
    void postPlan() {
        //Mock 객체에서 특정 메소드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        given(planService.addPlan(9L, new RequestPlan(9L, "제목", 1, 2024-03-12, 2024-03-12, "서울특별시", "계획", "후기", 5, "", null, null))
                .willReturn(
                new ResponsePlanDetail(9L, "제목", 1, 2024-03-12, 2024-03-12, "서울특별시", "계획", "후기", 5, "", null, null)
        );

        //post 전송할 객체 생성 builder 뒤에 전송할 값 점으로 붙임
        RequestPlan requestPlan = RequestPlan.builder().id("9L").title("제목").build();

        String json = new ObjectMapper().writeValueAsString(requestPlan);

        mockMvc.perform(
                post("/api/plan/")
                        .content("content")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                .andExpect(jsonPath("$.status").exists())
                .andDo(print());

        // 여기서 객체에 맞는 값을 넣어줘야될 듯
        verify(planService).addPlan(9L, new RequestPlan());

    }

    @Test
    void putPlan() {
    }

    @Test
    void deletePlan() {
    }
}
*/
