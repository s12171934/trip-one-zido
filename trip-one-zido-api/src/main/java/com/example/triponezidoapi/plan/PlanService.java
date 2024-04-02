package com.example.triponezidoapi.plan;

import com.example.triponezidoapi.commnet.CommentService;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.member.MemberMapper;
import com.example.triponezidoapi.spot.SpotMapper;
import com.example.triponezidoapi.spot.SpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanService {
    @Autowired
    PlanMapper planMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    CommentService commentService;
    @Autowired
    SpotMapper spotMapper;
    @Autowired
    SpotService spotService;
    @Autowired
    MemberMapper memberMapper;

    //일정 삭제
    public void deletePlan(Long id){
        contentMapper.deleteContent(id);
    }

    //일정 상세 조회
    public ResponsePlanDetail getPlan(RequestSessionTarget requestSessionTarget) {
        Long id = requestSessionTarget.getTargetId();
        Long sessionId = requestSessionTarget.getMyMemberId();

        //일정 게시물에 딸린 장소 게시물 목록
        List<ResponseSpotPlan> responseSpotPlan = planMapper.getSpot(id);
        for(int i =0; i < responseSpotPlan.size(); i++){
            responseSpotPlan.get(i).setPhotos(spotMapper.getPhoto(responseSpotPlan.get(i).getId()));
        }

        //내가 누른 좋아요 및 싫어요 여부
        RequestGood requestGood = new RequestGood();
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);
        Boolean myGood = null;
        if(contentMapper.isGood(requestGood) != 0){
            myGood = contentMapper.myGood(requestGood);
        }

        ResponsePlanDetail responsePlanDetail = planMapper.getPlan(requestSessionTarget);
        responsePlanDetail.setSpots(responseSpotPlan);
        responsePlanDetail.setWriter(contentMapper.getWriter(id));
        responsePlanDetail.setMembers(contentMapper.getWith(id));
        responsePlanDetail.setComments(commentService.getComments(id));
        responsePlanDetail.setMyGood(myGood);
        responsePlanDetail.setMine(contentMapper.getWriter(id).getId().equals(sessionId));
        return responsePlanDetail;
    }

    //일정 등록
    public void addPlan(RequestPlan requestPlan) {
        //content
        RequestContent requestContent = new RequestContent();
        requestContent.setType("plan");
        requestContent.setVisibility(requestPlan.isVisibility());
        requestContent.setTitle(requestPlan.getTitle());
        contentMapper.addContent(requestContent);
        //content table AI key
        Long generatedId = requestContent.getId();

        //plan
        requestPlan.setId(generatedId);
        planMapper.addPlan(requestPlan);

        //spots - spot, spotPlan
        for (int i = 0; i < requestPlan.getSpots().size(); i++) {
            RequestSpot requestSpot = requestPlan.getSpots().get(i);
            planSpot(requestSpot, requestPlan);
        }

        //owner - writer
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(requestPlan.getSessionId());
        requestOwner.setContentId(requestPlan.getId());
        contentMapper.addOwner(requestOwner);

        //owner - with
        requestOwner.setOwn("with");
        for (int i = 0; i < requestPlan.getMembers().size(); i++) {
            Long withMemberId = memberMapper.getIdByLoginId(requestPlan.getMembers().get(i).getLoginId());
            requestOwner.setMemberId(withMemberId);
            contentMapper.addOwner(requestOwner);
        }
    }

    //일정 수정
    public void updatePlan(RequestPlan requestPlan) {
        //plan
        Long id = requestPlan.getId();
        planMapper.updatePlan(requestPlan);

        //content
        RequestContent requestContent = new RequestContent();
        requestContent.setId(requestPlan.getId());
        requestContent.setVisibility(requestPlan.isVisibility());
        requestContent.setTitle(requestPlan.getTitle());
        contentMapper.updateContent(requestContent);

        //owner - with 삭제 후 다시 추가
        contentMapper.deletePlanSpotOwner(id);

        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("with");
        requestOwner.setContentId(requestPlan.getId());
        for (int i = 0; i < requestPlan.getMembers().size(); i++) {
            Long withMemberId = memberMapper.getIdByLoginId(requestPlan.getMembers().get(i).getLoginId());
            requestOwner.setMemberId(withMemberId);
            contentMapper.addOwner(requestOwner);
        }

        //spotUpdate
        for(int i =0; i < requestPlan.getSpots().size(); i++) {
            RequestSpot requestSpot = requestPlan.getSpots().get(i);
            if (requestSpot.getId() == 0) {
                //새로운 장소 등록
                requestSpot.setSessionId(requestPlan.getSessionId());
                planSpot(requestSpot, requestPlan);
            } else {
                //기존 글 수정
                requestSpot.setVisibility((requestPlan.isVisibility()));
                requestSpot.setMembers(requestPlan.getMembers());
                spotService.updateSpot(requestSpot);
            }
        }
        //spotDelete
        if(requestPlan.getDeleteSpots() != null) {
            for (int i = 0; i < requestPlan.getDeleteSpots().size(); i++) {
                spotService.deleteSpot(requestPlan.getDeleteSpots().get(i));
            }
        }
    }

    //일정 게시물에 따른 장소 등록을 위한 변환
    private void planSpot(RequestSpot requestSpot, RequestPlan requestPlan){
        //한국 시간 조정
        requestSpot.setStartDate(requestSpot.getStartDate().plusSeconds(60 * 60 * 9));
        requestSpot.setEndDate(requestSpot.getEndDate().plusSeconds(60 * 60 * 9));
        requestSpot.setSessionId(requestPlan.getSessionId());
        requestSpot.setVisibility((requestPlan.isVisibility()));
        requestSpot.setMembers(requestPlan.getMembers());

        //spot
        Long generatedSpotId = spotService.addSpot(requestSpot);

        //spotPlan
        RequestPlanSpot requestPlanSpot = new RequestPlanSpot(requestPlan.getId(),generatedSpotId);
        planMapper.addSpot(requestPlanSpot);
    }
}
