package com.example.triponezidoapi.dto.response;

import lombok.Data;

@Data
public class ResponseConfigPage {

    ResponseMember memberProfile;
    int postCount;
    int followerCount;
    int followingCount;
    int bookmarkCount;

}
