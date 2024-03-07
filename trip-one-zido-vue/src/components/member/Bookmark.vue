<template>
  <section id="main" class="wrapper">
    <h1>찜한 게시글</h1>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>・모든 일정 & 장소 게시글<span>{{ tourCount }}</span></h3>
    </div>

    <ContentList firstList="tourList"/>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>・모든 관광지<span>{{ spotPlanCount }}</span></h3>
    </div>

    <ContentList firstList="spotPlanList"/>
  </section>
</template>

<script>
import ContentList from "../util/ContentList.vue";
import data from "@/assets/data";

export default {
  components: {
    ContentList,
  },
  data(){
    return{
      tourList: [],
      tourCount: 0,
      spotPlanList: [],
      spotPlanCount: 0,
    }
  },
  methods: {
    getData(){
      this.tourList = data.tourList;
      this.tourCount = data.tourCount;
      this.spotPlanList = data.spotPlanList;
      this.spotPlanCount = data.spotPlanCount;
      
    }
  },
  mounted(){
    this.getData();

    this.$emit("meta",this.$route.matched[0].meta.isLogin);
  }
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
