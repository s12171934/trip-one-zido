<template>
  <div class="d-flex mb-3 w-100">
    <div class="flex-shrink-0">
      <img
        class="rounded-circle"
        :src=" data.member.profile
          ? `data:image/jpeg;base64,${data.member.profile}`
          : '/images/nomal.jpeg'"
        alt="..."
        id="commentProfilePic"
      />
    </div>
    <div class="ms-3 w-100">
      <div class="d-flex gap-5">
        <span class="fw-bold">{{ data.member.loginId }}</span>
        <div v-if="true && !editComment" class="d-flex gap-2 edit-comment">
          <span @click="editComment = !editComment" class="fw-bold">수정</span>
          <span
            @click="
              $zido.deleteComment(data.id);
              $emit('reload');
            "
            class="fw-bold"
            >삭제</span
          >
        </div>
      </div>
      <form
        @submit.prevent="edit"
        v-if="editComment"
        class="border-bottom mb-3"
      >
        <input v-model="data.comment" type="text" class="form-control me-3" />
        <button class="button alt" type="submit" id="comment">수정</button>
      </form>
      <span v-else>{{ data.comment }}</span>

      <div v-if="first">
        <small
          ><b
            ><span @click="addNestedComment = !addNestedComment">답글</span></b
          ></small
        >
      </div>
      <form
        @submit.prevent="
          $zido.addComment(data.id, comment);
          $emit('reload');
        "
        v-if="addNestedComment"
        class="border-bottom mb-3"
      >
        <input
          v-model="comment"
          type="text"
          class="form-control me-3"
          placeholder="답글 추가하기"
        />
        <button class="button alt" type="submit" id="comment">등록</button>
      </form>
      <Comment
        v-if="data.comments"
        v-for="comment in data.comments"
        :first="false"
        :data="comment"
        @reload="$emit('reload')"
      />
    </div>
  </div>
</template>

<script>
export default {
  props: {
    data: Object,
    first: Boolean,
  },
  data() {
    return {
      addNestedComment: false,
      editComment: false,
      comment: "",
    };
  },
  methods: {
    edit() {
      this.$zido.editComment(this.data.id, this.data.comment);
      this.editComment = false;
    },
  },
};
</script>

<style scoped>
form {
  display: flex;
  width: 100%;
  padding-bottom: 1rem;
  margin-top: 0.5rem;
}

#plusComment {
  color: darkgray;
  text-decoration: none;
}

#addedComment {
  text-decoration: none;
}

.edit-comment {
  color: darkgray;
  text-decoration: none;
}

#commentProfilePic {
  width: 75px;
  height: 75px;
}

span {
  font-weight: normal !important;
}

@media (max-width: 465px) {
  span {
    font-size: small;
  }
}
@media (max-width: 440px) {
  img {
    width: 50px !important;
    height: 50px !important;
  }
}

@media (max-width: 385px) {
  span {
    font-size: x-small;
  }
}

@media (max-width: 365px) {
  img {
    width: 40px !important;
    height: 40px !important;
  }
}

@media (max-width: 340px) {
  img {
    width: 30px !important;
    height: 30px !important;
  }
}
</style>
