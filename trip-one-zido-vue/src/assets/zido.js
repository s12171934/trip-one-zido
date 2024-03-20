import data from "@/assets/data";
import axios from "axios";

export default {
  //BookMark

  //찜하기
  //DELETE -- api/bookmark/id
  //POST -- api/bookmark/id
  toggleBookmark(content) {
    const id = content.id;
    if (!content.myBookmark) {
      axios
        .post(`/api/bookmark/${id}`)
        .then((response) => {
          console.log(response.data);
          content.myBookmark = !content.myBookmark;
          content.bookmarkCount++;
        })
        .catch((error) => {
          console.error("찜하기 요청 오류", error);
          throw error;
        });
    } else {
      axios
        .delete(`/api/bookmark/${id}`)
        .then((response) => {
          console.log(response.data);
          content.myBookmark = !content.myBookmark;
          content.bookmarkCount--;
        })
        .catch((error) => {
          console.error("찜삭제 요청 오류", error);
          throw error;
        });
    }
  },

  //찜페이지 조회
  //GET -- api/bookmark/id
  async getBookmarkById(id) {
    try {
      const response = await axios.get(`/api/bookmark/${id}`);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.error("찜페이지 조회 요청 오류", error);
      throw error;
    }
  },

  //Comment

  //댓글 삭제
  //DELETE -- api/comment/id
  deleteComment(targetId) {
    axios
      .delete(`/api/comment/${targetId}`)
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  },

  //댓글 등록
  //POST -- api/comment
  addComment(targetId, comment) {
    console.log(targetId);
    axios
      .post(`/api/comment`, {
        comment: comment,
        contentId: targetId,
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  },

  //댓글 수정
  //PUT -- api/comment/id
  editComment(targetId, comment) {
    axios
      .put(`/api/comment/${targetId}`, {
        id: targetId,
        comment: comment,
      })
      .then((response) => {
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  },

  //Community

  //커뮤니티 삭제
  //DELETE -- api/community/id
  deleteCommunity(targetId) {
    axios
      .delete(`/api/community/${targetId}`)
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("커뮤니티 삭제 요청 오류", error);
        throw error;
      });
  },

  //커뮤니티 참여 취소
  //DELETE -- api/community/member/id
  joinCancleCommunity(id) {
    axios
      .delete(`/api/community/member/${id}`, {
        id: id,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("커뮤니티 참여 취소 요청 오류", error);
        throw error;
      });
  },

  //커뮤니티 상세 조회
  //GET -- api/community/id
  async getCommunityDetail(targetId) {
    try {
      const response = await axios.get(`/api/community/${targetId}`);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.error("커뮤니티 상세 조회 요청 오류", error);
      throw error;
    }
  },

  //커뮤니티 목록 조회
  //GET -- api/community/list/page
  //GET -- api/community/search/page -> query를 통해 GET전송이 어떤가?
  //POST -- 커뮤니티 목록 조회와 검색한 커뮤니티 목록 결합
  // async getCommunityList(options) {
  async getCommunityList(page){
      try {
        const response = await axios.get(`/api/community/list/${page}`);
        console.log(response.data);
        return response.data;
      } catch (error) {
        console.error("커뮤니티 목록 조회 요청 오류", error);
        throw error;
    }
  },

  async searchCommunity(form,page){
    if(page === undefined){page = 0}
    try{
      const response = await axios.post(`/api/community/search/${page}`, form)
      console.log(response.data)
      return response.data;
    } catch (error) {
      console.error("커뮤니티 목록 검색 요청 오류", error);
        throw error;
    }
  },

  //커뮤니티 참여
  //POST -- api/community/member/id
  joinCommunity(id) {
    axios
      .post(`/api/community/member/${id}`, {
        id: id,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("커뮤니티 참여 요청 오류", error);
        throw error;
      });
  },

  //커뮤니티 등록
  //POST -- api/community
  addCommunity(communityData) {
    axios
      .post(`/api/community/`, communityData)
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("커뮤니티 등록 오류", error);
        throw error;
      });
  },

  //커뮤니티 수정
  //PUT -- api/community/id
  updateCommunity(id, communityData) {
    axios
      .put(`/api/community/${id}`, communityData)
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("커뮤니티 수정 오류", error);
        throw error;
      });
  },

  //Content

  //최근 본 게시물 조회
  //GET -- api/content/recent-view
  async getRecentView() {
    const res = await axios.get(`/api/content/recent-view`).catch((error) => {
      console.log(error);
      throw error;
    });
    console.log(res.data);
    return res.data;
  },

  //최근 본 게시물 등록
  //POST --api/content/recent-view
  addRecentView(id) {
    axios.post(`/api/content/recent-view/${id}`)
  },

  //주소 가져오기
  //GET -- api/content/address/id
  async getAddress(id) {
    const res = await axios.get(`/api/content/address/${id}`)
    return res.data
  },

  //Follow

  //팔로우 및 언팔로우
  //DELETE -- api/page/follow/id
  //POST -- api/page/follow/id
  async toggleFollow(userProfile) {
    console.log(userProfile.follow);
    //팔로잉으로 변경한다면 POST요청
    if (userProfile.follow == false) {
      console.log("post");
      await axios.post(`/api/page/follow/${userProfile.id}`);
      //팔로잉을 해제한다면 delete요청
    } else {
      console.log("delete");
      await axios.delete(`/api/page/follow/${userProfile.id}`);
    }
    userProfile.follow = !userProfile.follow;
  },

  //팔로잉, 팔로워 목록 조회
  //GET -- api/page/follower/id
  //GET -- api/page/following/id
  getFollowList(type, targetId) {
    console.log(type);
    //타입체크후 get API설정
    let url;
    if (type === "follower") {
      console.log("follower");
      url = `/api/page/follower/${targetId}`;
    } else {
      console.log("following");
      url = `/api/page/following/${targetId}`;
    }

    return axios
      .get(url)
      .then((response) => {
        console.log("follow");
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  },

  //Good

  //좋아요 싫어요
  //POST -- api/content/good/id
  toggleLike(content, good) {
    if (content.myGood) {
      content.goodCount--;
    } else if(good){
      content.goodCount++;
    }

    if (content.myGood == good) {
      content.myGood = null;
    } else {
      content.myGood = good;
    }
    axios
      .post(`/api/content/good/${content.id}`, { good: good })
      .then((response) => {
        data.userProfiles = response.data;
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  },

  //Member

  //회원탈퇴 확인
  //DELETE -- api/member -> 비밀번호, 비밀번호 확인 추가 전송
  async checkResign(password, passwordCheck) {
    try {
      const response = await axios.delete(`/api/member/?password=${password}&passwordCheck=${passwordCheck}`, {
        password: password,
        passwordCheck: passwordCheck,
      });
      // 서버로부터 응답을 받으면 처리
      // 예를 들어, 응답이 성공인 경우 처리
      console.log(response.data); // 서버 응답 데이터 출력
      return response.data;
    } catch (error) {
      // 오류 발생 시 처리
      console.error("회원탈퇴시 비밀번호+비밀번호오류 확인 오류:", error);
      throw error; // 오류를 호출자로 전파하거나 다른 방식으로 처리
    }
  },

  //회원정보 조회
  //GET -- api/member
  async getUserInfo() {
    // return data.userInfo;
    try {
      const response = await axios.get(`/api/member/`);
      // 서버로부터 응답을 받으면 처리
      // 예를 들어, 응답이 성공인 경우 처리
      console.log(response.data); // 서버 응답 데이터 출력
      return response.data;
    } catch (error) {
      // 오류 발생 시 처리
      console.error("회원정보 조회 요청 오류:", error);
      throw error; // 오류를 호출자로 전파하거나 다른 방식으로 처리
    }
  },

  //보안질문 목록 조회
  //GET -- api/member/signup
  async getSecurityQuestions() {
    // return data.securityQuestions;
    try {
      const response = await axios.get(`/api/member/signup`);
      // 서버로부터 응답을 받으면 처리
      // 예를 들어, 응답이 성공인 경우 처리
      console.log(response.data); // 서버 응답 데이터 출력
      return response.data;
    } catch (error) {
      // 오류 발생 시 처리
      console.error("회원가입시 보안질문 조회 요청 오류:", error);
      throw error; // 오류를 호출자로 전파하거나 다른 방식으로 처리
    }
  },

  //비밀번호 찾기 보안질문 조회
  //GET -- api/member/check/id
  async getSecurityQuestion(id) {
    // return "질문지도하기";
    try {
      const response = await axios.get(`/api/member/check/${id}`, {
        id: id,
      });
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.error("선택한 보안질문 조회 요청 오류", error);
      throw error;
    }
  },

  //회원 페이지 조회
  //GET -- api/page/id
  async getMemberPageData(id) {
    // return data.memberPageData[id ? id : 1];
    try {
      const response = await axios.get(`/api/page/${id}`);
      console.log(response.data);
      return response.data;
    } catch (error) {
      console.error("회원 페이지 조회 요청 오류", error);
      throw error;
    }
  },

  //프로필 사진 가져오기
  //미구현 GET -- api/member/profile -> header와 프로필 사진 편집시 필요
  async getProfileImg() {
    // return data.userProfiles[1].imgSrc;
    try {
      const response = await axios.get("/api/member/profile");
      console.log("checkPro");
      console.log(response.data);
      return response.data.profile;
    } catch (error) {
      console.error("프로필 사진 가져오기 오류", error);
      throw error;
    }
  },

  //설정 페이지 조회
  // ★미구현 GET -- api/member/config
  getConfigData() {
    return axios
      .get("/api/member/config", {})
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("설정페이지 조회 오류:", error);
        throw error;
      });
  },

  //회원가입
  //POST -- api/member/signup
  async signUp(form) {
    // return true;
    try {
      const response = await axios.post(`/member/signup`, form);
      console.log(response.data);
      return true;
    } catch (error) {
      console.error("회원가입 오류:", error);
      return false;
    }
  },

  //아이디 중복 확인
  //POST -- api/member/signup/loginId
  async checkLoginId(loginId) {
    // return true;
    if(loginId !== ''){
    return axios
      .post(`/api/member/signup/${loginId}`, {
        loginId: loginId,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("아이디 중복확인 오류:", error);
        throw error;
      });
    } else {
      return false;
    }
  },

  //로그인
  //POST -- api/member/login
  async login(loginId, password) {
    // return true;
    return axios
      .post("/api/member/login", {
        loginId: loginId,
        password: password,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("로그인 오류:", error);
        throw error;
      });
  },

  //자동 로그인
  //POST -- api/member/autoLogin
  async autoLogin(loginId) {
    await axios.post("/api/member/autoLogin", {
      loginId: loginId,
    });
  },

  //비밀번호 찾기 보안답변 전송
  //POST -- api/member/check -> id를 requestBody로 편입해 중복 방지
  async checkSecurityAnswer(securityId,securityAnswer) {
    // return true;
    return axios
      .post("/api/member/check", {
        id : securityId,
        answer: securityAnswer
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("비밀번호 찾기 보안답변 전송 오류:", error);
        throw error;
      });
  },

  //비밀번호 찾기 회원번호 조회
  //POST -- api/member/check/pw
  async findPassword(name, email, loginId) {
    if(name !== '' && email !== '' && loginId !== ''){
    // return 1;
    return axios
      .post("/api/member/check/pw", {
        name: name,
        email: email,
        loginId: loginId,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("비밀번호 찾기 회원번호 조회 오류:", error);
        throw error;
      });
    } else {
      return null;
    }
  },

  //아이디 찾기
  //POST -- api/member/check/id
  async findId(name, email) {
    // return null;
    if(name !== '' && email !== ''){
    return axios
      .post(`/api/member/check/id`, {
        name: name,
        email: email,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        throw error;
      })
    } else {
      return null
    };
  },

  //프로필 사진 변경
  //PUT -- api/member/profile
  async editProfileImg(img) {
    const binaryString = img.split(",")[1];
    console.log(binaryString);

    return axios
      .put("/api/member/profile", {
        img: binaryString,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("프로필 사진변경 오류:", error);
        throw error;
      });
  },

  //비밀번호 변경
  //PUT -- api/member/password
  async changePassword(nowPassword, changePassword, changePasswordCheck) {
    // return true;
    return axios
      .put(`/api/member/password`, {
        nowPassword: nowPassword,
        changePassword: changePassword,
        changePasswordCheck: changePasswordCheck,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("비밀번호 변경 오류:", error);
        throw error;
      });
  },

  //★비밀번호 재설정
  //PUT -- api/member/password/id
  async resetPassword(id, changePassword, changePasswordCheck) {
    // return true;
    return axios
      .put(`/api/member/password/{id}`, {
        id: id,
        changePassword: changePassword,
        changePasswordCheck: changePasswordCheck,
      })
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("비밀번호 재설정 오류:", error);
        throw error;
      });
  },

  //★회원정보수정
  //PUT -- api/member
  async updateUserInfo(userInfo) {
    // alert(userInfo.name);
    return axios
      .put("/api/member/", userInfo)
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((error) => {
        console.error("회원정보수정 오류:", error);
        throw error;
      });
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
  async newContents(addApi, page, method, Options) {
    let res = null;
    if (method) {
      res = await axios.post(addApi + page, Options);
    } else {
      res = await axios.get(addApi + page + '?' + Options);
      console.log(addApi + page + '?' + Options)
    }
    return res.data;
  },

  //계정정보 더보기
  //GET -- api/search/keyword/member/page
  async newMembers(addApi, page) {
    const res = await axios.get(addApi + page);
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
    for (let spot of planData.spots) {
      for (let photo in spot.photos) {
        console.log(spot.photos[photo]);
        spot.photos[photo].photo = spot.photos[photo].photo.split(",")[1];
      }
    }

    console.log(planData);
    await axios.post("/api/plan", planData);
  },

  //일정 수정
  //PUT -- api/plan/id
  async updatePlan(id,planData) {
    for (let spot of planData.spots) {
      for (let photo in spot.photos) {
        console.log(spot.photos[photo]);
        spot.photos[photo].photo = spot.photos[photo].photo.split(",")[1];
      }
    }
    await axios.put(`/api/plan/${id}`, planData);
  },

  //Search

  //검색 정보 조회
  //GET -- api/search/keyword
  async getSearchData(keyword) {
    const res = await axios.get(`/api/search/${keyword}`);
    console.log(res.data);
    return res.data;
  },

  //상세 검색 정보 조회
  //POST -- api/search
  async getDetailSearchData(searchOptions) {
    const res = await axios.post("/api/search", searchOptions);
    return res.data;
  },

  //SNS Connect

  //소셜로그인 연동
  //PUT -- api/social
  async updateSocialLogin(socialType, socialId, loginId) {
    console.log(123)
    await axios.put("/api/social", {
      socialType: socialType,
      socialId: socialId,
      loginId: loginId,
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
    console.log(res.data)
    return res.data;
  },

  //장소 등록
  //POST -- api/spot
  async addSpot(spotData) {
    for (let photo in spotData.photos) {
      console.log(spotData.photos[photo]);
      spotData.photos[photo].photo = spotData.photos[photo].photo.split(",")[1];
    }
    console.log(spotData);
    await axios.post("/api/spot", spotData);
  },

  //장소 수정
  //PUT -- api/spot/id
  async updateSpot(id, spotData) {
    for (let photo in spotData.photos) {
      spotData.photos[photo].photo = spotData.photos[photo].photo.split(",")[1];
    }
    await axios.put(`/api/spot/${id}`, spotData);
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
    console.log(res.data)
    return res.data;
  },
};
