package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class RequestSearch {
    long page;
    String type;
    String keyword;
}
