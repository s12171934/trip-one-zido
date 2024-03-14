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

    public ResponseComment(Long id, String comment, Long memberId, Long contentId, String type, LocalDateTime createdAt){
        this.id = id;
        this.comment = comment;
        this.memberId = memberId;
        this.contentId = contentId;
        this.type = type;
        this.createdAt = createdAt;
    }
}
