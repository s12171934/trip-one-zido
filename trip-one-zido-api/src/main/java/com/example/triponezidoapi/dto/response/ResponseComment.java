package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseComment {
    Long id;
    String comment;
    Long memberId;
    Long contentId;
    String type;
    LocalDateTime createdAt;
}
