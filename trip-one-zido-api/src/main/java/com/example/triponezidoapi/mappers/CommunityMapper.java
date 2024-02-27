package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    //insert
    void addCommunity(CommunityDetail communityDetail);

    //select
    List<CommunityTable> getCommunities(long page);
    List<CommunityTable> getCommunitiesWithSearch(RequestSearch requestSearch);
    CommunityDetail getCommunity(long id);
    List<ProfileMember> getMembers(long id);

    //update
    void updateCommunity(CommunityDetail communityDetail);
}
