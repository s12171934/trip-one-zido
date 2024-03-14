<template>
  <Header :isLogin="isLogin" :key="key" :path="path" />
  <router-view @meta="meta" :id="id"/>
  <Footer />
</template>

<script>
import Header from "./components/public/Header.vue";
import Footer from "./components/public/Footer.vue";

export default {
  components: {
    Header,
    Footer,
  },

  data() {
    return {
      isLogin: false,
      key: 0,
      path: location.pathname,
      id: 0,
    };
  },
  methods: {
    meta(isLogin) {
      this.isLogin = isLogin;
    },
    forceRender() {
      this.key++;
    },
  },
  watch: {
    $route(to, from) {
      if (to.path != from.path) {
        this.forceRender;
        this.path = to.fullPath
        this.id = to.params.id
      }
    },
  },
};
</script>

<style>
@font-face {
  font-family: "Jalnan";
  src: url("/public/fonts/Jalnan2TTF.ttf") format("truetype");
}
@import url(/public/css/font-awesome.min.css);
@import url(/public/css/main.css);
@import url(https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css);

div::-webkit-scrollbar {
  /* 스크롤바 배경 */
  height: 15px;
  border-radius: 10px;
  background: rgb(236, 236, 250);
}

div::-webkit-scrollbar-thumb {
  /* 스크롤바 */
  width: 15px;
  border-radius: 10px;
  background: pink;
}

main {
  margin-left: 5%;
  margin-right: 5%;
}

* {
  font-family: "Jalnan" !important;
}

h1 {
  color: #ff928e !important;
}


h3 {
  color: rgb(80, 80, 80) !important;
}

h4,
#email {
  color: rgb(0, 0, 0) !important;
}
</style>
