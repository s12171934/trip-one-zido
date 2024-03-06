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

    //select
    List<ResponseMember> getOwner(Long id);
    ResponseMember getWriter(Long id);
    List<ResponseContentList> getRecentView(RequestSessionTarget requestSessionTarget);
    boolean isGood(RequestContentMember requestContentMember);
    int isMine(RequestContentMember requestContentMember);

    //update
    void updateIsPublic(RequestIsPublic requestIsPublic);
    void updateTitle(RequestTitle requestTitle);

    //delete
    void deleteContent(Long id);
    void deleteGood(RequestContentMember requestContentMember);
    void deleteOwner(RequestContentMember requestContentMember);
    void deletePin(RequestContentMember requestContentMember);
}
