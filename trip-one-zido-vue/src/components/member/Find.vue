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
  <SecurityModal @securityFail="modal='findPasswordFail'" :securityId="securityId" />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";
import SecurityModal from "../util/modal/SecurityModal.vue";

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
    };
  },
  methods: {
    loginIdModal() {
      if (this.$zido.findId()) {
        this.modal = "findLoginIdSuccess";
        console.log(document.getElementById("findId"))
      } else {
        this.modal = "findLoginIdFail";
      }
    },
    securityModal() {
      this.securityId = this.$zido.findPassword();
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
#button {
  border-radius: 40px;
  font-size: 17px;
}

h1 {
  color: #ff928e !important;
  text-align: center;
}
</style>
