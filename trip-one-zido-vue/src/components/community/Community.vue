<template>
  <main class="wrapper">
    <div id="leftPosition">
      <h1>커뮤니티(여행 멤버 모집)</h1>
      <br />
    </div>

    <div v-show="!searchStatus" class="table-wrapper" id="centerPosition">
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
            <td>{{ list.writer }}</td>
            <td>{{ list.deadline }}</td>
            <td>{{ list.viewPoint }}</td>
            <td>{{ list.withCount }} / {{ list.total }}</td>
            <td>{{ list.status }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div v-show="searchStatus" class="table-wrapper" id="centerPosition">
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
          <tr v-for="list in searchList" id="tdList">
            <td>{{ list.id }}</td>
            <td @click="goToCommunityDetail(list.id)" id="cursor">
              {{ list.title }}
            </td>
            <td>{{ list.writer }}</td>
            <td>{{ list.deadLine }}</td>
            <td>{{ list.viewPoint }}</td>
            <td>{{ list.withCount }} / {{ list.total }}</td>
            <td>{{ list.status }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <a @click="add" class="button small rounded-3">등록</a>
    </div>

    <nav v-show="!searchStatus" aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
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

    <nav v-show="searchStatus" aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
        <li :class="hasPrevSearchPage() ? 'page-item' : 'page-item disabled'">
          <a class="page-link" @click="moveToPrevSearchPage">Previous</a>
        </li>
        <li v-for="page in paginatedSearchPages" :key="page" :class="{'page-item': true, 'active': page === searchCurrentPage}">
          <a class="page-link" @click="searchPageMove(page)">{{ page }}</a>
        </li>
        <li :class="hasNextSearchPage() ? 'page-item' : 'page-item disabled'">
          <a class="page-link" @click="moveToNextSearchPage">Next</a>
        </li>
      </ul>
    </nav>

    <!-- 검색창 -->
    <div class="col-md-9 mb-3 mt-5" id="searchBar">
      <!-- <form action="#" method="POST" class="input-group"> -->
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
  paginatedSearchPages() {
    const searchTotalPages = Math.ceil(this.searchTotalCount / this.maxRecordsPerPage);
    const searchCurrentPageGroup = Math.ceil(this.searchCurrentPage / this.maxPageOnScreen);
    let searchStartPage = (searchCurrentPageGroup - 1) * this.maxPageOnScreen + 1;
    let searchEndPage = Math.min(searchStartPage + this.maxPageOnScreen - 1, searchTotalPages);
    
    // 현재 페이지 그룹이 마지막 페이지 그룹이 아닌 경우에만 이전 페이지 그룹을 표시함
    if (searchCurrentPageGroup > 1) {
      searchStartPage = (searchCurrentPageGroup - 1) * this.maxPageOnScreen + 1;
    }
      return Array(searchEndPage - searchStartPage + 1).fill().map((_, index) => searchStartPage + index);
    },
  },
  methods: {
    add() { this.$router.push("/add/community"); },
    goToCommunityDetail(id) { this.$router.push(`/community/${id}`); },
    //이전 페이지
    hasPrevPage() { return this.currentPage > 1;},
    hasPrevSearchPage() { return this.searchCurrentPage > 1;},
    //다음 페이지
    hasNextPage() { return this.currentPage < this.totalCount / 6;},
    hasNextSearchPage() { return this.searchCurrentPage < this.searchTotalCount / 6;},
    //이전 페이지그룹 이동
    moveToPrevPage() { if (this.hasPrevPage()) { this.pageMove(this.currentPage - 1)} },
    moveToPrevSearchPage() { 
      if (this.hasPrevSearchPage()) { this.searchPageMove(this.searchCurrentPage - 1)} },
    //다음 페이지그룹 이동
    moveToNextPage() {
      const totalPages = Math.ceil(this.totalCount / this.maxRecordsPerPage);
      const currentPageGroup = Math.ceil(this.currentPage / this.maxPageOnScreen);
      const nextPageGroupStart = (currentPageGroup * this.maxPageOnScreen) + 1;
      if (nextPageGroupStart <= totalPages) {
        this.pageMove(nextPageGroupStart);
      }
    },
    moveToNextSearchPage(){
      const totalPages = Math.ceil(this.searchTotalCount / this.maxRecordsPerPage);
      const currentPageGroup = Math.ceil(this.currentPage / this.maxPageOnScreen);
      const nextPageGroupStart = (currentPageGroup * this.maxPageOnScreen) + 1;
      if (nextPageGroupStart <= totalPages) {
        this.searchPageMove(nextPageGroupStart);
      }
    },
    //번호로 이동
    pageMove(page) {
      const targetPage = page - 1; // 요청할 페이지 번호 계산
      this.currentPage = page; // 현재 페이지 번호 저장
      this.$zido.getCommunityList(targetPage).then((res) => {
        this.communityList = res.communityList;
      });
    },
    searchPageMove(page){
      const targetPage = page - 1; // 요청할 페이지 번호 계산
      this.searchCurrentPage = page;
      this.$zido.searchCommunity(this.form,targetPage).then((res) => {
        this.searchList = res.communityList;
        this.searchTotalCount = res.totalCount;
      });
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
          this.$zido.searchCommunity(this.form, this.currentPage).then((res) => {
            this.searchList = res.communityList;
            this.searchTotalCount = res.totalCount
            this.searchCurrentPage = 1;
            this.searchStatus = true;
        });
      } 
    }
  },
  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido
      // .getCommunityList(this.$route.query)
      .getCommunityList(0)
      .then((res) => {
        this.communityList = res.communityList;
        this.totalCount = res.totalCount;
    });
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
thead,
thead hd {
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
  color: #929292 !important;
}
#black {
  color: black !important;
}
table tbody tr {
  background-color: white !important;
}
#more {
  color: black !important;
}
.page-link {
  color: black !important;
}
.select-wrapper {
  width: 15%;
  font-size: 15px;
}
.select-wrapper #category {
  height: 100%;
  justify-content: right;
}
#cursor {
  cursor: pointer;
}

@media screen and (max-width: 800px) {
  
  .table-wrapper, #leftPosition {
    white-space: nowrap; 
    overflow: hidden;
    text-overflow: ellipsis; 
  }

  
}
</style>
