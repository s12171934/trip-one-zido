package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.Request.RequestFind;
import com.example.triponezidoapi.dto.Request.RequestLogin;
import com.example.triponezidoapi.dto.Response.ResponseMember;
import com.example.triponezidoapi.dto.Response.ResponseQuestions;
import com.example.triponezidoapi.dto.beforeUse.Follow;
import com.example.triponezidoapi.dto.beforeUse.LoginId;
import com.example.triponezidoapi.dto.beforeUse.Password;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //insert
    void signUp(Member member);
    void follow(Follow follow);

    //select
    List<ResponseQuestions> securityQuestions();
    RequestLogin getLoginFormByLoginId(String loginId);
    LoginId getLoginIdByFind(RequestFind requestFind);
    ResponseMember getLogin(RequestLogin requestLogin);
    long getId(RequestFind requestFind);
    String getSecurityQuestion(long id);
    String getSecurityAnswer(long id);
    Member getMemberById(long id);
    int followerCount(long id);
    int followingCount(long id);
    List<ResponseMember> followerList(long id);
    List<ResponseMember> followingList(long id);

    //update
    void updateMember(Member member);
    void updatePassword(Password password);
    void updateProfile(ResponseMember responseMember);

    //delete
    void deleteMember(long id);
    void unFollow(Follow follow);
}
