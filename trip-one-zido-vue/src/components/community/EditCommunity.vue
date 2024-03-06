<template>
	<section class="wrapper">
		<div class="inner">
			<h1>게시글 등록하기</h1>
			<br>
			<form method="post" action="#">
				<!-- 테이블 -->
				<table id="table" class="border">
					<tr>
						<td id="tdTitle">지역</td>
						<td id="tdSelect">
							<div class="select-wrapper"  id="table-select">
								<select class="local-select" name="category">
									<option value="" selected>지역 선택</option>
									<option value="1">서울특별시</option>
									<option value="2">부산광역시</option>
									<option value="3">대구광역시</option>
									<option value="4">인천광역시</option>
									<option value="5">광주광역시</option>
									<option value="6">대전광역시</option>
									<option value="7">울산광역시</option>
									<option value="8">세종특별자치시</option>
									<option value="9">경기도</option>
									<option value="10">강원특별자치도</option>
									<option value="11">충청북도</option>
									<option value="12">충청남도</option>
									<option value="13">전북특별자치도</option>
									<option value="14">전라남도</option>
									<option value="15">경상북도</option>
									<option value="16">경상남도</option>
									<option value="17">제주특별자치도</option>
								</select>
							</div>
						</td>
						<td id="tdTitle" class="border-start">여행 일정</td>
						<td id="tdSelect"><input type="date"> ~ <input type="date"></td>
					</tr>

					<tr>
						<td>모집 인원</td>
						<td>
							<div class="select-wrapper" id="table-select">
								<select class="local-select" name="category">
									<option value="" selected>인원 선택</option>
									<option value="2">2 명</option>
									<option value="3">3 명</option>
									<option value="4">4 명</option>
									<option value="5">5 명</option>
									<option value="6">6 명</option>
									<option value="7">7 명</option>
									<option value="8">8 명</option>
									<option value="9">9 명</option>
									<option value="10">10 명</option>
									<option value="11">11 명</option>
									<option value="12">12 명</option>
									<option value="13">13 명</option>
									<option value="14">14 명</option>
									<option value="15">15 명</option>
									<option value="16">16 명</option>
									<option value="17">17 명</option>
									<option value="18">18 명</option>
									<option value="19">19 명</option>
									<option value="20">20 명</option>
								</select>
							</div>
						</td>
						<td class="border-start">모집 마감일 </td>
						<td><input type="date"></td>
					</tr>

					<tr>
						<td>제목</td>
						<td colspan="3"><input type="text" name="text" placeholder="제목을 입력해 주세요"></td>
					</tr>

					<tr>
						<td>내용</td>
						<td colspan="3">
							<textarea rows="13" class="form-control" id="content" placeholder="내용을 입력하세요"></textarea>
						</td>
					</tr>
				</table>

				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
					<a href="/community" id="button" class="button small rounded-3">등록</a>
					<a href="/community" id="button" class="button small rounded-3">취소</a>
				</div>
			</form>

			<!-- 모달1 -->
			<div class="p-1 12u$">
				<ul class="actions">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-secondary" data-bs-toggle="modal"
						data-bs-target="#exampleModal">
						게시글등록 모달
					</button>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<!-- Vertically centered scrollable modal -->
						<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
							<div class="modal-content">
								<div class="modal-header border border-0">
									<!-- <h1 class="modal-title fs-5" id="exampleModalLabel">타이틀 없애고</h1> -->
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>

								<div class="m-5 modal-body border rounded-3" id="modalBox">
									<h3>게시물 등록이</h3>
									<h3>완료되었습니다</h3>
								</div>

								<div class="modal-footer border border-0">
									<!-- <button type="button" class="btn btn-secondary" >로그인하러가기</button> -->
									<button type="button" class="rounded-3" id="modalButton"
										onclick="location.href='/html-css/community/board/community.html'">목록가기</button>
								</div>
							</div>
						</div>
					</div>
				</ul>
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

#tdSelect {
    width: 35%;
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
    color: black !important;
}
input {
    color: black !important;
}
table tbody tr {
    background-color: white !important;
} 

#table-select {
	width: 80%
}
</style>