<template>
  <section class="wrapper d-flex flex-row justify-content-center">
    <div id="box" class="flex-column">
      <h1>로그인</h1>
      <form @submit.prevent class="d-flex flex-column border border-5 gap-4">
        
        <input type="text" v-model="loginId" placeholder="아이디" />
        <input type="password" v-model="password" placeholder="비밀번호" />

        <div class="d-flex justify-content-between">
          <input type="checkbox" id="idsave" name="idsave" />
          <label for="idsave">아이디 저장</label>

          <input type="checkbox" id="autologin" name="autologin" />
          <label for="autologin">자동 로그인</label>

          <a href="/find">아이디 / 비밀번호 찾기</a>
        </div>

        <div class="d-flex justify-content-center">
          <a @click="login" class="button small">로그인</a>
        </div>
      </form>

      <h1>SNS 연동하기</h1>
      <div class="d-flex gap-2 justify-content-center mb-4">
        <a href="#" id="naver-login-btn">
          <img
            src="\images\btnG_아이콘사각.png"
            alt="네이버 아이디로 로그인"
            height="70px"
          />
        </a>
        <a href="#" id="kakao-login-btn">
          <img
            src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
            alt="카카오톡 아이디로 로그인"
            height="70px"
          />
        </a>
      </div>

      <h3>아직 회원이 아니시라면?</h3>
      <div class="d-flex justify-content-center">
        <a href="/sign-up" class="button small">회원가입</a>
      </div>
    </div>
  </section>

  <AlertModal
    @modal="$emit('modal')"
    :modalShown="modalShown"
    url="/login"
    message="아이디와 비밀번호를<br />확인해주세요."
    buttonMessage="다시 로그인 하기"
  />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";

export default {
  components: {
    AlertModal,
  },
  data(){
    return{
      loginId: ""
    }
  },
  props: {
    modalShown: Boolean,
  },
  methods: {
    login() {
      if (this.loginId == "test") {
        this.$cookies.set("login",1);
        this.$router.push("/");
      } else {
        this.$emit("modal");
      }
    },
  },
  mounted(){
    this.$emit("meta",this.$route.matched[0].meta.isLogin);
  }
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
h3,
section {
  font-family: "Jalnan";
}

h1,
h3 {
  color: #ff928e !important;
  text-align: center;
}

input {
  border-radius: 15px;
  font-family: "Jalnan";
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
  font-family: "Jalnan";
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
