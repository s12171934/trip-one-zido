package com.example.triponezidoapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestPassword {
    Long id;
    String password;
    String passwordCheck;
}
