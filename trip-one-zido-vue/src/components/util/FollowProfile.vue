<template>
  <div
    @click="goToMemberPage"
    class="d-flex align-items-center gap-2 mb-2 p-2"
    id="profile"
  >
    <img
      class="rounded-circle"
      id="followPic"
      :src="`data:image/jpeg;base64,${userProfile.profile}`"
      alt="..."
    />

    <div class="d-flex justify-content-between w-100 align-items-center" >
      <h4>{{ userProfile.loginId }}</h4>
        <button v-if="userProfile.id != userProfile.sessionId"
        @click.stop="$emit('followToggle', userProfile)"
        @mouseover="hover = true"
        @mouseleave="hover = false"
        v-html="
          userProfile.follow ? (hover ? '언 팔로우' : '팔로잉 중') : '팔로우'
        "
        class="button"
      />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    userProfile: Object,
  },
  methods: {
    goToMemberPage() {
      location.href = `/member-page/${this.userProfile.id}`;
    },
  },
  data() {
    return {
      hover: false,
    };
  },
};
</script>

<style scoped>
#profile:hover {
  box-shadow: 0 0 0 2px grey inset;
  border-radius: 1rem;
}

#followPic {
  width: 75px;
  height: 75px;
}

h4 {
  margin: 0;
  line-height: 70px;
  display: inline;
}

.button {
  padding-left: 10px;
  padding-right: 10px;
  height: 70%;
}
.button:hover {
  background: gray;
}
</style>
