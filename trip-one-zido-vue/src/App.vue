<template>
  <Header :main="main" :isLogin="isLogin" />
  <router-view :modalShown="modalShown" @modal="modal" @meta="meta" />
  <Footer />
  <div id="modal-back" v-if="modalShown"></div>
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
      main: window.location.pathname === "/" ? true : false,
      modalShown: false,
      isLogin: false
    };
  },
  methods: {
    modal() {
      this.modalShown = !this.modalShown;
    },
    meta(isLogin){
      this.isLogin = isLogin;
    }
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
</style>

<style scoped>
#modal-back {
  position: fixed;
  background-color: black;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  opacity: 80%;
}
</style>
