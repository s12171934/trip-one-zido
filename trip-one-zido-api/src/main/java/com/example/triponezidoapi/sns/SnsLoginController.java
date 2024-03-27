package com.example.triponezidoapi.sns;

import com.example.triponezidoapi.dto.request.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/social")
@Tag(name = "SNS Connect", description = "SNS API")
public class SnsLoginController {
    @Autowired
    SnsLoginService snsLoginService;
    @GetMapping("/naver_login")
    void naverLogin(@Parameter String code, @Parameter String state, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String id = snsLoginService.naverLogin(code,state);
        if(snsLoginService.isConnected("naver_id", id, request)){
            Cookie loginCookie = new Cookie("login","1");
            loginCookie.setPath("/");
            response.addCookie(loginCookie);
            response.sendRedirect("http://localhost:3000/");
        }
        else{
            response.sendRedirect("http://localhost:3000/social/naver/" + id);
        }

    }

    @GetMapping("/kakao_login")
    void kakoLogin(@Parameter String code, HttpServletResponse response,  HttpServletRequest request) throws IOException {
        String id = snsLoginService.kakaoLogin(code);
        if(snsLoginService.isConnected("kakao_id", id, request)){
            Cookie loginCookie = new Cookie("login","1");
            loginCookie.setPath("/");
            response.addCookie(loginCookie);
            response.sendRedirect("http://localhost:3000/");
        }
        else{
            response.sendRedirect("http://localhost:3000/social/kakao/" + id);
        }

    }

    @PutMapping("")
    void connectWithMember(@RequestBody RequestSocialConnect requestSocialConnect){
        snsLoginService.connect(requestSocialConnect);
    }
}
