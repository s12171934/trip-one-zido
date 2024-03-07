package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestGood {
    boolean good = false;
    long memberId;
    long contentId;
}
