<template>
  <!-- Header -->
  <header id="header">
    <nav class="left">
      <a href="#menu"><span>Menu</span></a>
    </nav>
    <a href="/html-css/index.html" class="logo"
      ><img src="/images/여행한지도_로고.png" width="325" />
    </a>
    <nav class="right">
      <div
        v-if="isLogin(url)"
        class="d-grid gap-2 d-md-flex justify-content-md-end"
        id="headerProfile"
      >
        <form action="/html-css/search/search.html">
          <input type="text" required id="headerSearchBar" />
        </form>
        <span class="image round" box-sha>
          <a href="/html-css/main/detail/detail.html">
            <img src="/images/남자.png" width="75" height="75" />
          </a>
        </span>
      </div>
      <nav v-if="!isLogin(url)" class="right">
        <a href="/html-css/sign/login/login.html" id="button" class="button alt"
          >로그인</a
        >
        <a
          href="/html-css/sign/signin/signin.html"
          id="button"
          class="button alt"
          >회원가입</a
        >
      </nav>
    </nav>
  </header>
</template>

<script>
export default {
  props: {
    url: String,
  },
  data() {},
  methods: {
    isLogin(url) {
      console.log(url);
      if (url === "/") {
        return true;
      } else {
        return false;
      }
    },
  },
};
</script>

<style scoped>
#headerProfile {
  margin-top: 3%;
}
#headerSearchBar {
  margin-top: 2%;
  font-family: "Jalnan";
}
#button {
  border-radius: 30px;
  font-size: 17px;
}
H1 {
  font-family: "Jalnan";
}
#input {
  font-family: "Jalnan";
  font-size: 17px;
}
</style>
