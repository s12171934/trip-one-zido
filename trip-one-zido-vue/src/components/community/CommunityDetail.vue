<template>
  <main class="wrapper">
    <div class="inner">
      <h1>게시글 상세</h1>
      <br />
      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <span
          v-if="isRecruitmentClosed"
          class="button alt small rounded-3"
          id="mouseHover"
          >마감</span
        >
        <span v-else class="button small rounded-3" id="mouseHover"
          >모집중</span
        >
        <a href="/community" class="button alt small rounded-3">목록</a>
        <!-- 현재 id-1 : 이전글 / 현재 id+1 : 다음글   -->
        <!-- <a href="/html-css/community/detail/detail.html" id="button2" class="button small rounded-3">이전글</a> -->
        <a @click="goToPreviousPost" class="button small rounded-3">이전글</a>
        <a @click="goToNextPost" class="button small rounded-3">다음글</a>
      </div>
      <br />

      <form method="get" action="#">
        <!-- 테이블 -->
        <table id="table" class="border">
          <tr>
            <td id="tdTitle">제목 :</td>
            <td id="black">{{ detail.title }}</td>
            <td id="tdTitle" class="border-start">작성자 :</td>
            <td id="black">{{ detail.login_id }}</td>
          </tr>

          <tr>
            <td>지역 :</td>
            <td>
              <option value="1" id="black">{{ detail.loCategory }}</option>
            </td>
            <td class="border-start">모집 인원 :</td>
            <td id="black">
              {{ detail.withMember.length }}/{{ detail.total }} 명
            </td>
          </tr>

          <tr>
            <td>참여 인원 :</td>
            <td colspan="3" id="black">
              <span v-for="withMember in detail.withMember" class="me-2">{{
                withMember
              }}</span>
            </td>
          </tr>

          <tr>
            <td>일정 :</td>
            <td id="black">{{ detail.start }} ~ {{ detail.end }}</td>
            <td class="border-start">모집 마감일 :</td>
            <td id="black">{{ detail.deadLine }}</td>
          </tr>

          <tr>
            <td>작성일 :</td>
            <td id="black">{{ detail.created_at }}</td>
            <td class="border-start">조회수 :</td>
            <td id="black">{{ detail.viewCount }}</td>
          </tr>

          <tr>
            <td>내용</td>
            <td colspan="3">
              <textarea
                class="form-control"
                rows="5"
                cols="50"
                id="content"
                readOnly
                >{{ detail.content }} 
								</textarea
              >
            </td>
          </tr>
        </table>

        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
          <div v-if="detail.content_id == 1">
            <a @click="update" class="button alt small rounded-3"> 수정</a>
            <a
              @click="del"
              class="button small rounded-3"
              data-bs-toggle="modal"
              data-bs-target="#alertModal"
            >
              삭제</a
            >
            <AlertModal :modal="modal" />
          </div>
          <div v-else>
            <a
              @click="participateOrCancel"
              class="button small rounded-3"
              id="button"
              data-bs-toggle="modal"
              data-bs-target="#alertModal"
            >
              참여 / 참여 취소</a
            >
            <AlertModal
              :modal="showSuccessModal ? 'withSuccess' : 'withFail'"
            />
          </div>
        </div>
      </form>
    </div>
  </main>
</template>

<script>
import AlertModal from "../util/modal/AlertModal.vue";
import data from "@/assets/data.js";

