package com.example.triponezidoapi.page;

import com.example.triponezidoapi.bookmark.BookmarkMapper;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.member.MemberMapper;
import com.example.triponezidoapi.plan.PlanMapper;
import com.example.triponezidoapi.spot.SpotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageService {
    @Autowired
    PlanMapper planMapper;
    @Autowired
    SpotMapper spotMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    BookmarkMapper bookmarkMapper;
    @Autowired
    ContentMapper contentMapper;

    //팔로잉 목록 조회
    public List<ResponseMember> getFollowingList(RequestSessionTarget requestSessionTarget){
        List<ResponseMember> followingList = memberMapper.followingList(requestSessionTarget);
        setFollow(followingList,requestSessionTarget);

        return followingList;
    }

    //팔로워 목록 조회
    public List<ResponseMember> getFollowerList(RequestSessionTarget requestSessionTarget){
        List<ResponseMember> followerList = memberMapper.followerList(requestSessionTarget);
        setFollow(followerList, requestSessionTarget);

        return followerList;
    }

    //팔로잉 여부 확인
    private void setFollow(List<ResponseMember> responseMembers, RequestSessionTarget requestSessionTarget){
        for (int i = 0; i < responseMembers.size(); i++) {
            ResponseMember responseMember = responseMembers.get(i);
            Long sessionId = requestSessionTarget.getMyMemberId();
            RequestFollow requestFollow = new RequestFollow(sessionId, responseMember.getId());
            responseMember.setFollow(memberMapper.isFollow(requestFollow));
        }
    }

    //팔로우
    public void follow(RequestFollow requestFollow){
        memberMapper.follow(requestFollow);
    }

    //언팔로우
    public void unFollow(RequestFollow requestFollow){
        memberMapper.unFollow(requestFollow);
    }

    //회원페이지 조회
    public ResponseMemberPage getMemberPage(RequestSessionTarget requestSessionTarget){
        Long id = requestSessionTarget.getTargetId();
        Long sessionId = requestSessionTarget.getMyMemberId();

        RequestFollow requestFollow = new RequestFollow(sessionId,id);

        ResponseMember member = new ResponseMember();
        member.setId(id);
        member.setLoginId(memberMapper.getMemberProfile(id).getLoginId());
        member.setProfile(memberMapper.getMemberProfile(id).getProfile());
        member.setFollow(memberMapper.isFollow(requestFollow));

        return new ResponseMemberPage(
            id,
            sessionId,
            memberMapper.getLoginId(sessionId),
            memberMapper.isFollow(requestFollow),
            planMapper.getPlanList(requestSessionTarget),
            planMapper.getPlanListCount(requestSessionTarget),
            spotMapper.getSpotList(requestSessionTarget),
            spotMapper.getSpotListCount(requestSessionTarget),
            member,
            memberMapper.postCount(id),
            bookmarkMapper.bookmarkCount(id),
            memberMapper.followingCount(id),
            memberMapper.followerCount(id),
            id.equals(sessionId)
        );
    }

    //일정 게시물 더보기
    public List<ResponseContentList> getPlanListByPage(RequestSessionTarget requestSessionTarget){
        return planMapper.getPlanList(requestSessionTarget);
    }

    //장소 게시물 더보기
    public List<ResponseContentList> getSpotListByPage(RequestSessionTarget requestSessionTarget) {
        return spotMapper.getSpotList(requestSessionTarget);
    }

    //지도 장소 카테고리 빈도 조회
    public List<ResponseLocMap> getLocMap(Long memberId){
        return spotMapper.getLocMap(memberId);
    }
}
