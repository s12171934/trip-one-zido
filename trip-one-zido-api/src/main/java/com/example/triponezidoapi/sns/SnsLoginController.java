package com.example.triponezidoapi.sns;

import com.example.triponezidoapi.dto.request.*;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "네이버 연동 로그인")
    void naverLogin(
        @Parameter
        String code,
        @Parameter
        String state,
        HttpServletResponse response,
        HttpServletRequest request
    )   throws IOException {
        String id = snsLoginService.naverLogin(code,state);
        RequestSocialConnect requestSocialConnect = new RequestSocialConnect();
        requestSocialConnect.setSocialType("naver_id");
        requestSocialConnect.setSocialId(id);

        if(snsLoginService.isConnected(requestSocialConnect, request)){
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
    @Operation(summary = "카카오 연동 로그인")
    void kakoLogin(
        @Parameter
        String code,
        HttpServletResponse response,
        HttpServletRequest request
    )   throws IOException {
        String id = snsLoginService.kakaoLogin(code);
        RequestSocialConnect requestSocialConnect = new RequestSocialConnect();
        requestSocialConnect.setSocialType("kakao_id");
        requestSocialConnect.setSocialId(id);

        if(snsLoginService.isConnected(requestSocialConnect, request)){
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
    @Operation(summary = "로그인 연동")
    void connectWithMember(
        @RequestBody RequestSocialConnect requestSocialConnect
    ){
        snsLoginService.connect(requestSocialConnect);
    }
}
