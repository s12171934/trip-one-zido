package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.dto.response.ResponseRecentView;
import com.example.triponezidoapi.mappers.ContentMapper;
import com.example.triponezidoapi.mappers.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;

@Service
public class ContentService {
    @Autowired
     ContentMapper contentMapper;
    @Autowired
    MemberMapper memberMapper;

    // 핀의 최대 갯수
    private static final int MAX_PIN_COUNT = 50;
    public void addPin(Long id, Long sessionId) {
        // 사용자가 추가한 핀의 갯수 확인
        int pinCount = contentMapper.getPinCountByMemberId(sessionId);

        // 핀의 최대 갯수에 도달한 경우 추가 거부
        if (pinCount >= MAX_PIN_COUNT) {
            throw new RuntimeException("핀의 갯수 제한에 도달했습니다.");
        }

        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.addPin(requestContentMember);
    }
    public void deletePin(Long id, Long sessionId) {
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.deletePin(requestContentMember);
    }

    public void toggleGood(Long id, Long sessionId, RequestGood requestGood) {
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);

        if(contentMapper.isGood(requestGood) == 0){
            contentMapper.addGood(requestGood);
            return;
        }

        if(contentMapper.myGood(requestGood) == requestGood.isGood()){
            contentMapper.deleteGood(requestGood);
        }
        else{
            contentMapper.updateGood(requestGood);
        }

}

    public ResponseRecentView getRecentViewPage(Long sessionId) {
        ResponseRecentView responseRecentView = new ResponseRecentView();

        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setPage(0);
        requestSessionTarget.setMyMemberId(sessionId);
        responseRecentView.setRecentList(contentMapper.getRecentView(requestSessionTarget));
        responseRecentView.setMember(memberMapper.getMemberProfile(sessionId));
        responseRecentView.setRecentViewCount(contentMapper.getRecentViewCount(sessionId));
        return responseRecentView;
    }

    public List<ResponseContentList> getRecentView(Long sessionId, long page) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setPage(page * 6);
        requestSessionTarget.setMyMemberId(sessionId);
        return contentMapper.getRecentView(requestSessionTarget);
    }

    public void addRecentView(Long sessionId, long id){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.changeRecentView(requestContentMember);
        contentMapper.addRecentView(requestContentMember);
    }

    public String getAddress(Long id){
        return contentMapper.getAddress(id);
    }
}
