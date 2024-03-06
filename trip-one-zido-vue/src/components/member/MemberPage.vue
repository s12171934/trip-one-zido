<template>
  <section id="main" class="wrapper">
    <div class="d-flex flex-row mb-2">
      <div class="d-flex flex-column mb-2" id="leftSide">
        <div class="p-2">
          <div id="mainTitle">
            <p>
              <img
                id="userPic"
                src="/images/pic01.jpg"
                alt=""
                class="rounded-circle"
              />
              <sapn id="userName">여행자님!</sapn>
            </p>
          </div>
          <ul class="actions fit" id="edit">
            <li>
              <a
                href="/html-css/config/config/config.html"
                class="button rounded-5"
                id="edit-profile"
                >프로필 편집</a
              >
            </li>
          </ul>
          <hr />
          <div class="d-flex flex-row mb-3">
            <div class="p-2" id="p-2">
              <h3>12</h3>
              <h3>게시글</h3>
            </div>
            <div @click="$emit('modal')" class="p-2" id="p-2">
              <h3>255</h3>
              <h3>팔로워</h3>
            </div>
            <div @click="$emit('modal')" class="p-2" id="p-2">
              <h3>37</h3>
              <h3>팔로잉</h3>
            </div>
            <div class="p-2" id="p-2">
              <h3>999</h3>
              <h3><a href="/html-css/main/zzim/zzim.html">찜</a></h3>
            </div>
          </div>
        </div>
        <div class="p-2">
          <div class="fs-5 mb-5" id="googleMap">
            <iframe
              class="rounded-5"
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3260.7271121845406!2d129.2207447762419!3d35.18835345689094!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35688c1bbbdea6c1%3A0xa141406d6f112e5c!2z7ZW064-Z7Jqp6raB7IKs!5e0!3m2!1sko!2skr!4v1708994953688!5m2!1sko!2skr"
              style="border: 0"
              allowfullscreen=""
              loading="lazy"
              width="100%"
              height="250"
              referrerpolicy="no-referrer-when-downgrade"
            >
            </iframe>
          </div>
        </div>
      </div>
      <!-- <div class="p-2"> -->
      <div class="d-flex flex-column mb-2" id="rightSide">
        <div>
          <div class="d-flex flex-row mb-3">
            <h1>일정 게시글</h1>
            <div id="viewOrder">
              <ul class="actions">
                <li>
                  <div class="select-wrapper" id="category-width">
                    <select
                      class="local-select rounded-4"
                      name="category"
                      id="category"
                    >
                      <option value="" selected>정렬기준</option>
                      <option value="1">조회순</option>
                      <option value="2">최신순</option>
                      <option value="3">가나다순</option>
                      <option value="4">핀꽂기</option>
                    </select>
                  </div>
                </li>
              </ul>
            </div>

            <div id="addBoardList">
              <ul class="actions">
                <li>
                  <div class="select-wrapper" id="category-width">
                    <select
                      class="local-select rounded-4"
                      name="category"
                      id="mySelect"
                    >
                      <option value="">게시글 작성</option>
                      <option value="/html-css/schedule/add/add.html">
                        일정 게시글
                      </option>
                      <option value="/html-css/location/add/add.html">
                        장소 게시글
                      </option>
                    </select>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <ContentList />

        <div class="p-2">
          <div class="d-flex flex-row mb-3 mt-5">
            <h1>장소 게시글</h1>
            <div id="viewOrder">
              <ul class="actions">
                <li>
                  <div class="select-wrapper" id="category-width">
                    <select
                      class="local-select rounded-4"
                      name="category"
                      id="category"
                    >
                      <option value="" selected>정렬기준</option>
                      <option value="1">조회순</option>
                      <option value="2">최신순</option>
                      <option value="3">가나다순</option>
                      <option value="4">핀꽂기</option>
                    </select>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <ContentList />
      </div>
    </div>
  </section>

  <FollowModal :modalShown="modalShown" @modal="$emit('modal')" />
</template>

<script>
import ContentList from "../util/ContentList.vue";
import FollowModal from "../util/modal/FollowModal.vue"

export default {
  components: {
    ContentList,
    FollowModal
  },
  props: {
    modalShown: Boolean
  }
};
</script>

<style scoped>
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
