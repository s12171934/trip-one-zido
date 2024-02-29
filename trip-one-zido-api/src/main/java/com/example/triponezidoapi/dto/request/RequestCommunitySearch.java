package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestCommunitySearch {
    long page;
    String type;
    String keyword;
}
