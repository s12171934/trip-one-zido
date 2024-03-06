<template>
  <section>
    <form method="post" id="mainbox" class="d-flex flex-row">
      <!-- 왼쪽창 -->
      <div
        id="box"
        class="d-flex flex-column justify-content-evenly align-items-center"
      >
        <h1 class="text-center">아이디 찾기</h1>

        <div
          id="boxin"
          class="d-flex flex-column justify-content-evenly align-items-center border border-5"
        >
          <div class="12u$">
            <input
              type="text"
              name="name"
              id="name"
              v-model="loginIdName"
              placeholder="이름"
            />
          </div>
          <hr />
          <div class="12u$">
            <input
              type="email"
              name="email"
              v-model="loginIdEmail"
              placeholder="이메일 주소"
            />
          </div>
        </div>
        <!-- Break -->
        <div class="12u$ p-5" id="centerPosition">
          <ul class="actions">
            <li>
              <a
                @click="loginIdModal"
                id="input"
                class="button small rounded-3"
              >
                아이디 찾기
              </a>
            </li>
          </ul>
        </div>
      </div>

      <!-- 오른쪽창 -->
      <div
        id="box"
        class="d-flex flex-column justify-content-evenly align-items-center"
      >
        <h1 class="text-center pt-4 pb-2">비밀번호 찾기</h1>

        <div
          id="boxin"
          class="d-flex flex-column justify-content-evenly align-items-center border border-5"
        >
          <div class="12u$">
            <input
              type="text"
              name="login_id"
              id="login_id"
              v-model="pwLoginId"
              placeholder="아이디"
            />
          </div>
          <hr />
          <div class="12u$">
            <input
              type="text"
              name="name"
              id="name"
              v-model="pwName"
              placeholder="이름"
            />
          </div>
          <hr />
          <div class="12u$">
            <input
              type="email"
              name="email"
              v-model="pwEmail"
              placeholder="이메일 주소"
            />
          </div>
        </div>

        <!-- Break -->
        <div class="12u$ p-5" id="centerPosition">
          <ul class="actions">
            <li>
              <a
                @click="securityModal"
                id="input"
                class="button small rounded-3"
                >비밀번호 변경하기</a
              >
            </li>
          </ul>
        </div>
      </div>
    </form>
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
    securityFail(){
      this.security = false;
      this.modalData = {
        url: "/find",
          message: "올바르지 않는 보안질문 입니다",
          buttonMessage: "재시도",
      }
    }
  },
};
</script>

<style scoped>
#mainbox {
  border-radius: 50px;
  background-color: #d9d9d9;
  margin: 5%;
}
#box {
  border-radius: 40px;
  background-color: white;
  margin: 5%;
  width: 50% !important;
}
#boxin {
  border-radius: 30px;
  padding: 4%;
}
#input {
  background-color: #ff928e;
  font-family: "Jalnan";
  font-size: 20px;
}
#button {
  font-family: "Jalnan";
  border-radius: 40px;
  font-size: 17px;
}

#leftPosition {
  text-align: left;
}

#centerPosition {
  text-align: center;
}

#header,
section,
h1,
h3 {
  font-family: "Jalnan";
}

#menu {
  font-size: 20px;
}

#footer {
  font-size: 10px;
}

h1 {
  color: #ff928e !important;
}
select {
  color: #bcbcbc;
}
</style>
