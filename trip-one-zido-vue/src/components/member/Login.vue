<template>
  <main class="wrapper d-flex flex-row justify-content-center">
    <div id="box" class="flex-column">
      <h1>로그인</h1>
      <!-- 로그인 입력폼 엔터시 로그인 -->
      <form
        @submit.prevent="login"
        class="d-flex flex-column border border-5 gap-4"
      >
        <input type="text" v-model="loginId" placeholder="아이디" />
        <input type="password" v-model="password" placeholder="비밀번호" />

        <div class="d-flex justify-content-between flex-column-on-small-screen">
          <div>
            <input
              type="checkbox"
              id="idsave"
              name="idsave"
              v-model="saveLoginId"
            />
            <label for="idsave">아이디 저장</label>
          </div>

          <div>
            <input
              type="checkbox"
              id="autologin"
              name="autologin"
              v-model="autoLogin"
            />
            <label for="autologin">자동 로그인</label>
          </div>
        </div>

        <div class="find">
          <a @click="$router.push('/find')" style="color: #767d85"
            >아이디 / 비밀번호 찾기</a
          >
        </div>
        
        <div class="d-flex justify-content-center">
          <button
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
        <a @click="$router.push('sign-up')" class="button small" id="button"
          >회원가입</a
        >
      </div>
    </div>
  </main>

  <!-- 로그인상태 모달 -->
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
      //아이디 저장 쿠키 유효시 아이디 삽입 및 체크버튼 자동 활성화
      loginId: this.$cookies.isKey("saveLoginId")
        ? this.$cookies.get("saveLoginId")
        : "",
      saveLoginId: this.$cookies.isKey("saveLoginId"),
      autoLogin: false,
      modal: "",
    };
  },
  methods: {
    //POST -- api/member/login
    async login() {
      //로그인 중 ...
      this.modal = "loginTry";
      //패스워드 미입력시
      if(this.password == null){
        this.modal = "PasswordTry";
        return;
      }
      //로그인 성공시 정보없는 로그인 쿠키 발행
      //아이디 저장 및 자동로그인 선택시 아이디를 값으로 가지는 토큰 발행
      if (await this.$zido.login(this.loginId, this.password)) {
        this.$cookies.set("login", 1, 0);
        this.saveLoginId
          ? this.$cookies.set("saveLoginId", this.loginId)
          : this.$cookies.remove("saveLoginId");
        this.autoLogin
          ? this.$cookies.set("autoLogin", this.loginId)
          : this.$cookies.remove("autoLogin");
        location.href = "/";
      } else {
        this.modal = "loginFail";
      }
    },

    //네이버로그인 API
    doNaverLogin() {
      const url =
        "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=" +
        "TGQMEJAd_Qx6raHI9ZZk" +
        "&redirect_uri=" +
        "http://localhost:8080/api/social/naver_login" +
        "&state=1234";
      location.href = url;
    },

    //카카오로그인 API
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
    //로그인 확인
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
.wrapper {
  padding: 1%;
  background-color: #d9d9d9;
  border-radius: 50px;
  margin-inline: 30%;
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

input[type="checkbox"] {
  margin-right: 0 !important;
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
  color: rgb(252, 252, 252);
  background-color: #ff928e;
  border-radius: 10px;
  font-size: 15px !important;
}

a {
  text-decoration: none;
  color: #ff928e;
}

.gap-4 {
    gap: 0.5rem !important;
}

@media (max-width: 1250px) {
  #box {
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .wrapper {
    margin-inline: 20% !important;
  }
}

@media (max-width: 768px) {
  .flex-column-on-small-screen {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  h3 {
    font-size: 16px; /* 작은 화면에서 폰트 크기를 작게 조정 */
  }
  form {
    border: none !important; /* 작은 화면에서 border 제거 */
  }
  .find {
    text-align: center;
  }
  .wrapper {
  padding: 3%;
  }
}

@media (max-width: 540px) {
  img {
    width: 50px;
    height: 50px;
  }
  button,
  #button {
    width: 50%;
    padding: 0;
  }
  .wrapper {
    margin-inline : 10% !important;
  }
}
</style>
