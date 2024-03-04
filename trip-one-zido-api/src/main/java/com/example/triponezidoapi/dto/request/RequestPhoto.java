package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestPhoto {
    long contentId;
    byte[] photo;
}
