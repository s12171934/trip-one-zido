package com.example.triponezidoapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestFollow {
    long follower;
    long following;
}
