package com.example.triponezidoapi.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseRecentView {
    List<ResponseContentList> recentList;
    ResponseMember member;
    int recentViewCount;
}
