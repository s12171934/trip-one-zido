import data from "@/assets/data";

export default {
  login(loginId, password) {
    return true;
  },

  getSecurityQuestions() {
    return data.securityQuestions;
  },

  checkLoginId(loginId) {
    return true;
  },

  signUp(form) {
    return true;
  },

  findId(name, email) {
    return null;
  },

  findPassword(name, email, loginId) {
    return 1;
  },

  getSecurityQuestion(id) {
    return "질문지도하기";
  },

  checkSecurityAnswer(securityAnswer) {
    return true;
  },

  updateSocialLogin(socialType, socialId, LoginId) {
    alert([socialType, socialId, LoginId]);
  },

  resetPassword(prevPassword, newPassword, newPasswordCheck) {
    return true;
  },

  getProfileImg() {
    return data.userProfiles[this.getMemberId()].imgSrc;
  },

  getMemberId() {
    return 1;
  },

  getUserProfile(id) {
    return data.userProfiles[id];
  },

  getUserInfo() {
    return data.userInfo;
  },

  updateUserInfo(userInfo) {
    alert(userInfo.name);
  },

  checkResign(password, passwordCheck) {
    return true;
  },

  getBookmarkById(id) {
    return data.bookmark[id];
  },

  editProfileImg(img) {},

  getMemberPageData(id) {
    return data.memberPageData[id ? id : this.getMemberId()];
  },

  getConfigData() {
    return data.configData;
  },

  newContents(addApi, page) {
    alert(page);
    return data.newContent;
  },

  toggleBookmark(content) {
    content.myBookmark = !content.myBookmark;
  },

  getFollowList(type, targetId, sessionId) {
    console.log(type, targetId, sessionId);
    return data.userProfiles;
  },

  toggleFollow(userProfile) {
    userProfile.isFollow = !userProfile.isFollow;
  },

  getSearchData(keyword) {
    return data.searchData;
  },

  getDetailSearchData(searchOptions) {
    return data.searchData;
  },

  getTourList(loc) {
    return data.tourList;
  },

  getTourData(id) {
    return data.tourData[id];
  },

  getSpotData(id) {
    return data.spotData[id];
  },

  getPlanData(id) {
    return data.planData[id];
  },

  toggleLike(content, like) {
    if (content.myLike == like) {
      content.myLike = null;
    } else {
      content.myLike = like;
    }
  },

  addComment(targetId, comment) {},

  editComment(targetId, comment) {},

  deleteComment(targetId) {},

  deleteSpot(targetId) {},

  deletePlan(targetId) {},

  togglePublic(targetId) {},

  addSpot(spotData) {},

  addPlan(planData) {},

  updateSpot(spotData) {},

  updatePlan(planData) {},

  getCommunityList(options) {
    return data.communityList;
  },

  getCommunityDetail(id){
return data.communityDetail[id]
  },

  addCommunity(communityData) {},

  updateCommunity(communityData) {},

  deleteCommunity(targetId) {},

  joinCommunity(targetId) {},

  joinCancleCommunity(targetId) {},
};
