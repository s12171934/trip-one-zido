<template>
  <div :id="content.id" class="container">
    <div @click.stop="this.$emit('plus')" class="image">
      <img
        :src="
          content.photo
            ? content.plus
              ? content.photo
              : content.type != 'tour'
              ? `data:image/jpeg;base64,${content.photo}`
              : content.photo
            : '/images/plan-default.jpg'
        "
        class="img"
      />
    </div>
    <div
      v-if="content.type != null"
      @click.stop="pushDetail"
      class="hover-effect flex-column"
    >
      <span
        ><img id="bookmark" src="/images/zzim.png" />&nbsp;{{
          content.bookmarkCount
        }}</span
      >
      <span v-if="content.type != 'tour'"
        ><img id="star" src="/images/star.png" />&nbsp;{{ content.grade }}</span
      >
      <span v-if="content.type != 'tour'"
        ><img id="like" src="/images/like.png" />&nbsp;{{
          content.goodCount
        }}</span
      >
    </div>
    <p>
      {{ content.title }}&nbsp;<img
        v-if="content.type != null"
        @click="$emit('toggle', content)"
        id="bookmark"
        :src="content.myBookmark ? '/images/zzim.png' : '/images/unzzim.png'"
      />
    </p>
  </div>
</template>

<script>
export default {
  props: {
    content: Object,
  },
  methods: {
    pushDetail() {
      this.$router.push({ path: `/${this.content.type}/${this.content.id}` });
    },
  },
};
</script>

<style scoped>
.container {
  position: relative;
  height: 360px;
  width: 300px;
  padding: 20px;
}
.image {
  border-radius: 20%;
  overflow: hidden;
  margin-bottom: 10px;
}
.img {
  height: 260px;
  width: 260px;
}
.hover-effect {
  position: absolute;
  top: 20px;
  left: 20px;
  border-radius: 20%;
  height: 260px;
  width: 260px;
  display: flex;
  justify-content: center;
  align-items: left;
  padding-left: 95px;
  background-color: black;
  opacity: 0;
}
.hover-effect:hover {
  opacity: 70%;
}
p {
  line-height: 28px;
  align-items: center;
  text-align: center;
  margin: 0;
}
span {
  display: flex;
  align-items: center;
  line-height: 30px;
  color: white;
}
#bookmark,
#star,
#like {
  width: 25px;
  height: 25px;
}
</style>
