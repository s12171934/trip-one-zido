package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestContent {
    Long id;
    String type;
    String title;
    boolean visibility = true;
}
