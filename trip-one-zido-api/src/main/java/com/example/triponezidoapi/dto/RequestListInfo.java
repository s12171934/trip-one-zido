package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class RequestListInfo {
    long page;
    long targetId;
    long myMemberId;
    String keyword;
}
