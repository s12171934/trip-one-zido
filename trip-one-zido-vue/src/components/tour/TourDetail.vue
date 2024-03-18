<template>
  <main class="wrapper">
    <div class="d-flex justify-content-end">
      <ul class="actions">
        <li>
          <a
            @click="$router.push(`/tour/loc/${tour.locCategory}`)"
            class="button alt small"
            >목록</a
          >
        </li>
        <li>
          <a
            :href="tour.prevId ? `/tour/${tour.prevId}` : ''"
            class="button alt small"
            >이전</a
          >
        </li>
        <li>
          <a
            :href="tour.nextId ? `/tour/${tour.nextId}` : ''"
            class="button alt small"
            >다음</a
          >
        </li>
      </ul>
    </div>
    <div class="row">
      <div class="col-md-6 d-flex">
        <img class="rounded-5" :src="`data:image/jpeg;base64,${tour.photo}`" />
      </div>
      <div class="col-md-6">
        <div class="d-flex align-items-center">
          <h1 class="display-5">{{ tour.title }}</h1>
          <img
            @click="$zido.toggleBookmark(tour)"
            id="bookmark"
            :src="tour.myBookmark ? '/images/zzim.png' : '/images/unzzim.png'"
          />
          <h5 class="ms-2">{{ tour.bookmarkCount }}</h5>
        </div>
        <div class="fs-5 mb-5">
          <span>・관광지 한줄 설명</span>
          <p id="explanation">
            {{ tour.info }}
          </p>
        </div>
        <div class="fs-5">
          <span>・장소 주소</span>
          <div class="d-flex fill">
            <iframe
              class="rounded-5"
              src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3260.7271121845406!2d129.2207447762419!3d35.18835345689094!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35688c1bbbdea6c1%3A0xa141406d6f112e5c!2z7ZW064-Z7Jqp6raB7IKs!5e0!3m2!1sko!2skr!4v1708994953688!5m2!1sko!2skr"
              width="925"
              height="450"
              style="border: 0"
              allowfullscreen=""
              loading="lazy"
              referrerpolicy="no-referrer-when-downgrade"
            ></iframe>
          </div>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      tour: {
        id: null,
        title: null,
        address: null,
        locCategory: null,
        info: null,
        photo: null,
        bookmarkCount: null,
        myBookmark: null,
        nextId: null,
        prevId: null,
      }
    };
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido.getTourData(this.$route.params.id).then((res) => {
      this.tour = res;
    });
  },
};
</script>

<style scoped>
h1 {
  color: #ff928e !important;
}

#bookmark {
  width: 25px;
  height: 25px;
  margin-left: 5%;
}

img {
  vertical-align: top;
  width: 100%;
  height: 100%;
}
</style>
