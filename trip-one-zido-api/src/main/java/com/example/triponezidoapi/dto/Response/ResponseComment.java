package com.example.triponezidoapi.dto.Response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseComment {
    long id;
    String comment;
    long memberId;
    long contentId;
    String type;
    LocalDateTime createdAt;
}
