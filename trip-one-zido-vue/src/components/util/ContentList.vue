<template>
  <div class="d-flex flex-row mb-6" id="contentList">
    <ContentCard
      v-for="content in contents"
      :content="content"
      @toggle="toggle"
    />
    <ContentCard :content="plus" @plus="addContent" />
  </div>
</template>

<script>
import ContentCard from "./ContentCard.vue";

export default {
  components: {
    ContentCard,
  },
  data() {
    return {
      plus: {
        imgSrc: "/images/plusbutton.png",
        title: "더보기",
      },
      contents: [
        {
          id: 1,
          imgSrc: "/images/busan2.jpg",
          title: "더미",
          bookmarkCount: 3000,
          grade: 10,
          like: 300,
          myBookmark: true,
          type: "plan",
        },
        {
          id: 1,
          imgSrc: "/images/busan2.jpg",
          title: "더미",
          bookmarkCount: 3000,
          grade: 10,
          like: 300,
          myBookmark: true,
          type: "tour",
        },
        {
          id: 1,
          imgSrc: "/images/busan2.jpg",
          title: "더미",
          bookmarkCount: 3000,
          grade: 10,
          like: 300,
          myBookmark: true,
          type: "spot",
        },
        {
          id: 1,
          imgSrc: "/images/busan2.jpg",
          title: "더미",
          bookmarkCount: 3000,
          grade: 10,
          like: 300,
          myBookmark: true,
          type: "plan",
        },
        {
          id: 1,
          imgSrc: "/images/busan2.jpg",
          title: "더미",
          bookmarkCount: 3000,
          grade: 10,
          like: 300,
          myBookmark: true,
          type: "tour",
        },
        {
          id: 1,
          imgSrc: "/images/busan2.jpg",
          title: "더미",
          bookmarkCount: 3000,
          grade: 10,
          like: 300,
          myBookmark: true,
          type: "spot",
        },
      ],
      newContent: {
        id: 1,
        imgSrc: "/images/busan2.jpg",
        title: "더미",
        bookmarkCount: 3000,
        grade: 10,
        like: 300,
        myBookmark: true,
        type: "spot",
      },
    };
  },
  methods: {
    toggle(content) {
      content.myBookmark = !content.myBookmark;
    },
    addContent() {
      this.contents.push(this.newContent);
    },
  },
};
</script>

<style scoped>
#contentList {
  overflow: auto;
  overflow-y: hidden;
}
div::-webkit-scrollbar {
  /* 스크롤바 배경 */
  height: 15px;
  border-radius: 10px;
  background: rgb(236, 236, 250);
}

div::-webkit-scrollbar-thumb {
  /* 스크롤바 */
  width: 15px;
  border-radius: 10px;
  background: pink;
}
</style>
