import data from "@/assets/data";
import axios from "axios";

export default {
  //BookMark

  //찜하기
  //DELETE -- api/bookmark/id
  //PUT -- api/bookmark/id
  toggleBookmark(content) {
    content.myBookmark = !content.myBookmark;
  },

  //찜페이지 조회
  //GET -- api/bookmark/id
  getBookmarkById(id) {
    return data.bookmark[id ? id : 1];
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
  deleteCommunity(targetId) {},

  //커뮤니티 참여 취소
  //DELETE -- api/community/member/id
  joinCancleCommunity(targetId) {},

  //커뮤니티 상세 조회
  //GET -- api/community/id
  getCommunityDetail(id) {
    return data.communityDetail[id];
  },

  //커뮤니티 목록 조회
  //GET -- api/community/list/page
  //GET -- api/community/search/page -> query를 통해 GET전송이 어떤가?
  getCommunityList(options) {
    return data.communityList;
  },

  //커뮤니티 참여
  //POST -- api/community/member/id
  joinCommunity(targetId) {},

  //커뮤니티 등록
  //POST -- api/community
  addCommunity(communityData) {},

  //커뮤니티 수정
  //PUT -- api/community/id
  updateCommunity(communityData) {},

  //Content

  //최근 본 게시물 조회
  //GET -- api/content/recent-view
  getRecentView() {
    return data.recentView;
  },

  //Follow

  //팔로우 및 언팔로우
  //DELETE -- api/page/follow/id
  //POST -- api/page/follow/id
  toggleFollow(userProfile) {
    userProfile.isFollow = !userProfile.isFollow;
  },

  //팔로잉, 팔로워 목록 조회
  //GET -- api/page/follower/id
  //GET -- api/page/following/id
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
  //api/tour/list/loc/page
  //api/content/recent-view/page
  //api/page/id/plan/page
  //api/page/id/spot/page
  //api/search/keyword/spot/page
  //api/search/keyword/plan/page
  //POST -- api/search/spot/page
  //POST -- api/search/plan/page
  async newContents(addApi, page, method, searchOptions) {
    let res = null;
    if(method){
      res = await axios.post(addApi + page, searchOptions)
    }
    else{
      res = await axios.get(addApi + page)
    }
    return res.data;
  },

  //계정정보 더보기
  //GET -- api/search/keyword/member/page
  async newMembers(addApi, page) {
    const res = await axios.get(addApi + page)
    return res.data;
  },

  //Pin

  //게시물 고정 취소
  //DELETE -- api/content/pin/id

  //게시물 고정
  //POST -- api/content/pin/id

  //Plan

  //일정 삭제
  //DELETE -- api/plan/id
  async deletePlan(targetId) {
    await axios.delete(`/api/plan/${targetId}`);
  },

  //일정 상세 조회
  //GET -- api/plan/id
  async getPlanData(id) {
    const res = await axios.get(`/api/plan/${id}`);
    return res.data;
  },

  //일정 등록
  //POST -- api/plan
  async addPlan(planData) {
    await axios.post("/api/plan", planData);
  },

  //일정 수정
  //PUT -- api/plan/id
  async updatePlan(planData) {
    await axios.put("/api/plan", planData);
  },

  //Search

  //검색 정보 조회
  //GET -- api/search/keyword
  async getSearchData(keyword) {
    const res = await axios.get(`/api/search/${keyword}`)
    return res.data;
  },

  //상세 검색 정보 조회
  //POST -- api/search
  async getDetailSearchData(searchOptions) {
    const res = await axios.post('/api/search',searchOptions)
    return res.data;
  },

  //SNS Connect

  //소셜로그인 연동
  //PUT -- api/social
  async updateSocialLogin(socialType, socialId, loginId) {
    await axios.put('/api/socal/connect',{
      socialType: socialType,
      socialId: socialId,
      loginId: loginId
    });
  },

  //Spot

  //장소 삭제
  //DELETE -- api/spot/id
  async deleteSpot(targetId) {
    await axios.delete(`/api/spot/${targetId}`);
  },

  //장소 상세 조회
  //GET -- api/spot/id
  async getSpotData(id) {
    const res = await axios.get(`/api/spot/${id}`);
    return res.data;
  },

  //장소 등록
  //POST -- api/spot
  async addSpot(spotData) {
    await axios.post("/api/spot", spotData);
  },

  //장소 수정
  //PUT -- api/spot/id
  async updateSpot(spotData) {
    await axios.put("/api/spot", spotData);
  },

  //Tour

  //관광지 상세 조회
  //GET -- api/tour/id
  async getTourData(id) {
    const res = await axios.get(`/api/tour/${id}`);
    return res.data;
  },

  //관광지 목록 조회
  //GET -- api/tour/list/loc
  async getTourList(loc) {
    const res = await axios.get(`/api/tour/list/${loc}`);
    return res.data;
  },
};
