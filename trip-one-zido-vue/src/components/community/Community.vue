<template>
  <main class="wrapper">
    <div id="leftPosition">
      <h1>커뮤니티(여행 멤버 모집)</h1>
      <br />
    </div>

    <div class="table-wrapper" id="centerPosition">
      <table>
        <thead>
          <tr>
            <td>No.</td>
            <td>제목</td>
            <td>아이디</td>
            <td>마감날짜</td>
            <td>조회</td>
            <td>모집</td>
            <td>상태</td>
          </tr>
        </thead>
        <tbody>
          <tr v-for="list in communityList" id="tdList">
            <td>{{ list.id }}</td>
            <td @click="goToCommunityDetail(list.id)" id="cursor">
              {{ list.title }}
            </td>
            <td>
              <router-link :to="`/member-page/${list.writerId}`" id="noUnderLine">
                {{ list.writer }}
              </router-link>
            </td>
            <td>{{ list.deadline }}</td>
            <td>{{ list.viewPoint }}</td>
            <td>{{ list.withCount }} / {{ list.total }}</td>
            <td>{{ list.status == 0 ? "모집중" : "마감" }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="d-grid gap-2 justify-content-md-end">
      <a @click="add" class="button small rounded-3" id="button-add">등록</a>
    </div>
    
    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center" id="pagination">
        <li :class="hasPrevPage() ? 'page-item' : 'page-item disabled'">
          <a class="page-link" @click="moveToPrevPage">Previous</a>
        </li>
        <li v-for="page in paginatedPages" :key="page" :class="{'page-item': true, 'active': page === currentPage}">
          <a class="page-link" @click="pageMove(page)">{{ page }}</a>
        </li>
        <li :class="hasNextPage() ? 'page-item' : 'page-item disabled'">
          <a class="page-link" @click="moveToNextPage">Next</a>
        </li>
      </ul>
    </nav>

    <!-- 검색창 -->
    <div class="col-md-9 mb-3 mt-5" id="searchBar">
        <form @submit.prevent class="input-group">
        <div class="select-wrapper">
          <select class="local-select" v-model="form.type" id="category">
            <option value="" selected>카테고리</option>
            <option value="1">제목</option>
            <option value="2">작성자</option>
          </select>
        </div>
        <input
          v-model="form.keyword"
          type="text"
          class="form-control"
          placeholder="검색어를 입력해주세요"
          aria-label="Recipient's username"
          aria-describedby="button-addon2"
        />
        <button 
          @click="communitySearch" 
          class="button alt" 
          type="submit" 
          id="more"
        >
        검색</button>
      </form>
    </div>
  </main>
</template>

<script>
export default {
  data() {
    return {
      communityList: [{
        id: 0,
        title: null,
        writerId: null,
        writer: null,
        deadline: null,
        viewPoint: null,
        total: null,
        withCount: null,
        status: null,
      }],
      searchList: [],
      form: {
        type: "",
        keyword: "",
      },
      totalCount: 0,
      searchTotalCount: 0,
      currentPage: 1,
      searchCurrentPage: 1,

      startPageOnScreen: 1,
      maxPageOnScreen: 5,
      maxRecordsPerPage: 6,
      searchStatus: false,
    };
  },
  computed: {
  paginatedPages() {
    const totalPages = Math.ceil(this.totalCount / this.maxRecordsPerPage);
    const currentPageGroup = Math.ceil(this.currentPage / this.maxPageOnScreen);
    let startPage = (currentPageGroup - 1) * this.maxPageOnScreen + 1;
    let endPage = Math.min(startPage + this.maxPageOnScreen - 1, totalPages);
    // 현재 페이지 그룹이 마지막 페이지 그룹이 아닌 경우에만 이전 페이지 그룹을 표시함
    if (currentPageGroup > 1) {
        startPage = (currentPageGroup - 1) * this.maxPageOnScreen + 1;
      }
      return Array(endPage - startPage + 1).fill().map((_, index) => startPage + index);
    },
  },
  methods: {
    add() { 
      this.$router.push("/add/community"); 
    },
    goToCommunityDetail(id) { 
      this.$router.push(`/community/${id}`); 
    },
    hasPrevPage() { 
      return this.currentPage > 1;
    },
    hasNextPage() { 
      return this.currentPage < this.totalCount / 6;
    },
    moveToPrevPage() { 
      if (this.hasPrevPage()) { 
        this.pageMove(this.currentPage - 1)
      } 
    },
    moveToNextPage() {
      if(this.hasNextPage()){
        this.pageMove(this.currentPage + 1)
      }
    },
    //다음 페이지그룹(5개씩 한 그룹) 이동 (추후에 그룹단위 이동이 필요할 경우를 위해 주석)
    // moveToNextPage() {
    //   const totalPages = Math.ceil(this.totalCount / this.maxRecordsPerPage);
    //   const currentPageGroup = Math.ceil(this.currentPage / this.maxPageOnScreen);
    //   const nextPageGroupStart = (currentPageGroup * this.maxPageOnScreen) + 1;
    //   if (nextPageGroupStart <= totalPages) {
    //     this.pageMove(nextPageGroupStart);
    //   } 
    // },
    pageMove(page) {
      const targetPage = page - 1; // 요청할 페이지 번호 계산
      this.currentPage = page; // 현재 페이지 번호 저장
      if(this.searchStatus){
        this.$zido.searchCommunity(this.form,targetPage).then((res) => {
        this.communityList = res.communityList;
        this.totalCount = res.totalCount;
        })
      } else {
        this.$zido.getCommunityList(targetPage).then((res) => {
        this.communityList = res.communityList;
        this.totalCount = res.totalCount;
        })
      };
    },
    communitySearch() {
      //카테고리일 경우 전체 검색으로 리셋
      if(this.form.type === ""){
        this.$zido.getCommunityList(0).then((res) => {
          this.communityList = res.communityList;
          this.currentPage = 1;
          this.searchStatus = false;
        });
      }
      //타입있고 키워드도 있는 경우
      if (this.form.type !== "" && this.form.keyword !== "") {
          this.currentPage = 0
          //커뮤니티 검색 조회 GET -- api/community/search/page
          this.$zido.searchCommunity(this.form, this.currentPage).then((res) => {
            this.communityList = res.communityList;
            this.totalCount = res.totalCount;
            this.currentPage = 1;
            this.searchStatus = true;
        });
      } 
    }
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido
      // .getCommunityList(this.$route.query)
      //커뮤니티 목록 조회 GET -- api/community/list/page
      .getCommunityList(0)
      .then((res) => {
        this.communityList = res.communityList;
        this.totalCount = res.totalCount;
    });
  },
};
</script>

<style scoped>
/* 테이블 글꼴 가운데 정렬 */
#centerPosition {
  text-align: center;
}
/* 페이지네이션 폰트 색상 */
.page-link {
  color: black !important;
  text-decoration: none;
}
/* 테이블 배경 */
table tbody tr {
  background-color: white !important;
}
/* 테이블 머리글 설정 */
thead tr {
  font-size: large;
}
/* 검색바 가운데 정렬 */
#searchBar {
  margin: auto;
}
/* 카테고리 박스 */
.select-wrapper {
  width: 15%;
  font-size: 15px;
}
.select-wrapper #category {
  height: 100%;
  justify-content: right;
}
/* 아이디 밑줄 제거 */
#noUnderLine {
  text-decoration: none;
  color: black;
}

