<template>
  <section id="main" class="wrapper">
    <div
      id="box"
      class="d-flex flex-column justify-content-evenly align-items-center"
    >
      <form method="post" action="#" class="border border-5 p-5">
        <div class="row uniform">
          <div class="d-flex flex-row">
            <!-- 아이디 -->
            <div class="12u$(xsmall) w-50">
              <input
                type="text"
                name="name"
                id="name"
                value=""
                placeholder="사용할 아이디"
              />
            </div>
            <!-- 아이디중복체크 -->
            <div class="12u$(xsmall)" id="duplication-check">
              <ul class="actions">
                <li>
                  <button
                    class="btn btn-sm rounded-3"
                    id="duplication-check-font"
                  >
                    아이디중복체크
                  </button>
                  <!-- <input id="input" type="submit" value="아이디 중복체크"> -->
                </li>
              </ul>
            </div>
            <!-- 이름 -->
            <div class="12u$(xsmall) w-50">
              <input
                type="text"
                name="userid"
                id="userid"
                value=""
                placeholder="이름"
              />
            </div>
          </div>

          <!-- 비밀번호 -->
          <div class="6u 12u$(xsmall)">
            <input
              type="password"
              name="pw"
              id="pw"
              value=""
              placeholder="비밀번호"
            />
          </div>
          <!-- 비밀번호 확인 -->
          <div class="6u$ 12u$(xsmall)">
            <input
              type="password"
              name="pwchk"
              id="pwchk"
              value=""
              placeholder="비밀번호 확인"
            />
          </div>
          <!-- 보안질문 -->
          <div class="12u$">
            <div class="select-wrapper">
              <select name="category" id="security">
                <option value="">보안질문</option>
                <option value="1">당신이 태어난 곳은?</option>
                <option value="2">졸업한 초등학교명은?</option>
                <option value="3">당신의 별명은?</option>
                <option value="4">처음으로 국내여행 가본 곳은?</option>
                <option value="5">처음으로 해외여행 가본 국가는?</option>
              </select>
            </div>
          </div>
          <!-- 보안질문 -->
          <div class="12u$">
            <input
              type="text"
              name="answer"
              id="answer"
              value=""
              placeholder="보안질문 답"
            />
          </div>

          <!-- 이메일 -->
          <div class="6u$ 12u$(xsmall)">
            <input
              type="email"
              name="email"
              id="email"
              value=""
              placeholder="Email"
            />
          </div>
          <!-- 핸드폰번호 -->
          <div class="6u$ 12u$(xsmall)">
            <input
              type="text"
              name="phonenumber"
              id="phonenumber"
              value=""
              placeholder="핸드폰번호 ex)010-xxxx-xxxx"
            />
          </div>

          <!-- 주소 -->
          <div class="d-flex">
            <div class="flex-fill 4u 12u$" for="zipcode">
              <input
                type="text"
                id="zipcode"
                name="zipcode"
                readonly
                value=""
                placeholder="우편번호"
              />
            </div>
            <div class="flex-fill 12u$" for="address">
              <input
                type="text"
                id="address"
                name="address"
                readonly
                value=""
                placeholder="주소"
              />
            </div>
            <div class="flex-fill" id="search-adress">
              <button class="rounded-3" type="button" onclick="searchAddress()">
                <scirpt> </scirpt>주소 검색</button
              ><br />
            </div>
          </div>
          <div class="12u$" for="detailAddress">
            <input
              type="text"
              id="detailAddress"
              name="detailAddress"
              value=""
              placeholder="상세한 주소"
            />
          </div>

          <!-- 생년월일 -->
          <div class="d-flex">
            <div class="flex-fill 5u 12u$(xsmall)" id="leftPosition">
              <input
                type="text"
                name="birthday"
                id="birthday"
                value=""
                placeholder="생년월일"
              />
            </div>

            <div class="flex-fill 5u 12u$(small) pt-3" id="centerPosition">
              성별 :
              <input type="radio" id="male" name="gender" checked /><label
                for="male"
                id="gender"
                >남성</label
              >
              <input type="radio" id="female" name="gender" /><label
                for="female"
                id="gender"
                >여성</label
              >
            </div>
          </div>
        </div>
      </form>

      <!-- 회원가입버튼 -->
      <div class="12u$" id="centerPosition">
        <ul class="actions">
          <li>
            <input
              class="rounded-3"
              id="actions"
              type="submit"
              value="회원가입 하기!"
              onclick="location.href='/html-css/sign/login.html'"
            />
          </li>
        </ul>
      </div>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
#button {
  font-family: "Jalnan";
  border-radius: 30px;
  font-size: 17px;
}
#input {
  font-family: "Jalnan";
  font-size: 10px;
  padding: 1%;
}
.wrapper {
  padding-left: 5%;
  padding-right: 5%;
  background-color: #d9d9d9;
  border-radius: 50px;
  margin-inline: 10%;
  margin-top: 3%;
  margin-bottom: 3%;
}
#box {
  background-color: white;
  border-radius: 50px;
  padding: 10%;
}
#actions {
  font-family: "Jalnan";
  font-size: 25px;
  color: rgb(255, 255, 255);
  background-color: #ff928e;
}
option,
select,
div {
  color: #bcbcbc;
}

#leftPosition {
  text-align: left;
}

#centerPosition {
  text-align: center;
}

#gender {
  color: #bcbcbc;
}

#header,
section,
h1,
h2,
h3 {
  font-family: "Jalnan";
}

head {
  font-family: "Jalnan";
}

#menu {
  font-family: "Jalnan";
  font-size: 20px;
}

#footer {
  font-family: "Jalnan";
  font-size: 10px;
}
form {
  border-radius: 30px;
}
button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}

#duplication-check {
  margin-top: 1%;
  margin-right: 5%;
  margin-left: 1%;
}

#search-adress {
  /* margin-top: 1%; */
  /* margin-right: 5%; */
  margin-left: 1%;
}
</style>
