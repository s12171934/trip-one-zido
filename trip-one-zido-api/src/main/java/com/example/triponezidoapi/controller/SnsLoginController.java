package com.example.triponezidoapi.controller;

import com.example.triponezidoapi.service.SnsLoginService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("api/social")
public class SnsLoginController {
    @Autowired
    SnsLoginService snsLoginService;
    @GetMapping("/naver_callback")
    void naverLogin(@Parameter String code, @Parameter String state) throws JsonProcessingException{
        snsLoginService.naverLogin(code,state);
    }

    @GetMapping("/kakao_login")
    void kakoLogin(@Parameter String code) throws JsonProcessingException {
        snsLoginService.kakaoLogin(code);
    }
}
