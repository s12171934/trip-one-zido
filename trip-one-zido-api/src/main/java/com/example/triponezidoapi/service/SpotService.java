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
        //spotDetail 기본 (getSpot)
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
        requestContent.setPublic(requestSpot.isPublic());
        requestContent.setTitle(requestSpot.getTitle());
        contentMapper.addContent(requestContent);
        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        long generatedId = requestContent.getId();

        //addSpot
        requestSpot.setId(generatedId);
        requestSpot.setProfile(generatedId);
        spotMapper.addSpot(requestSpot);

        //addPhoto
        for (int i = 0; i < requestSpot.getPhotos().size(); i++) {
            RequestPhoto requestPhoto = new RequestPhoto();
            requestPhoto.setPhoto(requestSpot.getPhotos().get(i));
            requestPhoto.setContentId(generatedId);
            spotMapper.addPhoto(requestPhoto);
        }
       /* List<byte[]> photos = requestSpot.getPhotos();
         if (photos != null && !photos.isEmpty()) {
             for (byte[] photo : photos) {
                 RequestPhoto requestPhoto = new RequestPhoto();
                 requestPhoto.setPhoto(photo);
                 requestPhoto.setContentId(generatedId);
                 spotMapper.addPhoto(requestPhoto);
             }
         }*/

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setMemberId(sessionId);
        requestOwner.setContentId(generatedId);
        contentMapper.addOwner(requestOwner);
    }

    public void updateSpot(Long id, RequestSpot requestSpot, Long sessionId){
        //updateSpot
        requestSpot.setId(id);
        spotMapper.updateSpot(requestSpot);

        //updatePublic
        RequestIsPublic requestIsPublic = new RequestIsPublic();
        requestIsPublic.setId(id);
        requestIsPublic.setPublic(requestSpot.isPublic());
        contentMapper.updateIsPublic(requestIsPublic);

        //updateTitle
        RequestTitle requestTitle = new RequestTitle();
        requestTitle.setId(requestSpot.getId());
        requestTitle.setTitle(requestSpot.getTitle());
        contentMapper.updateTitle(requestTitle);

        //deleteOwner
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setMemberId(sessionId);
        requestContentMember.setContentId(id);
        contentMapper.deleteOwner(requestContentMember);

        //addOwner
        for (int i = 0; i < requestSpot.getMembers().size(); i++) {
            RequestOwner requestOwner = new RequestOwner();
            requestOwner.setOwn("with");
            requestOwner.setMemberId(requestSpot.getMembers().get(i));
            requestOwner.setContentId(id);
            contentMapper.addOwner(requestOwner);
        }
  /*      RequestOwner requestOwner = new RequestOwner();
        List<ResponseMember> addmembers =contentMapper.getOwner(id);
         if (addmembers != null && !addmembers.isEmpty()) {
             for (ResponseMember memberId : addmembers) {
                 requestOwner.setOwn(memberId.getOwn());
                 requestOwner.setMemberId(Long.parseLong(memberId.getLoginId()));
                 requestOwner.setContentId(id);
                 contentMapper.addOwner(requestOwner);
             }
         }*/
    }

    public void deleteSpot(Long id){
        contentMapper.deleteContent(id);
    }
}
