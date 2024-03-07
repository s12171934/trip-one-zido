<template>
  <section id="main" class="wrapper">
    <h1>찜한 게시글</h1>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>・모든 일정 & 장소 게시글<span>25</span></h3>
    </div>

    <ContentList />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>・모든 관광지<span>25</span></h3>
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
};
</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

h1,
h3 {
  font-family: "Jalnan";
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
