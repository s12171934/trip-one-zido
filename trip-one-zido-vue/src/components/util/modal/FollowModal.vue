<template>
  <div class="modal fade" :class="modalShown ? 'show' : ''">
    <!-- Vertically centered scrollable modal -->
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-0">
          <button @click="$emit('modal')" class="btn-close" />
        </div>
        <div class="m-4 modal-body border rounded-3" id="followType">
          <h4>{{ type === "follower" ? "팔로워" : "팔로잉" }} 목록</h4>
        </div>

        <div class="m-4 modal-body border rounded-3" id="followList">
          <FollowProfile
            @followToggle="followToggle"
            v-for="userProfile in userProfiles"
            :userProfile="userProfile"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FollowProfile from "../FollowProfile.vue";
import data from "/src/assets/data.js";

export default {
  components: {
    FollowProfile,
  },
  props: {
    type: String,
    modalShown: Boolean,
  },
  data() {
    return {
      userProfiles: data.userProfiles,
    };
  },
  methods: {
    followToggle(userProfile){
      userProfile.isFollow = !userProfile.isFollow;
    }
  }
};
</script>

<style scoped>
.show {
  opacity: 100;
  display: block;
  z-index: 11000;
}

#followType {
  background-color: #d9d9d9;
  overflow: hidden;
}

#followList {
  background-color: #d9d9d9;
  overflow: auto;
}

h4 {
  font-family: "Jalnan";
}
</style>