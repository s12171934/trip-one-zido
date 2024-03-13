<template>
  <main class="wrapper d-flex">
    <!-- ★왼쪽 -->
    <div class="p-2 d-flex flex-column border-end" id="leftSide">
      <div class="d-flex justify-content-between me-5 w-100">
        <h1 class="title">
          장소 제목
          <span class="comm"><img id="star" src="/images/star.png" />10</span>
        </h1>
        <div class="d-flex gap-2">
          <button class="rounded-5">관광지</button>
          <button class="rounded-5">약도보기</button>
        </div>
      </div>
      <h6>장소 기간</h6>
      <h6>장소 참여자</h6>

      <div class="p-2 h-100">
        <div class="d-flex justify-content-between"></div>
        <div class="d-flex align-items-center" id="photo-container">
          <img
            @contextmenu.prevent="delPhoto(idx)"
            v-for="(photo, idx) in photos"
            class="rounded"
            id="selectedPic"
            :src="photo"
            alt=""
          />
        </div>
      </div>
    </div>

    <!-- ★오른쪽 -->
    <div class="p-2 d-flex flex-column" id="rightSide">
      <h1 class="p-2">
        <span class="comm"
          ><img id="bookmark" src="/images/zzim.png" />123</span
        >
        <span class="comm"><img id="like" src="/images/like.png" />345</span>
        <span class="comm"
          ><img id="unLike" src="/images/unlike.png" />345</span
        >
      </h1>

      <h4 class="p-2">여행한 후기</h4>

      <textarea class="p-2" id="content" name="content" rows="5" cols="50" />

      <section id="commentBody">
        <div class="card bg-light">
          <div class="card-body">
            <form class="border-bottom">
              <input
                name="comment"
                type="text"
                class="form-control me-3"
                placeholder="댓글 추가하기"
              />
              <button class="button alt" type="submit" id="comment">
                댓글
              </button>
            </form>
            <!-- Comment with nested comments-->
            <div class="d-flex mb-4">
              <!-- Parent comment-->
              <div class="flex-shrink-0">
                <br />
                <img
                  class="rounded-circle"
                  src="/images/유재석.png"
                  alt="..."
                  id="commentProfilePic"
                />
              </div>
              <div class="ms-3">
                <br />
                <div class="fw-bold">유재석</div>
                부산 먹거리가 너무많네요 ~
                <small
                  ><b><a href="#" id="plusComment">답글</a></b></small
                >
                <div>
                  <small><a href="#" id="addedComment">▼답글 2개</a></small>
                </div>
                <!-- Child comment 1-->
                <div class="d-flex mt-4">
                  <div class="flex-shrink-0">
                    <img
                      class="rounded-circle"
                      src="/images/남자.png"
                      alt="..."
                      id="commentProfilePic"
                    />
                  </div>
                  <div class="ms-3">
                    <div class="fw-bold">
                      대댓글 단사람1
                      <a href="#" id="commentUpdate">수정</a>
                      <a href="#" id="commentDelete">삭제</a>
                    </div>
                    광안리 주변 횟집추천드려요!
                  </div>
                </div>
                <!-- Child comment 2-->
                <div class="d-flex mt-4">
                  <div class="flex-shrink-0">
                    <img
                      class="rounded-circle"
                      src="/images/여자.png"
                      alt="..."
                      id="commentProfilePic"
                    />
                  </div>
                  <div class="ms-3">
                    <div class="fw-bold">대댓글 단사람2</div>
                    거기도 맛있고~ 부산집도 맛있어요
                  </div>
                </div>
              </div>
            </div>
            <!-- Single comment-->
            <div class="d-flex mb-4">
              <div class="flex-shrink-0">
                <img
                  class="rounded-circle"
                  src="/images/조세호.png"
                  alt="..."
                  id="commentProfilePic"
                />
              </div>
              <div class="ms-3">
                <div class="fw-bold">조세호</div>
                ㅎㅎ
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      members: [""],
      photos: [
        "/images/busan1.jpg",
        "/images/busan1.jpg",
        "/images/busan1.jpg",
      ],
    };
  },
  methods: {
    async addPhotos(files) {
      for (let file of files) {
        const photo = file;
        await this.base64(photo);
      }
    },
    base64(file) {
      return new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => {
          resolve(e.target.result);
          this.photos.push(e.target.result);
          console.log(this.photos);
        };
        fileReader.readAsDataURL(file);
      });
    },
    delPhoto(idx) {
      this.photos.splice(idx, 1);
    },
    addMember() {
      this.members.push("");
    },
    delMember(idx) {
      this.members.splice(idx, 1);
    },
    searchAddress() {
      new daum.Postcode({
        oncomplete: function (data) {
          this.address = data.address;
          document.querySelector("#address").value = this.address;
        },
      }).open();
    },
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
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

#plusComment {
  color: darkgray;
  text-decoration: none;
}

#addedComment {
  text-decoration: none;
}

#commentUpdate,
#commentDelete {
  color: darkgray;
  text-decoration: none;
}

#commentProfilePic {
  width: 75px;
  height: 75px;
}
</style>
