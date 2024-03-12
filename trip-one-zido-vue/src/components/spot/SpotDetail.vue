<template>
  <main id="main" class="wrapper">
        <div class="d-flex justify-content-center">
            <!-- 왼쪽 영역 -->
            <div class="d-flex flex-column mb-2" id="leftSide">
                <div class="d-flex justify-content-start">
                    <h1 class="mb-1">부산 해동 용궁사</h1>
                    <ul class="actions" id="actions">
                        <li>
                            <button class="btn btn-outline-secondary btn-sm rounded-3" id="button" disabled>관광지</button>
                            <button class="btn btn-sm" id="openPopup" onclick="viewLocation()">위치보기</button>
                        </li>
                    </ul>
                    <h6 id="createdAt">2024 02.20 14:30</h6>
                </div>
                <div class="text-muted fst-italic mb-2">2024/02/19 08:00 ~ 2024/02/19 19:00</div>
                <div class="d-flex flex-row mb-6" id="recentViewBox">
                    <div class="p-2">
                        <img src="/images/busan1.jpg" alt="" class="rounded-5" width="800" height="600">
                    </div>
        
                    <div class="p-2">
                        <img src="/images/busan2.jpg" alt="" class="rounded-5" width="800" height="600">
                    </div>
        
                    <div class="p-2">
                        <img src="/images/busan3.jpg" alt="" class="rounded-5" width="800" height="600">
                    </div>
        
                    <div class="p-2">
                        <img src="/images/전라남도 보성(녹차밭).png" alt="" class="rounded-5" width="800" height="600">
                    </div>
                    <div class="p-2">
                        <img src="/images/전라남도 순천(순천만).png" alt="" class="rounded-5" width="800" height="600">
                    </div>
                    <div class="p-2">
                        <img src="/images/전라도 전주(한옥마을).png" alt="" class="rounded-5" width="800" height="600">
                    </div>
                    
                </div>
            </div>

            <!-- 오른쪽 영역 -->
            <div class="d-flex flex-column mb-3" id="rightSide">
                <div class="p-2">
                    <span><small>작성자 : jisoo</small></span>
                    <span><small>조회수 : 341,245</small></span>
                    <i class="heart-icon" :class="{ 'filled': isZzim }" @click="toggleZzim"></i>
                    <img id="icon" src="/images/star.png"><span><small>10점</small></span>
                    <img id="icon" src="/images/like.png" onclick="like()"><span><small>955</small></span>
                    <img id="icon" src="/images/unlike.png" onclick="unlike()"><span><small>127</small></span>
                </div>
                <div class="p-2" id="afterStory">
                    <textarea class="rounded-4" name="message" id="message" placeholder="후기를 작성해주세요!" rows="4"
                        data-gramm="false" wt-ignore-input="true" disabled>경상북도 경주에 왔습니다! 동궁과 월지 너무 좋습니다. 
                        주변에서 국수도 맛있게 먹었습니다!!!</textarea>
                </div>
                <div class="p-2">
                    <section class="mb-5" id="commentBody">
                        <div class="card bg-light">
                            <div class="card-body">
                                <form class="input-group">
                                    <div class="d-flex flex-row-reverse">
                                        <button class="button alt" type="submit" id="comment">댓글</button>
                                        <input name="comment" type="text" class="form-control" placeholder="댓글 추가하기"
                                            aria-label="Recipient's username" aria-describedby="button-addon2">
                                    </div>
                                </form>
                                <!-- Comment with nested comments-->
                                <div class="d-flex mb-4">
                                    <!-- Parent comment-->
                                    <div class="flex-shrink-0"><img class="rounded-circle" src="/images/유재석.png" alt="..." id="commentProfilePic"/></div>
                                    <div class="ms-3">
                                        <div class="fw-bold">유재석</div>
                                        조세호랑 촬영차 왔습니다!
                                        <small><b><a href="#" id="plusComment" >답글</a></b></small>
                                        <div><small><a href="#" id="addedComment" >▼답글 2개</a></small></div>
                                        <!-- Child comment 1-->
                                        <div class="d-flex mt-4">
                                            <div class="flex-shrink-0"><img class="rounded-circle" src="/images/남자.png" alt="..." id="commentProfilePic" /></div>
                                            <div class="ms-3">
                                                <div class="fw-bold">대댓글 단사람1
                                                    <small><a href="#" id="commentUpdate">수정</a></small>
                                                    <small><a href="#" id="commentDelete">삭제</a></small>
                                                </div>
                                                재밌게 잘 보고 있습니다! 너무 재밌네요!!! 어디까지 늘어나나 볼까요
                                            </div>
                                        </div>
                                        <!-- Child comment 2-->
                                        <div class="d-flex mt-4">
                                            <div class="flex-shrink-0"><img class="rounded-circle" src="/images/여자.png" alt="..." id="commentProfilePic" /></div>
                                            <div class="ms-3">
                                                <div class="fw-bold">대댓글 단사람2</div>
                                                박명수 오빠가 더 재밌어요!
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- Single comment-->
                                <div class="d-flex mb-4">
                                    <div class="flex-shrink-0"><img class="rounded-circle" src="/images/조세호.png" alt="..." id="commentProfilePic" /></div>
                                    <div class="ms-3">
                                        <div class="fw-bold">조세호</div>
                                        형님이랑 좋은 시간 보냈습니다
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>
            </div>

        </div>
      </main>
</template>

<script scoped>

export default {  
  data() {
    return {
      isZzim: false
    }
  },

  methods : {
    toggleZzim() {
      
      icon.src = isZzim ? "/images/zzim.png" : "/images/찜해제.png";
    },

    viewLocation() {
      const openPopUp = document.getElementById('openPopup');
      openPopUp.addEventListener('click', () => {
          window.open('/html-css/location/popup.html', 'popup', 'width=850, height=600, left=500, top=100');
      });
    }
  },
}



</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

h1 {
  color: #ff928e !important;
}

#mainTitle {
  text-align: left;
}

#leftSide {
  width: 50%;
}

#actions{
  margin-left: 1%;
}

#icon{
  width: 50px;
  height: 50px;
  margin-left: 1%; 
  margin-right: 1%;
}

#createdAt{
  margin: 1%;
}

#rightSide{
  width: 50%;
}

#recentViewBox{
  overflow: auto;
  overflow-y: hidden;
}

#formButton{
  background-color:#ff8783;
}

#formButton > a{
  color: aliceblue; text-decoration-line: none;
}

#deleteButton{
  color: aliceblue; background-color:#ff8783;
}

#afterStory{
  margin-top: 6%;
}

#afterStory > textarea{
  resize: none; width:75%;
}

#commentBody {
  width: 75%; margin-top: 3%;
}

.card-body > form {
  display: flex; align-items: center; justify-content: center;
}

#comment > input{
  margin-right: 10px;
}

#plusComment {
  color:darkgray; text-decoration:none
}

#addedComment {
  text-decoration:none
}

#commentUpdate,#commentDelete {
  color:darkgray; text-decoration:none
}

.ms-3 {
  width: 150%;
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
#button {
  background-color: white !important;
  color: black !important;
  box-shadow : none;
}
#openPopup {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
  border-radius: 10px !important;
}

#commentProfilePic {
    width: 75px;
    height: 75px;
}
</style>