<template>
  <div class="d-flex flex-row mb-3">
    <h1>{{ title }}</h1>
    <div class="select-wrapper ms-3">
      <select
        @change="$emit('option', option)"
        class="local-select rounded-4"
        v-model="option"
        id="category"
      >
        <option v-for="sort in sortMenu" :value="sort.value">
          {{ sort.view }}
        </option>
      </select>
    </div>
  </div>
</template>

<script>
import data from "/src/assets/data.js";

export default {
  props: {
    title: String,
  },
  data() {
    return {
      sortMenu: data.sortMenu,
      option: "created_at DESC",
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
