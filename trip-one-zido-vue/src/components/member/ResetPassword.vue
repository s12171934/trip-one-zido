<template>
  <section id="main" class="wrapper">
    <h1>비밀번호 재설정</h1>
    <hr />
    <table>
      <tr v-if="$route.params.id == ''">
        <td>현재 비밀번호 입력</td>
        <td>
          <input type="password" value="testtest123" />
        </td>
      </tr>

      <tr>
        <td width="200">재설정 비밀번호 입력</td>
        <td width="400">
          <input type="password" value="testtest123" />
        </td>
      </tr>

      <tr>
        <td width="200">재설정 비밀번호 확인</td>
        <td width="400">
          <input type="password" value="testtest123" />
        </td>
      </tr>
    </table>
    <div class="d-flex justify-content-center">
      <button class="button">비밀번호 재설정하기</button>
    </div>
  </section>
</template>

<script>
export default {
  mounted(){
    this.$emit("meta",this.$route.matched[0].meta.isLogin);
  }
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
