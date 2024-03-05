package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.RequestCommunity;
import com.example.triponezidoapi.dto.request.RequestCommunitySearch;
import com.example.triponezidoapi.dto.response.ResponseCommunity;
import com.example.triponezidoapi.dto.response.ResponseCommunityDetail;
import com.example.triponezidoapi.mappers.CommunityMapper;
import com.example.triponezidoapi.mappers.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    CommunityMapper communityMapper;

    //insert
    public void addCommunity(RequestCommunity requestCommunity, long sessionId){
        //세션 아이디가 없다면?
        communityMapper.addCommunity(requestCommunity);
    }

    //select
    public ResponseCommunityDetail getCommunity(long id){
        return communityMapper.getCommunity(id);
    }
    public List<ResponseCommunity> getCommunityList(long page){
        return communityMapper.getCommunityList(page);
    }

    public List<ResponseCommunity> getCommunityListWithSearch(RequestCommunitySearch requestCommunitySearch,int page){
        requestCommunitySearch.setPage(page);
        return communityMapper.getCommunityListWithSearch(requestCommunitySearch);
    }
    public long getNextId(long id){
        return communityMapper.getNextId(id);
    }
    public long getPrevId(long id){
        return communityMapper.getPrevId(id);
    }

    //update
    public void updateCommunity(RequestCommunity requestCommunity, long id){
        requestCommunity.setId(id);
        communityMapper.updateCommunity(requestCommunity);
    }
}
