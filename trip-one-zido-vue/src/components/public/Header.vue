<template>
  <header id="header" :style="view ? '' : 'box-shadow: none'">
    <nav v-if="isLogin" class="left">
      <a href="#menu" @click="toggleMenu"><span>Menu</span></a>
    </nav>

    <div v-if="view" class="h-100">
      <a href="/">
        <img src="/images/여행한지도_로고.png" class="logo" width="325" />
      </a>

      <nav v-if="isLogin" class="right d-flex gap-2 align-items-center">
        <form action="/search" class="m-0">
          <input type="text" id="headerSearchBar" required />
        </form>
        <span class="image round d-flex h-100 align-items-center" box-sha>
          <a href="/member-page">
            <img src="/images/남자.png" height="75" />
          </a>
        </span>
      </nav>
      <nav v-else class="right d-flex gap-2 align-items-center">
        <a href="/login" class="button alt">
          로그인
        </a>
        <a href="/sign-up" class="button alt">
          회원가입
        </a>
      </nav>
    </div>
  </header>

  <nav id="menu" :class="menu ? 'visible' : ''">
    <ul class="links">
      <li>
        <a @click="toggleAddMenu">게시글 등록</a>
        <ul id="add" v-if="addMenu">
          <li><a href="/plan/add">일정 게시글 등록</a></li>
          <li><a href="/spot/add">장소 게시글 등록</a></li>
          <li><a href="/community/add">커뮤니티 게시글 등록</a></li>
        </ul>
      </li>
      <li><a href="/community">커뮤니티</a></li>
      <li><a href="/recent-view">최근기록</a></li>
      <li><a href="/bookmark">찜목록</a></li>
      <li><a href="/tour/loc">관광정보</a></li>
      <li><a @click="logout">로그아웃</a></li>
    </ul>
    <a href="#menu" @click="toggleMenu" class="close"></a>
  </nav>
</template>

<script>
export default {
  props: {
    isLogin: Boolean,
  },
  data() {
    return {
      menu: false,
      addMenu: false,
      view: window.location.pathname != '/',
    };
  },
  methods: {
    toggleMenu() {
      this.menu = !this.menu;
    },
    toggleAddMenu() {
      this.addMenu = !this.addMenu;
    },
    logout() {
      this.$cookies.remove('login');
      this.$cookies.remove('autoLogin');
      location.href = '/welcome';
    }
  },
};
</script>

<style scoped>
a {
  font-family: "Jalnan";
}
input {
  font-family: "Jalnan";
  font-size: 17px;
}
.button {
  border-radius: 30px;
  font-size: 17px;
}
#add {
  list-style: none;
  margin-bottom: 1rem;
}
#add > li {
  text-indent: -10%;
}
#add > li > a {
  color: white;
  display: block;
  text-decoration: none;
}
</style>
