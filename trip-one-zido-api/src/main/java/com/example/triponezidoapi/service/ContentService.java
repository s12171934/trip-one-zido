package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.ResponseContentList;
import com.example.triponezidoapi.mappers.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContentService {
    @Autowired
     ContentMapper contentMapper;

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
    public boolean isGood(Long id, Long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        return contentMapper.isGood(requestContentMember);
    }
    public void addGood(Long id, Long sessionId, RequestGood requestGood) {
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);
        contentMapper.addGood(requestGood);
    }
    public List<ResponseContentList> getRecentView(Long sessionId) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();

        requestSessionTarget.setMyMemberId(sessionId);
        return contentMapper.getRecentView(requestSessionTarget);
    }
}
