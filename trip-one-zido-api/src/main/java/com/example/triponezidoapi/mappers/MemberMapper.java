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
    boolean isFollow(RequestFollow requestFollow);
    int followerCount(long id);
    int followingCount(long id);
    List<ResponseMember> followerList(RequestSessionTarget requestSessionTarget);
    List<ResponseMember> followingList(RequestSessionTarget requestSessionTarget);
    int postCount(long id);

    long getId(RequestFind requestFind);
    ResponseMember getLogin(Login login);
    String getLoginId(Long id);
    Login getLoginFormByLoginId(String loginId);
    String getLoginIdByFind(RequestFindId requestFindid);
    String getSecurityAnswer(Long id);
    String getSecurityQuestion(Long id);
    String getPasswordById(Long id);
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
