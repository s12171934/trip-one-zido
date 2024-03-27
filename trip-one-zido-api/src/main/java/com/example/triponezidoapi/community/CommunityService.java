package com.example.triponezidoapi.community;

import com.example.triponezidoapi.community.CommunityMapper;
import com.example.triponezidoapi.content.ContentMapper;
import com.example.triponezidoapi.dto.request.*;
import com.example.triponezidoapi.dto.response.*;
import com.example.triponezidoapi.member.MemberMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class CommunityService {
    @Autowired
    CommunityMapper communityMapper;
    @Autowired
    ContentMapper contentMapper;
    @Autowired
    MemberMapper memberMapper;

    public void deleteCommunity(Long id){
        contentMapper.deleteContent(id);
    }

    public void deleteOwner(RequestContentMember requestContentMember){
        contentMapper.deleteOwner(requestContentMember);
    }

    public ResponseCommunityDetail getCommunity(RequestContentMember requestContentMember){
        communityMapper.upViewPoint(requestContentMember.getContentId());
        //getCommunity 기본
        ResponseCommunityDetail responseCommunityDetail = communityMapper.getCommunity(requestContentMember.getContentId());

        //members(getOwner)
        responseCommunityDetail.setMembers(contentMapper.getOwner(requestContentMember.getContentId()));

        //isMine
        responseCommunityDetail.setMine(contentMapper.isMine(requestContentMember));

        //만약 앞 커뮤니티 게시글이 존재하지 않는다면
        if(communityMapper.getPrevId(requestContentMember.getContentId()) == null){
            responseCommunityDetail.setPrevId(0);
        } else {
            responseCommunityDetail.setPrevId(communityMapper.getPrevId(requestContentMember.getContentId()));
        }
        //만약 다음 커뮤니티 게시글이 존재하지 않는다면
        if(communityMapper.getNextId(requestContentMember.getContentId()) == null){
            responseCommunityDetail.setNextId(0);
        } else {
            responseCommunityDetail.setNextId(communityMapper.getNextId(requestContentMember.getContentId()));
        }

        responseCommunityDetail.setLoginId(memberMapper.getLoginId(requestContentMember.getMemberId()));

        if (responseCommunityDetail.getTotal() > responseCommunityDetail.getMembers().size()){
            RequestCommunity requestCommunity = new RequestCommunity();
            requestCommunity.setId(requestContentMember.getContentId());
            requestCommunity.setStatus(0);
            communityMapper.updateStatus(requestCommunity);
            responseCommunityDetail.setStatus(0);
        }
        return responseCommunityDetail;
    }

    public ResponseCommunityList getCommunityList(long page){
        //페이지 카운트 처리
        if(page != 0){
            page = page * 6;
        }
        ResponseCommunityList responseCommunityList = new ResponseCommunityList();
        responseCommunityList.setCommunityList(communityMapper.getCommunityList(page));
        responseCommunityList.setTotalCount(communityMapper.getCommunityCount());

        return responseCommunityList;
    }

    public ResponseCommunityList getCommunityListWithSearch(RequestCommunitySearch requestCommunitySearch,long page){
        //페이지 카운트 처리
        if(page != 0){
            page = page * 6;
        }
        requestCommunitySearch.setPage(page);
        //타입이 'title' 또는 'writer'에 따라 쿼리가 동적이 되게끔 수정
        if(requestCommunitySearch.getType().equals("1")){
            requestCommunitySearch.setType("title");
        }
        ResponseCommunityList responseCommunityList = new ResponseCommunityList();
        responseCommunityList.setCommunityList(communityMapper.getCommunityListWithSearch(requestCommunitySearch));
        responseCommunityList.setTotalCount(communityMapper.getCommunitySearchCount(requestCommunitySearch));
        return responseCommunityList;
    }

    public void addOwner(RequestContentMember requestContentMember){
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("with");
        requestOwner.setContentId(requestContentMember.getContentId());
        requestOwner.setMemberId(requestContentMember.getMemberId());
        contentMapper.addOwner(requestOwner);

        ResponseCommunityDetail responseCommunityDetail = getCommunity(requestContentMember);
        if (responseCommunityDetail.getTotal() == responseCommunityDetail.getMembers().size()){
            RequestCommunity requestCommunity = new RequestCommunity();
            requestCommunity.setId(requestContentMember.getContentId());
            requestCommunity.setStatus(1);
            communityMapper.updateStatus(requestCommunity);
        }
    }

    public void addCommunity(@Valid RequestCommunity requestCommunity, Long sessionId){
        //addContent
        RequestContent requestContent = new RequestContent();
        requestContent.setType("community");
        requestContent.setTitle(requestCommunity.getTitle());
        contentMapper.addContent(requestContent);

        //Content 테이블에 추가한 이후에 생성된 id를 가져옴
        Long generatedId = requestContent.getId();

        //addCommunity
        requestCommunity.setId(generatedId);
        requestCommunity.setStatus(0);

        // 여행종료일이 여행시작일보다 과거인지 확인
        if (requestCommunity.getEndDate().isBefore(requestCommunity.getStartDate())) {
            throw new IllegalArgumentException("여행 종료일은 여행 시작일보다 미래의 날짜여야 합니다.");
        }
        // 모집마감일이 여행시작일보다 미래인지 확인
        if (requestCommunity.getDeadline().isAfter(requestCommunity.getStartDate())) {
            throw new IllegalArgumentException("모집 마감일은 여행 시작일보다 과거의 날짜여야 합니다.");
        }
        communityMapper.addCommunity(requestCommunity);

        //addOwner
        RequestOwner requestOwner = new RequestOwner();
        requestOwner.setOwn("writer");
        requestOwner.setContentId(generatedId);
        requestOwner.setMemberId(sessionId);
        contentMapper.addOwner(requestOwner);
    }

    public void updateCommunity(RequestCommunity requestCommunity){
        //updateTitle
        RequestTitle requestTitle = new RequestTitle();
        requestTitle.setId(requestCommunity.getId());
        requestTitle.setTitle(requestCommunity.getTitle());
        contentMapper.updateTitle(requestTitle);

        //updateCommunity
        communityMapper.updateCommunity(requestCommunity);
    }
}
