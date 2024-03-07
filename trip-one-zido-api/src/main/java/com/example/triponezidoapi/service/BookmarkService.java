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

    // tourBookmarkCount,
    // planSpotBookmarkCount,
    public ResponseBookmark getAllBookmark(Long id, Long sessionId){
        //id가 null일때 세션정보를 이용한다
          if(id == null){
              id = sessionId;
          }

        ResponseBookmark responseBookmark = new ResponseBookmark();

        responseBookmark.setTourBookmarkCount(bookmarkMapper.tourBookmarkCount(id));
        responseBookmark.setPlanSpotBookMarkCount(bookmarkMapper.planSpotBookmarkCount(id));
        responseBookmark.setTourList(getTourBookmark(id,0, sessionId));
        responseBookmark.setContentList(getPlanSpotBookmark(id,0, sessionId));

        return responseBookmark;
    }

    public List<ResponseContentList> getPlanSpotBookmark(Long id, long page, Long sessionId){
        //id가 null일때 세션정보를 이용한다
          if(id == null){
              id = sessionId;
          }
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(sessionId);
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setPage(page);
        List<ResponseContentList> responseContentLists = bookmarkMapper.getPlanSpotBookmark(requestSessionTarget);
        return responseContentLists;
    }

    public List<ResponseTour> getTourBookmark(Long id, long page, Long sessionId){
        //id가 null일때 세션정보를 이용한다
          if(id == null){
              id = sessionId;
          }
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(sessionId);
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
