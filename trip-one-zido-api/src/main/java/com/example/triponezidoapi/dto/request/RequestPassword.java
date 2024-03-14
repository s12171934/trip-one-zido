package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestPassword {
    long id;
    @NotEmpty
    @Size(min = 8, max = 50, message = "비밀번호는 최소 8자 이상입니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$")
    String password;
}
