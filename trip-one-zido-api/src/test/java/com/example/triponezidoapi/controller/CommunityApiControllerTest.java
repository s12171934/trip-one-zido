package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.service.CommunityService;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommunityApiController.class)
class CommunityApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommunityService communityService;

    //LocalDateTime을 직렬화 및 역직렬화하기 위한 코드
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
    @Test
    @DisplayName("커뮤니티 목록 조회")
    void showCommunityAll() throws Exception {
        ResponseCommunity communityList1 = new ResponseCommunity();
        communityList1.setId(1L);
        communityList1.setTitle("되낭1");
        communityList1.setWriter("아");
        communityList1.setDeadline(LocalDateTime.of(2024,1,1,12,8,4));
        communityList1.setViewPoint(5);
        communityList1.setTotal(2);
        communityList1.setWithCount(2);
        communityList1.setStatus("어");

        List<ResponseCommunity> communityList = new ArrayList<>();
        communityList.add(communityList1);

        ResponseCommunityList responseCommunityList = new ResponseCommunityList();
        responseCommunityList.setCommunitys(communityList);
        responseCommunityList.setTotalCount(1L);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(communityService.getCommunityList(0)).willReturn(
                responseCommunityList
        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        mockMvc.perform(get("/api/community/list/{page}", 0L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].title").exists())
                .andExpect(jsonPath("$[0].writer").exists())
                .andExpect(jsonPath("$[0].deadline").exists())
                .andExpect(jsonPath("$[0].viewPoint").exists())
                .andExpect(jsonPath("$[0].total").exists())
                .andExpect(jsonPath("$[0].withCount").exists())
                .andExpect(jsonPath("$[0].status").exists())
                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());
        //verify : 해당 객체의 메소드가 실행됐는지 체크
        verify(communityService).getCommunityList(0L);
    }

    @Test
    @DisplayName("커뮤니티 상세 조회")
    void showCommunityDetail() throws Exception {
        ResponseMember responseMember = new ResponseMember();
        responseMember.setId(9L);
        responseMember.setLoginId("9L");
        responseMember.setOwn(null);
        responseMember.setProfile(null);
        responseMember.setFollow(true);

        List<ResponseMember> members = new ArrayList<>();
        members.add(responseMember);

        ResponseCommunityDetail responseCommunityDetail = new ResponseCommunityDetail();
        responseCommunityDetail.setId(1L);
        responseCommunityDetail.setStartDate(LocalDateTime.of(2024,3,16,0,0,0));
        responseCommunityDetail.setEndDate(LocalDateTime.of(2024,3,20,0,0,0));
        responseCommunityDetail.setLocCategory("서울특별시");
        responseCommunityDetail.setNotice("야미야미");
        responseCommunityDetail.setTotal(2);
        responseCommunityDetail.setDeadline(LocalDateTime.of(2024,3,15,0,0,0));
        responseCommunityDetail.setViewPoint(1);
        responseCommunityDetail.setStatus("모집중");
        responseCommunityDetail.setTitle("얌얌");
        responseCommunityDetail.setCreatedAt(LocalDateTime.of(2024,3,14,10,0,0));
        responseCommunityDetail.setModifiedAt(LocalDateTime.of(2024,3,14,10,0,0));
        responseCommunityDetail.setMembers(members);
        responseCommunityDetail.setMine(true);
        responseCommunityDetail.setNextId(2L);
        responseCommunityDetail.setPrevId(0L);



        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(communityService.getCommunity(1L, 9L)).willReturn(
                responseCommunityDetail
        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        // 반복문을 통해 jsonPath 값에 대한 검증
        mockMvc.perform(get("/api/community/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists())
                .andExpect(jsonPath("$.locCategory").exists())
                .andExpect(jsonPath("$.notice").exists())
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.deadline").exists())
                .andExpect(jsonPath("$.viewPoint").exists())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.title").exists())
                .andExpect(jsonPath("$.createdAt").exists())
                .andExpect(jsonPath("$.modifiedAt").exists())
                .andExpect(jsonPath("$.members").exists())
                .andExpect(jsonPath("$.mine").exists())
                .andExpect(jsonPath("$.nextId").exists())
                .andExpect(jsonPath("$.prevId").exists())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(communityService).getCommunity(1L,9L);
    }

    @Test
    @DisplayName("커뮤니티 게시물 등록")
    void postCommunity() throws Exception {

        RequestCommunity requestCommunity = new RequestCommunity();
        requestCommunity.setId(9L);
        requestCommunity.setTitle("될꺼야");
        requestCommunity.setStartDate(LocalDateTime.of(2024,3,14,10,0,0));
        requestCommunity.setEndDate(LocalDateTime.of(2024,3,14,10,0,0));
        requestCommunity.setNotice("테스트");
        requestCommunity.setTotal(2);
        requestCommunity.setLocCategory("서울특별시");
        requestCommunity.setDeadline(LocalDateTime.of(2024,3,14,10,0,0));
        requestCommunity.setStatus("모집중");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // community 객체를 JSON 문자열로 변환
        String jsonCommunity = objectMapper.writeValueAsString(requestCommunity);

        mockMvc.perform(post("/api/community/")
                        .sessionAttr("id", 9L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCommunity)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(communityService).addCommunity(any(RequestCommunity.class), eq(9L));
    }

    @Test
    @DisplayName("커뮤니티 게시물 삭제")
    void deleteCommunity() throws Exception {
        mockMvc.perform(delete("/api/community/{id}", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(communityService).deleteCommunity(9L);
    }

    @Test
    @DisplayName("검색한 커뮤니티 게시물 목록")
    void searchCommunity() throws Exception {

        RequestCommunitySearch requestCommunitySearch = new RequestCommunitySearch();
        requestCommunitySearch.setPage(0);
        requestCommunitySearch.setType("writer");
        requestCommunitySearch.setKeyword("keyword");

        // ObjectMapper 객체 생성
        ObjectMapper objectMapper = objectMapper();

        // member 객체를 JSON 문자열로 변환
        String jsonCommunitySearch = objectMapper.writeValueAsString(requestCommunitySearch);

        mockMvc.perform(post("/api/community/search/{page}", 0)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCommunitySearch)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(communityService).getCommunityListWithSearch(requestCommunitySearch, 0);
    }

    @Test
    @DisplayName("커뮤니티 참여하기")
    void entryCommunity() throws Exception {

        mockMvc.perform(post("/api/community/member/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(communityService).addOwner(1L, 9L);
    }

    @Test
    @DisplayName("커뮤니티 참여 취소하기")
    void departureCommunity() throws Exception {

        mockMvc.perform(delete("/api/community/member/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())
                .andDo(print());

        verify(communityService).deleteOwner(1L, 9L);
    }
}