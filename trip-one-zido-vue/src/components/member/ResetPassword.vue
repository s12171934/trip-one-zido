<template>
  <main class="wrapper">
    <h1>비밀번호 재설정</h1>
    <hr />
    <table>
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
    passwordCheck() {
      this.checkPassword().then(result => {
        if(result){
          this.modal = "resetPasswordSuccess";
      } else {
          this.modal = "resetPasswordFail";
      }  
    }).catch(error => {
      console.log("비밀번호가 틀립니다.");
    });
  },
    checkPassword(){
      if(this.$route.params.id){
        return this.$zido.resetPassword(this.$route.params.id, this.changePassword, this.changePasswordCheck);
      }
      else{
        return this.$zido.changePassword(this.nowPassword, this.changePassword, this.changePasswordCheck);
      }
    }
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
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
</style>
