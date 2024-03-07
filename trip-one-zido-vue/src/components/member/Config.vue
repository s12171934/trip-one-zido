<template>
  <section id="main" class="wrapper">
    <div class="mb-5">
      <h1>설정페이지</h1>
    </div>
    <div class="d-flex justify-content-between">
      <div class="w-50">
        <div class="d-flex align-items-center">
          <div class="d-flex flex-column gap-2">
            <img :src="userData.imgSrc" alt="" class="rounded-circle" />
            <button
              @click="
                $emit('modal');
                editProfile = true;
              "
              class="button rounded-5"
            >
              프로필 사진 편집
            </button>
          </div>
          <h1>{{ userData.loginId }}</h1>
        </div>

        <hr />
        <NumberSummary
          @modal="
            $emit('modal');
            editProfile = false;
          "
          @follower="followType = 'follower'"
          @following="followType = 'following'"
          @bookmark="goToBookmark"
        />
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
              <h3><a href="/member-info"> 회원 정보 수정 </a></h3>
            </li>
            <li>
              <h3><a href="/recent-view"> 최근 본 게시물 </a></h3>
            </li>
            <li>
              <h3><a href="/reset-pw"> 비밀번호 변경 </a></h3>
            </li>
            <li>
              <h3><a href="/resign"> 회원탈퇴 </a></h3>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </section>

  <EditProfileModal
    :modalShown="modalShown && editProfile"
    @modal="$emit('modal')"
  />

  <FollowModal
    :modalShown="modalShown && !editProfile"
    :type="followType"
    @modal="$emit('modal')"
  />
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
  props: {
    modalShown: Boolean,
  },
  data() {
    return {
      userData: {
        imgSrc: "/images/남자.png",
        loginId: "남자",
      },
      editProfile: false,
      followType: "",
    };
  },
  methods: {
    goToBookmark() {
      this.$router.push("/bookmark");
    },
  },
};
</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

img {
  width: 260px;
  height: 260px;
}

li {
  list-style: none;
}

button {
  width: 260px;
}

h1,
button,
a {
  font-family: "Jalnan";
}
</style>
