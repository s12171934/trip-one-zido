package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestTour {
    long id;
    String title;
    String address;
    String locCategory;
    String info;
    byte[] photo;
}
