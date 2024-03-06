package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    ContentMapper contentMapper;

    public List<ResponseCommunity> getCommunityList(long page){
        return communityMapper.getCommunityList(page);
    }
    public ResponseCommunityDetail getCommunity(long id){
        //컨텐트 매퍼에서 getOwner 가져오기
        return communityMapper.getCommunity(id);
    }

    public void addCommunity(RequestCommunity requestCommunity, long sessionId){
        //컨텐트 매퍼에서 addContent 가져오기

        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setContentId(requestCommunity.getId());
        requestOwner.setMemberId(sessionId);

        contentMapper.addOwner(requestOwner);
        communityMapper.addCommunity(requestCommunity);
    }

    public void updateCommunity(RequestCommunity requestCommunity, long id){
        //updateTitle이 필요

        requestCommunity.setId(id);
        communityMapper.updateCommunity(requestCommunity);
    }

    public void deleteCommunity(long id){
        contentMapper.deleteContent(id);
    }

    public List<ResponseCommunity> getCommunityListWithSearch(RequestCommunitySearch requestCommunitySearch,long page){
        requestCommunitySearch.setPage(page);
        return communityMapper.getCommunityListWithSearch(requestCommunitySearch);
    }

    public void addOwner(long id, long sessionId){
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("participants");
        requestOwner.setContentId(id);
        requestOwner.setMemberId(sessionId);
        contentMapper.addOwner(requestOwner);
    }

    public void deleteOwner(long id, long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.deleteOwner(requestContentMember);
    }

}
