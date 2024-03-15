package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseSearch {
    List<ResponseMember> memberList;
    List<ResponseContentList> planList;
    List<ResponseContentList> spotList;
    String keyword;
    String locCategory;
    String category;
    int memberCount;
    int planCount;
    int spotCount;
}
