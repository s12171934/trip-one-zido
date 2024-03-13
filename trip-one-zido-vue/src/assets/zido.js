export default {
  login(loginId, password){
    return true;
  },

  checkLoginId(loginId){
    return true;
  },

  signUp(){
    return true;
  },

  findId(){
    return null;
  },

  findPassword(){
    return 1;
  },

  getSecurityQuestion(){
    return '질문지도하기'
  },

  checkSecurityAnswer(){
    return true
  },

  updateSocialLogin(socialType,socialId,LoginId){
    alert([socialType,socialId,LoginId])
  },

  resetPassword(){
    return true
  },

}