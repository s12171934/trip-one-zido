package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseComment {
    Long id;
    String comment;
    ResponseMember member;
    Long contentId;
    Long memberId;
    String type;
    LocalDateTime createdAt;
    List<ResponseComment> comments;
}
