package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestFind {
    @NotEmpty
    @Size(min = 2, max = 100)
    String name;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    String email;
    @NotEmpty
    @Size(min = 1, max = 100)
    String loginId;

    public RequestFind() {
    }

    public RequestFind(String name, String email, String loginId) {
        this.name = name;
        this.email = email;
        this.loginId = loginId;
    }
}
