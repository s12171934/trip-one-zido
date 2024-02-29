package com.example.triponezidoapi.dto.Response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseMemberPage {
    List<ResponseContentList> planLists;
    List<ResponseContentList> spotLists;
    ResponseMember responseMember;
    int boardCount;
    int bookmarkCount;
    int followingCount;
    int followerCount;
}
