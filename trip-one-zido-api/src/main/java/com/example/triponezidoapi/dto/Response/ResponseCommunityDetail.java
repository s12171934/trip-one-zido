package com.example.triponezidoapi.dto.Response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ResponseCommunityDetail {
    long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String notice;
    int total;
    LocalDateTime deadline;
    int viewCount;
    String status;
    String title;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
    List<ResponseMember> members;
}
