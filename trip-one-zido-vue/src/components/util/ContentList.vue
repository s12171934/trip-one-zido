<template>
  <div class="d-flex flex-row mb-6" id="contentList">
    <ContentCard
      v-for="content in list"
      :content="content"
      @toggle="(content) => $zido.toggleBookmark(content)"
    />
    <ContentCard :content="plus" @plus="addContent" />
  </div>
</template>

<script>
import ContentCard from "./ContentCard.vue";

export default {
  props: {
    list: Object,
    sortOption: String,
    addApi: String,
    method: String,
  },
  components: {
    ContentCard,
  },
  data() {
    return {
      plus: {
        imgSrc: "/images/plusbutton.png",
        title: "더보기",
      },

      page: 0,
    };
  },
  methods: {
    addContent() {
      this.list.push(
        this.$zido.newContents(this.addApi, ++this.page, this.method)
      );
      console.log(this.page);
    },
  },
};
</script>

<style scoped>
#contentList {
  overflow: auto;
  overflow-y: hidden;
}
</style>
