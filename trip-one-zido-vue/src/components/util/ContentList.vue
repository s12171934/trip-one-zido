<template>
  <div class="d-flex flex-row mb-6" id="contentList">
    <ContentCard
      v-for="content in list"
      :content="content"
      @toggle="(content) => $zido.toggleBookmark(content)"
    />
    <ContentCard
      v-if="this.list.length < this.maxLen"
      :content="plusData"
      @plus="addContent"
    />
  </div>
</template>

<script>
import ContentCard from "./ContentCard.vue";

export default {
  props: {
    list: Array,
    addApi: String,
    method: String,
    maxLen: Number,
    option: String,
  },
  components: {
    ContentCard,
  },
  data() {
    return {
      plusData: {
        photo: "/images/plusbutton.png",
        title: "더보기",
        plus: true,
      },

      page: 0,
    };
  },
  methods: {
    async addContent() {
      const addContents = await this.$zido.newContents(
        this.addApi,
        ++this.page,
        this.method,
        this.option
      );
      this.list.push(...addContents);
    },
  },
  watch: {
    option: {
      async handler() {
        console.log("change option");
        const newContents = await this.$zido.newContents(
          this.addApi,
          0,
          false,
          this.option
        );
        this.list.length = 0;
        this.list.push(...newContents);
        this.page = 0;
        console.log(this.list)
      },
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