export default {
  components: {
    AlertModal,
  },

  data() {
    return {
      // 상세 내용 담는 배열
      detail: this.$zido.getCommunityDetail(this.$route.params.id),
      modal: "",
      loginId: "",
      id: this.$cookies.get("login"),
      showSuccessModal: false,

      title: "", // 추가: 게시글 제목
      content: "", // 추가: 게시글 내용
    };
  },

  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
  },

  created() {
    this.fetchCommunityDetail();
    // 해당 게시글의 viewCount를 증가시킵니다.
    const currentContentId = this.$route.params.id;
    const selectedPost = this.communityDetail.find(
      (post) => post.content_id == currentContentId
    );
    if (selectedPost) {
      selectedPost.viewCount += 1;
    }
  },

  methods: {
    fetchCommunityDetail() {
      // 라우트 매개변수에서 content_id를 가져옵니다.
      const currentContentId = this.$route.params.id;

      // 지정된 content_id에 대한 데이터만 필터링합니다.
      const selectedPost = data.communityDetail.find(
        (post) => post.content_id == currentContentId
      );

      // communityDetail 배열을 선택된 게시물 데이터로 업데이트합니다.
      if (selectedPost) {
        this.communityDetail = [selectedPost];
      } else {
        // 지정된 content_id에 대한 게시물이 없는 경우 처리
        console.error(
          "content_id에 해당하는 게시물을 찾을 수 없습니다:",
          currentContentId
        );
      }
    },

    goToPreviousPost() {
      const currentContentId = this.$route.params.id;
      const currentIndex = data.communityDetail.findIndex(
        (post) => post.content_id == currentContentId
      );

      if (currentIndex > 0) {
        const previousPostId =
          data.communityDetail[currentIndex - 1].content_id;
        this.$router.push({ path: `/community/${previousPostId}` });
      } else {
        console.log("이전 글이 없습니다.");
        // 이전 글이 없을 경우에 대한 처리 추가
      }
    },

    goToNextPost() {
      const currentContentId = this.$route.params.id;
      const currentIndex = data.communityDetail.findIndex(
        (post) => post.content_id == currentContentId
      );

      if (currentIndex < data.communityDetail.length - 1) {
        const nextPostId = data.communityDetail[currentIndex + 1].content_id;
        this.$router.push({ path: `/community/${nextPostId}` });
      } else {
        console.log("다음 글이 없습니다.");
        // 다음 글이 없을 경우에 대한 처리 추가
      }
    },

    update() {
      // 자기 자신이 버튼 누르면 수정완료
      this.$router.push({
        path: `/edit/community/${this.$cookies.get("login")}`,
      });
    },

    del() {
      this.modal = "deleteCommunity";
      this.$zido.deleteCommunity(this.$route.params.id);
    },

    participateOrCancel() {
      // 타인 자신이 참여/참여취소 버튼 누르면
      if (this.showSuccessModal) {
        this.$zido.joinCommunity(this.$route.params.id);
      } else {
        this.$zido.joinCancleCommunity(this.$route.params.id);
      }
      this.showSuccessModal = !this.showSuccessModal;
    },
  },

  computed: {
    // detail.withCount와 detail.total이 같으면 마감 상태로 간주합니다.
    isRecruitmentClosed() {
      return (
        this.communityDetail.length > 0 &&
        this.communityDetail[0].withCount === this.communityDetail[0].total
      );
    },
  },
};
</script>

<style scoped>
#leftPosition {
  text-align: left;
}

#centerPosition {
  text-align: center;
}

#searchBar {
  margin: auto;
}

#tdTitle {
  width: 15%;
}

#mouseHover {
  margin-right: auto;
  cursor: default;
}

#footer {
  font-family: "Jalnan";
  font-size: 10px;
}
h1,
h2,
h3,
h4,
body,
.button,
section {
  font-family: "Jalnan";
}
h1 {
  color: #ff928e !important;
}
#menu {
  font-family: "Jalnan";
  font-size: 20px;
}
thead {
  font-size: 15px;
  color: #929292;
}
.wrapper {
  margin-left: 5% !important;
  margin-right: 5% !important;
}
#button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
  font-size: 15px;
}
#button2 {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}
#table {
  font-size: 15px !important;
  color: black !important;
}
#black {
  width: 35%;
  color: black !important;
}
table tbody tr {
  background-color: white !important;
}
#content {
  resize: none;
}
</style>
