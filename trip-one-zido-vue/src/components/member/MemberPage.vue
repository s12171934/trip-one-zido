<template>
  <main class="wrapper d-flex">
    <div class="d-flex flex-column fill me-5" id="leftSide">
      <div class="d-flex align-items-center mb-5">
        <img :src="memberPageData.imgSrc" class="rounded-circle" />
        <span id="userName">{{ memberPageData.loginId }}</span>
      </div>
      <button
        @click="followOrConfig"
        @mouseover="hover = true"
        @mouseleave="hover = false"
        class="button rounded-5"
        id="edit-profile"
      >
        {{
          isMyPage()
            ? "프로필 편집"
            : memberPageData.isFollow
            ? hover
              ? "언 팔로우"
              : "팔로잉 중"
            : "팔로우"
        }}
      </button>
      <hr />
      <NumberSummary
        @follower="followType = 'follower'"
        @following="followType = 'following'"
        @bookmark="$router.push('/bookmark')"
        :totalBoard="memberPageData.totalBoard"
        :followerCount="memberPageData.followerCount"
        :followingCount="memberPageData.followingCount"
        :bookmarkCount="memberPageData.bookmarkCount"
      />
      <KakaoMapForMemberPage />
    </div>
    <div class="d-flex flex-column justify-content-between" id="rightSide">
      <div class="select-wrapper d-flex justify-content-end" id="add">
        <select
          @change="addSpotPlan"
          v-if="isMyPage()"
          class="local-select rounded-4 w-25"
          name="category"
          id="mySelect"
          v-model="addType"
        >
          <option selcected>게시물 작성</option>
          <option>일정 게시글</option>
          <option>장소 게시글</option>
        </select>
      </div>

      <ListTitle
        title="일정 게시글"
        @option="(option) => (planSortOption = option)"
      />

      <ContentList
        :list="memberPageData.planList"
        :sortOption="planSortOption"
        :addApi="`/page/${memberPageData.id}/plan/`"
      />

      <ListTitle
        title="장소 게시글"
        @option="(option) => (spotSortOption = option)"
        class="mt-5"
      />

      <ContentList
        :list="memberPageData.spotList"
        :sortOption="spotSortOption"
        :addApi="`/page/${memberPageData.id}/spot/`"
      />
    </div>
  </main>

  <FollowModal
    :type="followType"
    :followList="
      $zido.getFollowList(
        followType,
        memberPageData.id,
        memberPageData.sessionId
      )
    "
  />
</template>

<script>
import ContentList from "../util/ContentList.vue";
import FollowModal from "../util/modal/FollowModal.vue";
import NumberSummary from "../util/NumberSummary.vue";
import ListTitle from "../util/ListTitle.vue";
import KakaoMapForMemberPage from "../util/KakaoMapForMemberPage.vue";

export default {
  components: {
    ContentList,
    FollowModal,
    NumberSummary,
    ListTitle,
    KakaoMapForMemberPage,
  },
  props: {
    id: Number,
  },
  data() {
    return {
      followType: "",
      hover: false,
      addType: "게시물 작성",
      memberPageData: this.$zido.getMemberPageData(this.$route.params.id),
    };
  },
  methods: {
    goToBookmark() {
      location.href = `/bookmark/${this.id}`;
    },
    followOrConfig() {
      if (this.isMyPage()) {
        this.$router.push("/config");
      } else {
        this.memberPageData.isFollow = !this.memberPageData.isFollow;
      }
    },
    addSpotPlan() {
      if (this.addType === "일정 게시글") {
        this.$router.push("/add/plan");
      } else {
        this.$router.push("/add/spot");
      }
    },
    isMyPage() {
      return (
        !this.$route.params.id ||
        this.$route.params.id == this.memberPageData.sessionId
      );
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
#mainTitle {
  text-align: left;
}

#leftSide {
  width: 36%;
}

#rightSide {
  width: 64%;
}

span {
  font-size: 30px;
  margin-left: 30px;
}

img {
  width: 260px;
  height: 260px;
}

#edit-profile:hover {
  background-color: grey;
}
</style>
../util/KakaoMapForMemberPage.vue
