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

    <nav aria-label="Page navigation example">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <a class="page-link">Previous</a>
        </li>
        <li class="page-item">
          <a class="page-link" :href="`/community?page=${1}`">1</a>
        </li>
        <li class="page-item">
          <a class="page-link" href="#">Next</a>
        </li>
      </ul>
    </nav>

    <!-- 검색창 -->
    <div class="col-md-9 mb-3 mt-5" id="searchBar">
      <form action="#" method="GET" class="input-group">
        <div class="select-wrapper">
          <select class="local-select" v-model="type" id="category">
            <option value="" selected>카테고리</option>
            <option value="1">제목</option>
            <option value="2">작성자</option>
          </select>
        </div>
        <input
          v-model="keyword"
          type="text"
          class="form-control"
          placeholder="검색어를 입력해주세요"
          aria-label="Recipient's username"
          aria-describedby="button-addon2"
        />
        <button class="button alt" type="submit" id="more">검색</button>
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
        deadLine: null,
        viewPoint: null,
        total: null,
        withCount: null,
        status: null,
      }],
      type: "",
      keyword: "",
    };
  },

  methods: {
    add() {
      this.$router.push("/add/community");
    },
    goToCommunityDetail(id) {
      this.$router.push(`/community/${id}`);
    },
  },

  mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    this.$zido
      .getCommunityList(this.$route.query)
      .then((res) => (this.communityList = res));
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
