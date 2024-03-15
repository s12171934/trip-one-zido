<template>
  <main class="wrapper d-flex flex-row justify-content-center">
    <div id="box" class="flex-column">
      <h1>로그인</h1>
      <form @submit.prevent class="d-flex flex-column border border-5 gap-4">
        <input type="text" v-model="loginId" placeholder="아이디" />
        <input type="password" v-model="password" placeholder="비밀번호" />

        <div class="d-flex justify-content-between">
          <input
            type="checkbox"
            id="idsave"
            name="idsave"
            v-model="saveLoginId"
          />
          <label for="idsave">아이디 저장</label>
          <input
            type="checkbox"
            id="autologin"
            name="autologin"
            v-model="autoLogin"
          />
          <label for="autologin">자동 로그인</label>

          <a @click="$router.push('/find')">아이디 / 비밀번호 찾기</a>
        </div>

        <div class="d-flex justify-content-center">
          <button
            @click="login"
            data-bs-toggle="modal"
            data-bs-target="#alertModal"
            class="button small"
          >
            로그인
          </button>
        </div>
      </form>

      <h1>SNS 연동하기</h1>
      <div class="d-flex gap-2 justify-content-center mb-4">
        <a href="#" id="naver-login-btn">
          <img
            @click="doNaverLogin"
            src="\images\btnG_아이콘사각.png"
            alt="네이버 아이디로 로그인"
            height="70px"
          />
        </a>
        <a href="#" id="kakao-login-btn">
          <img
            @click="doKakaoLogin"
            src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
            alt="카카오톡 아이디로 로그인"
            height="70px"
          />
        </a>
      </div>

      <h3>아직 회원이 아니시라면?</h3>
      <div class="d-flex justify-content-center">
        <a @click="$router.push('sign-up')" class="button small">회원가입</a>
      </div>
    </div>
  </main>

  <AlertModal :modal="modal" />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";

export default {
  components: {
    AlertModal,
  },
  data() {
    return {
      loginId: this.$cookies.isKey("saveLoginId")
        ? this.$cookies.get("saveLoginId")
        : "",
      saveLoginId: this.$cookies.isKey("saveLoginId"),
      autoLogin: false,
      modal: "loginFail",
    };
  },
  methods: {
    async login() {
      if (await this.$zido.login(this.loginId,this.password)) {
        this.$cookies.set("login", 1, 0);
        if (this.saveLoginId) {
          this.$cookies.set("saveLoginId", this.loginId);
        } else {
          this.$cookies.remove("saveLoginId");
        }
        if (this.autoLogin) {
          this.$cookies.set("autoLogin", this.loginId);
        } else {
          this.$cookies.remove("autoLogin");
        }
        location.href = "/";
      } else {
      }
    },
    doNaverLogin() {
      const url =
        "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" +
        "TGQMEJAd_Qx6raHI9ZZk" +
        "&redirect_uri=" +
        "http://localhost:8080/api/social/naver_login" +
        "&state=1234";
      location.href = url;
    },
    doKakaoLogin() {
      const url =
        "https://kauth.kakao.com/oauth/authorize?client_id=" +
        "a02aa0f1daf88f640e2509406d97bec1" +
        "&redirect_uri=" +
        "http://localhost:8080/api/social/kakao_login" +
        "&response_type=code&" +
        "scope=	profile_nickname";
        location.href = url;
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
.wrapper {
  padding: 5%;
  background-color: #d9d9d9;
  border-radius: 50px;
  margin-inline: 20%;
  margin-top: 3%;
  margin-bottom: 3%;
}

h1,
h3 {
  color: #ff928e !important;
  text-align: center;
}

input {
  border-radius: 15px;
  font-size: 20px;
  background-color: #ff928e;
}

#box {
  background-color: white;
  border-radius: 50px;
  padding: 5%;
  display: flex;
  justify-content: center;
  align-content: center;
  width: 100%;
}

form {
  border-radius: 50px;
  padding: 5%;
}

.button {
  color: aliceblue;
  background-color: #ff928e;
  border-radius: 10px;
  font-size: 15px !important;
}

a {
  text-decoration: none;
  color: #ff928e;
}
</style>
