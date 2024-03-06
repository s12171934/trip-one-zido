package com.example.triponezidoapi.dto.request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestPlan {
    long id;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String status;
    String review;
    int grade;
    String title;
    boolean isPublic;
    LocalDateTime createdAt;
    byte[] profile;
    List<Long> spots;
    List<Long> members;
}
