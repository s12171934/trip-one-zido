<template>
  <main class="wrapper d-flex flex-row justify-content-center">
    <div id="box" class="flex-column">
      <h1 :style="{ color: sns === 'naver' ? '#2DB400' : '	#FEE500' }">
        {{ sns === "naver" ? "네이버" : "카카오" }}와 연동하기
      </h1>
      <form @submit.prevent class="d-flex flex-column border border-5 gap-4">
        <input type="text" v-model="loginId" placeholder="아이디" />
        <input type="password" v-model="password" placeholder="비밀번호" />

        <div class="d-flex justify-content-center gap-5">
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
      loginId: "",
      autoLogin: false,
      modal: "loginFail",
      sns: this.$route.params.sns,
    };
  },
  methods: {
    login() {
      this.$zido.login(this.loginId, this.password).then((res) => {
        console.log(res)
        if (res) {
          this.$cookies.set("login", 1, 0);      
          if (this.autoLogin) {
            this.$cookies.set("autoLogin", this.loginId);
          } else {
            this.$cookies.remove("autoLogin");
          }
          this.$zido.updateSocialLogin(
            this.$route.params.sns,
            this.$route.params.id,
            this.loginId
          );
          location.href = "/"
        } else {
        }
      });
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
  color: #ff928e;
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
