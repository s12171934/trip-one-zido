package com.example.triponezidoapi.member;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.bookmark.BookmarkMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    BookmarkMapper bookmarkMapper;

    //회원 탈퇴
    public boolean deleteMember(RequestPassword requestPassword){
        // 입력된 비밀번호와 비밀번호 확인이 일치하는지 확인
        boolean checkPw = requestPassword.getPassword().equals(requestPassword.getPasswordCheck()) &&
                memberMapper.getPasswordById(requestPassword.getId()).equals(requestPassword.getPassword());
        if (checkPw) {
            memberMapper.deleteMember(requestPassword.getId());
        }
        return checkPw;
    }

    //회원 프로필 정보 조회
    public ResponseMember getMemberProfile(Long id){
        return memberMapper.getMemberProfile(id);
    }

    //회원 상세 정보 조회
    public Member getMember(Long id) {
        return memberMapper.getMemberById(id);
    }

    //보안질문 리스트 조회
    public List<ResponseQuestions> getSecurityQuestions() {
        return memberMapper.getSecurityQuestionList();
    }

    //설정페이지 조회
    public ResponseConfigPage getConfig(Long id) {
        return new ResponseConfigPage(
                memberMapper.getMemberProfile(id),
                memberMapper.postCount(id),
                memberMapper.followerCount(id),
                memberMapper.followingCount(id),
                bookmarkMapper.bookmarkCount(id)
        );
    }

    //회원가입
    public void signUp(Member member) {
        memberMapper.signUp(member);
    }

    //회원가입시 아이디 중복 조회
    public boolean isUsingMemberId(String memberId){
        return memberMapper.getLoginFormByLoginId(memberId) == null;
    }

    //로그인
    public boolean login(Login login, HttpServletRequest request) {
        //회원 여부 조회
        boolean isMember = memberMapper.getLoginFormByLoginId(login.getLoginId()) != null;
        if(!isMember){
            return false;
        }
        //회원 비밀번호 확인
        String dbPassword = memberMapper.getLoginFormByLoginId(login.getLoginId()).getPassword();
        boolean checkPw = login.getPassword().equals(dbPassword);
        if(!checkPw){
            return false;
        }
        //세션에 회원번호 저장
        request.getSession().setAttribute("id", memberMapper.getIdByLoginId(login.getLoginId()));
        return true;
    }

    //비밀번호 찾기 1단계
    public Long getId(RequestFind requestFind) {
        return memberMapper.getId(requestFind);
    }

    //비밀번호 찾기 2단계
    public String getQuestion(Long id) {
        return memberMapper.getSecurityQuestion(id);
    }

    //비밀번호 찾기 3단계
    public boolean checkAnswer(RequestAnswer requestAnswer) {
        String answer = memberMapper.getSecurityAnswer(requestAnswer.getId());
        return requestAnswer.getAnswer().equals(answer);
    }

    //아이디 찾기
    public String getLoginId(RequestFindId requestFindid) {
        return memberMapper.getLoginIdByFind(requestFindid);
    }

    //자동 로그인
    public void autoLogin(Login login, HttpServletRequest request){
        request.getSession().setAttribute("id", memberMapper.getIdByLoginId(login.getLoginId()));
    }

    //회원 프로필 사진 변경
    public void updateProfile(RequestPhoto profile){
        //가져온 사진을 byte[]로 변환
        profile.setPhoto(Base64.getDecoder().decode(profile.getImg()));
        memberMapper.updateProfile(profile);
    }

    //비밀번호 재설정
    public boolean updatePw(RequestNewPassword requestNewPassword) {
        String changePassword = requestNewPassword.getChangePassword();
        String checkPassword = requestNewPassword.getChangePasswordCheck();
        //변경 비밀번호 확인
        if (!changePassword.equals(checkPassword)) {
            return false;
        }
        //현재 비밀번호가 필요한 경우 비밀번호 확인
        if (requestNewPassword.getNowPassword() != null &&
            !memberMapper.getPasswordById(requestNewPassword.getMemberId()).equals(requestNewPassword.getNowPassword())
        ) {
            return false;
        }
        memberMapper.updatePassword(requestNewPassword);
        return true;

    }

    //회원 정보 변경
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }
}
