package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.RequestDetailSearch;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseMember;
import com.example.triponezidoapi.dto.response.ResponseSearch;
import com.example.triponezidoapi.service.SearchService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchApiController.class)
class SearchApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchService searchService;

    @Test
    @DisplayName("기본 검색")
    void searchKeyword() throws Exception {

        ResponseSearch responseSearch = new ResponseSearch();
        List<ResponseMember> memberList = new ArrayList<>();
        memberList.add(new ResponseMember(1L,"test01",null,null,true));
        memberList.add(new ResponseMember(2L,"test02",null,null,false));

        List<ResponseContentList> planList = new ArrayList<>();
        planList.add(new ResponseContentList(1L,"일정 검색 테스트용",null,9,1,2,5,5));
        planList.add(new ResponseContentList(2L,"일정을 바꿔봐",null,7,12,15,30,3));

        List<ResponseContentList> spotList = new ArrayList<>();
        spotList.add(new ResponseContentList(1L,"장소 검색 테스트용",null,100,50,9,85,9));
        spotList.add(new ResponseContentList(2L,"장소를 바꿔봐",null,102,32,8,75,4));

        responseSearch.setMemberList(memberList);
        responseSearch.setPlanList(planList);
        responseSearch.setSpotList(spotList);
        responseSearch.setKeyword("검색테스트");
        responseSearch.setCategory("기본");
        responseSearch.setLocCategory("서울특별시");

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(searchService.searchByKeyword(9L,"검색테스트",0)).willReturn(
                responseSearch
        );

        mockMvc.perform(get("/api/search/{page}/{keyword}",0,"검색테스트")
                        .sessionAttr("id",9L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberList").exists())
                .andExpect(jsonPath("$.planList").exists())
                .andExpect(jsonPath("$.spotList").exists())
                .andExpect(jsonPath("$.keyword").exists())
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(searchService).searchByKeyword(eq(9L),eq("검색테스트"),eq(0L));
    }

    @Test
    @DisplayName("상세 검색")
    void searchDetail() throws Exception {
        RequestDetailSearch requestDetailSearch = new RequestDetailSearch();
        requestDetailSearch.setMyMemberId(9L);
        requestDetailSearch.setKeyword("spring에 있는 값이 세팅 될거에여");
        requestDetailSearch.setLocCategory("경기도");
        requestDetailSearch.setCategory("일정");
        requestDetailSearch.setSeason("spring");
        requestDetailSearch.setStartMonth(3);
        requestDetailSearch.setEndMonth(5);
        requestDetailSearch.setPage(1L);

        ResponseSearch responseSearch = new ResponseSearch();
        List<ResponseContentList> planList = new ArrayList<>();
        planList.add(new ResponseContentList(1L,"일정 검색 테스트용",null,9,1,2,5,5));
        planList.add(new ResponseContentList(2L,"일정을 바꿔봐",null,7,12,15,30,3));

        List<ResponseContentList> spotList = new ArrayList<>();
        spotList.add(new ResponseContentList(1L,"장소 검색 테스트용",null,100,50,9,85,9));
        spotList.add(new ResponseContentList(2L,"장소를 바꿔봐",null,102,32,8,75,4));

        responseSearch.setPlanList(planList);
        responseSearch.setSpotList(spotList);
        responseSearch.setKeyword("검색테스트");
        responseSearch.setCategory("기본");
        responseSearch.setLocCategory("서울특별시");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = new ObjectMapper();

        //객체를 JSON 문자열로 변환
        String jsonMember = objectMapper.writeValueAsString(requestDetailSearch);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(searchService.searchByDetail(9L, requestDetailSearch,1)).willReturn(
                responseSearch
        );

        mockMvc.perform(post("/api/search/{page}/detail",1)
                        .sessionAttr("id",9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMember))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberList").doesNotExist())
                .andExpect(jsonPath("$.planList").exists())
                .andExpect(jsonPath("$.spotList").exists())
                .andExpect(jsonPath("$.keyword").exists())
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(searchService).searchByDetail(eq(9L),eq(requestDetailSearch),eq(1L));
    }
}