package com.example.triponezidoapi.dto.request;

import lombok.Data;

@Data
public class RequestNewPassword {
    String nowPassword;
    String changePassword;
    String changePasswordCheck;
}
