<template>
  <section id="main" class="wrapper">
    <div id="mainTitle">
      <h1 class="title">상세검색</h1>
      <br />
    </div>

    <div class="row">
      <!-- Bootstrap 그리드 시스템 사용 -->

      <!-- 시작일 -->
      <div class="col-md-3 mb-3">
        <h5>일정 시작일</h5>
        <input type="date" class="form-control" />
        <br />
      </div>

      <!-- 종료일 -->
      <div class="col-md-3 mb-3">
        <h5>일정 종료일</h5>
        <input type="date" class="form-control" />
        <br />
      </div>

      <!-- 카테고리 -->
      <div class="col-md-3 mb-3">
        <h5>카테고리</h5>
        <div class="select-wrapper">
          <select class="local-select" name="category" id="security">
            <option value="" selected>카테고리 선택</option>
            <option value="1">일정</option>
            <option value="2">관광지</option>
          </select>
        </div>
        <br />
      </div>

      <!-- 지역 -->
      <div class="col-md-3 mb-3">
        <h5>지역</h5>
        <div class="select-wrapper">
          <select class="local-select" name="category" id="security">
            <option value="" selected>지역 선택</option>
            <option value="1">서울특별시</option>
            <option value="2">경기도</option>
            <option value="3">강원도</option>
            <option value="4">세종특별자치시시</option>
            <option value="5">대구광역시</option>
            <option value="6">울산광역시</option>
            <option value="7">부산광역시</option>
            <option value="8">제주특별자치도</option>
          </select>
        </div>
        <br />
      </div>

      <!-- 검색 -->
      <div class="col-md-12 mb-3">
        <h5>검색</h5>
        <form
          action="https://www.google.com/search"
          method="GET"
          class="input-group"
        >
          <input
            name="q"
            type="text"
            class="form-control"
            placeholder="검색어를 입력해주세요"
            aria-label="Recipient's username"
            aria-describedby="button-addon2"
          />
          <button class="button alt" type="submit" id="more">검색</button>
        </form>
        <br /><br /><br />
      </div>
    </div>
    <div>'검색어' 와 관련된 검색결과</div>

    <hr />
    <br /><br /><br />
    <div id="searchTitle">
      <div class="d-grid gap-3 d-md-flex justify-content-md">
        <h3 id="searchResult">일정</h3>
        <h5>5</h5>
        <!-- <div style="margin-left: auto">	 -->
        <!-- <a href="#" class="button alt small" id="more">더보기</a> -->
        <!-- </div> -->
      </div>
    </div>
    <ContentList />

    <br /><br /><br />
    <div id="searchTitle">
      <div class="d-grid gap-3 d-md-flex justify-content-md">
        <h3 id="searchResult">장소</h3>
        <h5>5</h5>
        <!-- <div style="margin-left: auto">	 -->
        <!-- <a href="#" class="button alt small" id="more">더보기</a> -->
        <!-- </div> -->
      </div>
    </div>

    <ContentList />
  </section>
</template>

<script>
import ContentList from "../util/ContentList.vue";

export default {
  components: {
    ContentList,
  },
};
</script>

<style>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

#mainTitle {
  text-align: left;
}

h1,
#searchResult {
  color: #ff928e !important;
}

#searchTitle {
  text-align: left;
  margin-left: 5%;
}

#searchBar {
  display: flex;
  justify-content: center;
}

#searchBar > form {
  width: 70%;
}

h5 {
  margin-top: 0.25%;
}

#userPic {
  width: 260px;
  height: 260px;
}

#searchPic {
  width: 260px;
  height: 260px;
}

#searchUser,
#searchSchedule,
#searchLocation {
  margin-left: 5%;
  overflow: auto;
  overflow-y: hidden;
}

#listTitle {
  text-align: center;
}

#buttonBackground {
  background-color: white;
}

.container > img {
  position: absolute;
  width: 100%;
  /* height: 100%; */
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>
