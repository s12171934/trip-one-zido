package com.example.triponezidoapi.dto.beforeUse;

import lombok.Data;

@Data
public class RequestOwner {
    String own;
    long memberId;
    long contentId;
}
