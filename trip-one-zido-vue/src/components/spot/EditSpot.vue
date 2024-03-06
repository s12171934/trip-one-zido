<template>
    <section id="main" class="wrapper">
        <form id="split-container" class="d-flex flex-row" action="">
            
            <!-- ★왼쪽 -->
            <div class="p-2 d-flex flex-column border-end" id="leftSide">
                  <div>
                    <h1 class="title">장소 등록</h1>
                    <br>
                </div>
    
                <div class="p-2" id="picContainer"><h4 id="font-vertical">사진 등록</h4>
                    <div class="d-flex flex-row" id="addPic">
                        <div class="d-inline-flex p-2">
                            <img class="img-fluid rounded" id="selectedPic" src="/images/dragoncastle.jpg" alt="" >
                            <img class="img-fluid rounded" id="selectedPic" src="/images/busan3.jpg" alt="" >
                            <button id="plusPic">
                                <img src="/images/plusbutton.png" alt="" class="rounded-5"사지>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- ★오른쪽 -->
            <div class="p-2 d-flex flex-column border-end" id="rightSide">
                <div class="p-2 d-flex flex-row mb-3" id="blank-title"><h4 id="font-vertical">장소 제목</h4>&nbsp;&nbsp;
                    <input type="text" value="" id="locationTitle" >
                </div>

                <div class="p-2 d-flex mb-3" id="blank"><h4 id="font-vertical">시간</h4>&nbsp;&nbsp;
                    <label for="start_date"></label>
                    <input class="flex-fill" type="time" id="start_date" name="start_date" >
                    <h4>~</h4>
                    <label for="end_date"></label>
                    <input class="flex-fill" type="time" id="end_date" name="end_date">
                </div>

                <div class="p-2 d-flex border-bottom"><h4 id="font-vertical">동행인</h4>&nbsp;&nbsp;
                    <!-- <form action="send_email.php" method="POST"> -->
                    <label for="email1"></label>
                    <input class="flex-fill" type="email" id="email" name="email1"><br><br>

                    <label for="email2"></label>
                    <input class="flex-fill" type="email" id="email" name="email2"><br><br>

                    <label for="email3"></label>
                    <input class="flex-fill" type="email" id="email" name="email3"><br><br>
                    <!-- 필요한 만큼 이메일 주소 입력 필드를 추가할 수 있음 -->
                    <!-- </form> -->
                    <div class="actions vertical small mb-4" id="plusButton">
                        <button id ="buttonBackground"><img src="/images/plusbutton.png" width="25" height="25"></button>
                    </div>
                </div>
                    
                <div class="p-2 d-flex flex-row mb-3" id="picLocation">
                    <h4 id="font-vertical">사진 장소</h4>&nbsp;&nbsp;

                      <div class="p-2 d-flex flex-row">
                        <div class="select-wrapper">
                          <select class ="local-select" name="category">
                                <option value="" selected>카테고리</option>
                                <option value="1">음식점</option>
                                <option value="2">여가(관람/체험/스포츠)</option>
                                <option value="3">관광지(산/바다/계곡/해변)</option>
                                <option value="4">테마파크</option>
                                <option value="5">기타</option>
                            </select>
                          </div>
                        </div>
                        <div>
                          <input type="text" id="addressArea" placeholder="위치를 입력해주세요~!">
                        </div>

                    <br>
                </div>

                <div class="p-2 d-flex border-top mb-3" ><h4>여행한 후기</h4>
                    <div class="rating flex-fill" id="rateStar">
                        <span onclick="rateStar(10)">☆</span>
                        <span onclick="rateStar(8)">☆</span>
                        <span onclick="rateStar(6)">☆</span>
                        <span onclick="rateStar(4)">☆</span>
                        <span onclick="rateStar(2)">☆</span>
                    </div>
                    <p class="flex-fill" id="ratingValue">별점을 선택하세요.</p>
                </div>

                <!-- 여행후기 -->
                <div class="p-2">
                        <textarea id="content" name="content" rows="5" cols="50"></textarea><br><br>
                </div>
                
                <div class="p-2 d-flex flex-row">
                    <div class="select-wrapper" id="security">
                        <select class ="local-select" name="category">
                            <option value="" selected>공개 설정</option>
                            <option value="1">공개</option>
                            <option value="2">비공개</option>
                        </select>
                    </div>
                    <div id="formButton">
                        <input id="input" class="button small" type="submit" value="등록">
                        <input class="button alt small" type="button" value="취소"
                        onclick="location.href='/html-css/main/detail/detail.html'">
                    </div>
                </div>
                    
                <!-- Break1 -->
				<div class="p-1 12u$">
					<ul class="actions">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
						게시글등록 모달
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
								    <h3>게시글 등록이</h3>
								    <h3>완료되었습니다</h3>
								</div>

								<div class="modal-footer border border-0">
								<!-- <button type="button" class="btn btn-secondary" >로그인하러가기</button> -->
								<button type="button" class="rounded-3" id="modalButton" 
                                    onclick="location.href='/html-css/location/detail.html'">목록가기</button>
								</div>
							</div>
						</div>
					</div>
					</ul>
				</div>
            </div>
        </form>
    </section>
        
    

</template>

<script>
export default {

}
</script>

<style scoped>

#main {
  margin-left: 5%;
  margin-right: 5%;
  margin-top: -5%;
}

#mainTitle {
  text-align: left;
}

#leftSide {
  margin-top: 3%;
  margin-right: auto;
}

#rightSide {
  margin-left: 3%; margin-top: 3%;
}

h1 {
  color:#ff928e !important;
}

h3 {
  color: rgb(80, 80, 80) !important;
}

h4, #email {
  color: rgb(0, 0, 0) !important;
}

