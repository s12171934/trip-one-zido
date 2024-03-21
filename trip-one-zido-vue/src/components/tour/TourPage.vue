<template>
  <main class="wrapper">
    <div class="d-flex p-2">
      <span class="title">이런 곳 어때요?</span>
      <select
        @change="changeLoc"
        class="button alt"
        id="locSelect"
        name="location"
        v-model="loc"
      >
        <option v-for="location in selectLocations" :value="location">
          {{ location.value }}
        </option>
      </select>
    </div>

    <ContentList
      :list="tourLists"
      :addApi="`/api/tour/list/${defaultLoc()}/`"
      :maxLen="20"
    />
  </main>
</template>

<script>
import ContentList from "../util/ContentList.vue";
import data from "/src/assets/data.js";

export default {
  components: {
    ContentList,
  },
  data() {
    return {
      selectLocations: data.selectLocations,
      loc: this.defaultLoc(),
      tourLists: [
        {
          id: null,
          title: null,
          photo: null,
          bookmarkCount: null,
          myBookmark: null,
          type: null,
        },
      ],
      tourListCount: 0,
    };
  },
  methods: {
    changeLoc() {
      location.href = `/tour/loc/${this.loc}`;
    },
    defaultLoc() {
      return this.$route.params.loc ? this.$route.params.loc : 11;
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido.getTourList(this.defaultLoc()).then((res) => {
      this.tourLists = res.tourLists;
      this.tourListCount = res.tourListCount
    });
  },
};
</script>

<style scoped>
span {
  line-height: 60px;
  font-size: 30px;
  margin-right: 30px;
}

#locSelect {
  box-shadow: none !important;
  border: none;
  font-family: "Jalnan";
  color: #ff928e !important;
  font-size: 30px;
  text-align: left;
  padding: 0;
  width: 300px;
  height: 60px;
  line-height: 60px;
}

#locSelect:hover {
  background-color: #fff;
}

@media (max-width: 1200px) {
  .title {
    white-space: nowrap; 
  }
}

@media (max-width: 1023px) { /* 원하는 크기로 설정 */
  
  #font-vertical {
      white-space: nowrap; 
      text-overflow: ellipsis; 
    }
}

@media (max-width: 767px) { /* 원하는 크기로 설정 */
  
  #font-vertical {
      white-space: nowrap; 
      text-overflow: ellipsis; 
    }
    body{
    width:100%;
    overflow-x:hidden;
    }
}


</style>
