package com.example.triponezidoapi.dto.Request;

import lombok.Data;

@Data
public class RequestTour {
    String title;
    String address;
    String locCategory;
    String info;
    byte[] photo;
}
