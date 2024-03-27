package com.example.triponezidoapi.bookmark;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.bookmark.BookmarkMapper;
import com.example.triponezidoapi.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    BookmarkMapper bookmarkMapper;
    @Autowired
    MemberMapper memberMapper;

    // tourBookmarkCount,
    // planSpotBookmarkCount,
    public ResponseBookmark getAllBookmark(Long id, Long sessionId){
        //id가 null일때 세션정보를 이용한다
        if(id == null){
            id = sessionId;
        }
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        ResponseBookmark responseBookmark = new ResponseBookmark();
        responseBookmark.setId(id);
        responseBookmark.setLoginId(memberMapper.getLoginId(id));
        responseBookmark.setTourBookmarkCount(bookmarkMapper.tourBookmarkCount(id));
        responseBookmark.setPlanSpotBookMarkCount(bookmarkMapper.planSpotBookmarkCount(requestSessionTarget));
        responseBookmark.setTourList(getTourBookmark(id,sessionId,0));
        responseBookmark.setContentList(getPlanSpotBookmark(id,sessionId,0));
        
        return responseBookmark;
    }
    public List<ResponseContentList> getPlanSpotBookmark(Long id, Long sessionId, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        //페이지 카운트 처리
        if(page == 0){
            requestSessionTarget.setPage(0);
        } else {
            requestSessionTarget.setPage(page * 6);
        }
        List<ResponseContentList> responseContentLists = bookmarkMapper.getPlanSpotBookmark(requestSessionTarget);
        return responseContentLists;
    }
    public List<ResponseTour> getTourBookmark(Long id, Long sessionId, long page){
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(sessionId);
        requestSessionTarget.setTargetId(id);
        //페이지 카운트 처리
        if(page == 0){
            requestSessionTarget.setPage(0);
        } else {
            requestSessionTarget.setPage(page * 6);
        }
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