#cursor {
  cursor: pointer;
}

@media (max-width: 860px) {
    /* 테이블 머리글 폰트 사이즈 */
    thead tr {
    font-size: medium;
  }
}

@media (max-width: 840px) {
  /* 전체적인 요소들의 크기와 글꼴 크기를 작게 조정 */
  #leftPosition h1 {
    font-size: 20px !important;
  }

  .table-wrapper table {
    font-size: 10px !important;
  }

  .button.small {
    margin-top: 2% !important;
  }

  #searchBar select,
  #searchBar input[type="text"],
  #searchBar button {
    font-size: 10px !important;
  }
}

@media (max-width: 768px) {
  #button-add {
    width: 15%;
    margin-left: auto;
    padding: 0;
    margin-bottom: 5%;
  }
}

@media (max-width: 610px) {
    /* 테이블 머리글 설정 */
    thead tr {
    font-size: small;
  }
  #button-add {
    margin-left: auto;
    padding: 0%;
  }

  .select-wrapper {
    width: 27%;
  }
}

@media screen and (max-width: 580px) {
  #leftPosition h1 {
    font-size: 12px !important;
  }

  .table-wrapper table {
    font-size: 6px !important;
  }

  .button.small {
    font-size: 10px;
  }
  #searchBar select,
  #searchBar input[type="text"],
  #searchBar button {
    font-size: 4px !important;
  }
  
  .table-wrapper, #leftPosition {
    white-space: nowrap; 
    /* overflow: hidden; */
    text-overflow: ellipsis; 
  }

    /* 페이지네이션 요소들의 크기를 작게 조정 */
    .pagination .page-item .page-link {
    font-size: 10px !important;
    padding: 2px 4px !important;
  }
}
</style>
