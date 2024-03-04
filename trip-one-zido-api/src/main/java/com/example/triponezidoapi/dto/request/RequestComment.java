package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestComment {
    long id;
    String comment;
    long memberId;
    long contentId;
}
