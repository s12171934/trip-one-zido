package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseCommunityDetail {
    Long id;
    String loginId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int locCategory;
    String notice;
    int total;
    LocalDateTime deadline;
    int viewPoint;
    int status;
    String title;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    List<ResponseMember> members;
    boolean isMine;
    long nextId;
    long prevId;
}
