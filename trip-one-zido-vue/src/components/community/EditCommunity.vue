<template>
	<main class="wrapper">
		<div class="inner">
			<h1>게시글 등록하기</h1>
			<br>
			<form method="post" action="/components/community">
				<!-- 테이블 -->
				<table id="table" class="border">
					<tr>
						<td id="tdTitle">지역</td>
						<td id="tdSelect">
							<div class="select-wrapper"  id="table-select">
								<select class="form-control" name="category">
									<option value="" selected>지역 선택</option>
									<option v-for="location in selectLocations" :value="location">
									<select v-model="locationSelect">{{ location }}</select>
									</option>
								</select>
							</div>
						</td>
						<td id="tdTitle" class="border-start">여행 일정</td>
						<td id="tdSelect"><input type="date" v-model="start_date"> ~ <input type="date" v-model="end_date"></td>
					</tr>

					<tr>
						<td>모집 인원</td>
						<td>
							<div class="select-wrapper" id="table-select">
								<select class="local-select" name="category">
									<option value="" selected>인원 선택</option>
									<option v-for="snop in selectNumberOfPeople" :value="snop">
									<select v-model="selectNumberOfPeople">{{ snop }}</select>
									</option>
								</select>
							</div>
						</td>
						<td class="border-start">모집 마감일 </td>
						<td><input type="date" v-model="date"></td>
					</tr>

					<tr>
						<td>제목</td>
						<td colspan="3"><input type="text" v-model="title" placeholder="제목을 입력해 주세요"></td>
					</tr>

					<tr>
						<td>내용</td>
						<td colspan="3">
							<textarea rows="13" class="form-control" id="content" v-model="content" placeholder="내용을 입력하세요"></textarea>
						</td>
					</tr>
				</table>

				<div class="d-grid gap-2 d-md-flex justify-content-md-end">
				<a 
				@click="addOrUpdate" 
				class="button small rounded-3"
				data-bs-toggle="modal"
				data-bs-target="#alertModal"
				>
				등록</a>
				<input class="button alt small rounded-3" type="button" value="취소"
                        onclick="location.href='/community'">
				</div>
     		 </form>
		
		</div>
	</main>
	<AlertModal modal="addCommunity" />

</template>
<script>
import AlertModal from "../util/modal/AlertModal.vue";
import data from "/src/assets/data.js";


let content_id = communityDetail.content_id;

export default {

	components : {
		AlertModal,
	},
		data() {
		return {
			modal: "",
			loginId: "",

			selectLocations: data.selectLocations,
			selectNumberOfPeople: data.selectNumberOfPeople,
			communityDetail: data.communityDetail,

			start_date: "",
			end_date: "",
			date: "",
			title: "",
			content: "",
		}
	},
	mounted() {
		this.$emit("meta", this.$route.matched[0].meta.isLogin);
	},

	methods: {


	}
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

textarea {
	resize: none;
}
</style>