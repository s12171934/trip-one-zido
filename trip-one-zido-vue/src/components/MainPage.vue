<template>
  <main>
    <div class="logo-wrap">
      <a href="#"><img src="/images/여행한지도_로고.png" /></a>
    </div>

    <form @submit.prevent="$router.push(`/search/${keyword}`)">
      <div class="searchbar-wrap">
        <!-- <input class="search-input" type="text"/> -->
        <i class="icon fa-search"></i>
        <input
          v-model="keyword"
          type="text"
          class="search-input"
          placeholder="검색어를 입력해주세요"
        />
      </div>
    </form>

    <div class="buttons">
      <a @click="$router.push('/member-page')" class="button" id="buttons"
        >마이페이지</a
      >
      <a
      @click="$router.push('/community')"
        class="button"
        id="buttons"
        >커뮤니티</a
      >
      <a @click="$router.push('/tour/loc')" class="button" id="buttons"
        >관광정보</a
      >
      <a @click="$router.push('/bookmark')" class="button" id="buttons">찜</a>
    </div>
  </main>
</template>

<script>
export default {
  data(){
    return{
      keyword: ""
    }
  },
  mounted(){
    this.$emit("meta",this.$route.matched[0].meta.isLogin);
  }
};
</script>

<style scoped>
main {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 60vh;
    margin-bottom: 90px;
}

.logo-wrap {
  margin-bottom: 50px;
}

.searchbar-wrap {
    border: solid 1px #dee0e5;
    width: 490px;
    height: 50px;
    border-radius: 25px;
    display: flex;
    justify-content: space-evenly;
    align-content: center;
    padding-left: 8px;
    padding-right: 8px;


}
.searchbar-wrap i {
    line-height: 50px;
    color: #999fa6;

}

.main .searchbar-wrap .search-input {
    width: 400px;
    height: 100% !important;
    font-size: 16px;
    border: none;
    outline: none;
}

input {
  height: 100% !important;
  margin: 0 1rem !important;
  border: none !important;
}

#buttons{
    background-color: #f0f1f0;
    border: 0;
    padding-left: 15px;
    padding-right: 15px;
    margin: 5px;
    border-radius: 5px;
    font-size: 14px;
    color: #5F6368;
}

#buttons:hover{
  background-color: #ff8783;
  color: white;
}

/* 화면이 작아질 때 스타일 수정 */
@media (max-width: 576px) {
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .logo-wrap img{
    max-width: 100% ; /* 이미지 너비 조정 */
    height: auto;
    margin-bottom: 20px; /* 로고와 검색바 사이 여백 추가 */
  }

  .searchbar-wrap {
    width: 100%; /* 검색바 너비 조정 */
    margin-bottom: 20px; /* 검색바 아래 여백 추가 */
  }

  .search-input {
    width: calc(100% - 40px); /* 검색 입력란 너비 조정 */
    padding-left: 40px; /* 아이콘과 입력란 사이 여백 추가 */
  }

  .buttons {
    width: 80%; /* 버튼 그룹 너비 조정 */
    display: flex;
    flex-direction: column; /* 버튼들을 세로로 나열 */
    align-items: center; /* 버튼들을 가운데 정렬 */
  }

  .button {
    width: 100%; /* 버튼 너비를 100%로 설정하여 가로폭 전체를 차지하도록 함 */
    margin-bottom: 10px; /* 버튼 아래 여백 추가 */
  }
}
</style>