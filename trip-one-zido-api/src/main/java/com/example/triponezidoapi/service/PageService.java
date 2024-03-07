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
    @Autowired
    ContentMapper contentMapper;

    public ResponseMemberPage getMemberPage(Long id, Long sessionId){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }
        ResponseMemberPage memberPage = new ResponseMemberPage();
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);

        //planLists
        memberPage.setPlanLists(planMapper.getPlanList(requestSessionTarget));
        //spotLists
        memberPage.setSpotLists(spotMapper.getSpotList(requestSessionTarget));
        //member
        ResponseMember member = new ResponseMember();
        member.setId(id);
        member.setLoginId(memberMapper.getMemberProfile(id).getLoginId());
        member.setProfile(memberMapper.getMemberProfile(id).getProfile());
        memberPage.setResponseMember(member);
        //post count
        memberPage.setPostCount(memberMapper.postCount(id));
        //bookmark count
        memberPage.setBookmarkCount(bookmarkMapper.bookmarkCount(id));
        //follower, following count
        memberPage.setFollowerCount(memberMapper.followerCount(id));
        memberPage.setFollowingCount(memberMapper.followingCount(id));

        // memberPage.setMine();
        //RequestContentMember requestContentMember = new RequestContentMember();
        //requestContentMember.setMemberId(id);
        //contentId 가져오지 못한다
        //requestContentMember.setContentId();
        //memberPage.setMine(contentMapper.isMine(requestContentMember));
        return memberPage;
    }

    public List<ResponseContentList> getPlanListByPage(Long id, Long sessionId, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page);

        return planMapper.getPlanList(requestSessionTarget);
    }

    public List<ResponseContentList> getSpotListByPage(Long id, Long sessionId, long page) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page);

        return spotMapper.getSpotList(requestSessionTarget);
    }

    public List<ResponseMember> getFollowingList(Long id, Long sessionId, long page){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);

        return memberMapper.followingList(requestSessionTarget);
    }

    public List<ResponseMember> getFollowerList(Long id, Long sessionId, long page){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);

        return memberMapper.followerList(requestSessionTarget);
    }

    public void follow(Long id, Long sessionId){
        RequestFollow requestFollow = new RequestFollow();
        requestFollow.setFollower(sessionId);
        requestFollow.setFollowing(id);
        memberMapper.follow(requestFollow);
    }

    public void unFollow(Long id, Long sessionId){
        RequestFollow requestFollow = new RequestFollow();
        requestFollow.setFollower(sessionId);
        requestFollow.setFollowing(id);
        memberMapper.unFollow(requestFollow);
    }
}
