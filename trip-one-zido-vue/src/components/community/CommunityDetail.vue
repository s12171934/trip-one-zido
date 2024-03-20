<template>
  <main class="wrapper">
    <div class="inner">
      <h1>게시글 상세</h1>
      <br />
      <div class="d-grid gap-2 d-flex justify-content-end">
        <span class="button small rounded-3" id="mouseHover">{{
          detail.status
        }}</span>
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
          <tr class="border-bottom">
            <td id="tdTitle">제목 :</td>
            <td id="black">{{ detail.title }}</td>
            <td id="tdTitle" class="border-start">작성자 :</td>
            <td id="black">{{ detail.members[0].loginId }}</td>
          </tr>

          <tr class="border-bottom">
            <td>지역 :</td>
            <td>
              <option value="1" id="black">{{ detail.locCategory }}</option>
            </td>
            <td class="border-start">모집 인원 :</td>
            <td id="black">
              {{ detail.members.length }}/{{ detail.total }} 명
            </td>
          </tr>

          <tr class="border-bottom">
            <td>참여 인원 :</td>
            <td colspan="3" id="black">
              <span v-for="withMember in detail.members" class="me-2">{{
                withMember.loginId
              }}</span>
            </td>
          </tr>

          <tr class="border-bottom">
            <td>일정 :</td>
            <td id="black">{{ detail.startDate }} ~ {{ detail.endDate }}</td>
            <td class="border-start">모집 마감일 :</td>
            <td id="black">{{ detail.deadline }}</td>
          </tr>

          <tr class="border-bottom">
            <td>작성일 :</td>
            <td id="black">{{ detail.createdAt }}</td>
            <td class="border-start">조회수 :</td>
            <td id="black">{{ detail.viewPoint }}</td>
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
                >{{ detail.notice }} 
								</textarea
              >
            </td>
          </tr>
        </table>

        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
          <div v-if="loginId == detail.members[0].loginId">
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
          <div v-else-if="detail.members.length < detail.total">
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

export default {
  components: {
    AlertModal,
  },

  data() {
    return {
      // 상세 내용 담는 배열
      detail: {
        id: null,
        startDate: null,
        endDate: null,
        locCategory: null,
        notice: null,
        total: null,
        deadLine: null,
        viewPoint: null,
        status: null,
        title: null,
        createdAt: null,
        modifiedAt: null,
        members: [{
          loginId: null,
        }],
        mine: null,
        nextId: null,
        prevId: null,
      },
      modal: "",
      loginId: "",
      id: "",
      showSuccessModal: false,

      title: "", // 추가: 게시글 제목
      content: "", // 추가: 게시글 내용
    };
  },

  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.id = this.$route.params.id;
    this.$zido
      .getCommunityDetail(this.$route.params.id)
      .then((res) => {
        this.detail = res;
        this.loginId = res.loginId;
      });
  },

  methods: {
    goToPreviousPost() {
      location.href = `/community/${this.detail.prevId}`;
    },

    goToNextPost() {
      location.href = `/community/${this.detail.nextId}`;
    },

    update() {
      // 자기 자신이 버튼 누르면 수정완료
      this.$router.push({
        path: `/edit/community/${this.$route.params.id}`,
      });
    },

    async del() {
      this.modal = "deleteCommunity";
      await this.$zido.deleteCommunity(this.$route.params.id);
    },

    async participateOrCancel() {
      // 타인 자신이 참여/참여취소 버튼 누르면
      if (!(this.detail.members.find(member => member.loginId === this.loginId))) {
        await this.$zido.joinCommunity(this.$route.params.id);
        this.showSuccessModal = true;
        console.log(this.showSuccessModal);
        this.detail.members.push({ loginId: this.loginId });
      } else {
        await this.$zido.joinCancleCommunity(this.$route.params.id);
        this.showSuccessModal = false;
        console.log(this.showSuccessModal);
        this.detail.members.pop(this.loginId);
      }
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
