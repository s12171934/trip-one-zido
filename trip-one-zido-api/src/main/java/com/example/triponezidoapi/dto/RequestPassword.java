package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class RequestPassword {
    String nowPassword;
    String changePassword;
    String changePasswordCheck;
}
