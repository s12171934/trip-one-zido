import data from "@/assets/data";

export default {
  //BookMark

  //찜하기
  //DELETE -- api/bookmark/id
  //PUT -- api/bookmark/id
  toggleBookmark(id, content) {
    if(!content.myBookmark){
        axios.put(`/api/bookmark/${id}`)
          .then(response => {
            console.log(response.data);
            return content.myBookmark = !content.myBookmark;
          })
          .catch(error => {
            console.error('찜하기 요청 오류', error);
            throw error;
          });
    } else {
        axios.delete(`/api/bookmark/${id}`)
            .then(response => {
              console.log(response.data);
              return content.myBookmark = !content.myBookmark;
            })
            .catch(error => {
              console.error('찜삭제 요청 오류', error);
              throw error;
            });
      }
  },

  //찜페이지 조회
  //GET -- api/bookmark/id
  getBookmarkById(id) {
    return axios.get(`/api/bookmark/${id}`)
      .then(response => {
        console.log(response.data);
        return response.data.bookmark[id ? id : 1];
      })
      .catch(error => {
        console.error('찜페이지 조회 요청 오류', error);
        throw error;
      });
  },

  //Comment

  //댓글 삭제
  //DELETE -- api/comment/id
  deleteComment(targetId) {},

  //댓글 등록
  //POST -- api/comment
  addComment(targetId, comment) {},

  //댓글 수정
  //PUT -- api/comment/id
  editComment(targetId, comment) {},

  //Community

  //커뮤니티 삭제
  //DELETE -- api/community/id
  deleteCommunity(targetId) {
    axios.delete(`/api/community/${targetId}`)
      .then(response => {
        console.log(response.data);
        return response.data;
      })
      .catch(error => {
        console.error('커뮤니티 삭제 요청 오류', error);
        throw error;
      });
  },

  //커뮤니티 참여 취소
  //DELETE -- api/community/member/id
  joinCancleCommunity(targetId) {
    axios.delete(`/api/community/${targetId}`)
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('커뮤니티 참여/취소 요청 오류', error);
        throw error;
      });
  },

  //커뮤니티 상세 조회
  //GET -- api/community/id
  getCommunityDetail(targetId) {
    return axios.get(`/api/community/${targetId}`)
      .then(response => {
        console.log(response.data);
        return response.data.communityDetail[targetId];
      })
      .catch(error => {
        console.error('커뮤니티 상세 조회 요청 오류', error);
        throw error;
      });
  },

  //커뮤니티 목록 조회
  //GET -- api/community/list/page
  //GET -- api/community/search/page -> query를 통해 GET전송이 어떤가?
  //POST -- 커뮤니티 목록 조회와 검색한 커뮤니티 목록 결합
  postCommunityList(options) {
    return axios.post(`/api/community/list`, options)
      .then(response => {
        console.log(response.data);
        return response.data.communityList;
      })
      .catch(error => {
        console.error('커뮤니티 목록 조회 요청 오류', error);
        throw error;
      });
  },

  //커뮤니티 참여
  //POST -- api/community/member/id
  joinCommunity(targetId) {
    axios.post(`/api/community/member/${targetId}`)
      .then(response => {
        console.log(response.data);
        return response.data;
      })
      .catch(error => {
        console.error('커뮤니티 참여 요청 오류', error);
        throw error;
      });
  },

  //커뮤니티 등록
  //POST -- api/community
  addCommunity(communityData) {
    axios.post(`/api/community`, communityData)
      .then(response => {
        console.log(response.data);
        return response.data;
      })
      .catch(error => {
        console.error('커뮤니티 등록 오류', error);
        throw error;
      });
  },

  //커뮤니티 수정
  //PUT -- api/community/id
  updateCommunity(targetId, communityData) {
    axios.put(`/api/community/{targetId}`, communityData)
    .then(response => {
      console.log(response.data);
      return response.data;
    })
    .catch(error => {
      console.error('커뮤니티 수정 오류', error);
      throw error;
    });
  },

  //Content

  //최근 본 게시물 조회
  //GET -- api/content/recent-view
  getRecentView() {
    return data.recentView;
  },

  //공개 비공개 여부
  //미구현 PUT -- api/content/public -> 일정, 장소 조회시 토글로 변경가능?
  togglePublic(targetId) {},

  //Follow

  //팔로우 및 언팔로우
  //DELETE -- api/page/follow/id
  //POST -- api/page/follow/id
  toggleFollow(userProfile) {
    userProfile.isFollow = !userProfile.isFollow;
  },

  //팔로잉, 팔로워 목록 조회
  //GET -- api/page/follower/id/page -> 현재는 페이징 없음
  //GET -- api/page/following/id/page -> 현재는 페이징 없음
  getFollowList(type, targetId, sessionId) {
    console.log(type, targetId, sessionId);
    return data.userProfiles;
  },

  //Good

  //좋아요 싫어요
  //POST -- api/content/good/id
  toggleLike(content, like) {
    if (content.myLike == like) {
      content.myLike = null;
    } else {
      content.myLike = like;
    }
  },

  //Member

  //회원탈퇴 확인
  //DELETE -- api/member -> 비밀번호, 비밀번호 확인 추가 전송
  checkResign(password, passwordCheck) {
    return true;
  },

  //회원정보 조회
  //GET -- api/member
  getUserInfo() {
    return data.userInfo;
  },

  //보안질문 목록 조회
  //GET -- api/member/signup
  getSecurityQuestions() {
    return data.securityQuestions;
  },

  //비밀번호 찾기 보안질문 조회
  //GET -- api/member/check/id
  getSecurityQuestion(id) {
    return "질문지도하기";
  },

  //회원 페이지 조회
  //GET -- api/page/id
  getMemberPageData(id) {
    return data.memberPageData[id ? id : 1];
  },

  //프로필 사진 가져오기
  //미구현 GET -- api/member/profile -> header와 프로필 사진 편집시 필요
  getProfileImg() {
    return data.userProfiles[1].imgSrc;
  },

  //설정 페이지 조회
  //미구현 GET -- api/member/config
  getConfigData() {
    return data.configData;
  },

  //회원가입
  //POST -- api/member/signup
  signUp(form) {
    return true;
  },

  //아이디 중복 확인
  //POST -- api/member/signup/loginId
  checkLoginId(loginId) {
    return true;
  },

  //로그인
  //POST -- api/member/login
  login(loginId, password) {
    return true;
  },

  //비밀번호 찾기 보안답변 전송
  //POST -- api/member/check -> id를 requestBody로 편입해 중복 방지
  checkSecurityAnswer(securityAnswer) {
    return true;
  },

  //비밀번호 찾기 회원번호 조회
  //POST -- api/member/check/pw
  findPassword(name, email, loginId) {
    return 1;
  },

  //아이디 찾기
  //POST -- api/member/check/id
  findId(name, email) {
    return null;
  },

  //프로필 사진 변경
  //PUT -- api/member/profile
  editProfileImg(img) {},

  //비밀번호 변경
  //PUT -- api/member/password
  changePassword(prevPassword, newPassword, newPasswordCheck) {
    return true;
  },

  //비밀번호 재설정
  //PUT -- api/member/password/id
  resetPassword(id, newPassword, newPasswordCheck) {
    return true;
  },

  //회원정보수정
  //PUT -- api/member
  updateUserInfo(userInfo) {
    alert(userInfo.name);
  },

  //More

  //데이터 목록 더보기
  //GET Methods -> page전까지가 addApi
  //api/bookmark/id/tour/page
  //api/bookmark/id/spot-plan/page
  //미구현 api/tour/list/loc/page
  //미구현 api/content/recent-view/page
  //api/page/id/plan/page
  //api/page/id/spot/page
  //미구현 api/search/keyword/spot/page
  //미구현 api/search/keyword/plan/page
  //미구현 POST -- api/search/spot/page
  //미구현 POST -- api/search/plan/page
  newContents(addApi, page, method) {
    alert(method);
    return data.newContent;
  },

  //계정정보 더보기
  //미구현 GET -- api/search/member/keyword/page
  newMembers(page) {
    alert(page);
    return data.newMember;
  },

  //Pin

  //게시물 고정 취소
  //DELETE -- api/content/pin/id

  //게시물 고정
  //POST -- api/content/pin/id

  //Plan

  //일정 삭제
  //DELETE -- api/plan/id
  deletePlan(targetId) {},

  //일정 상세 조회
  //GET -- api/plan/id
  getPlanData(id) {
    return data.planData[id];
  },

  //일정 등록
  //POST -- api/plan
  addPlan(planData) {},

  //일정 수정
  //PUT -- api/plan/id
  updatePlan(planData) {},

  //Search

  //검색 정보 조회
  //GET -- api/search/keyword/page  -> remove page, api/search/keyword
  getSearchData(keyword) {
    return data.searchData;
  },

  //상세 검색 정보 조회
  //POST -- api/search/detail/page -> remove page, api/search 로만
  getDetailSearchData(searchOptions) {
    return data.searchData;
  },

  //SNS Connect

  //소셜로그인 연동
  //미구현 PUT -- api/social/connect/
  updateSocialLogin(socialType, socialId, LoginId) {
    alert([socialType, socialId, LoginId]);
  },

  //Spot

  //장소 삭제
  //DELETE -- api/spot/id
  deleteSpot(targetId) {},

  //장소 상세 조회
  //GET -- api/spot/id
  getSpotData(id) {
    return data.spotData[id];
  },

  //장소 등록
  //POST -- api/spot
  addSpot(spotData) {},

  //장소 수정
  //PUT -- api/spot/id
  updateSpot(spotData) {},

  //Tour

  //관광지 상세 조회
  //GET -- api/tour/id
  getTourData(id) {
    return data.tourData[id];
  },

  //관광지 목록 조회
  //GET -- api/tour/list/page -> page 에서 loc으로 변경 필요
  getTourList(loc) {
    return data.tourList;
  },
};
