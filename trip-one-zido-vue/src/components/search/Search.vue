<template>
  <section id="main" class="wrapper">
		<div id="searchBar">
			<form action="#" method="GET" class="input-group">
				<input name="q" type="text" class="form-control" placeholder="검색어를 입력해주세요"
					aria-label="Recipient's username" aria-describedby="button-addon2">
				<button class="button alt" type="submit" id="more">검색</button>
				<button class="button alt" type="button" id="more" @click="this.$router.push('/search/detail')">일정 & 장소 상세검색으로</button>
			</form>
		</div>
		<div id="mainTitle">
			<div class="d-flex flex-row mb-6" id="search">
				<!-- 검색어 -->
				<h1> 12 건 </h1>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<h1 class="title">의 검색 결과</h1>
				<br>
			</div>
		</div>
		<br><br><br>
		<div id="searchTitle">
			<div class="d-flex flex-row mb-6">
				<h3 id="searchResult">계정</h3>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<h5>2</h5>
			</div>
		</div>
		<div class="d-flex flex-row mb-6" id="searchUser">
			<div class="p-2">
				<span class="image round"><a href="/html-css/main/detail/detail_other.html"><img id="userPic"
							src="/images/유재석.png" alt=""></a></span>
				<p id="listTitle">유재석</p>
			</div>
			<div class="p-2">
				<span class="image round"><img id="userPic" src="/images/조세호.png" alt=""></span>
				<p id="listTitle">조세호</p>
			</div>
			<div class="p-2">
				<span class="image round"><img id="userPic" src="/images/남자.png" alt=""></span>
				<p id="listTitle">조세호</p>
			</div>
			<div class="p-2">
				<span class="image round"><img id="userPic" src="/images/여자.png" alt=""></span>
				<p id="listTitle">조세호</p>
			</div>
			<div class="p-2">
				<span class="image round"><img id="userPic" src="/images/유재석.png" alt=""></span>
				<p id="listTitle">조세호</p>
			</div>
			<div class="p-2">
				<span class="image round"><img id="userPic" src="/images/조세호.png" alt=""></span>
				<p id="listTitle">조세호</p>
			</div>
			<div class="actions vertical small">
                <button id="buttonBackground"><img src="/images/plusbutton.png" width="260" height="260"></button>
            </div>
		</div>

		<br><br><br>
		<div id="searchTitle">
			<div class="d-grid gap-3 d-md-flex justify-content-md">
				<h3 id="searchResult">일정</h3>
				<h5>5</h5>
				<!-- <div style="margin-left: auto">	 -->
				<!-- <a href="/html-css/search/searchDetail.html" class="button alt small">더보기</a> -->
				<!-- </div> -->
			</div>
		</div>

    <ContentList />

		<br><br><br>
		<div id="searchTitle">
			<div class="d-grid gap-3 d-md-flex justify-content-md">
				<h3 id="searchResult">장소</h3>
				<h5>5</h5>
				<!-- <div style="margin-left: auto">	 -->
				<!-- <a href="/html-css/search/searchDetail.html" class="button alt small">더보기</a> -->
				<!-- </div> -->
			</div>
		</div>

    <ContentList />

	</section>
</template>

<script>
import ContentList from '../util/ContentList.vue';

export default {
  components : {
    ContentList
  }

}
</script>

<style scpoed>
#main {
    margin-left: 5%;
    margin-right: 5%;
}

#mainTitle {
    text-align: left;
}


h1,
#searchResult {
    color: #ff928e !important;
}

#searchTitle {
    text-align: left;
    margin-left: 5%;
}

#searchBar {
    display: flex;
    justify-content: center;
}

#searchBar>form {
    width: 70%;
}

h5 {
    margin-top: 0.25%;
}

#userPic {
    width: 260px;
    height: 260px;
}

#searchPic {
    width: 260px;
    height: 260px;
}

#searchUser,
#searchSchedule,
#searchLocation {
    margin-left: 5%;
    overflow:auto;
    overflow-y: hidden;
}

#listTitle {
    text-align: center;
}

#buttonBackground {
    background-color: white;
}

.container > img {
    position: absolute;
    width: 100%;
    /* height: 100%; */
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }

</style>