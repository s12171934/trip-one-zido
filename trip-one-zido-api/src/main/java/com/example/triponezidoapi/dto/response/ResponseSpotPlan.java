package com.example.triponezidoapi.dto.response;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class ResponseSpotPlan {
    long id;
    String title;
    LocalDateTime start_date;
    LocalDateTime end_date;
}
