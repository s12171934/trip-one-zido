<template>
  <main class="wrapper">
    <h1>
      <span>{{ bookmark.loginId }}</span
      >님이 찜한 게시글
    </h1>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        ・모든 일정 & 장소 게시글<span>{{ bookmark.tourCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="bookmark.tourList"
      :addApi="`/api/bookmark/${bookmark.id}/tour`"
      :sessionId="bookmark.sessionId"
    />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>
        ・모든 관광지<span>{{ bookmark.spotPlanCount }}</span>
      </h3>
    </div>

    <ContentList
      :list="bookmark.spotPlanList"
      :addApi="`/api/bookmark/${bookmark.id}/spotPlan`"
      :sessionId="bookmark.sessionId"
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
      bookmark: this.$zido.getBookmarkById(this.pageMemberId()),
    };
  },
  methods: {
    pageMemberId() {
      if (this.$route.params.id) {
        return this.$route.params.id;
      } else {
        return this.$zido.getMemberId();
      }
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
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
