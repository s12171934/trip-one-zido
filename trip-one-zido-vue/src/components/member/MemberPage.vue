<template>
  <section id="main" class="wrapper d-flex">
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
        @modal="$emit('modal')"
        @follower="followType = 'follower'"
        @following="followType = 'following'"
        @bookmark="goToBookmark"
      />
      <div class="fs-5 h-100" id="googleMap">
        <iframe
          class="rounded-5 w-100 h-100"
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3260.7271121845406!2d129.2207447762419!3d35.18835345689094!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35688c1bbbdea6c1%3A0xa141406d6f112e5c!2z7ZW064-Z7Jqp6raB7IKs!5e0!3m2!1sko!2skr!4v1708994953688!5m2!1sko!2skr"
          style="border: 0"
          allowfullscreen=""
          loading="lazy"
          referrerpolicy="no-referrer-when-downgrade"
        />
      </div>
    </div>
    <div class="d-flex flex-column" id="rightSide">
      <div class="select-wrapper d-flex justify-content-end" id="add">
        <select
          @change="addSpotPlan"
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
  </section>

  <FollowModal
    :modalShown="modalShown"
    :type="followType"
    @modal="$emit('modal')"
  />
</template>

<script>
import ContentList from "../util/ContentList.vue";
import FollowModal from "../util/modal/FollowModal.vue";
import NumberSummary from "../util/NumberSummary.vue";
import ListTitle from "../util/ListTitle.vue";
import data from "/src/assets/data.js";

export default {
  components: {
    ContentList,
    FollowModal,
    NumberSummary,
    ListTitle,
  },
  props: {
    modalShown: Boolean,
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
      location.href = `/bookmark/${this.$route.params.id}`;
    },
    getUserData() {
      for (let profile of data.userProfiles) {
        console.log(profile);
        if (profile.id == this.$route.params.id) {
          this.userData = profile;
        }
      }
    },
    followOrConfig() {
      if (this.userData.id == 1 || this.userData.id == null) {
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
  },
};
</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

#mainTitle {
  text-align: left;
}

#leftSide {
  width: 36%;
}

#rightSide {
  width: 64%;
}

button,
span,
select {
  font-family: "Jalnan";
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
