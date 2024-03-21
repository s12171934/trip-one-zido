package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
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

    public ResponsePlanDetail getPlan(Long id, Long sessionId) {
        ResponsePlanDetail responsePlanDetail;

        // getPlan
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        responsePlanDetail = planMapper.getPlan(requestSessionTarget);

        // getSpot
        //List<ResponseSpotPlan> spots; 여기에 플랜에 붙은 스팟게시글을 불러와야함
        //ResponseSpotPlan 안에 사진 배열이 존재함
        List<ResponseSpotPlan> responseSpotPlan = planMapper.getSpot(id);
        for(int i =0; i < responseSpotPlan.size(); i++){
            responseSpotPlan.get(i).setPhotos(spotMapper.getPhoto(responseSpotPlan.get(i).getId()));
        }
        responsePlanDetail.setSpots(responseSpotPlan);

        // getOwner
        responsePlanDetail.setMembers(contentMapper.getOwner(id));

        // getComment
        responsePlanDetail.setComments(commentService.getComments(id));

        RequestGood requestGood = new RequestGood();
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);
        if(contentMapper.isGood(requestGood) != 0){
            responsePlanDetail.setMyGood(contentMapper.myGood(requestGood));
        }

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
        requestContent.setVisibility(requestPlan.isVisibility());
        requestContent.setTitle(requestPlan.getTitle());
        contentMapper.addContent(requestContent);
        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        Long generatedId = requestContent.getId();

        // addPlan
        requestPlan.setId(generatedId);
        planMapper.addPlan(requestPlan);

        // addSpot - 장소게시글 번호만 plan_spot에 등록
        for (int i = 0; i < requestPlan.getSpots().size(); i++) {
            requestContent = new RequestContent();
            requestContent.setType("spot");
            requestContent.setVisibility(requestPlan.isVisibility());
            requestContent.setTitle(requestPlan.getTitle());
            contentMapper.addContent(requestContent);
            Long generatedSpotId = requestContent.getId();

            requestPlan.getSpots().get(i).setStartDate(
                requestPlan.getSpots().get(i).getStartDate().plusSeconds(60 * 60 * 9)
            );

            requestPlan.getSpots().get(i).setEndDate(
                requestPlan.getSpots().get(i).getEndDate().plusSeconds(60 * 60 * 9)
            );

            requestPlan.getSpots().get(i).setId(generatedSpotId);
            requestPlan.getSpots().get(i).setLocCategory("서울");
            requestPlan.getSpots().get(i).setMembers(requestPlan.getMembers());
            requestPlan.getSpots().get(i).setVisibility(true);
            spotMapper.addSpot(requestPlan.getSpots().get(i));

            //addPhoto
            for (int j = 0; j < requestPlan.getSpots().get(i).getPhotos().size(); j++) {
                RequestPhoto requestPhoto = new RequestPhoto();
                requestPhoto.setPhoto(requestPlan.getSpots().get(i).getPhotos().get(j).getPhoto());
                requestPhoto.setContentId(generatedSpotId);
                spotMapper.addPhoto(requestPhoto);
            }

            RequestPlanSpot requestPlanSpot = new RequestPlanSpot();
            requestPlanSpot.setPlanId(generatedId);
            requestPlanSpot.setSpotId(requestPlan.getSpots().get(i).getId());
            planMapper.addSpot(requestPlanSpot);

            RequestOwner requestOwner = new RequestOwner();
            requestOwner.setOwn("writer");
            requestOwner.setMemberId(sessionId);
            requestOwner.setContentId(generatedSpotId);
            contentMapper.addOwner(requestOwner);
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

        //updateVisibility
        RequestVisibility requestVisibility = new RequestVisibility();
        requestVisibility.setId(id);
        requestVisibility.setVisibility(!requestPlan.isVisibility());
        contentMapper.updateVisibility(requestVisibility);

        //updateTitle
        RequestTitle requestTitle = new RequestTitle();
        requestTitle.setId(id);
        requestTitle.setTitle(requestPlan.getTitle());
        contentMapper.updateTitle(requestTitle);

        // deleteOwner - 이전에 등록된 동행인(해당 게시글의 동행인 조회) 삭제
        for (int i = 0; i < requestPlan.getMembers().size(); i++) {
            RequestContentMember requestContentMember = new RequestContentMember();
            requestContentMember.setMemberId(requestPlan.getMembers().get(i).getId());
            requestContentMember.setContentId(id);
            contentMapper.deleteOwner(requestContentMember);
        }

        //addOwner
        for (int i = 0; i < requestPlan.getMembers().size(); i++) {
            RequestOwner requestOwner = new RequestOwner();
            requestOwner.setOwn("with");
            requestOwner.setContentId(id);
            requestOwner.setMemberId(requestPlan.getMembers().get(i).getId());
            contentMapper.addOwner(requestOwner);
        }
        //spotUpdate
        for(int i =0; i < requestPlan.getSpots().size(); i++){
            RequestSpot requestSpot = new RequestSpot();
            if(requestPlan.getSpots().get(i).getId() == 0){
                //일정 게시글 수정 클릭후 장소를 신규등록 할 경우
                //Content테이블에 우선 등록
                RequestContent requestContent = new RequestContent();
                requestContent.setType("spot");
                requestContent.setVisibility(requestPlan.isVisibility());
                requestContent.setTitle(requestPlan.getSpots().get(i).getTitle());
                contentMapper.addContent(requestContent);
                Long generatedId = requestContent.getId();

                requestSpot = requestPlan.getSpots().get(i);
                requestSpot.setLocCategory(requestPlan.getLocCategory());
                requestSpot.setId(generatedId);
                requestSpot.setStartDate(
                        requestSpot.getStartDate().plusSeconds(60 * 60 * 9)
                );

                requestSpot.setEndDate(
                        requestSpot.getEndDate().plusSeconds(60 * 60 * 9)
                );
                spotMapper.addSpot(requestSpot);

                //plan_spot에 등록
                RequestPlanSpot requestPlanSpot = new RequestPlanSpot();
                requestPlanSpot.setPlanId(id);
                requestPlanSpot.setSpotId(generatedId);
                planMapper.addSpot(requestPlanSpot);
            } else {
                //기존에 있는 장소게시글 수정일 경우
                requestSpot.setId(requestPlan.getSpots().get(i).getId());
                requestSpot.setCategory(requestPlan.getSpots().get(i).getCategory());
                requestSpot.setLocCategory(requestPlan.getLocCategory());
                requestSpot.setStartDate(requestPlan.getSpots().get(i).getStartDate());
                requestSpot.setEndDate(requestPlan.getSpots().get(i).getEndDate());
                requestSpot.setAddress(requestPlan.getSpots().get(i).getAddress());
                requestSpot.setAddress2(requestPlan.getSpots().get(i).getAddress2());
                requestSpot.setGrade(requestPlan.getSpots().get(i).getGrade());
                requestSpot.setProfile(requestPlan.getSpots().get(i).getProfile());
                requestSpot.setReview(requestPlan.getSpots().get(i).getReview());
                spotMapper.updateSpot(requestSpot);

                //장소 제목 변경
                RequestTitle requestSpotTitle = new RequestTitle();
                requestSpotTitle.setId(requestSpot.getId());
                requestSpotTitle.setTitle(requestPlan.getSpots().get(i).getTitle());
                contentMapper.updateTitle(requestSpotTitle);

                //deletePhoto
                spotMapper.deletePhoto(requestSpot.getId());
            }
            //addPhoto
            for (int j = 0; j < requestPlan.getSpots().get(i).getPhotos().size(); j++) {
                RequestPhoto requestPhoto = new RequestPhoto();
                requestPhoto.setPhoto(requestPlan.getSpots().get(i).getPhotos().get(j).getPhoto());
                requestPhoto.setContentId(requestPlan.getSpots().get(i).getId());
                spotMapper.addPhoto(requestPhoto);
            }
        }
    }

    public void deletePlan(Long id){
        contentMapper.deleteContent(id);
    }
}
