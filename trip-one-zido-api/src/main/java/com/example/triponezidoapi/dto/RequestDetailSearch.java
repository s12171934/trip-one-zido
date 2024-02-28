package com.example.triponezidoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestDetailSearch {
    String keyword;
    String locCategory;
    String category;
    LocalDateTime startDate;
    LocalDateTime endDate;
}