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

        memberPage.setId(id);
        memberPage.setSessionId(sessionId);
        memberPage.setLoginId(memberMapper.getLoginId(sessionId));

        //isFollow
        RequestFollow follow = new RequestFollow();
        follow.setFollower(sessionId);
        follow.setFollowing(id);
        memberPage.setFollow(memberMapper.isFollow(follow));

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setSort("created_at");

        //planLists
        memberPage.setPlanLists(planMapper.getPlanList(requestSessionTarget));
        //spotLists
        memberPage.setSpotLists(spotMapper.getSpotList(requestSessionTarget));

        //member
        ResponseMember member = new ResponseMember();
        member.setId(id);
        member.setLoginId(memberMapper.getMemberProfile(id).getLoginId());
        member.setProfile(memberMapper.getMemberProfile(id).getProfile());
        RequestFollow requestFollow = new RequestFollow();
        requestFollow.setFollower(sessionId);
        requestFollow.setFollowing(id);
        member.setFollow(memberMapper.isFollow(requestFollow));
        memberPage.setResponseMember(member);

        //post count
        memberPage.setPostCount(memberMapper.postCount(id));
        //bookmark count
        memberPage.setBookmarkCount(bookmarkMapper.bookmarkCount(id));
        //follower, following count
        memberPage.setFollowerCount(memberMapper.followerCount(id));
        memberPage.setFollowingCount(memberMapper.followingCount(id));

        //나의 페이지라면 true를, 아니라면 false를 반환
        memberPage.setMine(id.equals(sessionId));

        return memberPage;
    }

    public List<ResponseContentList> getPlanListByPage(Long id, Long sessionId, long page, String sort){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page * 6);
        requestSessionTarget.setSort(sort == null ? "created_at" : sort);


        return planMapper.getPlanList(requestSessionTarget);
    }

    public List<ResponseContentList> getSpotListByPage(Long id, Long sessionId, long page, String sort) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setPage(page * 6);
        requestSessionTarget.setSort(sort == null ? "created_at" : sort);


        return spotMapper.getSpotList(requestSessionTarget);
    }

    public List<ResponseMember> getFollowingList(Long id, Long sessionId){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);

        List<ResponseMember> followingList = memberMapper.followingList(requestSessionTarget);
        // 팔로잉 여부 확인
        for (int i = 0; i < followingList.size(); i++) {
            RequestFollow requestFollow = new RequestFollow();
            requestFollow.setFollower(sessionId);
            requestFollow.setFollowing(followingList.get(i).getId());
            followingList.get(i).setFollow(memberMapper.isFollow(requestFollow));
        }

        return followingList;
    }

    public List<ResponseMember> getFollowerList(Long id, Long sessionId){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);

        List<ResponseMember> followerList = memberMapper.followerList(requestSessionTarget);
        // 팔로잉 여부 확인
        for (int i = 0; i < followerList.size(); i++) {
            RequestFollow requestFollow = new RequestFollow();
            requestFollow.setFollower(sessionId);
            requestFollow.setFollowing(followerList.get(i).getId());
            followerList.get(i).setFollow(memberMapper.isFollow(requestFollow));
        }

        return followerList;
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
