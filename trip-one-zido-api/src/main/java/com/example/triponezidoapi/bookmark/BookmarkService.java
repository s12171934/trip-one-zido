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

    public void deleteBookmark(RequestContentMember requestContentMember){
        bookmarkMapper.deleteBookmark(requestContentMember);
    }

    public ResponseBookmark getAllBookmark(RequestSessionTarget requestSessionTarget){
        ResponseBookmark responseBookmark = new ResponseBookmark();
        responseBookmark.setId(requestSessionTarget.getTargetId());
        responseBookmark.setLoginId(memberMapper.getLoginId(requestSessionTarget.getTargetId()));
        responseBookmark.setTourBookmarkCount(bookmarkMapper.tourBookmarkCount(requestSessionTarget.getTargetId()));
        responseBookmark.setPlanSpotBookMarkCount(bookmarkMapper.planSpotBookmarkCount(requestSessionTarget));

        responseBookmark.setTourList(getTourBookmark(requestSessionTarget));
        responseBookmark.setContentList(getPlanSpotBookmark(requestSessionTarget));
        return responseBookmark;
    }

    public List<ResponseContentList> getPlanSpotBookmark(RequestSessionTarget requestSessionTarget){
        return bookmarkMapper.getPlanSpotBookmark(requestSessionTarget);
    }

    public List<ResponseTour> getTourBookmark(RequestSessionTarget requestSessionTarget){
        return bookmarkMapper.getTourBookmark(requestSessionTarget);
    }

    public void addBookMark(RequestContentMember requestContentMember){
        bookmarkMapper.addBookmark(requestContentMember);
    }
}
