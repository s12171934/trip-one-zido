<template>
  <section id="main" class="wrapper">
    <h1>회원 정보 수정</h1>
    <table>
      <tr id="trHeader">
        <td colspan="3" id="updateTdSet">
          <h3>아이디 & 비밀번호</h3>
        </td>
      </tr>
      <tr id="updateArea">
        <td width="200">아이디</td>
        <td width="300">kitritest</td>
      </tr>

      <tr id="updateArea">
        <td>비밀번호</td>
        <td>
          <a href="/reset-pw" class="button">비밀번호 변경</a>
        </td>
      </tr>
      <tr id="trHeader">
        <td colspan="3" id="updateTdSet">
          <h3 id="trTitle">개인정보</h3>
        </td>
      </tr>
      <tr id="updateArea">
        <td>이름</td>
        <td>
          <input type="text" value="키트리" />
        </td>
      </tr>
      <tr id="updateArea">
        <td>주소</td>
        <td>
          <input type="text" value="08378" />
        </td>
        <td>
          <div class="d-flex">
            <input
              type="text"
              name="address"
              size="70"
              value="서울 구로구 디지털로 34길 43"
            />
            <button
              type="button"
              class="button icon fa-search ps-2 pe-2 ms-2"
              onclick="searchAddress()"
            >
              주소 검색
            </button>
          </div>
        </td>
      </tr>

      <tr>
        <td>상세주소</td>
        <td colspan="2"><input type="text" value="4층 406호 kitri" /></td>
      </tr>

      <tr id="updateArea">
        <td>보안질문</td>
        <td>
          <div class="select-wrapper">
            <select name="category" id="security">
              <option value="">보안질문</option>
              <option value="1" selected>당신이 태어난 곳은?</option>
              <option value="2">졸업한 초등학교명은?</option>
              <option value="3">당신의 별명은?</option>
              <option value="4">처음으로 국내여행 가본 곳은?</option>
              <option value="5">처음으로 해외여행 가본 국가는?</option>
              <option value="6">직접 작성</option>
            </select>
          </div>
        </td>
      </tr>
      <tr id="updateArea">
        <td>보안질문답</td>
        <td>
          <input type="text" value="대한민국" />
        </td>
      </tr>
      <tr id="updateArea">
        <td>생년월일</td>
        <td>1999-01-01</td>
      </tr>
      <tr id="updateArea">
        <td>성별</td>
        <td>
          <input type="radio" id="male" name="gender" checked /><label
            for="male"
            >남성</label
          >
          <input type="radio" id="female" name="gender" /><label for="female"
            >여성</label
          >
        </td>
      </tr>
      <tr id="updateArea">
        <td>전화번호</td>
        <td>
          <input type="text" value="010-9999-1111" />
        </td>
        <td>( - 는 제외하고 입력하세요)</td>
      </tr>
      <tr id="updateArea">
        <td>이메일 :</td>
        <td>
          <input type="email" value="kitri@kitri.com" />
        </td>
      </tr>
    </table>

    <div class="gap-2 d-flex justify-content-between">
      <a href="/resign" class="button alt">회원 탈퇴</a>
      <div>
        <button @click="updateMemberInfo" class="button">수정</button>
        <a href="/config" class="button alt ms-3">취소</a>
      </div>
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
import AlertModal from '../util/modal/AlertModal.vue';

export default {
  components: {
    AlertModal
  },
  data(){
    return{
      modalData: {},
    }
  },
  props: {
    modalShown: Boolean
  },
  methods: {
    updateMemberInfo(){
      this.modalData = {
        url: "/config",
        message: "회원정보 수정이<br />완료되었습니다.",
        buttonMessage: "확인",
      }
      this.$emit("modal");
    }
  },
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

#updateTdSet {
  height: 39;
  text-align: center;
}

#trHeader {
  background-color: rgba(144, 144, 144, 0.075);
}

#updateArea {
  background-color: white;
}
h3 {
  margin: 0;
  font-family: "Jalnan";
}
label {
  color: black !important;
}

.button {
  border-radius: 10px !important;
  font-family: "Jalnan";
}

section {
  font-family: "Jalnan";
}
</style>
