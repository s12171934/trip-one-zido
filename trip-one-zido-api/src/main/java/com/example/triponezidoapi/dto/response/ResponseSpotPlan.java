package com.example.triponezidoapi.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class ResponseSpotPlan {
    Long id;
    String title;
    LocalDateTime startDate;
    LocalDateTime endDate;
    //vue와 맞추기 위한 추가
    List<ResponsePhoto> photos;
    int category;
    String address;
    String address2;
    String rate;
    String review;
}
