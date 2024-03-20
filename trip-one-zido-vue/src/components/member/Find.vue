<template>
  <main class="wrapper">
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

  <AlertModal :modal="modal" />
  <SecurityModal @securityFail="modal='findPasswordFail'" :securityId="securityId" :SecurityQuestion="SecurityQuestion" />
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
    loginIdModal() {
      this.$zido.findId(this.loginIdName, this.loginIdEmail).then(result => {
        if (result !== null && result !== '') {
            this.modal = "findLoginIdSuccess";
            console.log(result)
            data.modalDatas.findLoginIdSuccess.message = `당신의 아이디는<br />${result}<br />입니다.`,
            console.log(document.getElementById("findId"));
        } else {
            this.modal = "findLoginIdFail"; 
        }
      })
    },
    async securityModal() {
      try {
        const result = await this.$zido.findPassword(this.pwName, this.pwEmail, this.pwLoginId);
      if (result !== null && result !== '') {
        this.securityId = result;
        // SecurityQuestion 값을 가져오기 위해 프로미스를 기다립니다.
        this.SecurityQuestion = await this.$zido.getSecurityQuestion(this.securityId);
      } else {
        this.modal = "findPasswordFail";
      }
      } catch (error) {
        console.error("비밀번호 찾기 오류:", error);
        // 에러 처리 로직 추가
      }
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
</style>
