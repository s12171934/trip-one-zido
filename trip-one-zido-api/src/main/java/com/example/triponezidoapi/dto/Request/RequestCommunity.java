package com.example.triponezidoapi.dto.Request;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

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
