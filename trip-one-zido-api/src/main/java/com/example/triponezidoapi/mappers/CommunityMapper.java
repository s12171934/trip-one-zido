package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommunityMapper {
    //insert
    void addCommunity(RequestCommunity requestCommunity);

    //select
    ResponseCommunityDetail getCommunity(Long id);
    List<ResponseCommunity> getCommunityList(long page);
    List<ResponseCommunity> getCommunityListWithSearch(RequestCommunitySearch requestCommunitySearch);
    Long getNextId(Long id);
    Long getPrevId(Long id);

    Long getCommunityCount();
    Long getCommunitySearchCount(RequestCommunitySearch requestCommunitySearch);

    //update
    void updateCommunity(RequestCommunity requestCommunity);
    void updateStatus(RequestCommunity requestCommunity);
}
