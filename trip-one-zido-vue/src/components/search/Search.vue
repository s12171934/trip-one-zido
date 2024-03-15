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
        type="button"
        @click="this.$router.push('/search-detail')"
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

    <MemberList :list="searchData.memberList" :plus="true" />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        일정<span>{{ searchData.planCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="searchData.planList"
      :addApi="`/search/${keyword}/plan/`"
    />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        장소<span>{{ searchData.spotCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="searchData.spotList"
      :addApi="`/search/${keyword}/spot/`"
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
        memberList,
        memberCount,
        planList,
        planCount,
        spotList,
        spotCount,
      },
    };
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido
      .getSearchData(this.$route.params.keyword)
      .then((res) => (this.searchData = res));
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
</style>
