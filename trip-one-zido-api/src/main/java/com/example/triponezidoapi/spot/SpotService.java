package com.example.triponezidoapi.spot;

import com.example.triponezidoapi.commnet.CommentMapper;
import com.example.triponezidoapi.commnet.CommentService;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.member.MemberMapper;
import com.example.triponezidoapi.spot.SpotMapper;
import com.example.triponezidoapi.util.LocationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotService {
    @Autowired
    SpotMapper spotMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    CommentService commentService;

    //DELETE
    public void deleteSpot(Long id){
        contentMapper.deleteContent(id);
    }

    //GET
    public ResponseSpotDetail spotDetail(RequestSessionTarget requestSessionTarget){
        Long id = requestSessionTarget.getTargetId();
        Long sessionId = requestSessionTarget.getMyMemberId();

        //spotDetail(getSpot)
        ResponseSpotDetail responseSpotDetail = spotMapper.getSpot(requestSessionTarget);

        //getPhoto
        responseSpotDetail.setPhotos(spotMapper.getPhoto(id));

        //getComment
        responseSpotDetail.setComments(commentService.getComments(id));

        //getOwner
        responseSpotDetail.setWriter(contentMapper.getWriter(id));
        responseSpotDetail.setMembers(contentMapper.getWith(id));

        //isGood
        RequestGood requestGood = new RequestGood();
        requestGood.setMemberId(sessionId);
        requestGood.setContentId(id);
        if(contentMapper.isGood(requestGood) != 0){
            responseSpotDetail.setMyGood(contentMapper.myGood(requestGood));
        }

        //isMine
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setMemberId(sessionId);
        requestContentMember.setContentId(id);
        responseSpotDetail.setMine(contentMapper.isMine(requestContentMember));

        return responseSpotDetail;
    }
  
    //POST
    public Long addSpot(RequestSpot requestSpot){
        //addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("spot");
        requestContent.setVisibility(requestSpot.isVisibility());
        requestContent.setTitle(requestSpot.getTitle());
        contentMapper.addContent(requestContent);

        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        Long generatedId = requestContent.getId();
        long profileId = 0;

        //addPhoto
        for (int i = 0; i < requestSpot.getPhotos().size(); i++) {
            RequestPhoto requestPhoto = new RequestPhoto();
            requestPhoto.setPhoto(requestSpot.getPhotos().get(i).getPhoto());
            requestPhoto.setContentId(generatedId);
            spotMapper.addPhoto(requestPhoto);

            if(requestSpot.getProfile() == i){
                profileId = requestPhoto.getId();
            }
        }

        //addSpot
        requestSpot.setId(generatedId);
        requestSpot.setLocCategory(LocationCode.getCode(requestSpot.getAddress()));
        requestSpot.setProfile(profileId);
        spotMapper.addSpot(requestSpot);

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(requestSpot.getSessionId());
        requestOwner.setContentId(generatedId);
        contentMapper.addOwner(requestOwner);

        //getWriterOwner - 동행인 등록전 writer가 누군지 저장
        ResponseMember responseMember =  contentMapper.getWriter(generatedId);
        for (int i = 0; i < requestSpot.getMembers().size(); i++) {
            //writer의 loginId와 동행인의 LoginId 비교하여 다를 경우 동행인으로 저장
            if(!responseMember.getLoginId().equals(requestSpot.getMembers().get(i).getLoginId())){
                RequestOwner requestWithOwner = new RequestOwner();
                requestWithOwner.setOwn("with");
                requestWithOwner.setMemberId(memberMapper.getIdByLoginId(requestSpot.getMembers().get(i).getLoginId()));
                requestWithOwner.setContentId(requestSpot.getId());
                contentMapper.addOwner(requestWithOwner);
            }
        }
        return generatedId;
    }

    //PUT
    public void updateSpot(RequestSpot requestSpot){
        Long id = requestSpot.getId();
        //deletePhoto
        spotMapper.deletePhoto(id);

        long profileId = 0;

        //addPhoto
        for (int i = 0; i < requestSpot.getPhotos().size(); i++) {
            RequestPhoto requestPhoto = new RequestPhoto();
            requestPhoto.setPhoto(requestSpot.getPhotos().get(i).getPhoto());
            requestPhoto.setContentId(id);
            spotMapper.addPhoto(requestPhoto);
            if(requestSpot.getProfile() == i){
                profileId = requestPhoto.getId();
            }
        }
        //updateSpot
        requestSpot.setLocCategory(LocationCode.getCode(requestSpot.getAddress()));
        requestSpot.setProfile(profileId);
        spotMapper.updateSpot(requestSpot);

        //updatePublic
        RequestVisibility requestVisibility = new RequestVisibility();
        requestVisibility.setId(id);
        requestVisibility.setVisibility(requestSpot.isVisibility());
        contentMapper.updateVisibility(requestVisibility);

        //updateTitle
        RequestTitle requestTitle = new RequestTitle();
        requestTitle.setId(requestSpot.getId());
        requestTitle.setTitle(requestSpot.getTitle());
        contentMapper.updateTitle(requestTitle);

        //deletePlanSpotOwner - 게시글에 등록되어있던 writer 제외, 동행인 전부 삭제
        contentMapper.deletePlanSpotOwner(id);

        //addOwner
        for (int i = 0; i < requestSpot.getMembers().size(); i++) {
            ResponseMember responseMember = contentMapper.getWriter(id);
            if(!responseMember.getLoginId().equals(requestSpot.getMembers().get(i).getLoginId())) {
                RequestOwner requestOwner = new RequestOwner();
                requestOwner.setOwn("with");
                requestOwner.setMemberId(memberMapper.getIdByLoginId(requestSpot.getMembers().get(i).getLoginId()));
                requestOwner.setContentId(id);
                contentMapper.addOwner(requestOwner);
            }
        }
    }

}
