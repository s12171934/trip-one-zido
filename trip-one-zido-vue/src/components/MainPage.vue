<template>
  <section class="main-section">
    <div class="logo-wrap">
      <a href="#"><img src="/images/여행한지도_로고.png" /></a>
    </div>

    <form action="/html-css/search/search.html">
      <div class="searchbar-wrap">
        <!-- <input class="search-input" type="text"/> -->
        <i class="icon fa-search"></i>
        <input
          name="q"
          type="text"
          class="search-input"
          placeholder="검색어를 입력해주세요"
          aria-label="Recipient's username"
          aria-describedby="button-addon2"
        />
        <i class="fas fa-keyboard"></i>
      </div>
    </form>

    <div class="buttons">
      <a href="/member-page" class="button" id="buttons"
        >마이페이지</a
      >
      <a
        href="/community"
        class="button"
        id="buttons"
        >커뮤니티</a
      >
      <a href="/tour/loc" class="button" id="buttons"
        >관광정보</a
      >
      <a href="/bookmark" class="button" id="buttons">찜</a>
    </div>
  </section>
</template>

<script>
export default {};
</script>

<style scoped>
input, a {
  font-family: 'Jalnan';
}


.main-section {
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

.main-section .searchbar-wrap .search-input {
    width: 400px;
    height: 100%;
    font-size: 16px;
    border: none;
    outline: none;
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
</style>