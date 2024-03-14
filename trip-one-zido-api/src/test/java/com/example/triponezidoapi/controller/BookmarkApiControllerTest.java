package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.BookmarkService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(BookmarkApiController.class)
class BookmarkApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    // Controller에서 잡고 있는 Bean 객체에 대해 Mock 형태의 객체를 생성해줌
    @MockBean
    private BookmarkService bookmarkService;

    @Test
    @DisplayName("찜 목록 전체 조회")
    void showBookmarkAll() throws Exception {

        List<ResponseTour> tours = new ArrayList<>();
        ResponseTour tour = new ResponseTour();
        tour.setId(1L);
        tour.setTitle("덕수궁");
        tour.setAddress("서울시");
        tour.setLocCategory("서울특별시");
        tour.setInfo("굿");
        tour.setPhoto(null);
        tour.setBookmarkCount(1);
        tour.setMyBookmark(1);
        tour.setPrevId(0L);
        tour.setNextId(0L);
        tours.add(tour);

        List<ResponseContentList> contentLists = new ArrayList<>();
        ResponseContentList contentList = new ResponseContentList();
        contentList.setId(1L);
        contentList.setTitle("에버랜드");
        contentList.setPhoto(null);
        contentList.setBookmarkCount(1);
        contentList.setGoodCount(1);
        contentList.setGrade(1);
        contentList.setMyBookmark(1);
        contentList.setPin(1);
        contentLists.add(contentList);

        ResponseBookmark responseBookmark = new ResponseBookmark();
        responseBookmark.setTourBookmarkCount(5);
        responseBookmark.setPlanSpotBookMarkCount(5);
        responseBookmark.setTourList(tours);
        responseBookmark.setContentList(contentLists);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(bookmarkService.getAllBookmark(9L, 9L)).willReturn(
                responseBookmark
        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        mockMvc.perform(get("/api/bookmark/{id}", 9L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tourBookmarkCount").exists())
                .andExpect(jsonPath("$.planSpotBookMarkCount").exists())
                .andExpect(jsonPath("$.tourList").exists())
                .andExpect(jsonPath("$.contentList").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());
        //verify : 해당 객체의 메소드가 실행됐는지 체크

        verify(bookmarkService).getAllBookmark(9L, 9L);
    }

    @Test
    @DisplayName("찜목록 일정 장소 더보기 조회")
    void showSpotPlanListByPage() throws Exception {

        List<ResponseContentList> contentLists = new ArrayList<>();
        ResponseContentList contentList = new ResponseContentList();
        contentList.setId(1L);
        contentList.setTitle("에버랜드");
        contentList.setPhoto(null);
        contentList.setBookmarkCount(1);
        contentList.setGoodCount(1);
        contentList.setGrade(1);
        contentList.setMyBookmark(1);
        contentList.setPin(1);
        contentLists.add(contentList);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(bookmarkService.getPlanSpotBookmark(9L, 9L, 0)).willReturn(
                contentLists
        );

        mockMvc.perform(get("/api/bookmark/{id}/SpotPlan/{page}", 9L, 0)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(bookmarkService).getPlanSpotBookmark(9L, 9L, 0);
    }

    @Test
    @DisplayName("찜목록 관광지 더보기 조회")
    void showTourListByPage() throws Exception {

        List<ResponseTour> tours = new ArrayList<>();
        ResponseTour tour = new ResponseTour();
        tour.setId(1L);
        tour.setTitle("덕수궁");
        tour.setAddress("서울시");
        tour.setLocCategory("서울특별시");
        tour.setInfo("굿");
        tour.setPhoto(null);
        tour.setBookmarkCount(1);
        tour.setMyBookmark(1);
        tour.setPrevId(0L);
        tour.setNextId(0L);
        tours.add(tour);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(bookmarkService.getTourBookmark(9L, 9L, 0)).willReturn(
                tours
        );

        mockMvc.perform(get("/api/bookmark/{id}/tour/{page}", 9L, 0)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").isNotEmpty())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(bookmarkService).getTourBookmark(9L,9L,0);

    }

    @Test
    @DisplayName("찜 등록")
    void postBookmark() throws Exception {

        mockMvc.perform(post("/api/bookmark/{id}", 9L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(bookmarkService).addBookMark(9L, 9L);
    }

    @Test
    @DisplayName("찜 삭제")
    void deleteBookmark() throws Exception {

        mockMvc.perform(delete("/api/bookmark/{id}", 9L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(bookmarkService).deleteBookmark(9L, 9L);
    }
}
