<template>
  <main class="wrapper d-flex">
    <div class="d-flex flex-column fill me-5" id="leftSide">
      <!-- <div class="d-flex align-items-center mb-5"> -->
      <div class="align-items-center mb-5">
        <img
          :src="`data:image/jpeg;base64,${memberPageData.responseMember.profile}`"
          class="rounded-circle"
        />
        <span id="userName">{{ memberPageData.responseMember.loginId }}</span>
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
            : memberPageData.responseMember.follow
            ? hover
              ? "언 팔로우"
              : "팔로잉 중"
            : "팔로우"
        }}
      </button>
      <hr />
      <div class="summary">
      <NumberSummary
        @follower="
          followType = 'follower';
          getFollowList();
        "
        @following="
          followType = 'following';
          getFollowList();
        "
        :totalBoard="memberPageData.postCount"
        :followerCount="memberPageData.followerCount"
        :followingCount="memberPageData.followingCount"
        :bookmarkCount="memberPageData.bookmarkCount"
      />
      </div>
      <KakaoMapForMemberPage v-if="isDataLoaded" :locMap="locMap"/>
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
      
      <div id="title">
      <ListTitle
        title="일정 게시글"
        @option="(option) => (planSort = option)"
      />
      </div>

      <ContentList
        :list="memberPageData.planLists"
        :addApi="`/api/page/${memberPageData.id}/plan/`"
        :maxLen="memberPageData.planListsCount"
        :option="`sort=${planSort}`"
      />

      <div id="title">
      <ListTitle
        title="장소 게시글"
        @option="(option) => (spotSort = option)"
        class="mt-5"
      />
      </div>

      <ContentList
        :list="memberPageData.spotLists"
        :addApi="`/api/page/${memberPageData.id}/spot/`"
        :maxLen="memberPageData.spotListsCount"
        :option="`sort=${spotSort}`"
      />
    </div>
  </main>

  <FollowModal :type="followType" :followList="followList" />
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
      followList: null,
      hover: false,
      addType: "게시물 작성",
      memberPageData: {
        id: null,
        sessionId: null,
        loginId: null,
        planLists: [],
        planListsCount: 0,
        spotLists: [],
        spotListsCount: 0,
        responseMember: {},
        postCount: null,
        bookmarkCount: null,
        followerCount: null,
        followingCount: null,
        isMine: null,
      },
      locMap: [{
          code: null, 
          count: null,
      }],
      planSort: 'created_at',
      spotSort: 'created_at',
      isDataLoaded: false,
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
        this.$zido.toggleFollow(this.memberPageData.responseMember);
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
    getFollowList() {
      this.$zido
        .getFollowList(
          this.followType,
          this.memberPageData.id,
          this.memberPageData.sessionId
        )
        .then((res) => (this.followList = res));
    },
    getLocMap(id){
      this.$zido
      .getLocMap(id)
      .then((res) => (this.locMap = res));
    },
},
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido
      .getMemberPageData(this.$route.params.id)
      .then((res) => {
        this.memberPageData = res;
        this.getLocMap(this.memberPageData.id);
        this.$nextTick(() => {
          this.isDataLoaded = true; // 데이터 로드 상태를 true로 설정
        });
      });
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
  color: rgb(80, 80, 80);
}

img {
  width: 250px;
  height: 250px;
}

#edit-profile:hover {
  background-color: grey;
}

@media (max-width: 1250px) {
  .summary, #title, .w-25 {
    white-space: nowrap; 
    text-overflow: ellipsis; 
  }
}

@media (max-width: 1023px) {
.wrapper {
    display: flex;
    flex-wrap: wrap; /* 작은 화면에서 넘치는 요소들을 아래로 이동 */
  }

  .d-flex {
    flex: 1; /* 아이템이 동일한 너비를 가지도록 함 */
    box-sizing: border-box;
  }
  #add{
    margin-bottom: -25%;
    margin-top: 5%;
  }
  #rightSide {
    margin-right: 10%;
  }
  #contentList{
    padding-bottom: 80px;
  }
}

@media (max-width: 600px) {
  .w-25 {
    font-size: 10px;
  }
  #leftSide {
    margin-right: 0 !important; /* 화면의 너비가 600px 이하일 때 me-5 클래스 제거 */
  }
  #add{
    margin-bottom: -60%;
    margin-top: 5%;
  }
}

@media (max-width: 1460px) {
  .wrapper {
    flex-direction: initial !important; 
  }
}
</style>
../util/KakaoMapForMemberPage.vue
