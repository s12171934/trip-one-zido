<template>
    <section class="wrapper">
<div class="inner">
	<h1>게시글 상세</h1>
	<br>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">   
		<span class="button small rounded-3" id="mouseHover">모집중</span>
		<a href="/community" class="button alt small rounded-3">목록</a>
		<!-- 현재 id-1 : 이전글 / 현재 id+1 : 다음글   -->
		<!-- <a href="/html-css/community/detail/detail.html" id="button2" class="button small rounded-3">이전글</a> -->
		<a @click="goToPreviousPost" class="button small rounded-3">이전글</a>
		<a @click="goToNextPost" class="button small rounded-3">다음글</a>
	</div>
	<br>
	<form method="get" action="#">
	<!-- 테이블 -->
	<table id="table" class="border" 
	
		v-for="detail in communityDetail" :key="detail.content_id">

		<tr>
			<td id="tdTitle"> 제목 : </td>
			<td id="black">{{ detail.title }}</td>
			<td id="tdTitle" class="border-start"> 작성자 : </td>
			<td id="black">{{ detail.login_id }}</td>
		</tr>
			
		<tr>
			<td> 지역 : </td>
			<td><option value="1" id="black">{{ detail.local }}</option></td>
			<td class="border-start"> 모집 인원 : </td>
			<td id="black">{{ detail.withCount }}/{{ detail.total }} 명</td>
		</tr>

		<tr>
			<td> 참여 인원 : </td>
			<td colspan="3" id="black"> {{ detail.with1 }} {{ detail.with2 }} {{ detail.with3 }} </td>
		</tr>

		<tr>
			<td>일정 : </td>
			<td id="black">{{ detail.start_date }} ~ {{ detail.end_date }}</td>
			<td class="border-start">모집 마감일 : </td>
			<td id="black">{{ detail.deadLine }}</td>
		</tr>

		<tr>
			<td>작성일 : </td>
			<td id="black">{{ detail.created_at }}</td>
			<td class="border-start">조회수 : </td>
			<td id="black">{{ detail.viewCount }}</td>
		</tr>

		<tr>						
			<td>내용</td>
			<td colspan="3">
				<textarea class="form-control" rows="5" cols="50" id="content" readonly> {{ detail.content }} </textarea>
			</td>
		</tr> 
	</table>

	<div class="d-grid gap-2 d-md-flex justify-content-md-center">   
		<a href="/html-css/community/board/community.html" id="button" class="button small rounded-3">참여 / 참여 취소</a>
	</div>
	</form>

	<div class="d-flex flex-row">
		<!-- 모달1 -->
		<div class="12u$">
			<ul class="actions">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
				참여하기 모달
			</button>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<!-- Vertically centered scrollable modal -->
				<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header border border-0">
						<!-- <h1 class="modal-title fs-5" id="exampleModalLabel">타이틀 없애고</h1> -->
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>

						<div class="m-5 modal-body border rounded-3" id="modalBox">
						<h3>참여신청이 </h3>
						<h3>완료되었습니다</h3>
						</div>

					</div>
				</div>
			</div>
			</ul>
		</div>

		<!-- 모달2 -->
		<div class="12u$">
			<ul class="actions">
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
				참여하기 취소 모달
			</button>

			<!-- Modal -->
			<div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<!-- Vertically centered scrollable modal -->
				<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header border border-0">
						<!-- <h1 class="modal-title fs-5" id="exampleModalLabel">타이틀 없애고</h1> -->
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>

						<div class="m-5 modal-body border rounded-3" id="modalBox">
						<h3>참여신청이 </h3>
						<h3>취소되었습니다</h3>
						</div>

					</div>
				</div>
			</div>
			</ul>
		</div>
	</div>
</div>
</section>
</template>

<script>

import data from "/src/assets/data.js";

export default {
	data() {
        return {
            communityDetail: [],
        };
    },

    created() {
        this.fetchCommunityDetail();
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
                console.error("content_id에 해당하는 게시물을 찾을 수 없습니다:", currentContentId);
            }
        },

		goToPreviousPost() {
            const currentContentId = this.$route.params.id;
            const currentIndex = data.communityDetail.findIndex(
                (post) => post.content_id == currentContentId
            );

            if (currentIndex > 0) {
                const previousPostId = data.communityDetail[currentIndex - 1].content_id;
                this.$router.push(`/community/${previousPostId}`);
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
                this.$router.push(`/community/${nextPostId}`);
            } else {
                console.log("다음 글이 없습니다.");
                // 다음 글이 없을 경우에 대한 처리 추가
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
    margin-right: auto; cursor: default;
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
    color: #929292;
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
#mouseHover, #button2 {
    color: rgb(255, 255, 255) !important; 
    background-color:#ff928e !important;
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
#content{
        resize: none;
}
</style>