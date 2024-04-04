package com.example.triponezidoapi.content;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.member.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    MemberMapper memberMapper;

    //주소 가져오기
    public String getAddress(Long id){
        return contentMapper.getAddress(id);
    }

    //최근본 게시물 조회
    public ResponseRecentView getRecentViewPage(Long sessionId) {
        //mapper와 통신에 필요한 dto
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setPage(0);
        requestSessionTarget.setMyMemberId(sessionId);

        return new ResponseRecentView(
                contentMapper.getRecentView(requestSessionTarget),
                memberMapper.getMemberProfile(sessionId),
                contentMapper.getRecentViewCount(sessionId)
        );
    }

    //최근 본 게시물 더보기 버튼 클릭시
    public List<ResponseContentList> getRecentView(RequestSessionTarget requestSessionTarget) {
        return contentMapper.getRecentView(requestSessionTarget);
    }

    //최근 본 게시물 등록
    public void addRecentView(RequestSessionTarget requestSessionTarget){
        contentMapper.changeRecentView(requestSessionTarget);
        contentMapper.addRecentView(requestSessionTarget);
    }

    //좋아요 및 싫어요
    public void toggleGood(RequestGood requestGood) {
        //좋아요 및 싫어요를 누르지 않았을 경우
        if(contentMapper.isGood(requestGood) == 0){
            contentMapper.addGood(requestGood);
            return;
        }
        //이미 좋아요 및 싫어요가 선택되어 있을 때, 변경 및 삭제
        if(contentMapper.myGood(requestGood) == requestGood.isGood()){
            contentMapper.deleteGood(requestGood);
        }
        else{
            contentMapper.updateGood(requestGood);
        }
    }
}
