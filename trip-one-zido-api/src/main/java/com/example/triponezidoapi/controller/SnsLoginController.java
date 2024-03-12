package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.SnsLoginService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/social")
public class SnsLoginController {
    @Autowired
    SnsLoginService snsLoginService;
    @GetMapping("/naver_callback")
    void naverLogin(@Parameter String code, @Parameter String state, HttpServletResponse response) throws IOException {
        String id = snsLoginService.naverLogin(code,state);
        response.sendRedirect("http://localhost:3000/social/naver/" + id);
    }

    @GetMapping("/kakao_login")
    void kakoLogin(@Parameter String code, HttpServletResponse response) throws IOException {
        String id = snsLoginService.kakaoLogin(code);
        response.sendRedirect("http://localhost:3000/social/kakao/" + id);
    }
}
