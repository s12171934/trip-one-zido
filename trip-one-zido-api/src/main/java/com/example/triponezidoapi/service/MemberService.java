package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.*;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.BookmarkMapper;
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
    @Autowired
    BookmarkMapper bookmarkMapper;

    public List<ResponseQuestions> getSecurityQuestions() {
        return memberMapper.getSecurityQuestionList();
    }

    public void signUp(Member member) {
        // 회원 가입 제약 조건 추가
        memberMapper.signUp(member);
    }

    public boolean isUsingMemberId(String memberId) {
        if (memberMapper.getLoginFormByLoginId(memberId) != null) {
            return true;
        }
        return false;
    }

    public boolean login(Login login, HttpServletRequest request) {
        // 아이디, 비밀번호 정보가 맞는지 확인
        // 세션에 회원번호, 아아디, 프로필사진 저장
        // 해당 회원번호 찾기 -> 그 회원의 비밀번호가 서로 같은지 확인

        if (memberMapper.getLoginFormByLoginId(login.getLoginId()) != null && login.getPassword().equals(memberMapper.getLoginFormByLoginId(login.getLoginId()).getPassword())) {
            ResponseMember member = memberMapper.getLogin(login);
            HttpSession session = request.getSession();
            session.setAttribute("id", member);
            System.out.println(session);
            return true;
        }
        return false;
    }

    public String getLoginId(RequestFindId requestFindid) {
        return memberMapper.getLoginIdByFind(requestFindid);
    }

    public Long getId(RequestFind requestFind) {
        return memberMapper.getId(requestFind);
    }

    public String getQuestion(Long id) {
        return memberMapper.getSecurityQuestion(id);
    }

    public boolean checkAnswer(Long id, String myAnswer) {
        String answer = memberMapper.getSecurityAnswer(id);
        // 보안질문이 맞는지 확인
        if (myAnswer.equals(answer)) {
            return true;
        }
        return false;
    }

    public boolean updatePw(Long id, RequestNewPassword requestNewPassword) {
        // requestNewPassword의 changePassword, changePasswordCheck를 체크
        String changePassword = requestNewPassword.getChangePassword();
        String chkPassword = requestNewPassword.getChangePasswordCheck();

        //새로운 비밀번호와 새로운 비밀번호 확인이 불일치 하는 경우
        if (!changePassword.equals(chkPassword)) {
            return false;
        } else {
            // 새로운 비밀번호와 새로운 비밀번호 확인이 일치하는 경우
            // requestNewPassword 에서 비밀번호 제약조건 확인 후 requestPassword에 넣어서 전송

            // 현재 비밀번호가 맞는지 확인
            if (requestNewPassword.getNowPassword() != null) {
                if (!memberMapper.getPasswordById(id).equals(requestNewPassword.getNowPassword())) {
                    return false;
                }
            }
            RequestPassword requestPassword = new RequestPassword();
            requestPassword.setId(id);
            requestPassword.setPassword(requestNewPassword.getChangePassword());
            memberMapper.updatePassword(requestPassword);
            return true;
        }
    }

    public Member getMember(Long id) {
        return memberMapper.getMemberById(id);
    }

    public void updateMember(Long id, Member member) {
        member.setId(id);
        memberMapper.updateMember(member);
    }

    public void updateProfile(Long id, RequestPhoto profile) {
        // 받은 아이디와 프로필을 requestPhoto에 저장 후 매퍼로 전송
        profile.setContentId(id);
        memberMapper.updateProfile(profile);
    }

    public void deleteMember(Long id) {
        memberMapper.deleteMember(id);
    }

    public ResponseConfigPage getConfig(Long id) {

        ResponseConfigPage responseConfigPage = new ResponseConfigPage();
        responseConfigPage.setPostCount(memberMapper.postCount(id));
        //bookmark count
        responseConfigPage.setBookmarkCount(bookmarkMapper.bookmarkCount(id));
        //follower, following count
        responseConfigPage.setFollowerCount(memberMapper.followerCount(id));
        responseConfigPage.setFollowingCount(memberMapper.followingCount(id));
        responseConfigPage.setMemberProfile(memberMapper.getMemberProfile(id));

        return responseConfigPage;
    }
}
