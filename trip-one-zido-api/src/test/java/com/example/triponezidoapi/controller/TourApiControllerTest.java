package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestTour;
import com.example.triponezidoapi.dto.response.ResponseTour;
import com.example.triponezidoapi.service.TourService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TourApiController.class)
class TourApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TourService tourService;

    @Test
    @DisplayName("관광지 목록 조회")
    void showTourAll() throws Exception {
        List<ResponseTour> tours = new ArrayList<>();
        ResponseTour tours1 = new ResponseTour();
        tours1.setId(1L);
        tours1.setTitle("덕수궁");
        tours1.setAddress("서울시");
        tours1.setLocCategory("서울특별시");
        tours1.setInfo("굿");
        tours1.setPhoto(null);
        tours1.setBookmarkCount(1);
        tours1.setMyBookmark(1);
        tours1.setPrevId(0L);
        tours1.setNextId(0L);

        tours.add(tours1);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
//        given(tourService.getTourList(9L, "서울특별시", 0L)).willReturn(
//                tours
//        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        // 반복문을 통해 jsonPath 값에 대한 검증
        mockMvc.perform(get("/api/tour/list/{page}", 1)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].title").isNotEmpty())
                .andExpect(jsonPath("$[0].address").exists())
                .andExpect(jsonPath("$[0].locCategory").exists())
                .andExpect(jsonPath("$[0].info").exists())
                .andExpect(jsonPath("$[0].photo").doesNotExist())
                .andExpect(jsonPath("$[0].bookmarkCount").exists())
                .andExpect(jsonPath("$[0].myBookmark").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        //응답 객체 확인
        for (ResponseTour tour : tours) {
            System.out.println(tour);
        }
    }

    @Test
    @DisplayName("관광지 상세 조회")
    void showTourDetail() throws Exception {
        ResponseTour tours = new ResponseTour();
        tours.setId(1L);
        tours.setTitle("덕수궁");
        tours.setAddress("서울시");
        tours.setLocCategory("서울특별시");
        tours.setInfo("굿");
        tours.setPhoto(null);
        tours.setBookmarkCount(1);
        tours.setMyBookmark(1);
        tours.setPrevId(0L);
        tours.setNextId(0L);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(tourService.getTour(9L, 1L)).willReturn(
                tours
        );

        // 반복문을 통해 jsonPath 값에 대한 검증
        mockMvc.perform(get("/api/tour/{id}", 1)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.address").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                .andExpect(jsonPath("$.info").exists())
                .andExpect(jsonPath("$.photo").doesNotExist())
                .andExpect(jsonPath("$.bookmarkCount").exists())
                .andExpect(jsonPath("$.myBookmark").exists())
                .andExpect(jsonPath("$.nextId").exists())
                .andExpect(jsonPath("$.prevId").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        //응답 객체 출력 확인
        System.out.println(tours);
    }

    @Test
    @DisplayName("관광지 등록")
    void postTour() throws Exception {
        //요청객체
        RequestTour requestTour = new RequestTour();
        requestTour.setId(1L);
        requestTour.setTitle("덕수궁");
        requestTour.setAddress("서울시");
        requestTour.setLocCategory("서울특별시");
        requestTour.setInfo("굿");
        requestTour.setPhoto(null);

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        // 객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestTour);
        //변환 출력 확인
        System.out.println(jsonMember);

        mockMvc.perform(post("/api/tour/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember)
                )
                .andExpect(status().isOk())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        //void 메소드의 호출 확인
        verify(tourService).addTour(any(RequestTour.class));
    }
}