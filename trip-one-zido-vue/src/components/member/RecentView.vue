<template>
  <section id="main" class="wrapper">
    <div class="d-flex align-items-center mb-5">
      <img :src="userData.imgSrc" alt="" class="rounded-circle" />
      <h1>{{ userData.loginId }}</h1>
    </div>
    <div>
      <h1>조회한 게시글(기록)</h1>
    </div>
    <ContentList />
  </section>
</template>

<script>
import ContentList from "../util/ContentList.vue";

export default {
  components: {
    ContentList,
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
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },
};
</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

h1 {
  color: #ff928e !important;
  font-family: "Jalnan";
}

img {
  vertical-align: top;
  width: 260px;
  height: 260px;
}
</style>
