<template>
  <div class="d-flex flex-row mb-6" id="searchUser">
    <div @click="$router.push(`/member-page/${profile.id}`)" v-for="profile in userProfiles" class="p-2">
      <img :src="profile.imgSrc"/>
      <p>{{ profile.loginId }}</p>
    </div>
    <div class="p-2">
      <img src="/images/plusbutton.png"/>
      <p>더보기</p>
    </div>
  </div>
</template>

<script>
import data from "/src/assets/data.js";

export default {
  data() {
    return {
      userProfiles: data.userProfiles,
    };
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
