package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //insert
    void signUp(Member member);

    //select
    List<ResponseQuestion> securityQuestions();
    LoginForm getLoginFormByLoginId(String loginId);
    LoginId getLoginIdByFind(RequestFind requestFind);
    profileMember getLogin(LoginForm loginForm);
    long getId(RequestFind requestFind);
    String getSecurityQuestion(long id);
    String getSecurityAnswer(long id);
    Member getMemberById(long id);

    //update
    void updateMember(Member member);
    void updatePassword(Password password);
    void updateProfile(profileMember profileMember);

    //delete
    void deleteMember(long id);
}
