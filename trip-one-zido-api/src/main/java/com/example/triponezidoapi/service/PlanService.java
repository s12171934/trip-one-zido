package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanService {
    @Autowired // 자동생성자
    PlanMapper planMapper;
    @Autowired // 자동생성자
    ContentMapper contentMapper;

    public List<ResponseContentList> getPlanList(long id, long sessionId) {
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setTargetId(sessionId);
        return planMapper.getPlanList(requestSessionTarget);
    }

    public void addPlan(long sessionId, RequestPlan requestPlan) {
        planMapper.addPlan(requestPlan);
    }

    public void updatePlan(long id, RequestPlan requestPlan) {
        requestPlan.setId(id);
        planMapper.updatePlan(requestPlan);
    }

    public void deletePlan(long id){
        contentMapper.deleteContent(id);
    }
}
