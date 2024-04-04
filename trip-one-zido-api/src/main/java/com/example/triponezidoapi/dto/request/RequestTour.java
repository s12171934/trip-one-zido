package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestTour {
    Long id;
    String title;
    String address;
    int locCategory;
    String info;
    String photo;
}
