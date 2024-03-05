<template>
  <section id="main" class="wrapper">
    <div id="leftPosition">
      <h1>탈퇴 안내</h1>
      <h3>
        회원탈퇴를 신청하기 전에
        <b><span id="alertText">안내 사항을 꼭 확인</span></b
        >해 주세요.
      </h3>
    </div>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <ul class="actions" id="actions">
      <li>
        <h3 class="alt icon fa-check">
          사용하고 계신 아이디 (name) 는 탈퇴할 경우 재사용 및 복구가 불가능
          합니다.
        </h3>
      </li>
      <p id="leftPosition">
        &nbsp;&nbsp;&nbsp;&nbsp;<b
          ><span id="alertText"
            >탈퇴한 아이디는 본인과 타인 모두 재사용 및 복구</span
          ></b
        >가 불가 하오니 신중하게 선택하시기 바랍니다.
      </p>
    </ul>
    <ul class="actions" id="actions">
      <li>
        <h3 class="alt icon fa-check">
          탈퇴 후 회원정보 및 개인형 서비스 이용기록은 모두 삭제됩니다.
        </h3>
      </li>
    </ul>
    <ul class="actions" id="actions">
      <li>
        <h3 class="alt icon fa-check">
          탈퇴를 원하신다면 비밀번호를 입력해주세요
        </h3>
      </li>
    </ul>
    <br />
    <div class="inner">
      <form action="/html-css/index.html">
        <div class="row uniform">
          <div class="d-flex flex-row mb-6">
            <span class="details" id="inputArea">비밀번호 : </span>
            <div class="4u">
              <input
                type="password"
                name="pw"
                id="pw"
                value=""
                placeholder="비밀번호"
              />
            </div>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <span class="details" id="inputArea">비밀번호 확인 : </span>
            <div class="4u">
              <input
                type="password"
                name="pwchk"
                id="pwchk"
                value=""
                placeholder="비밀번호 확인"
              />
            </div>
          </div>
        </div>
        <div class="12u$" id="formArea">
          <ul class="actions">
            <li>
              <button id="button" class="rounded-3" type="submit">
                회원탈퇴
              </button>
            </li>
            <li>
              <a
                href="/html-css/config/config/config.html"
                class="button alt rounded-3"
                >취소하기</a
              >
            </li>
          </ul>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

#mainTitle {
  text-align: left;
}

h1 {
  color: #ff928e !important;
}

h3 {
  color: rgb(80, 80, 80) !important;
}

#alertText {
  color: rgb(235, 12, 12);
}

#actions {
  text-align: left;
  margin-left: 5%;
  margin-top: 2%;
}

#updateTdSet {
  width: 200;
  height: 39;
  text-align: center;
}

#trHeader {
  background-color: rgba(144, 144, 144, 0.075);
}

#trTitle {
  color: #767d85;
}

#updateArea {
  background-color: white;
}

#modalButton {
  color: rgb(255, 255, 255);
  background-color: #ff928e;
}

#modalBox {
  background-color: #d9d9d9;
}

#formButton {
  margin-left: 40%;
}

#inputArea {
  margin: 1%;
}

#formArea {
  text-align: center;
  margin: 2%;
}

#leftPosition {
  text-align: left;
}

#leftPosition {
  text-align: left;
}
#button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}
</style>
