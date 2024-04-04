<template>
  <main class="wrapper">
    <div class="d-flex justify-content-end">
      <ul class="actions">
        <li id="button-list">
          <a
            @click="$router.push(`/tour/loc/${tour.locCategory}`)"
            class="button alt small"
            > 목록
          </a>
        </li>
        <li>
          <a
            :href="tour.prevId ? `/tour/${tour.prevId}` : ''"
            class="button alt small"
            > 이전
          </a>
        </li>
        <li>
          <a
            :href="tour.nextId ? `/tour/${tour.nextId}` : ''"
            class="button alt small"
            > 다음
          </a>
        </li>
      </ul>
    </div>

    <div class="row">
      <div class="col-md-6 d-flex">
        <img class="rounded-5" :src="tour.photo" />
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
        <div class="fs-5 mb-5 mt-2" id="text-css">
          <span>・관광지 한줄 설명</span>
          <p id="explanation">
            - {{ tour.info }}
          </p>
        </div>
        <div class="fs-5">
          <span>・장소 주소</span>
          <KakaoMap/>
        </div>
      </div>
    </div>
  </main>
</template>

<script>
import KakaoMap from '@/components/util/KakaoMap.vue'

export default {
  components: {
    KakaoMap
  },
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
    //GET -- /api/tour/${id}
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

@media (max-width: 767px) { /* 원하는 크기로 설정 */
  .actions {
    display: flex; /* 버튼을 가로로 나열하기 위해 flex로 설정 */
    padding: none;
    width: 100%;
    justify-content: space-between; /* 버튼 사이의 간격을 조절 */
    margin-right: 10px; /* 버튼 사이 간격 추가 */
  }
  .display-5 {
    margin-top: 5%;
    margin-left: 3%;
  }
  #explanation {
    margin-left: 6%;
  }
}

@media screen and (max-width: 480px) { /* 원하는 크기로 설정 */
  ul.actions li > * {
    width: 90%;
  }
  #button-list {
    padding: 16px 0px 0px;
  }
  #map {
    height: 350px;
    margin-bottom: 10%;
  }
  .button {
    padding: 0;
  }
}

</style>
