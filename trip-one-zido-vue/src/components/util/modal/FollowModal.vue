<template>
  <div
    class="modal fade"
    :class="modalShown ? 'show' : ''"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <!-- Vertically centered scrollable modal -->
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-0">
          <button
            @click="$emit('modal')"
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="m-4 modal-body border rounded-3" id="followSearch">
          <h4>팔로워 목록</h4>
        </div>

        <div class="m-4 modal-body border rounded-3" id="followList">
          <div class="d-flex">
            <a href="/html-css/main/detail/detail_other.html"
              ><img
                class="rounded-circle"
                id="followPic"
                src="/images/유재석.png"
                alt="..."
            /></a>

            <div class="d-flex flex-column" id="followRequest">
              <div class="fw-bold"><h4>유재석</h4></div>
              <div class="fw-bold">
                아이디
                <span id="followButton"
                  ><button class="btn btn-sm">삭제</button></span
                >
              </div>
            </div>
          </div>

          <div class="d-flex">
            <a href="/html-css/main/detail/detail_other.html"
              ><img
                class="rounded-circle"
                id="followPic"
                src="/images/조세호.png"
                alt="..."
            /></a>
            <div class="d-flex flex-column" id="followRequest">
              <div class="fw-bold"><h4>조세호</h4></div>
              <div class="fw-bold">
                아이디
                <span id="followButton"
                  ><button class="btn btn-sm">삭제</button></span
                >
              </div>
            </div>
          </div>

          <div class="d-flex">
            <a href="/html-css/main/detail/detail_other.html"
              ><img
                class="rounded-circle"
                id="followPic"
                src="/images/여자.png"
                alt="..."
            /></a>
            <div class="d-flex flex-column" id="followRequest">
              <div class="fw-bold"><h4>여자</h4></div>
              <div class="fw-bold">
                아이디
                <span id="followButton"
                  ><button class="btn btn-sm">삭제</button></span
                >
              </div>
            </div>
          </div>

          <div class="d-flex">
            <a href="/html-css/main/detail/detail_other.html"
              ><img
                class="rounded-circle"
                id="followPic"
                src="/images/남자.png"
                alt="..."
            /></a>
            <div class="d-flex flex-column" id="followRequest">
              <div class="fw-bold"><h4>남자</h4></div>
              <div class="fw-bold">
                아이디
                <span id="followButton"
                  ><button class="btn btn-sm">삭제</button></span
                >
              </div>
            </div>
          </div>

          <div class="d-flex">
            <a href="/html-css/main/detail/detail_other.html"
              ><img
                class="rounded-circle"
                id="followPic"
                src="/images/남자.png"
                alt="..."
            /></a>
            <div class="d-flex flex-column" id="followRequest">
              <div class="fw-bold"><h4>남자</h4></div>
              <div class="fw-bold">
                아이디
                <span id="followButton"
                  ><button class="btn btn-sm">삭제</button></span
                >
              </div>
            </div>
          </div>
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

#main {
  margin-left: 5%;
  margin-right: 5%;
}

#mainTitle {
  text-align: left;
}

#leftSide {
  width: 36%;
}

#rightSide {
  width: 64%;
}

h1 {
  color: #ff928e !important;
}

h3 {
  color: rgb(80, 80, 80) !important;
}

#userPic {
  width: 260px;
  height: 260px;
}

#userName {
  font-size: 25px;
  margin-left: 10%;
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
#followingStatus {
  margin-left: 5%;
}

#followButton {
  margin-left: 200px;
}

#followingButton {
  margin-left: 180px;
}

#p-2 {
  margin: 2%;
}

#googleMap {
  margin-top: 4%;
}

#viewOrder {
  margin-left: 2%;
}

#addBoardList {
  margin-left: auto;
}

#zzim,
#unzzim,
#star,
#like,
#unlike {
  width: 25px;
  height: 25px;
}

#scheduleTitle,
#locationTitle {
  text-align: center;
}

