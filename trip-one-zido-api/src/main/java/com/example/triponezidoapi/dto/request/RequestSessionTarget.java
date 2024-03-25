package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestSessionTarget {
    long page;
    Long targetId;
    Long myMemberId;
    String sort;
}
