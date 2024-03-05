<template>
  <section>
    <form method="post" id="mainbox" class="d-flex flex-row">
      <!-- 왼쪽창 -->
      <div
        id="box"
        class="d-flex flex-column justify-content-evenly align-items-center"
      >
        <h1 class="text-center">아이디 찾기</h1>

        <div
          id="boxin"
          class="d-flex flex-column justify-content-evenly align-items-center border border-5"
        >
          <div class="12u$">
            <input
              type="text"
              name="name"
              id="name"
              value=""
              placeholder="이름"
            />
          </div>
          <hr />
          <div class="12u$">
            <input
              type="email"
              name="email"
              id="email"
              value=""
              placeholder="이메일 주소"
            />
          </div>
        </div>
        <!-- Break -->
        <div class="12u$ p-5" id="centerPosition">
          <ul class="actions">
            <li>
              <input
                id="input"
                class="button small rounded-3"
                type="submit"
                value="찾기!"
              />
            </li>
          </ul>
        </div>
      </div>

      <!-- 오른쪽창 -->
      <div
        id="box"
        class="d-flex flex-column justify-content-evenly align-items-center"
      >
        <h1 class="text-center pt-4 pb-2">비밀번호 찾기</h1>

        <div
          id="boxin"
          class="d-flex flex-column justify-content-evenly align-items-center border border-5"
        >
          <div class="12u$">
            <input
              type="text"
              name="login_id"
              id="login_id"
              value=""
              placeholder="아이디"
            />
          </div>
          <hr />
          <div class="12u$">
            <input
              type="text"
              name="name"
              id="name"
              value=""
              placeholder="이름"
            />
          </div>
          <hr />
          <div class="12u$">
            <input
              type="email"
              name="email"
              id="email"
              value=""
              placeholder="이메일 주소"
            />
          </div>
        </div>

        <!-- Break -->
        <div class="12u$ p-5" id="centerPosition">
          <ul class="actions">
            <li>
              <a
                href="/html-css/sign/reset/reset-pw.html"
                id="input"
                class="button small rounded-3"
                >비밀번호 변경하기</a
              >
            </li>
          </ul>
        </div>
      </div>
    </form>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
#mainbox {
    border-radius: 50px;
    background-color: #D9D9D9;
    margin: 5%;
}
#box{
    border-radius: 40px;
    background-color: white; 
    margin: 5%;
    width: 50%!important;
}
#boxin{
    border-radius: 30px;
    padding: 4%;
}
#input{
    background-color: #ff928e;
    font-family: 'Jalnan';
    font-size: 20px;
}
#button {
    font-family: 'Jalnan';
    border-radius: 40px;
    font-size: 17px;
}

#leftPosition {
    text-align: left;
}

#centerPosition {
    text-align: center;
}

#header, section, h1, h3{
    font-family: 'Jalnan';
}

#menu {
    font-size: 20px;
}

#footer {
    font-size: 10px;
}

h1{
    color:#ff928e !important;
}
select{
    color: #bcbcbc;
}
</style>
