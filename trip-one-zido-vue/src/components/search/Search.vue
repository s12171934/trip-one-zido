<template>
  <main class="wrapper">
    <form
      @submit.prevent="$router.push(`/search/${keyword}`)"
      class="input-group"
    >
      <input
        name="q"
        type="text"
        class="form-control"
        placeholder="검색어를 입력해주세요"
        v-model="keyword"
      />
      <button class="button alt" type="submit" id="more">검색</button>
      <button
        class="button alt"
        id="detail"
        type="button"
        @click="this.$router.push(`/search-detail?keyword=${$route.params.keyword}`)"
      >
        일정 & 장소 상세검색으로
      </button>
    </form>

    <h1>
      {{ searchData.memberCount + searchData.planCount + searchData.spotCount }}
      건의 검색 결과
    </h1>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        계정<span>{{ searchData.memberCount }}</span>
      </h3>
    </div>

    <MemberList
      :list="searchData.memberList"
      :addApi="`/api/search/${keyword}/member/`"
      :maxLen="searchData.memberList"
    />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        일정<span>{{ searchData.planCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="searchData.planList"
      :addApi="`/api/search/${keyword}/plan/`"
      :maxLen="searchData.planCount"
    />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        장소<span>{{ searchData.spotCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="searchData.spotList"
      :addApi="`/api/search/${keyword}/spot/`"
      :maxLen="searchData.spotCount"
    />
  </main>
</template>

<script>
import ContentList from "../util/ContentList.vue";
import MemberList from "../util/MemberList.vue";

export default {
  components: {
    ContentList,
    MemberList,
  },
  data() {
    return {
      searchData: {
        memberList: [],
        memberCount: null,
        planList: [],
        planCount: null,
        spotList: [],
        spotCount: null,
      },
      keyword: null,
    };
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.keyword = this.$route.params.keyword;
    this.$zido
      .getSearchData(this.$route.params.keyword)
      .then((res) => (this.searchData = res));
  },
  watch: {
    $route(to, from) {
      if (to.params.keyword != from.params.keyword) {
        this.keyword = to.params.keyword;
        this.$zido
          .getSearchData(to.params.keyword)
          .then((res) => (this.searchData = res));
      }
    },
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

@media (max-width: 700px) {
  .button.alt, input {
    font-size: 0.9rem; /* 작은 화면에서 버튼 텍스트 크기를 줄입니다. */
  }
}

@media (max-width: 545px) {
  .button.alt, input {
    font-size: 0.8rem; /* 작은 화면에서 버튼 텍스트 크기를 줄입니다. */
  }
  #detail {
    width: 100%;
  }
}
</style>
