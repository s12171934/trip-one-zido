<template>
  <main class="wrapper">
    <h1 id="mainTitle">
      <span>{{ bookmark.loginId }}</span
      >님이 찜한 게시글
    </h1>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        ・모든 일정 & 장소 게시글<span>{{
          bookmark.planSpotBookMarkCount
        }}</span>
      </h3>
    </div>

    <ContentList
      :list="bookmark.contentList"
      :addApi="`/api/bookmark/${bookmark.id}/SpotPlan/`"
      :maxLen="bookmark.planSpotBookMarkCount"
    />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        ・모든 관광지<span>{{ bookmark.tourBookmarkCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="bookmark.tourList"
      :addApi="`/api/bookmark/${bookmark.id}/tour/`"
      :maxLen="bookmark.tourBookmarkCount"
    />
  </main>
</template>

<script>
import ContentList from "../util/ContentList.vue";

export default {
  components: {
    ContentList,
  },
  data() {
    return {
      bookmark: {
        id: null,
        loginId: null,
        planSpotBookMarkCount: null,
        tourBookmarkCount: null,
        contentList: [],
        tourList: [],
      },
    };
  },
  methods: {
    maxLen(){
      return this.bookmark.tourBookmarkCount;
    }
  },
  async mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.bookmark = await this.$zido.getBookmarkById(this.$route.params.id);
    console.log(this.bookmark)
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

@media (max-width: 1000px) { /* 원하는 크기로 설정 */

  #wrapper, #subTitle, #mainTitle {
    white-space: nowrap; 
    text-overflow: ellipsis; 
  }

}
</style>
