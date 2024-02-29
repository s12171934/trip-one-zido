package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestContent {
    String type;
    String title;
    boolean isPublic = true;
}
