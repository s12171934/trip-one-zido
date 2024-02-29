package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestPassword {
    String nowPassword;
    String changePassword;
    String changePasswordCheck;
}
