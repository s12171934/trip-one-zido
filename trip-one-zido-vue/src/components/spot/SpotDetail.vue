<template>
  <main class="wrapper d-flex">
    <!-- ★왼쪽 -->
    <div class="d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between pe-2 w-100">
        <h1 class="title">
          {{ spotData.title }}
          <span class="comm"
            ><img id="star" src="/images/star.png" />{{ spotData.grade }}</span
          >
        </h1>
        <div class="d-flex gap-2" id="left-category">
          <button class="rounded-5" id="spot-data">{{ spotData.category }}</button>
          <button @click="openSpotMap()" class="rounded-5">약도보기</button>
        </div>
      </div>
      <h6 class="left-css">
        {{
          `${spotData.startDate} : ${spotData.startTime} ~ ${spotData.endDate} : ${spotData.endTime}`
        }}
      </h6>
      <h6 class="left-css">
        <span v-for="member in spotData.members">{{ member.loginId }}</span>
      </h6>

      <div class="p-2 h-100">
        <div class="d-flex justify-content-between"></div>
        <div class="d-flex align-items-center" id="photo-container">
          <img
            v-for="photo in spotData.photos"
            class="rounded"
            id="selectedPic"
            :src="`data:image/jpeg;base64,${photo.photo}`"
            alt=""
          />
        </div>
      </div>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <table>
        <tr>
          <td>
            <h1>
              <span class="comm"
                ><img
                  @click="$zido.toggleBookmark(spotData)"
                  id="bookmark"
                  :src="
                    spotData.myBookmark
                      ? '/images/zzim.png'
                      : '/images/unzzim.png'
                  "
                />{{ spotData.bookmarkCount }}</span
              >
              <span class="comm" :class="spotData.myGood == true ? 'like' : ''"
                ><img
                  @click="$zido.toggleLike(spotData, true)"
                  id="like"
                  src="/images/like.png"
                />{{ spotData.goodCount }}</span
              >
              <span class="comm" :class="spotData.myGood == false ? 'like' : ''"
                ><img
                  @click="$zido.toggleLike(spotData, false)"
                  id="unLike"
                  src="/images/unlike.png"
              /></span>
            </h1>
          </td>
          <td>
            <div
              v-if="spotData.mine"
              class="m-0 d-flex justify-content-end gap-2"
            >
              <input
                @click="$router.push(`/edit/spot/${$route.params.id}`)"
                id="input"
                class="button small"
                type="submit"
                value="수정"
              />
              <input
                @click="
                  $zido.deleteSpot($route.params.id);
                  $router.push('/member-page');
                "
                class="button alt small"
                type="button"
                value="삭제"
              />
            </div>
          </td>
        </tr>
      </table>

      <h4 class="p-2">여행한 후기</h4>

      <textarea class="p-2" id="content" name="content" rows="5" cols="100" readonly>
        {{ spotData.review }}
      </textarea>

      <section id="commentBody">
        <div class="card bg-light">
          <div class="card-body">
            <form
              @submit.prevent="
                $zido.addComment(spotData.id, comment);
                reloadComment();
              "
              class="border-bottom mb-3"
            >
              <input
                v-model="comment"
                type="text"
                class="form-control me-3"
                placeholder="댓글 추가하기"
              />
              <button class="button alt" type="submit" id="comment">
                댓글
              </button>
            </form>

            <Comment
              v-for="comment in spotData.comments"
              :first="true"
              :data="comment"
              @reload="reloadComment"
            />
          </div>
        </div>
      </section>
    </div>
  </main>
</template>

<script>
import Comment from "../util/Comment.vue";

export default {
  components: {
    Comment,
  },
  data() {
    return {
      spotData: {
        id: null,
        category: null,
        startDate: null,
        startTime: null,
        endDate: null,
        endTime: null,
        locCategory: null,
        address: null,
        review: null,
        grade: null,
        viewCount: null,
        goodCount: null,
        myGood: null,
        bookmarkCount: null,
        myBookmark: null,
        title: null,
        visibility: null,
        photos: null,
        members: [{}],
        comments: null,
        mine: null,
      },
      comment: "",
    };
  },
  methods: {
    async reloadComment() {
      this.spotData = await this.$zido.getSpotData(this.$route.params.id);
    },
    openSpotMap() {
      window.open(
        `/map/${this.$route.params.id}`,
        "",
        "toolbar=no,scrollbars=no,resizable=no,top=100,left=300,width=1000,height=800"
      );
    },
  },
  async mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.spotData = await this.$zido.getSpotData(this.$route.params.id);
    console.log(this.spotData);
  },
};
</script>

