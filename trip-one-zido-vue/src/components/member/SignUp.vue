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
          <input
            type="text"
            v-model="form.loginId"
            placeholder="사용할 아이디"
          />
          <button
            @click="checkLoginId"
            data-bs-toggle="modal"
            data-bs-target="#alertModal"
            class="button"
          >
            아이디중복체크
          </button>
        </div>

        <input type="text" v-model="form.name" placeholder="이름" />

        <input type="password" v-model="form.password" placeholder="비밀번호" />
        <input
          type="password"
          v-model="form.passwordCheck"
          placeholder="비밀번호 확인"
        />
        <div class="select-wrapper">
          <select name="category" id="security" v-model="form.question">
            <option value="" disabled selected>보안질문을 선택하세요</option>
            <option
              v-for="securityQuestion in securityQuestions"
              :value="securityQuestion.id"
            >
              {{ securityQuestion.question }}
            </option>
          </select>
        </div>

        <input type="text" v-model="form.answer" placeholder="보안질문 답" />

        <input type="email" v-model="form.email" placeholder="Email" />

        <input
          type="text"
          v-model="form.phoneNumber"
          placeholder="핸드폰번호 ex)010-xxxx-xxxx"
        />
        <div class="d-flex gap-3">
          <input type="text" readonly id="zipcode" placeholder="우편번호" />

          <button class="button icon fa-search" @click="searchAddress">
            주소 검색
          </button>
        </div>
        <div class="d-flex gap-3">
          <input type="text" readonly id="address" placeholder="주소" />
        </div>
        <input
          type="text"
          id="address2"
          v-model="form.address2"
          placeholder="상세한 주소"
        />

        <div
          class="d-flex flex-fill gap-3 justify-content-start"
          id="birth-gender"
        >
          <div class="birth-gender">
            생년월일:
            <input
              type="date"
              id="birthday"
              v-model="form.birth"
              placeholder="생년월일"
            />
          </div>
          <div class="birth-gender">
            성별:
            <input
              type="radio"
              id="male"
              name="gender"
              v-model="form.gender"
              value="0"
            />
            <label for="male" id="gender" class="m-0">남성</label>
            <input
              type="radio"
              id="female"
              name="gender"
              v-model="form.gender"
              value="1"
            />
            <label for="female" id="gender" class="m-0">여성</label>
          </div>
        </div>
      </form>
      <button
        @click="signUp"
        class="button"
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
      form: {
        loginId: "",
        name: "",
        password: "",
        passwordCheck: "",
        question: "",
        answer: "",
        email: "",
        phoneNumber: "",
        zipcode: "",
        address: "",
        address2: "",
        birth: "",
        gender: 0,
      },
      securityQuestions: [],
      securityQuestion: {
        id: "",
        question: "",
      },
    };
  },
  methods: {
    //POST -- api/member/signup/loginId
    checkLoginId() {
      const res = this.$zido.checkLoginId(this.form.loginId);
      if (res) {
        this.modal = "checkDuplicationLoginIdSuccess";
      } else {
        this.modal = "checkDuplicationLoginIdFail";
        this.form.loginId = "";
      }
    },
    //POST -- api/member/signup
    signUp() {
      this.form.zipcode = document.querySelector("#zipcode").value;
      this.form.address = document.querySelector("#address").value;
      this.form.address2 = document.querySelector("#address2").value;
      this.form.birth = new Date(this.form.birth);

      const res = this.$zido.signUp(this.form);
      if (res) {
        this.modal = "signUpSuccess";
      } else {
        this.modal = "signUpFail";
      }
    },
    // 다음 주소찾기 서비스
    searchAddress() {
      new daum.Postcode({
        oncomplete: function (data) {
          document.querySelector("#zipcode").value = data.zonecode;
          document.querySelector("#address").value = data.address;
        },
      }).open();
    },
  },
  async mounted() {
    //로그인 확인
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    //GET -- api/member/signup
    this.securityQuestions = await this.$zido.getSecurityQuestions();
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
  height: auto;
  width: 30%;
  padding: 0;
  color: rgb(255, 255, 255);
  background-color: #ff928e;
  border-radius: 10px;
  /* font-size: 15px !important; */
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
  padding: 1rem;
  color: rgb(118, 125, 133);
}

select {
  font-size: 20px;
  color: rgb(118, 125, 133);
}

.icon {
  padding: 0 !important;
}

@media (max-width: 1610px) {
  .birth-gender {
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  form {
    border: none !important;
    width: 100%;
  }
  #birth-gender {
    flex-direction: column;
  }
}

@media (max-width: 1250px) {
  #box {
    white-space: nowrap;
    text-overflow: ellipsis;
  }
  .wrapper {
    margin-inline: 10% !important;
  }
}

@media (max-width: 1050px) {
  form {
    width: 100%;
  }
  #birth-gender {
    flex-direction: column;
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

  input[type="text"],
  input[type="password"],
  input[type="email"],
  #security,
  #birth-gender,
  #birthday {
    font-size: 70%; /* 화면이 작아질 때 입력란의 너비를 조금씩 줄입니다. */
  }

  .button {
    font-size: 10px; /* 버튼의 너비 조정 */
    width: 40%;
  }

  form {
    margin-bottom: 0%;
  }
}

@media (max-width: 550px) {
  form {
    width: 120%;
  }
}

@media (max-width: 380px) {
  form {
    width: 140%;
  }
  #birthday {
    font-size: 10px !important;
  }
}
</style>
