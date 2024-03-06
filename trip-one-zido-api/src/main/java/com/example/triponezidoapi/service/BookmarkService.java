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

    public ResponseBookmark getAllBookmark(Long id, Long sessionId){
        //id가 null일때 세션정보를 이용한다
        ResponseBookmark responseBookmark = new ResponseBookmark();

        responseBookmark.setTourBookmarkCount(bookmarkMapper.tourBookmarkCount(id));
        responseBookmark.setPlanSpotBookMarkCount(bookmarkMapper.planSpotBookmarkCount(id));
        responseBookmark.setTourList(getTourBookmark(id,0));
        responseBookmark.setContentList(getPlanSpotBookmark(id,0));
        
        return responseBookmark;
    }
    public List<ResponseContentList> getPlanSpotBookmark(Long id, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);
        List<ResponseContentList> responseContentLists = bookmarkMapper.getPlanSpotBookmark(requestSessionTarget);
        return responseContentLists;
    }
    public List<ResponseTour> getTourBookmark(Long id, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);
        List<ResponseTour> responseTours = bookmarkMapper.getTourBookmark(requestSessionTarget);
        return responseTours;
    }

    public void addBookMark(Long id, Long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        bookmarkMapper.addBookmark(requestContentMember);
    }
    public void deleteBookmark(Long id, Long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        bookmarkMapper.deleteBookmark(requestContentMember);
    }
}
