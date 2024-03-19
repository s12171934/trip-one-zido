package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestPhoto {
    Long contentId;
    byte[] photo;
    String img;
}

