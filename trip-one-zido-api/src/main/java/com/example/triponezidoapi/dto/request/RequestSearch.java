package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestSearch {
    Long myMemberId;
    String keyword;
    long page;
}
