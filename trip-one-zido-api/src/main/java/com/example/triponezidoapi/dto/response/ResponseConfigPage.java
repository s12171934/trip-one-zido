package com.example.triponezidoapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseConfigPage {

    ResponseMember memberProfile;
    int postCount;
    int followerCount;
    int followingCount;
    int bookmarkCount;

}
