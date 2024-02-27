package com.example.triponezidoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    long id;
    String comment;
    long memberId;
    long contentId;
    String type;
    LocalDateTime createdAt;
}
