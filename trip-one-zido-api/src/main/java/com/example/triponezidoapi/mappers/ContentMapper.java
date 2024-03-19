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

    // 사용자의 ID를 기반으로 핀의 갯수를 가져오는 메서드
    int getPinCountByMemberId(Long memberId);

    //select
    List<ResponseMember> getOwner(Long id);
    ResponseMember getWriter(Long id);
    List<ResponseContentList> getRecentView(RequestSessionTarget requestSessionTarget);
    boolean isGood(RequestContentMember requestContentMember);
    boolean isMine(RequestContentMember requestContentMember);

    //update
    void updateVisibility(RequestVisibility requestVisibility);
    void updateTitle(RequestTitle requestTitle);

    //delete
    void deleteContent(Long id);
    void deleteGood(RequestContentMember requestContentMember);
    void deleteOwner(RequestContentMember requestContentMember);
    void deletePin(RequestContentMember requestContentMember);
}
