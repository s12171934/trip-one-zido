package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestDetailSearch {
    Long myMemberId;
    String keyword;
    String locCategory;
    String category;
    String season;
    int startMonth;
    int endMonth;
    long page;
}
