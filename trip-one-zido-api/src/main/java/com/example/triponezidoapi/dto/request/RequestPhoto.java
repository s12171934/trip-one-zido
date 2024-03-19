package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestPhoto {
    long id;
    long contentId;
    byte[] photo;
    String img;
}

