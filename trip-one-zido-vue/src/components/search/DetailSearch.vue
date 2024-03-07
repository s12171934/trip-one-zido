<template>
  <section id="main" class="wrapper">
    <h1 class="title mb-5">상세검색</h1>

    <div class="row mb-3">
      <div class="col-md-3 mb-3">
        <h5>일정 시작일</h5>
        <input type="date" class="form-control" />
        <br />
      </div>

      <div class="col-md-3 mb-3">
        <h5>일정 종료일</h5>
        <input type="date" class="form-control" />
        <br />
      </div>

      <div class="col-md-3 mb-3">
        <h5>카테고리</h5>
        <div class="select-wrapper">
          <select class="form-control" name="category">
            <option value="" selected>카테고리 선택</option>
            <option v-for="category in selectCategories" :value="category">
              {{ category }}
            </option>
          </select>
        </div>
        <br />
      </div>

      <div class="col-md-3 mb-3">
        <h5>지역</h5>
        <div class="select-wrapper">
          <select class="form-control" name="category">
            <option value="" selected>지역 선택</option>
            <option v-for="location in selectLocations" :value="location">
              {{ location }}
            </option>
          </select>
        </div>
      </div>

      <div class="col-md-12 mb-3">
        <h5>검색</h5>
        <form @submit.prevent class="input-group">
          <input
            name="search"
            type="text"
            class="form-control"
            placeholder="검색어를 입력해주세요"
            v-model="keyword"
          />
          <button class="button alt" type="submit" id="more">검색</button>
        </form>
      </div>
    </div>
    <div>'검색어' 와 관련된 검색결과</div>
    <hr />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>일정<span>25</span></h3>
    </div>

    <ContentList />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>장소<span>25</span></h3>
    </div>

    <ContentList />
  </section>
</template>

<script>
import ContentList from "../util/ContentList.vue";
import data from "/src/assets/data.js";

export default {
  components: {
    ContentList,
  },
  data() {
    return {
      selectCategories: data.selectCategories,
      selectLocations: data.selectLocations,
    };
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
h1,
h3 {
  font-family: "Jalnan";
  color: #ff928e !important;
}

h5,
button {
  font-family: "Jalnan";
}

span {
  padding-left: 20px;
  color: rgb(80, 80, 80);
}

#subTitle {
  margin-top: 5%;
}

#main {
  margin-left: 5%;
  margin-right: 5%;
}

.form-control {
  height: 50px;
}

button {
  height: 50px;
}
</style>
