import data from "@/assets/data";
const api = 'http://localhost:3000';
const member = '/api/member';
const bookmark = '/api/bookmark';
const page = '/api/page';
const tour = '/api/tour';
const spot = '/api/spot';
const plan = '/api/plan';

export default {
  getSecurityQuestions() {
    // return data.securityQuestions;
    return axios.get(api + member + '/login', {
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
      console.error('회원가입시 보안질문 조회 요청 오류:', error);
      throw error;  // 오류를 호출자로 전파하거나 다른 방식으로 처리
    });
  },

  signUp(form) {
    return axios.post(api + member + '/signup', form)
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('회원가입 요청 오류:', error);
        throw error;
      });
  },

  checkLoginId(loginId) {
    // return true;
    return axios.post(api + member + `'/signup/${loginId}'`, {
      loginId: loginId,
      password: password
    })
    .then(response => {
      console.log(response.data);
      return response.data; 
    })
    .catch(error => {
      console.error('아이디중복확인 요청 오류:', error);
      throw error; 
    });
  },

  login(loginId, password) {
    return axios.post(api + member + '/login', {
        loginId: loginId,
        password: password
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
        console.error('아이디찾기 요청 오류', error);
        throw error; 
      });
  },

  findPassword(name, email, loginId) {
    // return 1;
    return axios.post(api + member + '/check/pw', {
        name: name,
        email: email,
        loginId: loginId
      })
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('비밀번호찾기 요청 오류', error);
        throw error; 
      });
  },

  getSecurityQuestion(id) {
    // return "질문지도하기";
    return axios.get(api + member + `'/check/${id}'`, {
        id: id,
      })
      .then(response => {
        console.log(response.data); 
        return response.data;
      })
      .catch(error => {
        console.error('선택한 보안질문 조회 요청 오류', error);
        throw error; 
      });
  },

  checkSecurityAnswer(securityAnswer) {
    return true; 
    // 보안 답변 (id, answer)
},

resetPassword(prevPassword, newPassword, newPasswordCheck) {
  // return true;
  return axios.put(api + member + `'/passwd/${id}'`, {
    prevPassword: prevPassword,
    newPassword: newPassword,
    newPasswordCheck: newPasswordCheck
  })
  .then(response => {
    console.log(response.data); 
    return response.data;
  })
  .catch(error => {
    console.error('비밀번호 변경 요청 오류', error);
    throw error; 
  });
},

  updateSocialLogin(socialType, socialId, LoginId) {
    alert([socialType, socialId, LoginId]);
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
    // alert(userInfo.name);
    return axios.put(api + member + '/', {
      userInfo: userInfo
    })
    .then(response => {
      console.log(response.data); 
      return response.data;
    })
    .catch(error => {
      console.error('회원 정보 수정 요청 오류', error);
      throw error; 
    });
  },

  editProfileImg(img) {
    return axios.put(api + bookmark + '/profile', {
      img: img
   })
   .then(response => {
     console.log(response.data); 
     return response.data;
   })
   .catch(error => {
     console.error('프로필 사진 변경 요청 오류', error);
     throw error; 
   });
  },

  checkResign(password, passwordCheck) {
    // return true;
    return axios.put(api + member + '/password', {
      password: password,
      passwordCheck: passwordCheck
    })
    .then(response => {
      console.log(response.data); 
      return response.data;
    })
    .catch(error => {
      console.error('비밀번호 변경 요청 오류', error);
      throw error; 
    });
  },

  getBookmarkById(id) {
    // return data.bookmark[id];
    return axios.put(api + bookmark + `'/${id}'`, {
       id: data.bookmark [id]
    })
    .then(response => {
      console.log(response.data); 
      return response.data;
    })
    .catch(error => {
      console.error('찜 목록 전체조회 요청 오류', error);
      throw error; 
    });
  },

  getMemberPageData(id) {
    // return data.memberPageData[id ? id : this.getMemberId()];
    return axios.put(api + page + `'/${id}'`, {
      id: id ? id : this.getMemberId()
   })
   .then(response => {
     console.log(response.data); 
     return response.data;
   })
   .catch(error => {
     console.error('회원 페이지 조회 요청 오류', error);
     throw error; 
   });
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
    // return data.tourList;
    return axios.get(api + tour + `'/list/${page}'`, {
      loc: loc
   })
   .then(response => {
     console.log(response.data); 
     return response.data;
   })
   .catch(error => {
     console.error('관광지 목록 조회 요청 오류', error);
     throw error; 
   });
  },

  getTourData(id) {
    // return data.tourData[id];
    return axios.get(api + tour + `'/${id}'`, {
      id: id
   })
   .then(response => {
     console.log(response.data); 
     return response.data;
   })
   .catch(error => {
     console.error('관광지 상세조회 요청 오류', error);
     throw error; 
   });
  },

  getSpotData(id) {
    // return data.spotData[id];
    return axios.get(api + tour + `'/${id}'`, {
      id: id
   })
   .then(response => {
     console.log(response.data); 
     return response.data;
   })
   .catch(error => {
     console.error('관광지 상세조회 요청 오류', error);
     throw error; 
   });
  },

  getPlanData(id) {
    // return data.planData[id];
    return axios.get(api + plan + `'/${id}'`, {
      id: id
   })
   .then(response => {
     console.log(response.data); 
     return response.data;
   })
   .catch(error => {
     console.error('관광지 상세조회 요청 오류', error);
     throw error; 
   });
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
