package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.MemberMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;

    public List<ResponseQuestions> getSecurityQuestions(){
        return memberMapper.getSecurityQuestionList();
    }

    public void signUp(Member member){
        // 회원 가입 제약 조건 추가
        memberMapper.signUp(member);
    }

    public void isUsingMemberId(String memberId){
        memberMapper.getLoginFormByLoginId(memberId);
    }

    public boolean login(Login login, HttpServletRequest request){
        // 아이디, 비밀번호 정보가 맞는지 확인
        // 세션에 회원번호, 아아디, 프로필사진 저장
        // 해당 회원번호 찾기 -> 그 회원의 비밀번호가 서로 같은지 확인

        if(memberMapper.getLoginFormByLoginId(login.getLoginId()) != null && login.getPassword().equals(memberMapper.getLoginFormByLoginId(login.getLoginId()).getPassword())){
            ResponseMember member = memberMapper.getLogin(login);
            HttpSession session = request.getSession();
            session.setAttribute("id", member);
            System.out.println(session);
            return true;
        }
        return false;

    }

    public String getLoginId(RequestFind requestFind){
       return memberMapper.getLoginIdByFind(requestFind);
    }

    public long getId(RequestFind requestFind){
        return memberMapper.getId(requestFind);
    }

    public String getQuestion(long id){
        return memberMapper.getSecurityQuestion(id);
    }

    public boolean checkAnswer(long id, String myAnswer){
       String answer = memberMapper.getSecurityAnswer(id);
       // 보안질문이 맞는지 확인
       if(myAnswer.equals(answer)){
           return true;
       }
       return false;
    }

    public void updatePw(long id, RequestNewPassword requestNewPassword){
        // requestNewPassword 에서 비밀번호 제약조건 확인 후 requestPassword에 넣어서 전송
        RequestPassword requestPassword = new RequestPassword();
        requestPassword.setId(id);
        requestPassword.setPassword(requestNewPassword.getNowPassword());
        memberMapper.updatePassword(requestPassword);
    }

    public Member getMember(long id){
        return memberMapper.getMemberById(id);
    }

    public void updateMember(long id, Member member){
        member.setId(id);
        memberMapper.updateMember(member);
    }

    public void updateProfile(long id, byte[] profile){
        // 받은 아이디와 프로필을 requestPhoto에 저장 후 매퍼로 전송
        RequestPhoto requestPhoto = new RequestPhoto();
        requestPhoto.setContentId(id);
        requestPhoto.setPhoto(profile);
        memberMapper.updateProfile(requestPhoto);
    }

    public void deleteMember(long id){
        memberMapper.deleteMember(id);
    }

}
