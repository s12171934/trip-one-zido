package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.Content;
import com.example.triponezidoapi.dto.ProfileMember;
import com.example.triponezidoapi.dto.RequestOwner;
import com.example.triponezidoapi.dto.ResponseContentList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    void addContent(Content content);
    void addOwner(RequestOwner requestOwner);
    void deleteOwner(RequestOwner requestOwner);
    void deleteContent(long id);
    List<ProfileMember> getContentMembers(long id);
    ProfileMember getWriter(long id);
    void updateTitle(Content content);
    void updateIsPublic(Content content);
    void addPin(RequestOwner requestOwner);
    void deletePin(RequestOwner requestOwner);
    void addLike(RequestOwner requestOwner);
    void deleteLike(RequestOwner requestOwner);
    List<ResponseContentList> getRecentView(long id);
}
