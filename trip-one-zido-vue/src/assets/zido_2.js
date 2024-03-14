import data from "@/assets/data";
const api = 'http://localhost:3000';
const member = '/api/member';

export default {
  login(loginId, password) {

    return axios.post(api + member + '/login', {
        loginId: loginId,
        password: password
      })
      .then(response => {
        // 서버로부터 응답을 받으면 처리
        // 예를 들어, 응답이 성공인 경우 처리
        console.log(response.data); // 서버 응답 데이터 출력
        return response.data; // 다른 처리를 위해 응답 데이터 반환
      })
      .catch(error => {
        // 오류 발생 시 처리
        console.error('로그인 요청 오류:', error);
        throw error; // 오류를 호출자로 전파하거나 다른 방식으로 처리
      });
    
  },

  getSecurityQuestions() {
    return data.securityQuestions;
  },

  checkLoginId(loginId) {
    return true;
  },

  signUp(form) {
    return axios.get('/signup', {
        id, name, loginId, password, passwordCheck, question, answer, email, phoneNumber, address, birth, gender 
        : form
      })
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('로그인 요청 오류:', error);
        throw error;
      });
  },

  findId(name, email) {
    // return null;
    return axios.post(api + member + '/check/id', {
        name: name,
        email: email
      })
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('로그인 요청 오류:', error);
        throw error; 
      });
   
  },

  findPassword(name, email, loginId) {
    // return 1;
    return axios.post(api + member + '/check/id', {
        name: name,
        email: email,
        loginId: loginId
      })
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('로그인 요청 오류:', error);
        throw error; 
      });
  },

  getSecurityQuestion(id) {
    // return "질문지도하기";
    return axios.post(api + member + `'/check/${id}'`, {
        id: id,
      })
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('로그인 요청 오류:', error);
        throw error; 
      });
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
};
