package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.request.RequestCommunitySearch;
import com.example.triponezidoapi.dto.response.ResponseCommunity;
import com.example.triponezidoapi.dto.response.ResponseCommunityDetail;
import com.example.triponezidoapi.dto.response.ResponseMember;
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
