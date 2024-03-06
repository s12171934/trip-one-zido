<template>
    <section class="wrapper">
<div class="inner">
	<h1>게시글 상세</h1>
	<br>
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">   
		<span class="button small rounded-3" id="mouseHover">모집중</span>
		<a href="/html-css/community/board/community.html" class="button alt small rounded-3">목록</a>
		<a href="/html-css/community/detail/detail.html" id="button2" class="button small rounded-3">이전글</a>
		<a href="/html-css/community/detail/detailEnd.html" id="button2" class="button small rounded-3">다음글</a>
	</div>
	<br>
	<form method="post" action="#">
	<!-- 테이블 -->
	<table id="table" class="border">
	<tr>
		<td id="tdTitle"> 제목 : </td>
		<td id="black">부산 가실분?</td>
		<td id="tdTitle" class="border-start"> 작성자 : </td>
		<td id="black">윤지수</td>
	</tr>
		
	<tr>
		<td> 지역 : </td>
		<td><option value="1" id="black">부산광역시</option></td>
		<td class="border-start"> 모집 인원 : </td>
		<td id="black">3/4 명</td>
	</tr>

	<tr>
		<td> 참여 인원 : </td>
		<td colspan="3" id="black">윤지수 , 문성기 , 한희주 </td>
	</tr>

	<tr>
		<td>일정 : </td>
		<td id="black">2024-02-07 ~ 2020-02-14</td>
		<td class="border-start">모집 마감일 : </td>
		<td id="black">2024-02-05</td>
	</tr>

	<tr>
		<td>작성일 : </td>
		<td id="black">2024-02-01</td>
		<td class="border-start">조회수 : </td>
		<td id="black">5</td>
	</tr>

	<tr>						
		<td>내용</td>
		<td colspan="3">
			<textarea class="form-control" id="content"> 부산여행 갑시다! </textarea>
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
export default {

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
</style>