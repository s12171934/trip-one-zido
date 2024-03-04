package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ResponseCommunity {
    long id;
    String title;
    String writer;
    LocalDateTime deadline;
    int viewPoint;
    int total;
    int participantsCount;
    String status;
}