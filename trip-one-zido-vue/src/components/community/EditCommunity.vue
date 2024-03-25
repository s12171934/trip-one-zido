<template>
  <main class="wrapper">
    <div class="inner">
      <h1>게시글 {{ $route.params.mode == "add" ? "등록" : "수정" }}하기</h1>
      <br />
      <form>
        <!-- 테이블 -->
        <table id="table" class="border">
          <tr class="border-bottom">
            <td id="tdTitle">지역</td>
            <td id="tdSelect">
              <div class="select-wrapper" id="table-select">
                <select class="form-control" v-model="communityData.locCategory">
                  <option value="" selected>지역 선택</option>
                  <option v-for="location in selectLocations" :value="location.locCategory">
                    {{ location.value }}
                  </option>
                </select>
              </div>
            </td>
            <td id="tdTitle" class="border-start">여행 일정</td>
            <td>
              <input type="date" v-model="communityData.startDate" /> ~
              <input type="date" v-model="communityData.endDate" />
            </td>
          </tr>

          <tr class="border-bottom">
            <td>모집 인원</td>
            <td id="tdSelect">
              <div class="select-wrapper" id="table-select">
                <select class="local-select" v-model="communityData.total">
                  <option value="" selected>인원 선택</option>
                  <option v-for="snop in 20" :value="snop">
                    {{ snop }}&nbsp;명
                  </option>
                </select>
              </div>
            </td>
            <td class="border-start">모집 마감일</td>
            <td><input type="date" v-model="communityData.deadline" /></td>
          </tr>

          <tr class="border-bottom">
            <td>제목</td>
            <td colspan="3">
              <input
                type="text"
                v-model="communityData.title"
                placeholder="제목을 입력해 주세요"
              />
            </td>
          </tr>

          <tr>
            <td>내용</td>
            <td colspan="3">
              <textarea
                rows="13"
                class="form-control"
                id="content"
                v-model="communityData.notice"
                placeholder="내용을 입력하세요"
              ></textarea>
            </td>
          </tr>
        </table>

        <div class="d-grid gap-2 d-flex justify-content-end">
          <a
            @click="submitButton($route.params.mode)"
            class="button small rounded-3"
            data-bs-toggle="modal"
            data-bs-target="#alertModal"
            id="button-css"
          >
            {{ $route.params.mode == 'add' ? '등록' : '수정' }}</a
          >
          <input
            @click="$router.push('/community')"
            class="button alt small rounded-3"
            type="button"
            id="button-css"
            value="취소"
          />
        </div>
      </form>
    </div>
  </main>
  <AlertModal :modal="$route.params.mode == 'add' ? 'addCommunity' : 'updateCommunity'" />
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
      selectLocations: data.selectLocations,
      selectNumberOfPeople: data.selectNumberOfPeople,
      communityDetail: data.communityDetail,

      communityData: {
				locCategory: 0,
				startDate: "",
				endDate: "",
				total: "",
				deadline: "",
				title: "",
				notice: "",
			}
    };
  },
	methods: {
		submitButton(mode){
			if(mode === 'add'){
        this.communityData.startDate = new Date(this.communityData.startDate);
        this.communityData.endDate = new Date(this.communityData.endDate);
        this.communityData.deadline = new Date(this.communityData.deadline);
				this.$zido.addCommunity(this.communityData)
			}
			else{
        this.communityData.startDate = new Date(this.communityData.startDate);
        this.communityData.endDate = new Date(this.communityData.endDate);
        this.communityData.deadline = new Date(this.communityData.deadline);
				this.$zido.updateCommunity(this.$route.params.id, this.communityData)
			}
			this.$router.push('/community')
		},
		setCommunityData(){
			if(this.$route.params.mode != 'add'){
				this.$zido.getCommunityDetail(this.$route.params.id).then((res) => this.communityData = res)
			}
		}
	},
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
		this.setCommunityData();
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

#tdSelect {
  width: 35%;
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
  color: black;
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
#table {
  font-size: 15px !important;
  color: black !important;
}
input {
  color: black !important;
}
table tbody tr {
  background-color: white !important;
}

#table-select {
  width: 80%;
}

textarea {
  resize: none;
}
/* 작은 화면에서 테이블의 열이 쌓이도록 설정 */
@media (max-width: 768px) {
  td {
    display: block !important;
    width: 100% !important;
  }
  tr {
    display: block !important;
    margin-bottom: 10px !important;
  }

  #tdSelect {
    border-bottom: 1px solid #ddd;
  }
  td.border-start {
    border-inline: none !important;
  }
}

@media screen and (max-width: 1300px) {
  .inner {
    max-width: 100% !important;
  }
}

@media screen and (max-width: 400px) {
  .button {
    width: 30%;
    padding: 0;
  }
}
</style>
