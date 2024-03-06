<template>
      <section id="main" class="wrapper">
        <form id="split-container" class="d-flex flex-row">
            
            <!-- ★왼쪽 -->
            <div class="p-2 d-flex flex-column border-end" id="leftSide">
                <div class="d-flex">
                    <div id="leftPosition">
                    <h1 class="title">일정 조회</h1>
                    <br>
                </div>
                        <button class="btn btn-outline flex-shrink-0" type="button">
                            <i class="bi-cart-fill me-1"></i>
                            <img id="icon" src="/unzzim.png" onclick="zzim()">
                        </button>
                        <button class="btn btn-outline flex-shrink-0" type="button">
                            <i class="bi-cart-fill me-1"></i>
                            <img id="icon" src="/images/like.png"onclick="like()">
                        </button>
                    </div>
                <div class="p-2 d-flex flex-row" id="blank"><h4>일정 제목</h4>&nbsp;
                    <input type="text" value="★ 맛집 투어 | 부산 ★" id="detail-title" readonly>
                    
                        <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
                        <input type="radio hidden" class="btn-check" name="btnradio" id="btnradio1" autocomplete="off" checked>
                        <label class="btn btn-outline-primary" for="btnradio1">여행전</label>
                        
                        <input type="radio hidden" class="btn-check" name="btnradio" id="btnradio2" autocomplete="off">
                        <label class="btn btn-primary" for="btnradio2">여행중</label>
                        
                        <input type="radio hidden" class="btn-check" name="btnradio" id="btnradio3" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btnradio3">여행한</label>
                        </div>
                </div>

                <div class="p-2 d-flex" id="blank"><h4>기간</h4>&nbsp;&nbsp;
                    <label for="start_date"></label>
                    <input class="flex-fill" type="date" id="start_date" name="start_date" value="2024-02-10" readonly>
                    <h4>~</h4>
                    <label for="end_date"></label>
                    <input class="flex-fill" type="date" id="end_date" name="end_date" value="2024-02-14" readonly>
                </div>

                <div class="p-2 d-flex border-bottom" id="with"><h4>동행인</h4>&nbsp;&nbsp; 
                    <!-- <form action="send_email.php" method="POST"> -->
                    <label for="email1"></label>
                    <input class="flex-fill" type="email" id="email" name="email1" value="윤지수" readonly><br><br>

                    <label for="email2"></label>
                    <input class="flex-fill" type="email" id="email" name="email2" value="문성기" readonly><br><br>

                    <label for="email3"></label>
                    <input class="flex-fill" type="email" id="email" name="email3" value="한희주" readonly><br><br>
                    <!-- 필요한 만큼 이메일 주소 입력 필드를 추가할 수 있음 -->
                    <!-- </form> -->
                </div>

                <div class="p-2"><h4> 지도 </h4>
                    <!-- Google 지도를 삽입할 영역 -->
                    <iframe
                    id="map"
                    width="600"
                    height="300"
                    frameborder="0"
                    src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d26097.096481087745!2d129.09806643943534!3d35.15320239534059!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3568ed2f27c70ec7%3A0xff6df0e14d9216fb!2z6rSR7JWI66as7ZW07IiY7JqV7J6l!5e0!3m2!1sko!2skr!4v1709173272511!5m2!1sko!2skr"
                    allowfullscreen>
                    </iframe>
                </div>

                <div class="p-2 d-flex border-top"><h4>여행한 후기</h4>
                    <div class="rating flex-fill" id="star">
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
                    <!-- <form action="submit_post.php" method="POST">             -->
                        <textarea id="content" name="content" rows="5" cols="50">1일차 : 부산 광안리 너무 재밌다 !~!</textarea><br><br>
                    <!-- </form> -->
                </div>
            </div>

            <!-- ★오른쪽 -->
            <div class="p-2 d-flex flex-column border-end" id= "rightSide-margin">
 
                <div class="p-2 d-flex flex-row" id="rightSide">
                    <h4 id="title2">여행 중 일정 및 장소</h4>
                    &nbsp;&nbsp;
                    <input type="text" value="부산광역시" id="blank-city" readonly>
                    <br>
                </div>

                <div id='calendar'></div>

                <!-- ★댓글창  -->
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
                                <div class="flex-shrink-0">
                                    <br>
                                    <img class="rounded-circle" src="/images/유재석.png" alt="..." id="commentProfilePic"/></div>
                                <div class="ms-3">                                  
                                    <br>
                                    <div class="fw-bold">유재석</div>
                                    부산 먹거리가 너무많네요 ~
                                    <small><b><a href="#" id="plusComment">답글</a></b></small>
                                    <div><small><a href="#" id="adddComment">▼답글 2개</a></small></div>
                                    <!-- Child comment 1-->
                                    <div class="d-flex mt-4">
                                        <div class="flex-shrink-0"><img class="rounded-circle" src="/images/남자.png" alt="..." id="commentProfilePic"/></div>
                                        <div class="ms-3">
                                            <div class="fw-bold">대댓글 단사람1
                                                <a href="#" id="commentUpdate">수정</a>
                                                <a href="#" id="commentDelete">삭제</a>
                                            </div>
                                            광안리 주변 횟집추천드려요!
                                        </div>
                                    </div>
                                    <!-- Child comment 2-->
                                    <div class="d-flex mt-4">
                                        <div class="flex-shrink-0"><img class="rounded-circle" src="images/여자.png" alt="..." id="commentProfilePic"/></div>
                                        <div class="ms-3">
                                            <div class="fw-bold">대댓글 단사람2</div>
                                            거기도 맛있고~ 부산집도 맛있어요
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Single comment-->
                            <div class="d-flex mb-4">
                                <div class="flex-shrink-0"><img class="rounded-circle" src="images/조세호.png" alt="..." id="commentProfilePic"/></div>
                                <div class="ms-3">
                                    <div class="fw-bold">조세호</div>
                                    ㅎㅎ
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </form>
    </section>

