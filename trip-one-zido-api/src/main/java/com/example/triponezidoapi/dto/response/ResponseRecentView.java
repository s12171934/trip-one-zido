package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ResponseRecentView {
    List<ResponseContentList> recentList;
    ResponseMember member;
}
