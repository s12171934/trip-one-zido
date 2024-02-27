package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.Content;
import com.example.triponezidoapi.dto.ProfileMember;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentMapper {
    void addContent(Content content);
    List<ProfileMember> getContentMembers(long id);
    ProfileMember getWriter(long id);
}
