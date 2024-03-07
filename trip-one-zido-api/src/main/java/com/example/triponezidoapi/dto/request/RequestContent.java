package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestContent {
    long id;
    String type;
    String title;
    boolean isPublic = true;
}
