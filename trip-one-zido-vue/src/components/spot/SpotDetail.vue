<template>
  <main class="wrapper d-flex">
    <!-- ★왼쪽 -->
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between pe-2 w-100">
        <h1 class="title">
          {{ spotData.title }}
          <span class="comm"
            ><img id="star" src="/images/star.png" />{{ spotData.grade }}</span
          >
        </h1>
        <div class="d-flex gap-2">
          <button class="rounded-5">{{ spotData.category }}</button>
          <button class="rounded-5">약도보기</button>
        </div>
      </div>
      <h6>
        {{
          `${spotData.startDate} : ${spotData.startTime} ~ ${spotData.endDate} : ${spotData.endTime}`
        }}
      </h6>
      <h6>
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
      <table v-if="$route.params.id == 1">
        <tr>
          <td>
            <h1 class="p-2">
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
              <span class="comm" :class="spotData.myGood === true ? 'like' : ''"
                ><img
                  @click="$zido.toggleLike(spotData, true)"
                  id="like"
                  src="/images/like.png"
                />{{ spotData.goodCount }}</span
              >
              <span
                class="comm"
                :class="spotData.myGood === false ? 'like' : ''"
                ><img
                  @click="$zido.toggleLike(spotData, false)"
                  id="unLike"
                  src="/images/unlike.png"
              /></span>
            </h1>
          </td>
          <td>
            <div v-if="spotData.isMine" class="m-0 d-flex justify-content-end gap-2">
              <div class="select-wrapper" id="security">
                <select
                  @change="$zido.togglePublic($route.params.id)"
                  class="local-select"
                  v-model="spotData.isPublic"
                >
                  <option value=true>공개</option>
                  <option value=false>비공개</option>
                </select>
              </div>
              <input
                @click="$router.push(`/edit/spot/${$route.params.id}`)"
                id="input"
                class="button small"
                type="submit"
                value="수정"
              />
              <input
                @click="$zido.deleteSpot($route.params.id)"
                class="button alt small"
                type="button"
                value="삭제"
              />
            </div>
          </td>
        </tr>
      </table>

      <h4 class="p-2">여행한 후기</h4>

      <textarea class="p-2" id="content" name="content" rows="5" cols="50">
        {{ spotData.review }}
      </textarea>

      <section id="commentBody">
        <div class="card bg-light">
          <div class="card-body">
            <form
              @submit.prevent="$zido.addComment(spotData.id, comment)"
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
        isPublic: null,
        photos: null,
        members: [{}],
        comments: null,
        isMine: null,
      },
      comment: "",
    };
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido.getSpotData(this.$route.params.id).then((res) => this.spotData = res)
  },
};
</script>

<style scoped>
main > div {
  width: 50%;
  padding: 20px;
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
  margin: 1%;
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
</style>
