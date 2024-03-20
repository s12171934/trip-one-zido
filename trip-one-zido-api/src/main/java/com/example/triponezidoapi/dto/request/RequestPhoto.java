package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestPhoto {
    Long id;
    Long contentId;
    byte[] photo;
    String img;
}

