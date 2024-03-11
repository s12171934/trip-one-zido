<template>
  <main class="wrapper">
    <div
      id="box"
      class="d-flex flex-column justify-content-center align-items-center"
    >
      <h1>회원가입</h1>
      <form
        @submit.prevent
        class="d-flex flex-column gap-3 border border-5 p-5"
      >
        <div class="d-flex gap-3">
          <input type="text" v-model="loginId" placeholder="사용할 아이디" />
          <button @click="checkLoginId" class="button w-25">
            아이디중복체크
          </button>
        </div>

        <input type="text" value="" placeholder="이름" />

        <input type="password" value="" placeholder="비밀번호" />
        <input type="password" value="" placeholder="비밀번호 확인" />
        <div class="select-wrapper">
          <select name="category" id="security">
            <option value="">보안질문</option>
            <option value="1">당신이 태어난 곳은?</option>
            <option value="2">졸업한 초등학교명은?</option>
            <option value="3">당신의 별명은?</option>
            <option value="4">처음으로 국내여행 가본 곳은?</option>
            <option value="5">처음으로 해외여행 가본 국가는?</option>
          </select>
        </div>

        <input type="text" value="" placeholder="보안질문 답" />

        <input type="email" value="" placeholder="Email" />

        <input type="text" value="" placeholder="핸드폰번호 ex)010-xxxx-xxxx" />
        <div class="d-flex gap-3">
          <input
            type="text"
            class="w-25"
            readonly
            value=""
            placeholder="우편번호"
          />
          <input
            type="text"
            readonly
            class="w-50"
            value=""
            placeholder="주소"
          />
          <button class="button w-25 icon fa-search" onclick="searchAddress()">
            주소 검색
          </button>
        </div>
        <input type="text" value="" placeholder="상세한 주소" />

        <div class="d-flex flex-fill gap-3 justify-content-start">
          <div class="birth-gender">
            생년월일:
            <input type="date" id="birthday" value="" placeholder="생년월일" />
          </div>
          <div class="birth-gender">
            성별:
            <input type="radio" id="male" name="gender" checked />
            <label for="male" id="gender" class="m-0">남성</label>
            <input type="radio" id="female" name="gender" />
            <label for="female" id="gender" class="m-0">여성</label>
          </div>
        </div>
      </form>
      <button
        @click="signUp"
        class="button p-2"
        data-bs-toggle="modal"
        data-bs-target="#alertModal"
      >
        회원가입 확인
      </button>
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
      modal: "",
      loginId: "",
    };
  },
  methods: {
    checkLoginId() {
      if (this.loginId === "test") {
        this.modal = "checkDuplicationLoginIdSuccess";
      } else {
        this.modal = "checkDuplicationLoginIdFail";
      }
    },
    signUp() {
      if (this.loginId === "test") {
        this.modal = "signUpSuccess";
      } else {
        this.modal = "signUpFail";
      }
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
  height: 70px;
  padding: 0;
  color: aliceblue;
  background-color: #ff928e;
  border-radius: 10px;
  font-size: 15px !important;
}

a {
  text-decoration: none;
  color: #ff928e;
}

#birthday {
  border: none;
  border-radius: 0;
  background-color: #fff;
}

.birth-gender {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1.5rem;
  border: var(--bs-border-width) var(--bs-border-style) var(--bs-border-color) !important;
  border-radius: var(--bs-border-radius-sm) !important;
  padding: 0.5rem;
}

select {
  font-size: 20px;
}

.icon {
  padding: 0 !important;
}
</style>
