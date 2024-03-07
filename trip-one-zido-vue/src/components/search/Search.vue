<template>
  <section id="main" class="wrapper">
    <form @submit.prevent class="input-group">
      <input
        name="q"
        type="text"
        class="form-control"
        placeholder="검색어를 입력해주세요"
        v-model="keyword"
      />
      <button class="button alt" type="submit" id="more">검색</button>
      <button
        class="button alt"
        type="button"
        @click="this.$router.push('/search/detail')"
      >
        일정 & 장소 상세검색으로
      </button>
    </form>

    <h1>12 건의 검색 결과</h1>

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>계정<span>25</span></h3>
    </div>

    <MemberList />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>일정<span>25</span></h3>
    </div>

    <ContentList />

    <div class="d-flex flex-row mb-6" id="subTitle">
      <h3>장소<span>25</span></h3>
    </div>

    <ContentList />
  </section>
</template>

<script>
import ContentList from "../util/ContentList.vue";
import MemberList from "../util/MemberList.vue";

export default {
  components: {
    ContentList,
    MemberList,
  },
};
</script>

<style scoped>
h1,
h3 {
  font-family: "Jalnan";
  color: #ff928e !important;
}

button {
  font-family: "Jalnan";
}

span {
  padding-left: 20px;
  color: rgb(80, 80, 80);
}

#subTitle {
  margin-top: 5%;
}

#main {
  margin-left: 5%;
  margin-right: 5%;
}

</style>
