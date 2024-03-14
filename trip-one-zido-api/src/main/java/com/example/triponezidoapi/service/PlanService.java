package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    @Autowired
    PlanMapper planMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    CommentMapper commentMapper;

    public ResponsePlanDetail getPlan(Long id, Long sessionId) {
        ResponsePlanDetail responsePlanDetail;

        // getPlan
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        responsePlanDetail = planMapper.getPlan(requestSessionTarget);

        // getSpot
        responsePlanDetail.setResponseSpotPlans(planMapper.getSpot(id));

        // getOwner
        responsePlanDetail.setMembers(contentMapper.getOwner(id));

        // getComment
        responsePlanDetail.setComments(commentMapper.getComment(id));

        // isMine
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        responsePlanDetail.setMine(contentMapper.isMine(requestContentMember));

        return responsePlanDetail;
    }

    public void addPlan(Long sessionId, RequestPlan requestPlan) {
        // addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("plan");
        requestContent.setPublic(requestPlan.isPublic());
        requestContent.setTitle(requestPlan.getTitle());
        contentMapper.addContent(requestContent);
        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        long generatedId = requestContent.getId();

        // addPlan
        requestPlan.setId(generatedId);
        planMapper.addPlan(requestPlan);


        // addSpot - 장소게시글 번호만 plan_spot에 등록
        for (int i = 0; i < requestPlan.getSpots().size(); i++) {
            RequestPlanSpot requestPlanSpot = new RequestPlanSpot();
            requestPlanSpot.setPlanId(generatedId);
            requestPlanSpot.setSpotId(requestPlan.getSpots().get(i));
            planMapper.addSpot(requestPlanSpot);
        }

        // addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(sessionId);
        requestOwner.setContentId(requestPlan.getId());
        contentMapper.addOwner(requestOwner);
    }

    public void updatePlan(long id, RequestPlan requestPlan) {
        // updatePlan
        requestPlan.setId(id);
        planMapper.updatePlan(requestPlan);

        //updateIsPublic
        RequestIsPublic requestIsPublic = new RequestIsPublic();
        requestIsPublic.setId(id);
        requestIsPublic.setPublic(!requestPlan.isPublic());
        contentMapper.updateIsPublic(requestIsPublic);

        //updateTitle
        RequestTitle requestTitle = new RequestTitle();
        requestTitle.setId(id);
        requestTitle.setTitle(requestPlan.getTitle());
        contentMapper.updateTitle(requestTitle);


        // deleteOwner - 이전에 등록된 동행인(해당 게시글의 동행인 조회) 삭제
        for (int i = 0; i < requestPlan.getMembers().size(); i++) {
            RequestContentMember requestContentMember = new RequestContentMember();
            requestContentMember.setMemberId(requestPlan.getMembers().get(i));
            requestContentMember.setContentId(id);
            contentMapper.deleteOwner(requestContentMember);
        }

        //addOwner
        for (int i = 0; i < requestPlan.getMembers().size(); i++) {
            RequestOwner requestOwner = new RequestOwner();
            requestOwner.setOwn("with");
            requestOwner.setContentId(id);
            requestOwner.setMemberId(requestPlan.getMembers().get(i));
            contentMapper.addOwner(requestOwner);
        }
    }

    public void deletePlan(Long id){
        contentMapper.deleteContent(id);
    }
}