#email {
    margin-right: 5px; 
    margin-left: 5px; 
    width: 20%; 
    height: 60%;
}


#picContainer{
  justify-content: center;
}

#addPic{
  overflow: scroll; 
  overflow-y: hidden; 
  height: 125%;
}

#selectedPic {
  width: 75%;
  height: 100%;
  margin-right: 1%;
}

#plusPic {
  background-color: white; 
  margin-top: 20%;
}

#plusPic > img {
  width: 260;
  height: 260px;
}

#locationTitle {
  margin-right: 10px; 
  margin-left: 10px; 
  width: 40%; 
  height: 100%;
}

#locationPeople{
  justify-content: center;
}

#otherName {
  margin-right: 5px; 
  margin-left: 5px; 
  width: 50%; 
  height: 50%;
}

#buttonBackground {
  background-color: white;
}

#picLocation{
  align-items:center;
}

.p-2 > select {
  width: 200px; 
}

#select-category {
  width: 100%;
}

#addressArea {
  width: 100%;
  /* height: 130%; */
}

#category {
  height: 90%;
  margin-top: 3%;
}

#formButton {
  margin-left: auto
}

#modalButton {
  color: aliceblue;
  background-color: #ff928e;
}

#modalBox {
  background-color: #D9D9D9;
}

#footer {
  font-family: 'Jalnan';
  font-size: 10px;
}

h1, h2, h3, h4, h5, body, .button, #security, .rating, #ratingValue {
  font-family: 'Jalnan';
}

#menu {
  font-family: 'Jalnan';
  font-size: 20px;
}


/* 달력 */
html, body {
  margin: 0;
  padding: 0;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  /* font-size: 14px; */
}

#calendar {
  width: 90%;
  max-width: 1100px;
  margin: 40px auto;
}

/* 별점주기 */
.rating {
  unicode-bidi: bidi-override;
  direction: rtl;
  text-align: left;
}

.rating>span {
  display: inline-block;
  position: relative;
  width: 1.1em;
}

.rating>span:hover:before,
.rating>span:hover~span:before {
  content: "\2605";
  position: absolute;
}

#rateStar{
  margin-right: 10px;
   margin-left: 100px;
}


/* 화면 나누기 */
#split-container {
display: flex;
width: 100%;
}
#split-container > div {
  width: 50%;
  padding: 20px;
}

input{
  font-family: 'Jalnan' !important;
}
#input {
  color:white;
  background-color: #ff928e;
}
.flex-fill {
  color: #ff928e;
}

#start_date, #end_date {
  color: black;
  margin-right: 10px; margin-left: 10px;
}

textarea{
resize: none;
}


/* 스크롤바 */
div::-webkit-scrollbar{ /* 스크롤바 배경 */
    height: 15px;
    border-radius: 10px;
    background: rgb(236, 236, 250);
  }
  div::-webkit-scrollbar-thumb{ /* 스크롤바 */
    width: 15px;
    border-radius: 10px;
    background: pink;
  }
  
  /* 테스트용 */
.slide,.slide_bottom {
  /* layout */
  display: flex;
  flex-wrap: nowrap;
  /* 컨테이너의 내용물이 컨테이너 크기(width, height)를 넘어설 때 보이지 않도록 하기 위해 hidden을 준다. */
  overflow: hidden;

  /* position */
  /* slide_button의 position absolute가 컨테이너 안쪽에서 top, left, right offset이 적용될 수 있도록 relative를 준다. (기본값이 static인데, static인 경우 그 상위 컨테이너로 나가면서 현재 코드에선 html을 기준으로 offset을 적용시키기 때문) */
  position: relative;

  /* size */
  width: 100%;
}
.slide_item, .slide_item_bottom {
  /* layout */
  display: flex;
  align-items: center;
  justify-content: center;

  /* position - 버튼 클릭시 left offset값을 적용시키기 위해 */
  position: relative;
  left: 0px;

  /* size */
  width: 100%;
  /* flex item의 flex-shrink는 기본값이 1이므로 컨테이너 크기에 맞게 줄어드는데, 슬라이드를 구현할 것이므로 줄어들지 않도록 0을 준다. */
  flex-shrink: 0;

  /* transition */
  transition: left 0.5s;
}
.slide_button {
  /* layout */
  display: flex;
  justify-content: center;
  align-items: center;

  /* position */
  position: absolute;
  /* 버튼이 중앙에 위치하게 하기위해 계산 */
  top: calc(45%);

  /* size */
  width: 25px;
  height: 25px;

  /* style */
  border-radius: 100%;

  color: #fcbebc;
  cursor: pointer;
}

.slide_prev_button, .slide_prev_button_bottom {
  left: 0px;
}
.slide_next_button, .slide_next_button_bottom {
  right: 0px;
}


/* 페이지네이션 스타일 */
ul,li {
  list-style-type: none;
  padding: 0;
  margin: 0;
}
.slide_pagination, .slide_pagination_bottom {
  /* layout */
  display: flex;
  gap: 3px;

  /* position */
  position:relative;
  justify-content: center;
  bottom: 0;
  /* left:50%, translateX(-50%)를 하면 가로 가운데로 위치시킬 수 있다. */
  left: 50%;
  transform: translateX(-50%);
}
.slide_pagination > li , .slide_pagination_bottom > li{
  /* 현재 슬라이드가 아닌 것은 투명도 부여 */
  color: #30323588;
  cursor: pointer;
  font-size: 25px;
}
.slide_pagination > li.active, .slide_pagination_bottom > li.active {
  /* 현재 슬라이드 색상은 투명도 없이 */
  color: #f6755e;
}

#font-vertical {
  margin-top: 1%
}

#security {
  width: 15%;
}

</style>
