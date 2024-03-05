<template>
  <section id="main" class="wrapper">
    <div class="inner">
      <h1>비밀번호 변경</h1>

      <form action="/html-css/config/config/config.html">
        <table
          class="border border-secondary-subtle"
          width="600"
          border="1"
          cellspacing="0"
          cellpadding="3"
          align="center"
        >
          <tr>
            <td colspan="2" id="updateTdSet">
              <b>
                <h3 style="color: #767d85">비밀번호 변경하기</h3>
              </b>
            </td>
          </tr>

          <tr id="updateArea">
            <td width="200">현재 비밀번호 입력</td>
            <td width="400">
              <input
                type="password"
                name="passwd"
                size="10"
                maxlength="10"
                value="testtest123"
              />
            </td>
          </tr>

          <tr id="updateArea">
            <td width="200">변경 비밀번호 입력</td>
            <td width="400">
              <input
                type="password"
                name="changepasswd"
                size="10"
                maxlength="10"
                value="testtest123"
              />
            </td>
          </tr>

          <tr id="updateArea">
            <td width="200">변경 비밀번호 확인</td>
            <td width="400">
              <input
                type="password"
                name="changepasswdChk"
                size="10"
                maxlength="10"
                value="testtest123"
              />
            </td>
          </tr>
        </table>
        <ul class="actions">
          <li id="formButton">
            <button type="submit" class="rounded-3" id="button">
              비밀번호 변경하기
            </button>
          </li>
        </ul>
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

#modalButton,
#button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
  border-radius: 10px !important;
  padding-left: 50px !important;
  margin-left: 10px;
}

#modalBox {
  background-color: #d9d9d9;
}

#formButton {
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
