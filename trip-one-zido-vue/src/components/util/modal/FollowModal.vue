<template>
  <div
    class="modal fade"
    id="followModal"
    tabindex="-1"
    aria-labelledby="followModalLabel"
    aria-hidden="true"
  >
    <!-- Vertically centered scrollable modal -->
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header border border-0">
          <button
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          />
        </div>
        <div class="m-4 modal-body border rounded-3" id="followType">
          <h4>{{ type === "follower" ? "팔로워" : "팔로잉" }} 목록</h4>
        </div>
        <div class="m-4 modal-body border rounded-3" id="followList">
          <FollowProfile
            @followToggle="(userProfile) => $zido.toggleFollow(userProfile)"
            v-for="userProfile in followList"
            :userProfile="userProfile"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import FollowProfile from "../FollowProfile.vue";

export default {
  components: {
    FollowProfile,
  },
  props: {
    type: String,
    followList: Object,
  },
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
</style>
