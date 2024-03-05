<template>
  <section id="main" class="wrapper">
    <div id="mainTitle">
      <h1>설정페이지</h1>
    </div>
    <div id="mainTitle">
      <p>
        <a href="/html-css/main/detail/detail.html"
          ><img
            id="configPic"
            src="/images/pic01.jpg"
            alt=""
            class="rounded-circle"
          />
        </a>

        <button
          type="button"
          class="btn rounded-3"
          data-bs-toggle="modal"
          data-bs-target="#updatepic"
        >
          사진 수정
        </button>
        <sapn id="configName">여행자님!</sapn>
      </p>
    </div>
    <div class="d-flex flex-row mb-3">
      <div class="p-2" id="p-2">
        <h3>12</h3>
        <h3>게시물</h3>
      </div>
      <div class="p-2" id="p-2">
        <h3>255</h3>
        <h3 data-bs-toggle="modal" data-bs-target="#exampleModal">팔로워</h3>
      </div>
      <div class="p-2" id="p-2">
        <h3>37</h3>
        <h3 data-bs-toggle="modal" data-bs-target="#exampleModal_follow">
          팔로잉
        </h3>
      </div>
      <div class="p-2" id="p-2">
        <h3>999</h3>
        <h3><a href="/html-css/main/zzim/zzim.html">찜</a></h3>
      </div>
    </div>
    <h1 id="configInfo">내 정보</h1>
    <hr id="configInfoLine" />
    <h3 id="configInfo">
      <a href="/html-css/config/update/update.html"> 회원 정보 수정 </a>
    </h3>
    <h3 id="configInfo">
      <a href="/html-css/config/recent/recent-view.html"> 최근 본 게시물 </a>
    </h3>
    <h3 id="configInfo">
      <a href="/html-css/config/update/pwupdate.html"> 비밀번호 변경 </a>
    </h3>
    <h3 id="configInfo">
      <a href="/html-css/config/resign/resign.html"> 회원탈퇴 </a>
    </h3>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
/* 스크롤바 */
div::-webkit-scrollbar {
  /* 스크롤바 배경 */
  height: 15px;
  border-radius: 10px;
  background: rgb(236, 236, 250);
}
div::-webkit-scrollbar-thumb {
  /* 스크롤바 */
  width: 15px;
  border-radius: 10px;
  background: pink;
}

#main {
  margin-left: 5%;
  margin-right: 5%;
}

h1 {
  color: #ff928e !important;
}

h3 {
  color: rgb(80, 80, 80) !important;
}

a {
  text-decoration: none !important;
}

#mainTitle {
  text-align: left;
}

#configPic {
  width: 260px;
  height: 260px;
}

#configName {
  font-size: 25px;
  margin-left: 10%;
}

#configPicUpdate {
  width: 360px;
  height: 330px;
}

#picBox {
  background-color: #d9d9d9;
  display: flex;
  justify-content: center;
}

#modalButton,
.btn {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}

#followSearch,
#followingSearch {
  background-color: #d9d9d9;
  overflow: hidden;
}

#searchResult,
#followList,
#followingList {
  background-color: #d9d9d9;
  overflow: auto;
}

#followPic {
  width: 75px;
  height: 75px;
}

#followRequest,
#followingDrop {
  margin-left: 5%;
}

#followButton {
  margin-left: 200px;
}

#followingButton {
  margin-left: 180px;
}

#configInfo {
  text-align: right;
  margin-right: 10%;
}

#configInfoLine {
  width: 90%;
}

#p-2 {
  margin: 2%;
}

#footer {
  font-family: "Jalnan";
  font-size: 10px;
}

h1,
h2,
h3,
h4,
body,
p,
.button,
.main {
  font-family: "Jalnan";
}

#menu {
  font-size: 20px;
}
</style>
