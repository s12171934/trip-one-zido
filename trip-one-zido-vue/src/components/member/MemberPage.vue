<template>
  <main class="wrapper d-flex">
    <div class="d-flex flex-column fill me-5" id="leftSide">
      <div class="d-flex align-items-center mb-5">
        <img :src="userData.imgSrc" class="rounded-circle" />
        <span id="userName">{{ userData.loginId }}</span>
      </div>
      <button
        @click="followOrConfig"
        @mouseover="hover = true"
        @mouseleave="hover = false"
        class="button rounded-5"
        id="edit-profile"
      >
        {{
          userData.id == 1 || userData.id == null
            ? "프로필 편집"
            : userData.isFollow
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
      />
      <KakaoMap />
    </div>
    <div class="d-flex flex-column justify-content-between" id="rightSide">
      <div class="select-wrapper d-flex justify-content-end" id="add">
        <select
          @change="addSpotPlan"
          v-if="userData.id == 1 || userData.id == null"
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

      <ListTitle title="일정 게시글" />

      <ContentList />

      <ListTitle title="장소 게시글" class="mt-5" />

      <ContentList />
    </div>
  </main>

  <FollowModal :type="followType" />
</template>

<script>
import ContentList from "../util/ContentList.vue";
import FollowModal from "../util/modal/FollowModal.vue";
import NumberSummary from "../util/NumberSummary.vue";
import ListTitle from "../util/ListTitle.vue";
import KakaoMap from "../util/KakaoMap.vue";
import data from "/src/assets/data.js";

export default {
  components: {
    ContentList,
    FollowModal,
    NumberSummary,
    ListTitle,
    KakaoMap,
  },
  props: {
    id: Number,
  },
  data() {
    return {
      followType: "",
      userData: {
        imgSrc: "/images/남자.png",
        loginId: "남자",
      },
      hover: false,
      addType: "게시물 작성",
    };
  },
  methods: {
    goToBookmark() {
      location.href = `/bookmark/${this.id}`;
    },
    getUserData() {
      if (this.id != "") {
        this.userData = data.userProfiles[this.id];
      }
    },
    followOrConfig() {
      if (this.userData.id == null || this.userData.id == 1) {
        this.$router.push("/config");
      } else {
        this.userData.isFollow = !this.userData.isFollow;
      }
    },
    addSpotPlan() {
      if (this.addType === "일정 게시글") {
        this.$router.push("/add/plan");
      } else {
        this.$router.push("/add/spot");
      }
    },
  },
  mounted() {
    this.getUserData();
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
