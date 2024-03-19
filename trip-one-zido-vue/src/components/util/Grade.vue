<template>
  <div class="rating">
    <img @click="setGrade(idx)" v-for="(star, idx) in stars" :src="star" />
    <span>{{ grade }}점</span>
  </div>
</template>

<script>
export default {
  props: {
    grade: Number,
  },
  data() {
    return {
      stars: [
        "/images/emptyStar.png",
        "/images/emptyStar.png",
        "/images/emptyStar.png",
        "/images/emptyStar.png",
        "/images/emptyStar.png",
      ],
    };
  },
  methods: {
    setGrade(idx) {
      let star = 0;

      for (let i = 0; i < idx; i++) {
        this.stars[i] = "/images/fullStar.png";
      }
      for (let i = 4; i > idx; i--) {
        this.stars[i] = "/images/emptyStar.png";
      }
      if (this.stars[idx] == "/images/emptyStar.png") {
        this.stars[idx] = "/images/halfStar.png";
        star = 1;
      } else if (this.stars[idx] == "/images/halfStar.png") {
        this.stars[idx] = "/images/fullStar.png";
        star = 2;
      } else {
        this.stars[idx] = "/images/emptyStar.png";
      }
      this.$emit("grade", 2 * idx + star);
    },
  },
};
</script>

<style scoped>
.rating {
  unicode-bidi: bidi-override;
  text-align: left;
  padding-left: 1rem;
}

.rating > img {
  height: 40px;
}

.rating > span {
  padding-left: 1rem;
  font-size: 20px;
  color: #ffd179;
}
</style>
