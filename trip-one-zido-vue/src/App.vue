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
      url : window.location.pathname
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
@import url(/public/css/main.css);
@import url(https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css);
</style>
