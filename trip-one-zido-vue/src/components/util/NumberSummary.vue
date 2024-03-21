<template>
  <div class="d-flex flex-row justify-content-between mb-3">
    <div class="p-2" id="p-2">
      <h3>{{ totalBoard }}</h3>
      <h3>게시글</h3>
    </div>
    <div
      @click="$emit('follower')"
      class="p-2"
      id="p-2"
      data-bs-toggle="modal"
      data-bs-target="#followModal"
    >
      <h3>{{ followerCount }}</h3>
      <h3>팔로워</h3>
    </div>
    <div
      @click="$emit('following')"
      class="p-2"
      id="p-2"
      data-bs-toggle="modal"
      data-bs-target="#followModal"
    >
      <h3>{{ followingCount }}</h3>
      <h3>팔로잉</h3>
    </div>
    <div @click="$router.push(`/bookmark/${$route.params.id}`)" class="p-2" id="p-2">
      <h3>{{ bookmarkCount }}</h3>
      <h3>찜</h3>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    totalBoard: Number,
    followerCount: Number,
    followingCount: Number,
    bookmarkCount: Number,
  },
};
</script>

<style scoped>
h3 {
  color: rgb(80, 80, 80) !important;
}

@media (max-width: 380px) {
  #p-2 {
    font-size: 10%;
  }
}
</style>
