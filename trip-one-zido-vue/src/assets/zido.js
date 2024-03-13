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
    return "/images/남자.png";
  },

  getUserProfile() {
    return data.userProfiles[2];
  },

  getUserInfo() {
    return data.userInfo;
  },

  updateUserInfo(userInfo) {
    alert(userInfo.name);
  },
};
