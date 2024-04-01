<template>
  <main class="wrapper">
    <h1>비밀번호 재설정</h1>
    <hr />
    <table>
      <!-- 설정페이지 -> 비밀번호 변경시에 노출 -->
      <tr v-if="$route.params.id == ''">
        <td>현재 비밀번호 입력</td>
        <td>
          <input type="password" v-model="nowPassword" />
        </td>
      </tr>

      <tr>
        <td width="200">재설정 비밀번호 입력</td>
        <td width="400">
          <input type="password" v-model="changePassword" />
        </td>
      </tr>

      <tr>
        <td width="200">재설정 비밀번호 확인</td>
        <td width="400">
          <input type="password" v-model="changePasswordCheck" />
        </td>
      </tr>
    </table>
    <div class="d-flex justify-content-center">
      <button
        @click="passwordCheck"
        class="button"
        data-bs-toggle="modal"
        data-bs-target="#alertModal"
      >
        비밀번호 재설정하기
      </button>
    </div>
  </main>

  <!-- 비밀번호 재설정 성공여부 모달 -->
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
      nowPassword: "",
      changePassword: "",
      changePasswordCheck: "",
    };
  },
  methods: {
    async passwordCheck() {
      const res = await this.checkPassword();
      if (res) {
        this.modal = "resetPasswordSuccess";
      } else {
        this.modal = "resetPasswordFail";
      }
    },
    //PUT -- api/member/password/{id}
    //PUT -- api/member/password
    checkPassword() {
      if (this.$route.params.id) {
        return this.$zido.resetPassword(
          this.$route.params.id,
          this.changePassword,
          this.changePasswordCheck
        );
      } else {
        return this.$zido.changePassword(
          this.nowPassword,
          this.changePassword,
          this.changePasswordCheck
        );
      }
    },
  },
  mounted() {
    //로그인 확인
    this.$emit("meta", this.$route.matched[0].meta.isLogin || this.$cookies.isKey('login'));
  },
};
</script>

<style scoped>
main {
  margin-left: 20%;
  margin-right: 20%;
}

h1 {
  color: #ff928e !important;
}

.button {
  border-radius: 10px !important;
}

@media (max-width: 1000px) {
  main {
    margin-left: 10%;
    margin-right: 10%;
  }
  .button {
    margin-bottom: 5%;
  }
}

@media (max-width: 720px) {
  td {
    font-size: 13px;
  }
  main {
    margin-left: 5%;
    margin-right: 5%;
  }
}

@media (max-width: 475px) {
  td {
    font-size: 10px;
  }
  .button {
    font-size: 13px;
  }
}

@media (max-width: 365px) {
  td {
    font-size: 8px;
  }
  .button {
    font-size: 10px;
  }
}
</style>
