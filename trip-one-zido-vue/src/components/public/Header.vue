<template>
  <header id="header" :style="path != '/' ? '' : 'box-shadow: none'">
    <nav v-if="isLogin" class="left">
      <a
        href="#menu"
        data-bs-toggle="offcanvas"
        role="button"
        aria-controls="menu"
        ><span>Menu</span></a
      >
    </nav>

    <div v-if="path != '/'" class="h-100">
      <a @click="$router.push('/')">
        <img src="/images/여행한지도_로고.png" class="logo" width="325" />
      </a>

      <nav v-if="isLogin" class="right d-flex gap-2 align-items-center">
        <form @submit.prevent="$router.push(`/search/${keyword}`)" class="m-0">
          <input type="text" id="headerSearchBar" v-model="keyword" required />
        </form>
        <span class="image round d-flex h-100 align-items-center" box-sha>
          <img
            @click="goMypage()"
            :src="img 
              ? `data:image/jpeg;base64,${img}` 
              : '/images/nomal.jpeg'"
            height="75" width="75"
          />
        </span>
      </nav>
      <nav v-else class="right d-flex gap-2 align-items-center">
        <a @click="$router.push('/login')" class="button alt"> 로그인 </a>
        <a @click="$router.push('/sign-up')" class="button alt"> 회원가입 </a>
      </nav>
    </div>
  </header>

  <nav
    id="menu"
    class="offcanvas offcanvas-start"
    tabindex="-1"
    aria-labelledby="menu"
  >
    <ul class="links">
      <li>
        <a @click="toggleAddMenu">게시글 등록</a>
        <ul id="add" v-if="addMenu">
          <li>
            <a @click="goTo('/add/plan')" data-bs-dismiss="offcanvas"
              >일정 게시글 등록</a
            >
          </li>
          <li>
            <a @click="goTo('/add/spot')" data-bs-dismiss="offcanvas"
              >장소 게시글 등록</a
            >
          </li>
          <li>
            <a @click="goTo('/add/community')" data-bs-dismiss="offcanvas"
              >커뮤니티 게시글 등록</a
            >
          </li>
        </ul>
      </li>
      <li>
        <a @click="goTo('/community')" data-bs-dismiss="offcanvas">커뮤니티</a>
      </li>
      <li>
        <a @click="goTo('/recent-view')" data-bs-dismiss="offcanvas">최근기록</a>
      </li>
      <li>
        <a @click="goTo('/bookmark')" data-bs-dismiss="offcanvas">찜목록</a>
      </li>
      <li>
        <a @click="goTo('/tour/loc')" data-bs-dismiss="offcanvas">관광정보</a>
      </li>
      <li>
        <a @click="logout">로그아웃</a>
      </li>
    </ul>
    <a
      href="#menu"
      class="close"
      data-bs-dismiss="offcanvas"
      aria-label="Close"
    ></a>
  </nav>
</template>

<script>
export default {
  props: {
    isLogin: Boolean,
    path: String,
  },
  data() {
    return {
      menu: false,
      addMenu: false,
      keyword: "",
      img: null,
    };
  },
  methods: {
    goMypage(){
      location.href = "/member-page";
    },
    toggleAddMenu() {
      this.addMenu = !this.addMenu;
    },
    logout() {
      this.$cookies.remove("login");
      this.$cookies.remove("autoLogin");
      location.href = "/welcome";
    },
    goTo(path) {
      this.$router.push(path);
    },
  },
  mounted(){
    if(this.$cookies.get("login") != null){
      //프로필 사진 가져오기 GET -- api/member/profile
      this.$zido.getProfileImg().then((res) => this.img = res)
      console.log("응답이미지값")
      console.log(this.img)
    }
  }
};
</script>

<style scoped>
input {
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
