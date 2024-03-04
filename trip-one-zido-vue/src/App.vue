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
      url : window.location.pathname === "/"
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
</style>
