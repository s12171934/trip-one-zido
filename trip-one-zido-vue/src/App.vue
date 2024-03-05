<template>
  <Header :url="url" />
  <router-view />
  <Footer />
</template>

<script>
import Header from './components/public/Header.vue';
import Footer from './components/public/Footer.vue';

export default {
  components : {
    Header,
    Footer,
  },

  data(){
    return{
      url : window.location.pathname === "/" ? true : false
    }
  }
}
</script>

<style>
@font-face {
    font-family: 'Jalnan';
    src: url('/public/fonts/Jalnan2TTF.ttf') format('truetype');
}
@import url(/public/css/font-awesome.min.css);
@import url(/public/css/main.css);
@import url(https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css);
</style>
