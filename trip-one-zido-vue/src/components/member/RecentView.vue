<template>
  <main class="wrapper">
    <div class="d-flex align-items-center mb-5">
      <img
        :src="RecentViewData.member.profile 
          ? `data:image/jpeg;base64,${RecentViewData.member.profile}`
          : '/images/nomal.jpeg'"
        alt=""
        class="rounded-circle"
      />
      <h1>{{ RecentViewData.member.loginId }}</h1>
    </div>
    <div>
      <h1>조회한 게시글(기록)</h1>
    </div>
    <ContentList
      :list="RecentViewData.recentList"
      :addApi="`/api/content/recent-view/`"
      :maxLen="RecentViewData.recentViewCount"
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
      RecentViewData: {
        recentList: [{}],
        member: [{}],
        recentViewCount: 0,
      },
    };
  },
  async mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    //GET -- api/content/recent-view
    this.RecentViewData = await this.$zido.getRecentView();
  },
};
</script>

<style scoped>
img {
  vertical-align: top;
  width: 260px;
  height: 260px;
}

@media (max-width: 768px) {
  .d-flex.mb-5 {
    flex-direction: column;
  }

  .d-flex.mb-5 img {
    margin-bottom: 10px; /* 이미지와 텍스트 사이 여백 조정 */
  }
}
</style>
