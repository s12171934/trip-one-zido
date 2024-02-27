package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    //insert
    void signUp(Member member);
    void follow(Fallow fallow);

    //select
    List<ResponseQuestion> securityQuestions();
    LoginForm getLoginFormByLoginId(String loginId);
    LoginId getLoginIdByFind(RequestFind requestFind);
    ProfileMember getLogin(LoginForm loginForm);
    long getId(RequestFind requestFind);
    String getSecurityQuestion(long id);
    String getSecurityAnswer(long id);
    Member getMemberById(long id);
    int followerCount(long id);
    int followingCount(long id);
    List<ProfileMember> followerList(long id);
    List<ProfileMember> followingList(long id);

    //update
    void updateMember(Member member);
    void updatePassword(Password password);
    void updateProfile(ProfileMember profileMember);

    //delete
    void deleteMember(long id);
    void unFollow(Fallow fallow);
}
