package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ResponseSpotPlan {
    Long id;
    String title;
    LocalDateTime startDate;
    LocalDateTime endDate;

    public ResponseSpotPlan(Long id, String title, LocalDateTime startDate, LocalDateTime endDate){
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
