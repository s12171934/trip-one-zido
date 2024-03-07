<template>
  <section>
    <div @submit.prevent id="mainbox" class="d-flex">
      <div id="box" class="flex-column gap-5 p-5">
        <h1>아이디 찾기</h1>
        <div id="boxin" class="flex-column border border-5 gap-2">
          <input type="text" v-model="loginIdName" placeholder="이름" />
          <input
            type="email"
            v-model="loginIdEmail"
            placeholder="이메일 주소"
          />
        </div>
        <a @click="loginIdModal" id="input" class="button small rounded-3">
          아이디 찾기
        </a>
      </div>
      <div id="box" class="flex-column gap-5 p-5">
        <h1>비밀번호 찾기</h1>
        <div id="boxin" class="flex-column border border-5 gap-2">
          <input type="text" v-model="pwLoginId" placeholder="아이디" />
          <input type="text" v-model="pwName" placeholder="이름" />
          <input type="email" v-model="pwEmail" placeholder="이메일 주소" />
        </div>
        <a @click="securityModal" id="input" class="button small rounded-3"
          >비밀번호 변경하기</a
        >
      </div>
    </div>
  </section>

  <AlertModal
    :modalShown="modalShown && !security"
    @modal="$emit('modal')"
    :url="modalData.url"
    :message="modalData.message"
    :buttonMessage="modalData.buttonMessage"
  />
  <SecurityModal
    :modalShown="modalShown && security"
    @fail="securityFail"
    @modal="$emit('modal')"
  />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";
import SecurityModal from "../util/modal/SecurityModal.vue";

export default {
  components: {
    AlertModal,
    SecurityModal,
  },
  props: {
    modalShown: Boolean,
  },
  data() {
    return {
      security: false,
      loginIdName: "",
      loginIdEmail: "",
      pwLoginId: "",
      pwName: "",
      pwEmail: "",
      modalData: {},
    };
  },
  methods: {
    loginIdModal() {
      this.security = false;
      if (this.loginIdName === "test") {
        this.modalData = {
          url: "/login",
          message: "당신의 아이디는<br />테스트<br />입니다.",
          buttonMessage: "로그인 하러 가기",
        };
      } else {
        this.modalData = {
          url: "/find",
          message: "아이디 찾기에<br />실패했습니다.",
          buttonMessage: "재시도",
        };
      }
      this.$emit("modal");
    },
    securityModal() {
      if (this.pwLoginId === "test") {
        this.security = true;
      } else {
        this.security = false;
        this.modalData = {
          url: "/find",
          message: "비밀번호 찾기에<br />실패했습니다.",
          buttonMessage: "재시도",
        };
      }
      this.$emit("modal");
    },
    securityFail() {
      this.security = false;
      this.modalData = {
        url: "/find",
        message: "올바르지 않는 보안질문 입니다",
        buttonMessage: "재시도",
      };
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
#mainbox {
  border-radius: 50px;
  background-color: #d9d9d9;
  margin: 5%;
  display: flex;
  justify-content: center;
  align-content: center;
}
#box {
  border-radius: 40px;
  background-color: white;
  margin: 5%;
  width: 50% !important;
  display: flex;
  justify-content: center;
  align-content: center;
}
#boxin {
  border-radius: 30px;
  padding: 4%;
  display: flex;
  justify-content: center;
  align-content: center;
}
input,
a {
  font-family: "Jalnan";
  font-size: 20px;
}
#button {
  font-family: "Jalnan";
  border-radius: 40px;
  font-size: 17px;
}

h1 {
  font-family: "Jalnan";
  color: #ff928e !important;
  text-align: center;
}
</style>
