package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestSocialConnect;
import com.example.triponezidoapi.mappers.MemberMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SnsLoginService {
    @Autowired
    MemberMapper memberMapper;
    public String naverLogin(String code, String state) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        MultiValueMap<String,String> dto = new LinkedMultiValueMap<>();
        dto.add("grant_type","authorization_code");
        dto.add("client_id","TGQMEJAd_Qx6raHI9ZZk");
        dto.add("client_secret","sJS5Wv2uex");
        dto.add("code",code);
        dto.add("state",state);

        HttpHeaders header = new HttpHeaders();
        header.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity request = new HttpEntity<>(dto,header);

        ResponseEntity<String> authorization = restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token"
                , HttpMethod.POST
                ,request
                ,String.class);

        Map map = objectMapper.readValue(authorization.getBody(), Map.class);

        header = new HttpHeaders();
        header.set("authorization", "Bearer " + map.get("access_token"));
        request = new HttpEntity(header);

        ResponseEntity<String> naverInfo = restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me"
                ,HttpMethod.GET
                ,request
                ,String.class);

        map = objectMapper.readValue(naverInfo.getBody(),Map.class);
        return (String)((LinkedHashMap)map.get("response")).get("id");
    }

    public String kakaoLogin(String code) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String,String> dto = new LinkedMultiValueMap<>();
        dto.add("grant_type","authorization_code");
        dto.add("client_id","9f43f99ff692bc46c266ddd1b0118864");
        dto.add("redirect_uri","http://localhost:8080/api/social/kakao_login");
        dto.add("code",code);

        HttpHeaders header = new HttpHeaders();
        header.set("Content-type","application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity request = new HttpEntity<>(dto,header);

        ResponseEntity<String> authorization = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token"
                ,HttpMethod.POST
                ,request
                ,String.class);

        Map map = objectMapper.readValue(authorization.getBody(), Map.class);

        header = new HttpHeaders();
        header.set("authorization", "Bearer " + map.get("access_token"));
        request = new HttpEntity(header);

        ResponseEntity<String> kakaoInfo = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me"
                ,HttpMethod.GET
                ,request
                ,String.class);

        map = objectMapper.readValue(kakaoInfo.getBody(),Map.class);
        return String.valueOf(map.get("id"));
    }

    public void connect(RequestSocialConnect requestSocialConnect){
        if(requestSocialConnect.getSocialType().equals("naver")){
            memberMapper.updateNaverId(requestSocialConnect);
        }
        else {
            memberMapper.updateKakaoId(requestSocialConnect);
        }
    }
}
