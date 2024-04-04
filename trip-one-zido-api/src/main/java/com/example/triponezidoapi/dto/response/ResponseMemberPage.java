package com.example.triponezidoapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseMemberPage {
    Long id;
    Long sessionId;
    String loginId;
    boolean isFollow;
    List<ResponseContentList> planLists;
    int planListsCount;
    List<ResponseContentList> spotLists;
    int spotListsCount;
    ResponseMember responseMember;
    int postCount;
    int bookmarkCount;
    int followingCount;
    int followerCount;
    boolean isMine;
}
