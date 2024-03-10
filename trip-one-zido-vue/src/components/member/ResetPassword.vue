<template>
  <section id="main" class="wrapper">
    <h1>비밀번호 재설정</h1>
    <hr />
    <table>
      <tr v-if="$route.params.id == ''">
        <td>현재 비밀번호 입력</td>
        <td>
          <input type="password" v-model="prevPw" />
        </td>
      </tr>

      <tr>
        <td width="200">재설정 비밀번호 입력</td>
        <td width="400">
          <input type="password" v-model="resetPw" />
        </td>
      </tr>

      <tr>
        <td width="200">재설정 비밀번호 확인</td>
        <td width="400">
          <input type="password" v-model="resetPwCheck" />
        </td>
      </tr>
    </table>
    <div class="d-flex justify-content-center">
      <button @click="passwordCheck" class="button">비밀번호 재설정하기</button>
    </div>
  </section>

  <AlertModal
    :modalShown="modalShown"
    @modal="$emit('modal')"
    :url="modalData.url"
    :message="modalData.message"
    :buttonMessage="modalData.buttonMessage"
  />
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";

export default {
  components: {
    AlertModal,
  },
  data() {
    return {
      modalData: {},
      prevPw: "",
      resetPw: "",
      resetPwCheck: "",
    };
  },
  props: {
    modalShown: Boolean,
  },
  methods: {
    passwordCheck() {
      if (this.resetPw === this.resetPwCheck) {
        this.modalData = {
          url: "/config",
          message: "비밀번호가<br />변경되었습니다.",
          buttonMessage: "확인",
        };
      } else {
        this.modalData = {
          url: "/reset-pw",
          message: "비밀번호와<br />비밀번호 확인이<br />다릅니다.",
          buttonMessage: "비밀번호 재설정 하기",
        };
      }
      this.$emit("modal");
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
#main {
  margin-left: 20%;
  margin-right: 20%;
}

h1 {
  color: #ff928e !important;
  font-family: "Jalnan";
}

.button {
  border-radius: 10px !important;
  font-family: "Jalnan";
}

section {
  font-family: "Jalnan";
}
</style>
