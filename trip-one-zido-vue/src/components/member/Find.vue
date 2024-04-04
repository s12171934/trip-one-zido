<template>
  <main class="wrapper">
    <div @submit.prevent id="mainbox" class="d-flex">
      <!-- 아이디 찾기 -->
      <div id="box1" class="flex-column gap-5 p-5">
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
      <div id="box2" class="flex-column gap-5 p-5">
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
        data.modalDatas.findLoginIdSuccess.message = `당신의 아이디는<br />${res}<br />입니다.`;
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
/* 화면을 반반으로 나누기 */
.wrapper {
  display: flex;
  justify-content: center;
}
/* 회색 바탕 */
#mainbox {
  border-radius: 50px;
  background-color: #d9d9d9;
  display: flex;
  justify-content: center;
  align-content: center;
  margin-top: -30px;
}
/* 하얀색 바탕 */
#box1, #box2 {
  border-radius: 40px;
  background-color: white;
  margin: 1%;
  display: flex;
  justify-content: center;
  align-content: center;
}
/* 입력칸 상자 */
#boxin {
  border-radius: 30px;
  padding: 10%;
  display: flex;
  justify-content: center;
  align-content: center;
}

.button {
  margin-left: 50px;
  margin-right: 50px;
}

h1 {
  text-align: center;
}

@media (max-width: 1670px) {
  #mainbox {
    margin-left: 250px;
    margin-right: 250px;
  }
}

@media (max-width: 1600px) {
  #box {
    margin: 2%;
  }
  #boxin {
    margin-left: 10%;
    margin-right: 10%;
  }
}

@media (max-width: 1530px) {
  h1 {
    font-size: 28px;
  }
  #mainbox {
    margin-left: 300px;
    margin-right: 300px;
  }
  #box {
    margin: 45px;
    width: 40%;
  }
  #boxin {
    width: 80%;
    margin-right: 0%;
  }
  .button {
  margin-left: 15%;
  margin-right: 15%;
  padding: 0%;
  }
}

@media (max-width: 1460px) {
  #boxin {
    font-size: small;;
  }
  .gap-5 {
    gap: 2rem !important;
  }
  .p-5 {
    padding: 2rem !important;
  }
}

@media (max-width: 1320px) {
  #boxin {
    width: 100%;
    margin-left: 0%;
  }
  #mainbox {
    margin-left: 0px;
    margin-right: 0px;
  }
  .wrapper {
    padding-top: 90px;
  }
}

@media (max-width: 990px) {
  .wrapper {
    padding-bottom: 60px;
  }
}

/* 작은 화면에서 아이템을 세로로 배치하기 위해 추가된 스타일 */
@media (max-width: 768px) {
  #mainbox {
    flex-direction: column;
    padding: 1%;
  }
  #box {
    width: 90% !important;
  }
  h1 {
    font-size: 30px !important;
  }
  #boxin {
    border: none !important; /* 작은 화면에서 border 제거 */
  }
  .gap-5 {
    gap: 1rem !important;
  }
  .p-5 {
    padding: 1rem !important;
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
