<template>
  <div
    class="modal fade"
    :class="modalShown ? 'show' : ''"
    id="updatepic"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <!-- Vertically centered scrollable modal -->
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-0">
          <!-- <h1 class="modal-title fs-5" id="exampleModalLabel">타이틀 없애고</h1> -->
          <button
          @click="$emit('modal')"
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>

        <div class="m-5 modal-body border rounded-3" id="picBox">
          <img id="configPicUpdate" src="/images/조세호.png" alt="" />
        </div>

        <div class="modal-footer border border-0">
          <button type="button" class="rounded-3" id="modalButton">
            사진찾기
          </button>
          <form>
            <button
              type="button"
              class="rounded-3"
              id="modalButton"
              data-bs-dismiss="modal"
            >
              사진등록
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    modalShown: Boolean,
  },
};
</script>

<style scoped>
.show {
  opacity: 100;
  display: block;
}
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
