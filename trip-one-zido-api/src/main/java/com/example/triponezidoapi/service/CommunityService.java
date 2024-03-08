package com.example.triponezidoapi.service;

import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.mappers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    ContentMapper contentMapper;

    public List<ResponseCommunity> getCommunityList(long page){
        //페이지 카운트 처리
        if(page != 0){
            page = page * 6;
        }
        return communityMapper.getCommunityList(page);
    }
    public ResponseCommunityDetail getCommunity(Long id, Long sessionId){
        //getCommunity 기본
        ResponseCommunityDetail responseCommunityDetail = communityMapper.getCommunity(id);
        //members(getOwner)
        responseCommunityDetail.setMembers(contentMapper.getOwner(id));
        //isMine
        RequestContentMember contentMember = new RequestContentMember();
        contentMember.setMemberId(sessionId);
        contentMember.setContentId(id);
        responseCommunityDetail.setMine(contentMapper.isMine(contentMember));
        //NextId, PrevId
        responseCommunityDetail.setNextId(communityMapper.getNextId(id));
        responseCommunityDetail.setPrevId(communityMapper.getPrevId(id));

        return responseCommunityDetail;
    }

    public void addCommunity(RequestCommunity requestCommunity, Long sessionId){
        //addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("community");
        requestContent.setTitle(requestCommunity.getTitle());
        contentMapper.addContent(requestContent);
        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        long generatedId = requestContent.getId();

        //addCommunity
        requestCommunity.setId(generatedId);
        requestCommunity.setStatus("모집중");
        communityMapper.addCommunity(requestCommunity);

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setContentId(generatedId);
        requestOwner.setMemberId(sessionId);
        contentMapper.addOwner(requestOwner);
    }

    public void updateCommunity(RequestCommunity requestCommunity, Long id){
        //updateTitle
        RequestTitle requestTitle = new RequestTitle();
        requestTitle.setId(requestCommunity.getId());
        requestTitle.setTitle(requestCommunity.getTitle());
        contentMapper.updateTitle(requestTitle);

        //updateCommunity
        requestCommunity.setId(id);
        communityMapper.updateCommunity(requestCommunity);
    }

    public void deleteCommunity(Long id){
        contentMapper.deleteContent(id);
    }

    public List<ResponseCommunity> getCommunityListWithSearch(RequestCommunitySearch requestCommunitySearch,long page){
        requestCommunitySearch.setPage(page);

        return communityMapper.getCommunityListWithSearch(requestCommunitySearch);
    }

    public void addOwner(Long id, Long sessionId){
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("with");
        requestOwner.setContentId(id);
        requestOwner.setMemberId(sessionId);
        contentMapper.addOwner(requestOwner);
    }

    public void deleteOwner(Long id, Long sessionId){
        RequestContentMember requestContentMember = new RequestContentMember();
        requestContentMember.setContentId(id);
        requestContentMember.setMemberId(sessionId);
        contentMapper.deleteOwner(requestContentMember);
    }

}
