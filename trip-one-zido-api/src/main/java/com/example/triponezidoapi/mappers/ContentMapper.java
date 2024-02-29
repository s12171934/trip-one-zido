package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.beforeUse.RequestListInfo;
import com.example.triponezidoapi.dto.beforeUse.RequestOwner;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseMember;
import com.example.triponezidoapi.dto.beforeUse.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    void addContent(Content content);
    void addOwner(RequestOwner requestOwner);
    void deleteOwner(RequestOwner requestOwner);
    void deleteContent(long id);
    List<ResponseMember> getContentMembers(long id);
    ResponseMember getWriter(long id);
    void updateTitle(Content content);
    void updateIsPublic(Content content);
    void addPin(RequestOwner requestOwner);
    void deletePin(RequestOwner requestOwner);
    void addGood(RequestOwner requestOwner);
    void deleteGood(RequestOwner requestOwner);
    List<ResponseContentList> getRecentView(RequestListInfo requestListInfo);
}
