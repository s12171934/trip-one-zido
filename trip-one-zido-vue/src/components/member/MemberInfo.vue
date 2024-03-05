<template>
  <section id="main" class="wrapper">
    <div class="inner">
      <h1>회원 정보 수정</h1>

      <form action="/html-css/config/config/config.html">
        <table
          class="border border-secondary-subtle"
          width="600"
          border="1"
          cellspacing="0"
          cellpadding="3"
          align="center"
        >
          <tr id="trHeader">
            <td colspan="3" id="updateTdSet">
              <b>
                <h3 id="trTitle">아이디 & 비밀번호</h3>
              </b>
            </td>
          </tr>
          <tr id="updateArea">
            <td width="200">아이디 :</td>
            <td colspan="2" width="200">kitritest</td>
          </tr>

          <tr id="updateArea">
            <td width="200">비밀번호 :</td>

            <td>
              <ul class="actions">
                <li>
                  <button class="button" id="duplication-check">
                    <a
                      href="/html-css/config//update/pwupdate.html"
                      id="duplication-check"
                      >비밀번호 변경
                    </a>
                  </button>
                  <!-- <input id="input" type="submit" value="아이디 중복체크"> -->
                </li>
              </ul>
            </td>
          </tr>
          <tr id="trHeader">
            <td colspan="3" width="600" align="center">
              <b>
                <h3 id="trTitle">개인정보</h3>
              </b>
            </td>
          </tr>
          <tr id="updateArea">
            <td width="200">이름 :</td>
            <td colspan="2" width="200">
              <input
                type="text"
                name="name"
                size="15"
                maxlength="20"
                value="키트리"
              />
            </td>
          </tr>
          <tr id="updateArea">
            <td width="200">주소 :</td>
            <td width="200">
              <input type="text" name="address" size="70" value="08378" />
            </td>
            <td>
              <div class="d-flex flex-row">
                <input
                  type="text"
                  name="address"
                  size="70"
                  value="서울 구로구 디지털로 34길 43"
                />
                <ul class="actions">
                  <li>
                    <button
                      type="button"
                      class="button special icon fa-search"
                      onclick="searchAddress()"
                      id="button"
                    >
                      주소 검색
                    </button>
                  </li>
                </ul>
              </div>
              <input
                type="text"
                name="address"
                size="70"
                value="4층 406호 kitri"
              />
            </td>
          </tr>

          <tr id="updateArea">
            <td width="200">보안질문 :</td>
            <td colspan="2" width="200">
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
            <td width="200">보안질문답 :</td>
            <td colspan="2" width="200">
              <input
                type="text"
                name="security"
                size="15"
                maxlength="20"
                value="대한민국"
              />
            </td>
          </tr>
          <tr id="updateArea">
            <td width="200">생년월일 :</td>
            <td colspan="2" width="200">
              <input
                type="text"
                name="birthday"
                size="15"
                maxlength="20"
                value="1999-01-01"
                disabled
              />
            </td>
          </tr>
          <tr id="updateArea">
            <td width="200">성별 :</td>
            <td colspan="2">
              <input type="radio" id="male" name="gender" checked /><label
                for="male"
                >남성</label
              >
              <input type="radio" id="female" name="gender" /><label
                for="female"
                >여성</label
              >
            </td>
          </tr>
          <tr id="updateArea">
            <td width="200">전화번호 :</td>
            <td width="200">
              <input
                type="text"
                name="phonenumber"
                size="15"
                maxlength="20"
                value="010-9999-1111"
              />
            </td>
            <td>( - 는 제외하고 입력하세요)</td>
          </tr>
          <tr id="updateArea">
            <td width="200">이메일 :</td>
            <td colspan="2" width="200">
              <input
                type="text"
                name="email"
                size="15"
                maxlength="20"
                value="kitri@kitri"
              />
            </td>
          </tr>
        </table>
        <!-- <ul class="actions"> -->

        <div class="d-grid gap-2 d-md-flex">
          <a
            href="/html-css/config/resign/resign.html"
            class="button alt"
            id="withdrawal"
            >회원 탈퇴</a
          >
          <button type="submit" id="button">수정</button>
          <a href="/html-css/config/config/config.html" class="button alt"
            >취소</a
          >
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

#modalButton, #button {

    color: rgb(255, 255, 255) !important;
    background-color: #ff928e !important;
    border-radius: 10px !important;
    padding-left: 50px !important;
    margin-left: 10px;
}

#modalBox {
    background-color: #D9D9D9;
}

#formButton{
    margin-left: auto;
}

h3 {
    margin-top: 5px !important;
    margin-bottom: 0 !important;
}
label { 
    color: black !important;
}
.button {
    border-radius: 10px !important;
}

#button-pw {
    font-size: 100%;
    width: 50%;
}

#withdrawal {
    margin-right: auto;
}


.actions #duplication-check {
    text-decoration: none;
    color: rgb(255, 255, 255);
}
</style>
