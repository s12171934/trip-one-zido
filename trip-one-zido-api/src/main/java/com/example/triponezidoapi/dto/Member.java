package com.example.triponezidoapi.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    Long id;
    @NotEmpty
    @Size(min = 2, max = 100)
    String name;
    @NotEmpty
    @Size(min = 1, max = 100)
    String loginId;
    @NotEmpty
    @Size(min = 8, max = 50, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}$")
    String password;
    String passwordCheck;
    long question;
    @NotEmpty
    @Size(min = 1, max = 100, message = "최소 1자 이상의 답변이 필요합니다")
    String answer;
    @NotEmpty
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    String email;
    @NotEmpty
    @Size(max = 20)
    String phoneNumber;
    @NotEmpty
    String zipcode;
    @NotEmpty
    @Size(max = 200)
    String address;
    @Size(max = 200)
    String address2;
    @Past
    LocalDateTime birth;
    @NotEmpty
    String gender;
}
