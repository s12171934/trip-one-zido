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
    @Autowired
    SpotMapper spotMapper;
    @Autowired
    CommentMapper commentMapper;

    public ResponsePlanDetail getPlanList(long id, long sessionId) {
        // getPlan
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setMyMemberId(id);
        requestSessionTarget.setTargetId(sessionId);
        ResponsePlanDetail responsePlanDetail = planMapper.getPlan(requestSessionTarget);

        // getSpot
        responsePlanDetail.setResponseSpotPlans(planMapper.getSpot(id));

        // getOwner
        responsePlanDetail.setMembers(contentMapper.getOwner(id));

        // getComment
        responsePlanDetail.setComments(commentMapper.getComment(id));

        return responsePlanDetail;
    }

    public void addPlan(long sessionId, RequestPlan requestPlan) {
        //addContent, addPlan, addSpot ,addOwner
        RequestContent requestContent = new RequestContent();
        requestContent.setType("plan");
        requestContent.setTitle(requestContent.getTitle());
        contentMapper.addContent(requestContent);
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(sessionId);
        requestOwner.setContentId(requestOwner.getContentId());
        contentMapper.addOwner(requestOwner);
        RequestSpot requestSpot = new RequestSpot();
        requestSpot.setProfile(sessionId);
        spotMapper.addSpot(requestSpot);
        planMapper.addPlan(requestPlan);
    }

    public void updatePlan(long id, RequestPlan requestPlan) {
//       updatePlan, updateisPublic,updateTitle, deleteOwner,addOwner
        requestPlan.setId(id);
        planMapper.updatePlan(requestPlan);

        RequestIsPublic requestIsPublic = new RequestIsPublic();
        contentMapper.updateIsPublic(requestIsPublic);

        RequestTitle requestTitle = new RequestTitle();
        contentMapper.updateTitle(requestTitle);

        RequestContentMember requestContentMember = new RequestContentMember();
        contentMapper.deleteOwner(requestContentMember);

        RequestOwner requestOwner = new RequestOwner();
        contentMapper.addOwner(requestOwner);

    }

    public void deletePlan(long id){
        contentMapper.deleteContent(id);
    }
}
