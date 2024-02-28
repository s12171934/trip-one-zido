package com.example.triponezidoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CommunityDetail {
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
}
