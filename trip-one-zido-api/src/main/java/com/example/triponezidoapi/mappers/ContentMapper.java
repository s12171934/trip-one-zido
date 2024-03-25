package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    //insert
    void addContent(RequestContent requestContent);
    void addGood(RequestGood requestGood);
    void addOwner(RequestOwner requestOwner);
    void addRecentView(RequestContentMember requestContentMember);

    //select
    List<ResponseMember> getOwner(Long id);
    ResponseMember getWriter(Long id);
    List<ResponseContentList> getRecentView(RequestSessionTarget requestSessionTarget);
    int getRecentViewCount(Long memberId);
    int isGood(RequestGood requestGood);
    boolean myGood(RequestGood requestGood);
    boolean isMine(RequestContentMember requestContentMember);
    String getAddress(Long id);

    //update
    void updateVisibility(RequestVisibility requestVisibility);
    void updateTitle(RequestTitle requestTitle);
    void updateGood(RequestGood requestGood);

    //delete
    void deleteContent(Long id);
    void deleteGood(RequestGood requestGood);
    void deleteOwner(RequestContentMember requestContentMember);
    void deletePlanSpotOwner(Long contentId);
    void changeRecentView(RequestContentMember requestContentMember);
}
