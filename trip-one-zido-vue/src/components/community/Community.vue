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

                    <tr v-for="(row, idx) in list" :key="idx">
                        <td>{{ row.id }}</td>
                        <td>{{ row.title }}</td>
                        <td>{{ row.login_id }}</td>
                        <td>{{ row.deadline }}</td>
                        <td>{{ row.total }}</td>
                        <td>{{ row.status }}</td>
                    </tr>

                    <tr>
                        <td>2</td>
                        <td>부산 가실분</td>
                        <td>minsun</td>
                        <td>24/02/14</td>
                        <td>5</td>
                        <td>2/4</td>
                        <td>모집</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>서울이 처음인데 같이 남산 가실분!!</td>
                        <td>hyuno</td>
                        <td>24/02/13</td>
                        <td>6</td>
                        <td>4/5</td>
                        <td>모집</td>
                    </tr>
                    <tr>
                        <td>4</td>
                        <td>안녕하세요 제주도에 놀만한 곳</td>
                        <td>sungsil</td>
                        <td>24/02/11</td>
                        <td>6</td>
                        <td>4/4</td>
                        <td>마감</td>
                    </tr>

                </tbody>
            </table>
        </div>

        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <a href="/community/EditCommunity" id="button" class="button small rounded-3">등록</a>
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
export default {

    // 1. 페이지 네이션
    data () {
        return {
            posts: [], // 게시글 데이터
            currentPage: 1, // 현재 페이지 번호
            postsPerPage: 10, // 한 페이지에 보여줄 게시글의 수
        };
    },

    computed: {
        displayedPosts() {
            // 현재 페이지에 해당하는 게시글 목록을 반환하는 computed 속성
            const startIndex = (this.currentPage -1) * this.postsPerPage;
            const endIndex = startIndex + this.postsPerPage;
            return this.posts.slice (startIndex, endIndex)
        }    
    }
    // mode : add id 필요 x
    // mode : update id 필요 o


}
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
    width: 10%;
    font-size: 15px;
}
.select-wrapper #category {
    height: 100%;
    justify-content: right;
    
}
</style>