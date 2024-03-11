package com.example.triponezidoapi.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDetailSearch {
    Long myMemberId;
    String keyword;
    String locCategory;
    String category;
    LocalDateTime startDate;
    LocalDateTime endDate;
    long page;
}
