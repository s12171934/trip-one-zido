package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseMemberPage {
    Long id;
    Long sessionId;
    String loginId;
    boolean isFollow;
    List<ResponseContentList> planLists;
    List<ResponseContentList> spotLists;
    ResponseMember responseMember;
    int postCount;
    int bookmarkCount;
    int followingCount;
    int followerCount;
    boolean isMine;
}
