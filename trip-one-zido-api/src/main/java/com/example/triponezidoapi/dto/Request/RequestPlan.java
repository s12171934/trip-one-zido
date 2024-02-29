package com.example.triponezidoapi.dto.Request;

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
    String isPublic;
    LocalDateTime createdAt;
    byte[] profile;
    List<Long> spots;
    List<Long> members;
}
