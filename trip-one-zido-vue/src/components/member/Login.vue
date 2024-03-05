<template>
  <section class="wrapper d-flex flex-row justify-content-center">
    <div
      id="box"
      class="d-flex flex-column justify-content-evenly align-items-center border"
    >
      <form class="border border-5">
        <h1>로그인</h1>
        <div class="row uniform">
          <div class="12u$">
            <input
              type="text"
              name="userid"
              id="userid"
              value=""
              placeholder="아이디"
            />
          </div>
          <!-- 비밀번호 -->
          <div class="12u$">
            <input
              type="password"
              name="pw"
              id="pw"
              value=""
              placeholder="비밀번호"
            />
          </div>

          <div class="d-flex flex-row justify-content-cente">
            <div class="6u$ 12u$(small)" id="idsavePosition">
              <input type="checkbox" id="idsave" name="idsave" />
              <label for="idsave">아이디 저장</label>
            </div>
            <div class="6u$ 12u$(small)" id="autologinPosition">
              <input type="checkbox" id="autologin" name="autologin" checked />
              <label for="autologin">자동 로그인</label>
            </div>
            <div class="6u$ 12u$(small)" id="idpwPosition">
              <a href="/html-css/sign/find/find.html">아이디 / 비밀번호 찾기</a>
            </div>
          </div>

          <!-- Break -->
          <div class="12u$" id="loginPosition">
            <div class="d-grid gap-2 d-md-flex justify-content-md-center">
              <a href="/html-css/main/main/main.html" class="button small"
                >로그인</a
              >
            </div>
          </div>
        </div>
      </form>

      <h1>SNS 연동하기</h1>
      <div class="d-flex gap-2 flex-row justify-content-center mb-4">
        <a href="#" id="naver-login-btn">
          <img
            @click="naverLogin"
            src="\images\btnG_아이콘사각.png"
            alt="네이버 아이디로 로그인"
            height="70px"
          />
        </a>
        <a href="#" id="kakao-login-btn">
          <img
            src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
            alt="카카오톡 아이디로 로그인"
            height="70px"
          />
        </a>
      </div>

      <h1>아직 회원이 아니시라면?</h1>
      <div class="12u$" id="signinPosition">
        <input id="input" type="submit" value="회원가입하러 가기" />
      </div>
    </div>
  </section>
</template>

<script>
export default {
  methods : {
    naverLogin(){
      const url = `https://nid.naver.com/oauth2.0/authorize`;

            console.log("==================url====================");
            console.log(url);

            window.location.href=url;
    }
  }
};
</script>

<style scoped>
.wrapper {
  padding-left: 10%;
  padding-right: 10%;
  background-color: #d9d9d9;
  border-radius: 50px;
  margin-inline: 20%;
  margin-top: 3%;
  margin-bottom: 3%;
}

#header,
h1,
h2,
h3,
head,
section {
  font-family: "Jalnan";
}

h1 {
  color: #ff928e !important;
  text-align: center;
}

#button {
  border-radius: 30px !important;
  font-size: 17px !important;
}

#input {
  border-radius: 15px;
  font-family: "Jalnan";
  font-size: 20px;
  background-color: #ff928e;
}

#box {
  background-color: white;
  border-radius: 50px;
  padding: 5%;
}

form {
  border-radius: 50px;
  padding: 5%;
}

#idsavePosition {
  text-align: left;
}

#autuloginPosition,
#loginPosition,
#signinPosition {
  text-align: center;
}

#idpwPosition {
  text-align: right;
  font-size: 14px;
}

#idpwPosition > a {
  color: #ff928e;
}

#menu {
  font-family: "Jalnan";
  font-size: 20px;
}

.button {
  font-family: "Jalnan";
  color: aliceblue;
  background-color: #ff928e;
  border-radius: 10px;
  font-size: 15px !important;
}
</style>
