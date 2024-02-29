package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.Request.RequestCommunitySearch;
import com.example.triponezidoapi.dto.Response.ResponseCommunity;
import com.example.triponezidoapi.dto.Response.ResponseCommunityDetail;
import com.example.triponezidoapi.dto.Response.ResponseMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    //insert
    void addCommunity(ResponseCommunityDetail responseCommunityDetail);

    //select
    List<ResponseCommunity> getCommunities(long page);
    List<ResponseCommunity> getCommunitiesWithSearch(RequestCommunitySearch requestCommunitySearch);
    ResponseCommunityDetail getCommunity(long id);
    List<ResponseMember> getMembers(long id);

    //update
    void updateCommunity(ResponseCommunityDetail responseCommunityDetail);
}