#schedulePic,
#locationPic {
  width: 260px;
  height: 260px;
}

#buttonBackground {
  background-color: white;
}

#recentViewBox {
  overflow: auto;
  overflow-y: hidden;
}

img {
  vertical-align: top;
}

.banner_img,
.banner_bg {
  display: inline-block;
  position: relative;
  z-index: 0;
}

.banner_img:hover:after,
.banner_img:hover > .hover_text,
.banner_bg:hover:after,
.banner_bg:hover > .hover_text {
  display: block;
}

.banner_img:after,
.banner_bg:after,
.hover_text {
  display: none;
}

.banner_img:after,
.banner_bg:after {
  content: "";
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.3);
  z-index: 10;
  border-radius: 13%;
}

.banner_img {
  overflow: hidden;
}

.banner_img:hover img {
  transition: 1s;
}

.hover_text {
  position: absolute;
  top: 35%;
  /* right: 25%;
    bottom: 25%; */
  left: 30%;
  color: #fff;
  font-weight: 600;
  font-size: 20px;
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

/* 테스트용 */
.slide,
.slide_bottom {
  /* layout */
  display: flex;
  flex-wrap: nowrap;
  /* 컨테이너의 내용물이 컨테이너 크기(width, height)를 넘어설 때 보이지 않도록 하기 위해 hidden을 준다. */
  overflow: hidden;

  /* position */
  /* slide_button의 position absolute가 컨테이너 안쪽에서 top, left, right offset이 적용될 수 있도록 relative를 준다. (기본값이 static인데, static인 경우 그 상위 컨테이너로 나가면서 현재 코드에선 html을 기준으로 offset을 적용시키기 때문) */
  position: relative;

  /* size */
  width: 100%;
}

.slide_item,
.slide_item_bottom {
  /* layout */
  display: flex;
  align-items: center;
  justify-content: center;

  /* position - 버튼 클릭시 left offset값을 적용시키기 위해 */
  position: relative;
  left: 0px;

  /* size */
  width: 100%;
  /* flex item의 flex-shrink는 기본값이 1이므로 컨테이너 크기에 맞게 줄어드는데, 슬라이드를 구현할 것이므로 줄어들지 않도록 0을 준다. */
  flex-shrink: 0;

  /* transition */
  transition: left 0.5s;
}

.slide_button {
  /* layout */
  display: flex;
  justify-content: center;
  align-items: center;

  /* position */
  position: absolute;
  /* 버튼이 중앙에 위치하게 하기위해 계산 */
  top: calc(38%);

  /* size */
  width: 25px;
  height: 25px;

  /* style */
  border-radius: 100%;

  color: #fcbebc;
  cursor: pointer;
}

.slide_prev_button,
.slide_prev_button_bottom {
  left: 5px;
}

.slide_next_button,
.slide_next_button_bottom {
  right: 5px;
}

/* 페이지네이션 스타일 */
ul,
li {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.slide_pagination,
.slide_pagination_bottom {
  /* layout */
  display: flex;
  gap: 3px;

  /* position */
  position: absolute;
  bottom: 0;
  /* left:50%, translateX(-50%)를 하면 가로 가운데로 위치시킬 수 있다. */
  left: 49%;
  transform: translateX(-50%);
}

.slide_pagination > li,
.slide_pagination_bottom > li {
  /* 현재 슬라이드가 아닌 것은 투명도 부여 */
  color: #30323588;
  cursor: pointer;
  font-size: 25px;
}

.slide_pagination > li.active,
.slide_pagination_bottom > li.active {
  /* 현재 슬라이드 색상은 투명도 없이 */
  color: #f6755e;
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
.button {
  font-family: "Jalnan";
}

#menu {
  font-size: 20px;
}

#category-width {
  width: 130%;
}

#category,
#mySelect {
  font-size: 80%;
}

#edit-profile {
  background-color: #ff928e;
  width: 100%;
  /* font-size: 1; */
}
</style>
