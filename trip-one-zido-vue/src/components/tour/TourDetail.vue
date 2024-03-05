<template>
   <section id="main" class="wrapper">
        <div class="row gx-4 gx-lg-5 align-items-center">
            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0 rounded-5" src="/images/busan3.jpg"
                    alt="..." width="1000" height="670" /></div>
            <div class="col-md-6">
                <div class="d-flex justify-content-end">
                    <ul class="actions">
                        <li><a href="/html-css/info/info/info.html" class="button alt small">목록</a></li>
                        <li><a href="./detail_priv.html" class="button alt small">이전</a></li>
                        <li><a href="./detail_next.html" class="button alt small">다음</a></li>
                    </ul>
                </div>
                <div class="d-flex">
                    <h1 class="display-5">해동용궁사</h1>
                    <button class="btn btn-outline flex-shrink-0" type="button">
                        <i class="bi-cart-fill me-1"></i>
                        <img id="icon" src="/images/unzzim.png" onclick="zzim()">
                    </button>
                </div>
                <div class="fs-5 mb-5">
                    <span>・관광지 한줄 설명</span>
                    <p id="explanation">부산광역시 기장군 기장읍 시랑리에 있는 대한불교조계종 제19교구 본사 화엄사 소속 사찰.</p>
                </div>
                <div class="fs-5 mb-5">
                    <span>・장소 주소</span>
                    <div class="d-flex fill">
                        <iframe class="rounded-5"
                            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3260.7271121845406!2d129.2207447762419!3d35.18835345689094!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x35688c1bbbdea6c1%3A0xa141406d6f112e5c!2z7ZW064-Z7Jqp6raB7IKs!5e0!3m2!1sko!2skr!4v1708994953688!5m2!1sko!2skr"
                            width="925" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                            referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {

}
</script>

<style>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

#mainTitle {
  text-align: left;
}


h1 {
  color:#ff928e !important;
}


#icon {
  width: 25px;
  height: 25px;
}

#titlePic {
  width:260px;
  height:260px;
}

#explanation{
  margin-left: 3%;
}

#listTitle {
  text-align: center;
}

#spotInfoPic{
  width:850px; 
  height:690px;
}

img {
  vertical-align: top;
}

.banner_img,
.banner_bg {
  display: inline-block;
  position: relative;
}

.banner_img:hover:after,
.banner_img:hover>.hover_text,
.banner_bg:hover:after,
.banner_bg:hover>.hover_text {
  display: block;
}

.banner_img:after,
.banner_bg:after,
.hover_text {
  display: none;
}

.banner_img:after,
.banner_bg:after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.3);
  z-index: 10;
  border-radius: 13%;
}

.banner_img {
  overflow: hidden;
}

.banner_img:hover img {
  transition: 1s;
}

.hover_text {
  position: absolute;
  top: 35%;
  /* right: 25%;
    bottom: 25%; */
  left: 30%;
  color: #fff;
  font-weight: 600;
  font-size: 20px;
}

#footer {
  font-family: 'Jalnan';
  font-size: 10px;
}


h1,
h2,
h3,
h4,
body,
.button,
#main {
  font-family: 'Jalnan';
}

#menu {
  font-size: 20px;
  font-family: 'Jalnan';
}
</style>