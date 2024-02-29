package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //insert
    void follow(RequestFollow requestFollow);
    void signUp(Member member);

    //select
    int followerCount(long id);
    int followingCount(long id);
    List<ResponseMember> followerList(long id);
    List<ResponseMember> followingList(long id);
    long getId(RequestFind requestFind);
    ResponseMember getLogin(Login login);
    Login getLoginFormByLoginId(String loginId);
    String getLoginIdByFind(RequestFind requestFind);
    String getSecurityAnswer(long id);
    String getSecurityQuestion(long id);
    List<ResponseQuestions> getSecurityQuestionList();
    Member getMemberById(long id);

    //update
    void updateMember(Member member);
    void updatePassword(RequestPassword requestPassword);
    void updateProfile(RequestPhoto requestPhoto);

    //delete
    void deleteMember(long id);
    void unFollow(RequestFollow requestFollow);
}
