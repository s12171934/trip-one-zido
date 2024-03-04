package com.example.triponezidoapi.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestCommunity {
    long id;
    String title;
    LocalDateTime startDate;
    LocalDateTime endDate;
    String locCategory;
    String notice;
    int total;
    LocalDateTime deadline;
}
