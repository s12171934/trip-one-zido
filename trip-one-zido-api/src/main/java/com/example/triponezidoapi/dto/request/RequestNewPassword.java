package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestNewPassword {
    String nowPassword;
    @NotEmpty
    @Size(min = 8, max = 50, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$")
    String changePassword;
    String changePasswordCheck;

    public RequestNewPassword() {
    }

    public RequestNewPassword(String nowPassword, String changePassword, String changePasswordCheck) {
        this.nowPassword = nowPassword;
        this.changePassword = changePassword;
        this.changePasswordCheck = changePasswordCheck;
    }
}
