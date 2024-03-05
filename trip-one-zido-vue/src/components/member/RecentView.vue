<template>
  <section id="main" class="wrapper">
    <div id="mainTitle">
      <p>
        <a href="/html-css/main/detail/detail.html"
          ><img
            id="configPic"
            src="/images/pic01.jpg"
            alt=""
            class="rounded-circle"
        /></a>
        <sapn id="configName">여행자님!</sapn>
      </p>
    </div>
    <div id="mainTitle">
      <h1>조회한 게시글(기록)</h1>
    </div>
    <ContentList />
  </section>
</template>

<script>
import ContentList from '../util/ContentList.vue';

export default {
  components : {
    ContentList,
  }
};
</script>

<style scoped>
#main {
  margin-left: 5%;
  margin-right: 5%;
}

#mainTitle {
  text-align: left;
}

h1 {
  color: #ff928e !important;
}

#configPic {
  width: 260px;
  height: 260px;
}

#configName {
  font-size: 25px;
  margin-left: 10%;
}

#icon {
  width: 25px;
  height: 25px;
}

#listTitle {
  text-align: center;
}

#titlePic {
  width: 260px;
  height: 260px;
}

img {
  vertical-align: top;
}

.banner_img,
.banner_bg {
  display: inline-block;
  position: relative;
  z-index: 0;
}

.banner_img:hover:after,
.banner_img:hover > .hover_text,
.banner_bg:hover:after,
.banner_bg:hover > .hover_text {
  display: block;
}

.banner_img:after,
.banner_bg:after,
.hover_text {
  display: none;
}

.banner_img:after,
.banner_bg:after {
  content: "";
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

#buttonBackground {
  background-color: white;
}

#recentViewBox {
  overflow: auto;
}

#footer {
  font-family: "Jalnan";
  font-size: 10px;
}

h1,
h2,
h3,
h4,
body,
p,
.button,
.main {
  font-family: "Jalnan";
}

#menu {
  font-size: 20px;
  font-family: "Jalnan";
}
</style>
