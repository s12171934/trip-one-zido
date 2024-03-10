<template>
  <main class="wrapper">
    <h1>탈퇴 안내</h1>
    <h3>
      회원탈퇴를 신청하기 전에
      <b id="alertText">안내 사항을 꼭 확인</b>해 주세요.
    </h3>
    <h3 class="alt icon fa-check m-0">
      사용하고 계신 아이디 (name) 는 탈퇴할 경우 재사용 및 복구가 불가능 합니다.
    </h3>
    <p class="mb-3">
      <b id="alertText">탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구</b>가
      불가 하오니 신중하게 선택하시기 바랍니다.
    </p>

    <h3 class="alt icon fa-check">
      탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.
    </h3>
    <h3 class="alt icon fa-check">탈퇴를 원하신다면 비밀번호를 입력해주세요</h3>

    <div class="inner flex-column gap-2">
      <input type="password" v-model="password" placeholder="비밀번호" />
      <input type="password" v-model="passwordCheck" placeholder="비밀번호 확인" />
      <div class="d-flex gap-4">
        <button @click="resign" id="button" class="rounded-3" type="submit">회원탈퇴</button>
        <a href="/config" class="button alt rounded-3">취소하기</a>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  data(){
    return{
      password: "",
      passwordCheck: "",
    }
  },
  methods: {
    resign(){
      if(this.password === this.passwordCheck){
        this.$cookies.remove('login');
        this.$cookies.remove('autoLogin');
        location.href = '/welcome'
      }
    }
  },
  mounted(){
    this.$emit("meta",this.$route.matched[0].meta.isLogin);
  }
};
</script>

<style scoped>
h1 {
  color: #ff928e !important;
  margin-bottom: 3rem;
}

h3 {
  color: rgb(80, 80, 80) !important;
  margin-bottom: 1rem;
}

#alertText {
  color: rgb(235, 12, 12);
}

#button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}

.inner {
  margin-top: 3rem;
  display: flex;
  align-items: center;
  width: 30%;
}
</style>