<style scoped>
main > div {
  width: 100%;
  /* padding: 20px; */
}

.comm {
  font-size: 70%;
  margin-right: 1rem;
}

#bookmark,
#unLike,
#like,
#star {
  width: 40px;
  height: 40px;
  margin: 0.5rem;
}

#photo-container {
  background-color: #dee1e3;
  overflow: scroll;
  overflow-y: hidden;
  height: 100%;
  /* margin: 1%;*/
}

#selectedPic {
  width: 75%;
  height: 100%;
  margin-right: 1%;
}

#addPic {
  display: none;
}

h4 {
  margin: 0;
}

td {
  min-width: 170px;
}

.member-container {
  overflow: scroll;
  overflow-y: hidden;
  height: 100%;
  width: 500px;
}

.member {
  width: 200px;
}

#plusMember {
  margin: 2%;
}

.rating {
  unicode-bidi: bidi-override;
  direction: rtl;
  text-align: left;
  padding-left: 1rem;
  color: #ff928e;
}

.rating > span {
  display: inline-block;
  position: relative;
  width: 1.1em;
}

.rating > span:hover:before,
.rating > span:hover ~ span:before {
  content: "\2605";
  position: absolute;
}

textarea {
  resize: none;
}

#commentBody {
  width: 100%;
  margin-top: 5%;
}

.card-body > form {
  display: flex;
  width: 100%;
  padding-bottom: 1rem;
  margin: 0;
}

.like {
  border: #ff928e 1px solid;
  border-radius: 1.5rem;
  padding: 2%;
}

.select-wrapper .local-select {
  width: 100%;
  height: 100%;
}

@media (max-width: 1023px) { 
  #font-vertical {
    white-space: nowrap; 
    text-overflow: ellipsis; 
  }
  #rightSide {
    border: 1px;
    box-sizing: border-box;
  }
  main > div {
    width: 100%;
  }
  .wrapper {
    flex-direction: column;
  }
  #address {
    width: 350%;
  }
  #leftSide {
    flex-shrink: 0; /* leftSide가 작아질 때 줄어들지 않도록 설정 */
  }
  .border-end {

    border-right: none !important;
  }
}
#spot-data { 
  cursor: default;
}

@media (max-width: 767px) { /* 767이하일 때 아래코드 적용 */
 .rounded-5 {
  font-size: small;
 } 
 .title-section {
    flex-direction: column;
    align-items: flex-start;
  }
}

@media (max-width: 650px) {
  #bookmark[data-v-595cf560],
  #unLike[data-v-595cf560], 
  #like[data-v-595cf560], 
  #star[data-v-595cf560] {
    width: 30px !important;
    height: 30px !important;
    margin: 0.5rem;
  }
} 

@media (max-width: 638px) {
 .rounded-5 {
  font-size:x-small;
 } 
}

@media (max-width: 610px) {
  #bookmark[data-v-595cf560],
  #unLike[data-v-595cf560], 
  #like[data-v-595cf560], 
  #star[data-v-595cf560] {
    width: 25px !important;
    height: 25px !important;
    margin: 0.1rem;
  }
} 

@media (max-width: 555px) {
 .rounded-5 {
  font-size:xx-small;
 }
 #bookmark[data-v-595cf560],
  #unLike[data-v-595cf560], 
  #like[data-v-595cf560], 
  #star[data-v-595cf560] {
    width: 20px !important;
    height: 20px !important;
    margin: 0.1rem;
  }
}

@media (max-width: 495px) {
  table {
    width: 100%;
    border-collapse: collapse; /* 테이블 테두리가 겹치지 않도록 설정합니다. */
  }

  td, th {
    display: block;
    width: 100%;
    text-align: left; /* 텍스트를 왼쪽 정렬합니다. */
  }
}

@media (max-width: 425px) {
  .title {
    font-size: 23px;
  }
}


@media (max-width: 400px) {
  form, input {
    font-size: small;
  }
  .title {
    font-size: 21px;
  }
}

@media (max-width: 380px) {
  textarea {
    padding: 0.75em 1em;
  }
  .title {
    font-size: 20px;
  }
  .rounded-5 {
    padding-left: 25px;
    padding-right: 25px;
  }
}

@media (max-width: 350px) {
  .rounded-5 {
    padding-left: 22px;
    padding-right: 22px;
  }
}


@media (max-width: 340px) {
  form, input {
    font-size: x-small;
  }
}
</style>
