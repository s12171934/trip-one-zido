package com.example.triponezidoapi.bookmark;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookmarkMapper {
    //insert
    void addBookmark(RequestContentMember requestContentMember);

    //select
    int bookmarkCount(Long id);
    int tourBookmarkCount(Long id);
    int planSpotBookmarkCount(RequestSessionTarget requestSessionTarget);
    List<ResponseTour> getTourBookmark(RequestSessionTarget requestSessionTarget);
    List<ResponseContentList> getPlanSpotBookmark(RequestSessionTarget requestSessionTarget);

    //update
    void deleteBookmark(RequestContentMember requestContentMember);
}
