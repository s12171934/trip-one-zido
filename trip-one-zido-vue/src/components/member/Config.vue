<template>
  <main class="wrapper">
    <div class="mb-5">
      <h1>설정페이지</h1>
    </div>
    <div class="d-flex justify-content-between">
      <div >
        <div class="d-flex align-items-center">
          <div class="d-flex flex-column gap-2">
            <img
              :src="`data:image/jpeg;base64,${configData.memberProfile.profile}`"
              alt=""
              class="rounded-circle"
            />
            <button
              class="button rounded-5"
              data-bs-toggle="modal"
              data-bs-target="#profileModal"
            >
              프로필 사진 편집
            </button>
          </div>
          <h1>{{ configData.memberProfile.loginId }}</h1>
        </div>

        <div class="summary-css">
        <hr />
        <NumberSummary
          @follower="
            followType = 'follower';
            getFollowList();
          "
          @following="
            followType = 'following';
            getFollowList();
          "
          
          :totalBoard="configData.postCount"
          :followerCount="configData.followerCount"
          :followingCount="configData.followingCount"
          :bookmarkCount="configData.bookmarkCount"
        />
      </div>
      </div>
      <div
        class="w-50 d-flex justify-content-end align-items-end"
        id="configInfo"
      >
        <div class="w-50">
          <h1>내 정보</h1>
          <hr />
          <ul>
            <li>
              <h3>
                <a @click="$router.push('/member-info')"> 회원 정보 수정 </a>
              </h3>
            </li>
            <li>
              <h3>
                <a @click="$router.push('/recent-view')"> 최근 본 게시물 </a>
              </h3>
            </li>
            <li>
              <h3><a @click="$router.push('/reset-pw')"> 비밀번호 변경 </a></h3>
            </li>
            <li>
              <h3><a @click="$router.push('/resign')"> 회원탈퇴 </a></h3>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </main>

  <EditProfileModal />

  <FollowModal :type="followType" :followList="followList" />
</template>

<script>
import NumberSummary from "../util/NumberSummary.vue";
import EditProfileModal from "../util/modal/EditProfileModal.vue";
import FollowModal from "../util/modal/FollowModal.vue";

export default {
  components: {
    EditProfileModal,
    NumberSummary,
    FollowModal,
  },
  data() {
    return {
      configData: {
        memberProfile: {},
        postCount: null,
        followerCount: null,
        followingCount: null,
        bookmarkCount: null,
      },
      followType: "",
      followList: null,
    };
  },
  methods: {
    getFollowList() {
      this.$zido
        .getFollowList(
          this.followType,
          this.memberPageData.id,
          this.memberPageData.sessionId
        )
        .then((res) => (this.followList = res));
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido.getConfigData().then((res) => (this.configData = res));
  },
};
</script>

<style scoped>
img {
  width: 260px;
  height: 260px;
}

li {
  list-style: none;
}

button {
  width: 100%;
}


@media (max-width: 1200px) {
  .w-50, .p-2 {
    white-space: nowrap; 
  }
}

@media (max-width: 786px) {
  .d-flex {
    display: flex;
    flex-wrap: wrap; /* 작은 화면에서 넘치는 요소들을 아래로 이동 */
    flex-direction: column;
  }

  .w-50 {
    flex: 1; /* 아이템이 동일한 너비를 가지도록 함 */
    width: 90% !important;
    margin-bottom: 10%;
  }
}

@media (max-width: 400px) {
  h1 {
    margin-top: 6%;
  }
  button {
    margin-top: 3%;
  }
}
</style>