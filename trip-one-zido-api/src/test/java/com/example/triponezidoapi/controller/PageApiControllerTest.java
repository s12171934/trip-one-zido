package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseMember;
import com.example.triponezidoapi.dto.response.ResponseMemberPage;
import com.example.triponezidoapi.service.PageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PageApiController.class)
class PageApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PageService pageService;

    @Test
    @DisplayName("회원 페이지 조회")
    void showMemberPage() throws Exception {
        ResponseContentList planContent  = new ResponseContentList();
        planContent .setId(1L);
        planContent .setTitle("부산가자");
        planContent .setPhoto(null);
        planContent .setBookmarkCount(0);
        planContent .setGoodCount(0);
        planContent .setGrade(0);
        planContent .setMyBookmark(0);
        planContent .setPin(0);

        List<ResponseContentList> planList = new ArrayList<>();
        planList.add(planContent);

        ResponseContentList spotContent = new ResponseContentList();
        spotContent.setId(1L);
        spotContent.setTitle("부산용궁사");
        spotContent.setPhoto(null);
        spotContent.setBookmarkCount(0);
        spotContent.setGoodCount(0);
        spotContent.setGrade(0);
        spotContent.setMyBookmark(0);
        spotContent.setPin(0);

        List<ResponseContentList> spotLists = new ArrayList<>();
        spotLists.add(spotContent);

        ResponseMember responseMember = new ResponseMember();
        responseMember.setId(1L);
        responseMember.setLoginId("2L");
        responseMember.setOwn(null);
        responseMember.setProfile(null);
        responseMember.setFollow(true);

        ResponseMemberPage responseMemberPage = new ResponseMemberPage();
        responseMemberPage.setResponseMember(responseMember);
        responseMemberPage.setPostCount(0);
        responseMemberPage.setBookmarkCount(0);
        responseMemberPage.setFollowingCount(0);
        responseMemberPage.setFollowerCount(0);
        responseMemberPage.setMine(true);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(pageService.getMemberPage(1L, 1L)).willReturn(
                responseMemberPage
        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        // 반복문을 통해 jsonPath 값에 대한 검증
        mockMvc.perform(get("/api/page/{id}", 1L)
                        .sessionAttr("id", 1L)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.responseMember").exists())
                .andExpect(jsonPath("$.postCount").exists())
                .andExpect(jsonPath("$.bookmarkCount").exists())
                .andExpect(jsonPath("$.followingCount").exists())
                .andExpect(jsonPath("$.followerCount").exists())
                .andExpect(jsonPath("$.mine").exists())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).getMemberPage(1L, 1L);
    }

    @Test
    @DisplayName("회원 페이지 일정 게시물 더보기 조회")
    void showPlanListByPage() throws Exception {

        ResponseContentList planContent  = new ResponseContentList();
        planContent .setId(1L);
        planContent .setTitle("부산가자");
        planContent .setPhoto(null);
        planContent .setBookmarkCount(0);
        planContent .setGoodCount(0);
        planContent .setGrade(0);
        planContent .setMyBookmark(0);
        planContent .setPin(0);

        List<ResponseContentList> planList = new ArrayList<>();
        planList.add(planContent);


        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(pageService.getPlanListByPage(1L, 1L, 0L, null)).willReturn(
                planList
        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        // 반복문을 통해 jsonPath 값에 대한 검증
        mockMvc.perform(get("/api/page/{id}/plan/{page}", 1L, 0L)
                        .sessionAttr("id", 1L)
                        .sessionAttr("page", 0L)
                        )
                .andExpect(status().isOk())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).getPlanListByPage(1L, 1L, 0L, null);
    }

    @Test
    @DisplayName("회원 페이지 장소 게시물 더보기 조회")
    void showSpotListByPage() throws Exception {
        ResponseContentList spotContent = new ResponseContentList();
        spotContent.setId(1L);
        spotContent.setTitle("부산용궁사");
        spotContent.setPhoto(null);
        spotContent.setBookmarkCount(0);
        spotContent.setGoodCount(0);
        spotContent.setGrade(0);
        spotContent.setMyBookmark(true);
        spotContent.setPin(0);

        List<ResponseContentList> spotLists = new ArrayList<>();
        spotLists.add(spotContent);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(pageService.getSpotListByPage(1L, 1L, 0L, null)).willReturn(
                spotLists
        );

        //andExcept : 기대하는 값이 나왔는지 체크해볼 수 있는 메소드
        //jsonPath : body에 값들이 존재하는지?
        // 반복문을 통해 jsonPath 값에 대한 검증
        mockMvc.perform(get("/api/page/{id}/spot/{page}", 1L, 0L)
                        .sessionAttr("id", 1L)
                        .sessionAttr("page", 0L)
                )
                .andExpect(status().isOk())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).getSpotListByPage(1L, 1L, 0L, null);
    }

    @Test
    @DisplayName("팔로잉 명단")
    void listFollowing() throws Exception {
        ResponseMember responseMember = new ResponseMember();
        responseMember.setId(1L);
        responseMember.setLoginId("2L");
        responseMember.setOwn(null);
        responseMember.setProfile(null);
        responseMember.setFollow(true);

        List<ResponseMember> members = new ArrayList<>();
        members.add(responseMember);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(pageService.getFollowingList(1L, 1L)).willReturn(
                members
        );

        mockMvc.perform(get("/api/page/following/{id}/{page}", 1L, 0L)
                        .sessionAttr("id", 1L)
                        .sessionAttr("page", 0L)
                )
                .andExpect(status().isOk())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).getFollowingList(1L, 1L);
    }

    @Test
    @DisplayName("팔로워 명단")
    void listFollower() throws Exception {
        ResponseMember responseMember = new ResponseMember();
        responseMember.setId(1L);
        responseMember.setLoginId("2L");
        responseMember.setOwn(null);
        responseMember.setProfile(null);
        responseMember.setFollow(true);

        List<ResponseMember> members = new ArrayList<>();
        members.add(responseMember);

        //given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        given(pageService.getFollowerList(1L, 1L)).willReturn(
                members
        );

        mockMvc.perform(get("/api/page/follower/{id}/{page}", 1L, 0L)
                        .sessionAttr("id", 1L)
                        .sessionAttr("page", 0L)
                )
                .andExpect(status().isOk())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).getFollowerList(1L, 1L);
    }

    @Test
    @DisplayName("팔로우")
    void followingMember() throws Exception {
        mockMvc.perform(post("/api/page/follow/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).follow(1L, 9L);
    }

    @Test
    @DisplayName("언팔로우")
    void unfollowMember() throws Exception {
        mockMvc.perform(delete("/api/page/follow/{id}", 1L)
                        .sessionAttr("id", 9L)
                )
                .andExpect(status().isOk())

                // andDo -> 메소드가 어떻게 실행이 됐는지
                .andDo(print());

        verify(pageService).unFollow(1L, 9L);
    }
}