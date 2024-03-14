package com.example.triponezidoapi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestFindId {
    @NotEmpty
    @Size(min = 2, max = 100)
    String name;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    String email;

    public RequestFindId() {
    }

    public RequestFindId(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
