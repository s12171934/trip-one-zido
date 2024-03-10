<template>
      <section class="wrapper">
        <div id="leftPosition">
            <h1>커뮤니티(여행 멤버 모집)</h1>
            <br>
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
                    <!-- location.href="/community/"`${this.list.id}` -->
                    <tr v-for="list in communityList" :value="list" id="tdList">
                        <td>{{ list.content_id }}</td>
                        <td @click="goToCommunityDetail(list.content_id)" id="cursor">{{ list.title }}</td>
                        <td>{{ list.login_id }}</td>
                        <td>{{ list.deadLine }}</td>
                        <td>{{ list.viewCount }}</td>
                        <td>{{ list.withCount }} / {{ list.total }}</td>
                        <td>{{ list.status }}</td>
                        <!-- <td><a v-on:click="`/community/${list.content_id}`">{{ list.title }}</a></td> -->
                    </tr>

                </tbody>
            </table>
        </div>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="/:mode/community/id?" class="button small rounded-3" id="button">등록</a>
        </div>

        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item disabled">
                    <a class="page-link">Previous</a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">4</a></li>
                <li class="page-item"><a class="page-link" href="#">5</a></li>
                <li class="page-item"><a class="page-link" href="#">6</a></li>
                <li class="page-item"><a class="page-link" href="#">7</a></li>
                <li class="page-item"><a class="page-link" href="#">8</a></li>
                <li class="page-item"><a class="page-link" href="#">9</a></li>
                <li class="page-item"><a class="page-link" href="#">10</a></li>
                <li class="page-item">
                    <a class="page-link" href="#">Next</a>
                </li>
            </ul>
        </nav>

        <!-- 검색창 -->
        <div class="col-md-9 mb-3 mt-5" id="searchBar">
            <form action="#" method="GET" class="input-group">
                <div class="select-wrapper">
                    <select class="local-select" name="category" id="category">
                        <option value="" selected>카테고리</option>
                        <option value="1">제목</option>
                        <option value="2">작성자</option>
                    </select>
                </div>
                <input name="q" type="text" class="form-control" placeholder="검색어를 입력해주세요"
                    aria-label="Recipient's username" aria-describedby="button-addon2">
                <button class="button alt" type="submit" id="more">검색</button>
            </form>
            <br><br><br>
        </div>
    </section>
</template>

<script>

import data from "/src/assets/data.js";

export default {

    data() {
    return {
        communityList: data.communityList,
        }
    },
    
  methods : {

    status() {
        if(this.communityList.with == this.communityList.total) {
            this.communityList.status = "마감"
        } else {
            this.communityList.status = "모집"
        }
    },
    
    goToCommunityDetail(id) {
        this.currentContentId = id;
        this.$router.push({ path: `/community/${id}` })
      },
  }
}
    // // 1. 페이지 네이션
    // data () {
    //     return {
    //         posts: [], // 게시글 데이터
    //         currentPage: 1, // 현재 페이지 번호
    //         postsPerPage: 10, // 한 페이지에 보여줄 게시글의 수
    //     };
    // },

    // computed: {
    //     displayedPosts() {
    //         // 현재 페이지에 해당하는 게시글 목록을 반환하는 computed 속성
    //         const startIndex = (this.currentPage -1) * this.postsPerPage;
    //         const endIndex = startIndex + this.postsPerPage;
    //         return this.posts.slice (startIndex, endIndex)
    //     }    
    // },

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
    font-family: 'Jalnan';
    font-size: 10px;
}
h1, h2, h3, h4, body, .button, section {
    font-family: 'Jalnan';
}
h1 {
    color: #ff928e !important;
}
#menu {
    font-family: 'Jalnan';
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
    background-color:#ff928e !important;
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
</style>