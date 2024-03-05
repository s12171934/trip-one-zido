<template>
  <section id="main" class="wrapper">
    <div id="listTitle">
      <h1>이런 곳 어때요?</h1>
    </div>
    <div class="select-wrapper" style="display: flex; justify-content: center">
      <div class="d-flex flex-row mb-3">
        <div class="p-2">
          <select
            class="button alt rounded-3"
            name="category"
            id="paldo"
            style="width: 200px"
          >
            <option value="" selected>도 선택 ▼</option>
            <option value="1">서울특별시</option>
            <option value="2">경기도</option>
            <option value="3">강원도</option>
            <option value="4">충청북도</option>
            <option value="5">충청남도</option>
            <option value="6">전라북도</option>
            <option value="7">전라남도</option>
            <option value="8">경상북도</option>
            <option value="9">경상남도</option>
            <option value="10">제주도</option>
          </select>
        </div>
      </div>
    </div>

    <div id="mainTitle">
      <h1>・부산</h1>
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

<style></style>
