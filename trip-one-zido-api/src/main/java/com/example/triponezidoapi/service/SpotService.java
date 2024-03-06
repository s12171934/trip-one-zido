package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpotService {
    @Autowired
    SpotMapper spotMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    CommentMapper commentMapper;

    public ResponseSpotDetail spotDetail(Long id, Long sessionId){
        //spotDetail 기본
        RequestSessionTarget requestSessionTarget = new RequestSessionTarget();
        requestSessionTarget.setTargetId(id);
        requestSessionTarget.setMyMemberId(sessionId);
        ResponseSpotDetail responseSpotDetail = spotMapper.getSpot(requestSessionTarget);

        //getComment
        responseSpotDetail.setResponseComments(commentMapper.getComment(id));
        //getOwner
        responseSpotDetail.setMembers(contentMapper.getOwner(id));
        //isMine
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setMemberId(sessionId);
        requestContentMember.setContentId(id);
        responseSpotDetail.setMine(contentMapper.isMine(requestContentMember));

        return responseSpotDetail;
    }

    public void addSpot(RequestSpot requestSpot, Long sessionId){

        //addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("spot");
        requestContent.setTitle(requestSpot.getTitle());

        if(requestSpot.getIsPublic() == null){
            requestContent.setPublic(true);
        } else {
            requestContent.setPublic(false);
        }
        contentMapper.addContent(requestContent);

        //addSpot
        requestSpot.setProfile(sessionId);
        spotMapper.addSpot(requestSpot);

        //addPhoto
        for (RequestPhoto photos :  requestSpot.getPhotos()) {
            spotMapper.addPhoto(photos);
        }

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(sessionId);
        requestOwner.setContentId(requestSpot.getId());
        contentMapper.addOwner(requestOwner);
    }

    public void updateSpot(Long id, RequestSpot requestSpot){
//        updateSpot,updatePublic,updateTitle,deleteOwner,addOwner

        //updateSpot
        requestSpot.setId(id);
        spotMapper.updateSpot(requestSpot);

        //updatePublic
        RequestIsPublic requestIsPublic = new RequestIsPublic();
        requestIsPublic.setId(id);
        requestIsPublic.setIsPublic(requestSpot.getIsPublic());
        contentMapper.updateIsPublic();
    }

    public void deleteSpot(Long id){
        contentMapper.deleteContent(id);
    }
}
