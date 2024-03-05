package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.BookmarkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    BookmarkMapper bookmarkMapper;

    public ResponseBookmark getAllBookmark(long id, long sessionId){
        ResponseBookmark responseBookmark = new ResponseBookmark();

        responseBookmark.setTourBookmarkCount(bookmarkMapper.tourBookmarkCount(id));
        responseBookmark.setPlanSpotBookMarkCount(bookmarkMapper.planSpotBookmarkCount(id));
        responseBookmark.setTourList(getTourBookmark(id,0));
        responseBookmark.setContentList(getPlanSpotBookmark(id,0));
        
        return responseBookmark;
    }
    public List<ResponseContentList> getPlanSpotBookmark(long id, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);
        List<ResponseContentList> responseContentLists = bookmarkMapper.getPlanSpotBookmark(requestSessionTarget);
        return responseContentLists;
    }
    public List<ResponseTour> getTourBookmark(long id, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);
        List<ResponseTour> responseTours = bookmarkMapper.getTourBookmark(requestSessionTarget);
        return responseTours;
    }

    public void addBookMark(long id, long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        bookmarkMapper.addBookmark(requestContentMember);
    }
    public void deleteBookmark(long id, long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        bookmarkMapper.deleteBookmark(requestContentMember);
    }
}
