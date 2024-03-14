<template>
  <div class="d-flex flex-row mb-6" id="searchUser">
    <div
      @click="$router.push(`/member-page/${profile.id}`)"
      v-for="profile in memberList"
      class="p-2"
    >
      <img :src="profile.imgSrc" />
      <p>{{ profile.loginId }}</p>
    </div>
    <div @click="" v-if="plus" class="p-2">
      <img src="/images/plusbutton.png" />
      <p>더보기</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    addApi: String,
    memberList: Object,
    plus: Boolean,
  },
};
</script>

<style scoped>
#searchUser {
  margin: 0;
}

img {
  width: 180px;
  height: 180px;
  border-radius: 100%;
  margin-bottom: 30px;
}
p {
  text-align: center;
}
</style>
