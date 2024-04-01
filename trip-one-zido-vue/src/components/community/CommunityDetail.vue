<template>
  <main class="wrapper">
    <div class="inner">
      <h1>게시글 상세</h1>
      <br />
      <div class="d-grid gap-2 d-flex justify-content-end">
        <span class="button small rounded-3" id="mouseHover">
          {{ detail.status == 0 ? "모집중" : "마감" }}
        </span>
        <a href="/community" class="button alt small rounded-3">목록</a>
        <a @click="goToPreviousPost"
          :class="detail.prevId != 0 
            ? 'button small rounded-3' 
            : 'button small rounded-3 disabled'"
          id="button-high">이전글
        </a>
        <a @click="goToNextPost"
          :class="detail.nextId != 0 
            ? 'button small rounded-3' 
            : 'button small rounded-3 disabled'"
          id="button-high">다음글
        </a>
      </div>
      <br />

      <form method="get" action="#">
        <!-- 테이블 -->
        <table id="table" class="border">
          <tr class="border-bottom">
            <td id="tdTitle">제목 :</td>
            <td id="black">{{ detail.title }}</td>
            <td id="tdTitle" class="border-start">작성자 :</td>
            <td >{{ detail.members[0].loginId }}</td>
          </tr>

          <tr class="border-bottom">
            <td>지역 :</td>
            <td id="black">
              <option value="1">
                {{ selectedCategory }}
              </option>
            </td>
            <td class="border-start">모집 인원 :</td>
            <td >
              {{ detail.members.length }}/{{ detail.total }} 명
            </td>
          </tr>

          <tr class="border-bottom">
            <td>참여 인원 :</td>
            <td colspan="3">
              <span v-for="withMember in detail.members" class="me-2">{{
                withMember.loginId
              }}</span>
            </td>
          </tr>

          <tr class="border-bottom">
            <td>일정 :</td>
            <td id="black">{{ detail.startDate }} ~ {{ detail.endDate }}</td>
            <td class="border-start">모집 마감일 :</td>
            <td>{{ detail.deadline }}</td>
          </tr>

          <tr class="border-bottom">
            <td>작성일 :</td>
            <td id="black">{{ detail.createdAt }}</td>
            <td class="border-start">조회수 :</td>
            <td>{{ detail.viewPoint }}</td>
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
import data from "/src/assets/data.js";

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
        deadline: null,
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
      locations: data.selectLocations,

      title: "", // 추가: 게시글 제목
      content: "", // 추가: 게시글 내용
    };
  },

  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.id = this.$route.params.id;
    //커뮤니티 상세 조회 GET -- api/community/id
    this.$zido
      .getCommunityDetail(this.$route.params.id)
      .then((res) => {
        this.detail = res;
        this.loginId = res.loginId;
      });
  },

  computed: {
    selectedCategory() {
      const foundCategory = this.locations.find(item => item.locCategory === this.detail.locCategory);
      return foundCategory ? foundCategory.value : this.detail.locCategory; 
    }
  },

  methods: {
    goToPreviousPost() {
      if(this.detail.prevId !== 0){
        location.href = `/community/${this.detail.prevId}`;
      }
    },

    goToNextPost() {
      if(this.detail.nextId !== 0){
        location.href = `/community/${this.detail.nextId}`;
      }
    },

    update() {
      // 자기 자신이 버튼 누르면 수정완료
      this.$router.push({
        path: `/edit/community/${this.$route.params.id}`,
      });
    },

    async del() {
      this.modal = "deleteCommunity";
      //커뮤니티 삭제 DELETE -- api/community/id
      await this.$zido.deleteCommunity(this.$route.params.id);
    },

    async participateOrCancel() {
      // 타인 자신이 참여/참여취소 버튼 누르면
      if (!(this.detail.members.find(member => member.loginId === this.loginId))) {
        //커뮤니티 참여 POST -- api/community/member/id
        await this.$zido.joinCommunity(this.$route.params.id);
        this.showSuccessModal = true;
        console.log(this.showSuccessModal);
        this.detail.members.push({ loginId: this.loginId });
      } else {
        //커뮤니티 참여 취소 DELETE -- api/community/member/id
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
/* 4행 설정 */
#tdTitle {
  width: 15%;
}
/* 모집중 왼쪽정렬 */
#mouseHover {
  margin-right: auto;
  cursor: default;
}
/* 참여/취소버튼 폰트 크기 */
#button {
  font-size: 15px;
}
/* 테이블 폰트 크기 */
#table {
  font-size: 15px !important;
}
/* 테이블 왼쪽 크기 */
#black {
  width: 35%;
}
/* 내용칸 설정 */
#content {
  resize: none;
}

@media (max-width: 990px) {
  #table {
  font-size: 12px !important;
  }
}

@media (max-width: 950px) {
  #table {
  font-size: 10px !important;
  }
}

@media (max-width: 800px) {
  #table {
  font-size: 9px !important;
  }
}

/* 작은 화면에서 테이블의 열이 쌓이도록 설정 */
@media (max-width: 790px) {
  #table {
  font-size: 15px !important;
  }
  td {
    display: block !important;
    width: 100% !important;
  }

  tr {
    display: block !important;
    margin-bottom: 10px !important;
  }

  .d-grid {
    font-size: 12px !important;
    padding: 5px !important;
  }

  #black {
    border-bottom: 1px solid #ddd;
  }
  td.border-start {
    border-inline: none !important;
  }
}

@media (max-width: 768px) {
  #button {
    display: flex;
    justify-content: center;
    margin-left: 30%;
    margin-right: 30%;
  }
}

@media (max-width: 550px) {
  #button-high, #mouse-hover {
    display: block;
    width: 20%;
    padding: 0;
  }
  #button {
    font-size: 11px;
  }
}

@media (max-width: 380px) {
  .d-grid {
    font-size: 10px !important;
    padding: 0px !important;
  }
}

</style>
