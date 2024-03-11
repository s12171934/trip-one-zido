<template>
  <div class="d-flex flex-row justify-content-between mb-3">
    <div class="p-2" id="p-2">
      <h3>12</h3>
      <h3>게시글</h3>
    </div>
    <div
      @click="$emit('follower')"
      class="p-2"
      id="p-2"
      data-bs-toggle="modal"
      data-bs-target="#followModal"
    >
      <h3>255</h3>
      <h3>팔로워</h3>
    </div>
    <div
      @click="$emit('following')"
      class="p-2"
      id="p-2"
      data-bs-toggle="modal"
      data-bs-target="#followModal"
    >
      <h3>37</h3>
      <h3>팔로잉</h3>
    </div>
    <div @click="$emit('bookmark')" class="p-2" id="p-2">
      <h3>999</h3>
      <h3>찜</h3>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
h3 {
  color: rgb(80, 80, 80) !important;
}
</style>
