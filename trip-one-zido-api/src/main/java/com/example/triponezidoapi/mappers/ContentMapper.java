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
    void addPin(RequestContentMember requestContentMember);
    void addRecentView(RequestContentMember requestContentMember);

    // 사용자의 ID를 기반으로 핀의 갯수를 가져오는 메서드
    int getPinCountByMemberId(Long memberId);

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
    void deletePin(RequestContentMember requestContentMember);
    void changeRecentView(RequestContentMember requestContentMember);
}
