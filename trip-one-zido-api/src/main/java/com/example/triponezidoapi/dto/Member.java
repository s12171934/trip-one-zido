package com.example.triponezidoapi.dto;

import lombok.Data;

@Data
public class Member {
    long id;
    String name;
    String loginId;
    String password;
    String passwordCheck;
    long question;
    String answer;
    String email;
    String phoneNumber;
    String address;
    String birth;
    String gender;
}
