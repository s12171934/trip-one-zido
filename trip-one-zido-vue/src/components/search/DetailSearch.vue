<template>
  <main class="wrapper">
    <h1 class="title mb-5">상세검색</h1>
    <div class="row mb-3">
      <div class="col-md-3 mb-3">
        <h5>계절</h5>
        <div class="select-wrapper">
          <select class="form-control" name="category" v-model="season">
            <option value="" selected>계절 선택</option>
            <option v-for="season in selectSeason" :value="season.season">
              {{ season.value }}
            </option>
          </select>
        </div>
        <br/>
      </div>

      <div class="col-md-3 mb-3">
        <h5>카테고리</h5>
        <div class="select-wrapper">
          <select class="form-control" name="category" v-model="category">
            <option value="" selected>카테고리 선택</option>
            <option
              v-for="category in selectCategories"
              :value="category.category"
            >
              {{ category.value }}
            </option>
          </select>
        </div>
        <br/>
      </div>

      <div class="col-md-3 mb-3">
        <h5>지역</h5>
        <div class="select-wrapper">
          <select class="form-control" name="category" v-model="locCategory">
            <option value="" selected>지역 선택</option>
            <option
              v-for="location in selectLocations"
              :value="location.locCategory"
            >
              {{ location.value }}
            </option>
          </select>
        </div>
      </div>

      <div class="col-md-12 mb-3">
        <h5>검색</h5>
        <form @submit.prevent="detailSearch" class="input-group">
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
    <div>'{{ searchOptions() }}' 와 관련된 검색결과</div>
    <hr/>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        일정<span>{{ detailSearchData.planCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="detailSearchData.planList"
      :addApi="'/api/search/plan/page'"
      :maxLen="detailSearchData.planCount"
      method="post"
    />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        장소<span>{{ detailSearchData.spotCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="detailSearchData.spotList"
      :addApi="'/api/search/spot/page'"
      :maxLen="detailSearchData.spotCount"
      method="post"
    />
  </main>
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
      selectLocations: data.selectLocations,
      selectCategories: data.selectCategories,
      selectSeason: data.selectSeason,
      detailSearchData: {
        planList: [],
        planCount: 0,
        spotList: [],
        spotCount: 0,
      },
      season: this.$route.query.season ? this.$route.query.season : "",
      category: this.$route.query.category ? this.$route.query.category : "",
      locCategory: this.$route.query.locCategory ? this.$route.query.locCategory : "",
      keyword: this.$route.query.keyword,
    };
  },
  methods: {
    detailSearch() {
      location.href = `/search-detail?keyword=${this.keyword}&season=${this.season}&category=${this.category}&locCategory=${this.locCategory}`;
    },
    searchOptions() {
      let options = "";
      if (this.$route.query.season) {
        options += this.selectSeason.find(
          (list) => list.season == this.$route.query.season
        ).value;
        options += ", ";
      }
      if (this.$route.query.category) {
        options += this.selectCategories.find(
          (list) => list.category == this.$route.query.category
        ).value;
        options += ", ";
      }
      if (this.$route.query.locCategory) {
        options += this.selectLocations.find(
          (list) => list.locCategory == this.$route.query.locCategory
        ).value;
        options += ", ";
      }
      options += this.keyword;

      return options;
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    //POST -- /api/search
    this.$zido
      .getDetailSearchData(this.$route.query)
      .then((res) => (this.detailSearchData = res));
    console.log(this.$route.query);
  },
};
</script>

<style scoped>
h1,
h3 {
  color: #ff928e !important;
}

span {
  padding-left: 20px;
  color: rgb(80, 80, 80);
}

#subTitle {
  margin-top: 5%;
}

.form-control {
  height: 50px;
}

button {
  height: 50px;
}
</style>