</template>

<script>
export default {

}
</script>

<style scoped>
.select-wrapper {
    font-size: small;
}

#leftPosition {
    text-align: left;
}

h1, h2, h3, h4, body, .button, section {
    font-family: 'Jalnan';
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

/* 화면 나누기 */
#split-container {
display: flex;
width: 100%;
}
#split-container > div {
    width: 50%;
    padding: 20px;
}

/* 지도 크기 설정 */
#map {
    height: 400px;
    width: 100%;
}


#input {
    color:white;
    font-family: 'Jalnan';
    text-align: center;
    margin-right: 10px; 
    margin-left: 10px; 
    margin-bottom: 10px;
    padding-left: 20px;
    padding-right: 20px;
}
.flex-fill {
    color: #ff928e;
}

#start_date, #end_date {
    color: black;
    margin-right: 10px; margin-left: 10px;
}


#content{
        resize: none;
}


/* refactoring */

#main {
    margin-left: 5%;
    margin-right: 5%;
    margin-top: -3%;
}

#leftSide {
    margin-right: auto;
}

#leftSide .title {
    color:#ff928e;
}

#detail-title {
    margin-right: 10px; 
    margin-left: 10px;
    width: 40%;
    height: 60%;
}

#email {
    margin-right: 5px; 
    margin-left: 5px; 
    width: 20%; 
    height: 70%;
}


#buttonBackground {
    background-color: white;
}

#map {
    border: 0;
}

#trip-review {
    margin-right: 10%; 
}

#rightSide {
  
    margin-top:8%
}

#rightSide #title {
    margin-top: 15%
}

#rightSide #title2 {
    margin-top: 2%;
}

.select-wrapper {
    width: 20%
}


#with {
    margin-top: 2%
}

#star {
    margin-right: 10px;
    margin-left: 100px;
}

#rightSide-margin {
    margin-left: 3%;
}


#blank-city {
    margin-right: 10px; 
    margin-left: 10px; 
    width: 30%;
    height: 90%;
}


#commentBody {
    width: 95%; margin-top: 3%;
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
</style>