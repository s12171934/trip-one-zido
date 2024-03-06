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
    int isFollow(RequestFollow requestFollow);
    int followerCount(Long id);
    int followingCount(Long id);
    List<ResponseMember> followerList(Long id);
    List<ResponseMember> followingList(Long id);
    int postCount(Long id);
    long getId(RequestFind requestFind);
    ResponseMember getLogin(Login login);
    Login getLoginFormByLoginId(String loginId);
    String getLoginIdByFind(RequestFind requestFind);
    String getSecurityAnswer(Long id);
    String getSecurityQuestion(Long id);
    List<ResponseQuestions> getSecurityQuestionList();
    Member getMemberById(Long id);
    ResponseMember getMemberProfile(Long id);

    //update
    void updateMember(Member member);
    void updatePassword(RequestPassword requestPassword);
    void updateProfile(RequestPhoto requestPhoto);

    //delete
    void deleteMember(Long id);
    void unFollow(RequestFollow requestFollow);
}
