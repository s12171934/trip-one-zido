<template>
  <main class="wrapper">
    <div @submit.prevent id="mainbox" class="d-flex">
      <!-- 아이디 찾기 -->
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
        <a
          @click="loginIdModal"
          id="input"
          class="button small rounded-3"
          data-bs-toggle="modal"
          data-bs-target="#alertModal"
        >
          아이디 찾기
        </a>
      </div>

      <!-- 비밀번호 찾기 -->
      <div id="box" class="flex-column gap-5 p-5">
        <h1>비밀번호 찾기</h1>
        <div id="boxin" class="flex-column border border-5 gap-2">
          <input type="text" v-model="pwLoginId" placeholder="아이디" />
          <input type="text" v-model="pwName" placeholder="이름" />
          <input type="email" v-model="pwEmail" placeholder="이메일 주소" />
        </div>
        <a
          @click="securityModal"
          id="input"
          class="button small rounded-3"
          data-bs-toggle="modal"
          data-bs-target="#securityModal"
          >비밀번호 변경하기</a
        >
      </div>
    </div>
  </main>

  <!-- 아이디 조회 및 비밀번호 찾기 실패 모달 -->
  <AlertModal :modal="modal" />

  <!-- 보안질문 모달 -->
  <SecurityModal
    @securityFail="modal = 'findPasswordFail'"
    :securityId="securityId"
    :SecurityQuestion="SecurityQuestion"
  />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";
import SecurityModal from "../util/modal/SecurityModal.vue";
import data from "@/assets/data.js";

export default {
  components: {
    AlertModal,
    SecurityModal,
  },
  data() {
    return {
      security: false,
      securityId: null,
      loginIdName: "",
      loginIdEmail: "",
      pwLoginId: "",
      pwName: "",
      pwEmail: "",
      modal: "",
      SecurityQuestion: "",
    };
  },
  methods: {
    //아이디 찾기
    //POST -- api/member/check/id
    async loginIdModal() {
      const res = await this.$zido.findId(this.loginIdName, this.loginIdEmail);
      if (res !== null && res !== "") {
        this.modal = "findLoginIdSuccess";
        data.modalDatas.findLoginIdSuccess.message = `당신의 아이디는<br />${result}<br />입니다.`;
      } else {
        this.modal = "findLoginIdFail";
      }
    },

    //비밀번호 찾기
    //POST -- api/member/check/pw
    //GET -- api/member/check/id
    async securityModal() {
      try {
        const res = await this.$zido.findPassword(
          this.pwName,
          this.pwEmail,
          this.pwLoginId
        );
        if (res !== null && res !== "") {
          this.securityId = res;
          this.SecurityQuestion = await this.$zido.getSecurityQuestion(
            this.securityId
          );
        } else {
          this.modal = "findPasswordFail";
        }
      } catch (error) {
        console.error("비밀번호 찾기 오류:", error);
      }
    },
  },
  mounted() {
    //로그인 확인
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
  width: 40% !important;
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
#button {
  border-radius: 40px;
  font-size: 17px;
}

h1 {
  color: #ff928e !important;
  text-align: center;
}

/* 작은 화면에서 아이템을 세로로 배치하기 위해 추가된 스타일 */
@media (max-width: 768px) {
  #mainbox {
    flex-direction: column;
  }
  #box {
    width: 90% !important;
  }
  h1 {
    font-size: 35px !important;
  }
  #boxin {
    border: none !important; /* 작은 화면에서 border 제거 */
  }
}

@media (max-width: 1045px) {
  h1 {
    font-size: 30px;
  }
}
@media (max-width: 900px) {
  h1 {
    font-size: 23px;
  }
}
@media (max-width: 480px) {
  h1 {
    font-size: 21px !important;
  }
  .button {
    padding: 0;
  }
}
</style>
