package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
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

    public ResponseMemberPage getMemberPage(long id, long sessionId){
        //id가 null일때 세션정보를 이용한다

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);

        ResponseMember member = new ResponseMember();
        member.setId(id);
        member.setLoginId(memberMapper.getMemberProfile(id).getLoginId());
        member.setProfile(memberMapper.getMemberProfile(id).getProfile());

        ResponseMemberPage memberPage = new ResponseMemberPage();
        memberPage.setPlanLists(planMapper.getPlanList(requestSessionTarget));
        memberPage.setSpotLists(spotMapper.getSpotList(requestSessionTarget));
        memberPage.setResponseMember(member);
        memberPage.setPostCount(memberMapper.postCount(id));
        memberPage.setBookmarkCount(bookmarkMapper.bookmarkCount(id));
        memberPage.setFollowerCount(memberMapper.followerCount(id));
        memberPage.setFollowingCount(memberMapper.followingCount(id));

        return memberPage;
    }

    public List<ResponseContentList> getPlanListByPage(long id, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page);

        return planMapper.getPlanList(requestSessionTarget);
    }

    public List<ResponseContentList> getSpotListByPage(long id, long page) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page);

        return spotMapper.getSpotList(requestSessionTarget);
    }

    public List<ResponseMember> getFollowingList(long id, long page){
        // followlist pagenation, isfollow 추가 (쿼리)

        return memberMapper.followingList(id);
    }

    public List<ResponseMember> getFollowerList(long id, long page){
        // followlist pagenation, isfollow 추가 (쿼리)

        return memberMapper.followerList(id);
    }

    public void follow(long id, long sessionId){
        //id가 null일때 세션정보를 이용한다

        RequestFollow requestFollow = new RequestFollow();
        requestFollow.setFollower(sessionId);
        requestFollow.setFollowing(id);
        memberMapper.follow(requestFollow);
    }

    public void unFollow(long id, long sessionId){
        //id가 null일때 세션정보를 이용한다

        RequestFollow requestFollow = new RequestFollow();
        requestFollow.setFollower(sessionId);
        requestFollow.setFollowing(id);
        memberMapper.unFollow(requestFollow);
    }
}
