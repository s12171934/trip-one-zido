package com.example.triponezidoapi.mappers;

import com.example.triponezidoapi.dto.beforeUse.RequestListInfo;
import com.example.triponezidoapi.dto.beforeUse.RequestOwner;
import com.example.triponezidoapi.dto.Response.ResponseContentList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookmarkMapper {
    int bookmarkCount(long id);
    int tourBookmarkCount(long id);
    int planSpotBookmarkCount(long id);
    List<ResponseContentList> getTourBookmark(RequestListInfo requestListInfo);
    List<ResponseContentList> getPlanSpotBookmark(RequestListInfo requestListInfo);
    void addBookmark(RequestOwner requestOwner);
    void deleteBookmark(RequestOwner requestOwner);
}
