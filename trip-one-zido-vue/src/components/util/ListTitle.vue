<template>
  <div class="d-flex flex-row mb-3">
    <h1>{{title}}</h1>
    <div class="select-wrapper ms-3">
      <select class="local-select rounded-4" name="category" id="category">
        <option value="" selected>정렬기준</option>
        <option v-for="sort in sortMenu" :value="sort">{{ sort }}</option>
      </select>
    </div>
  </div>
</template>

<script>
import data from "/src/assets/data.js";

export default {
  props: {
    title: String
  },
  data() {
    return {
      sortMenu: data.sortMenu,
    };
  },
};
</script>

<style scoped>
h1 {
  color: #ff928e !important;
  margin: 0;
  line-height: 60px;
}

select {
  width: 180px;
}
</style>
