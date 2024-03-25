package com.example.triponezidoapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestTourList {
    long page;
    int loc;
    long myMemberId;
}
