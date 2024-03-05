package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    static MemberMapper memberMapper;

    public static List<ResponseQuestions> getSecurityQuestions(){

        return memberMapper.getSecurityQuestionList();
    }

    public static void signUp(Member member){
        // 회원 가입 제약 조건 추가
        memberMapper.signUp(member);
    }

    public static void isUsingMemberId(String memberId){

        memberMapper.getLoginFormByLoginId(memberId);
    }

    public static void login(Login login){
        // 로그인 정보가 맞는지 확인
        // 세션에 회원번호, 아아디, 프로필사진 저장
        memberMapper.getLogin(login);
    }

    public static String getLoginId(RequestFind requestFind){
       return memberMapper.getLoginIdByFind(requestFind);
    }

    public static long getId(RequestFind requestFind){
        return memberMapper.getId(requestFind);
    }

    public static String getQuestion(long id){
        return memberMapper.getSecurityQuestion(id);
    }

    public static void checkAnswer(long id, String myAnswer){
       String answer = memberMapper.getSecurityAnswer(id);
       // 보안질문이 맞는지 확인
       if(myAnswer.equals(answer)){

       }else
          ;
    }

    public static void updatePw(long id, RequestNewPassword requestNewPassword){
        // requestNewPassword 에서 비밀번호 제약조건 확인 후 requestPassword에 넣어서 전송
        RequestPassword requestPassword = new RequestPassword();
        requestPassword.setId(id);
        requestPassword.setPassword(requestNewPassword.getNowPassword());
        memberMapper.updatePassword(requestPassword);
    }

    public static Member getMember(long id){
        return memberMapper.getMemberById(id);
    }

    public static void updateMember(long id, Member member){
        member.setId(id);
        memberMapper.updateMember(member);
    }

    public static void updateProfile(long id, byte[] profile){
        // 받은 아이디와 프로필을 requestPhoto에 저장 후 매퍼로 전송
        RequestPhoto requestPhoto = new RequestPhoto();
        requestPhoto.setContentId(id);
        requestPhoto.setPhoto(profile);
        memberMapper.updateProfile(requestPhoto);
    }

    public static void deleteMember(long id){
        memberMapper.deleteMember(id);
    }

}
