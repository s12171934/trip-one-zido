package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class ResponseCommunity {
    Long id;
    String title;
    Long writerId;
    String writer;
    LocalDate deadline;
    int viewPoint;
    int total;
    int withCount;
    int status;
}
