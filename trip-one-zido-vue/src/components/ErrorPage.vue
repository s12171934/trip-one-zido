<template>
  <main>
    <h1><span>ERROR:</span> 존재하지 않는 페이지 입니다.</h1>
    <div class="d-flex justify-content-center m-5">
    <button @click="$router.push('/')">홈으로</button>
  </div>
  </main>
</template>

<script>
export default {};
</script>

<style scoped>
h1 {
  font-family: "Jalnan";
  text-align: center;
  color: grey;
  margin: 10% 0;
}

span {
  color: #ff928e;
}

button {
  width: 20%;
  border-radius: 1.5rem;
  font-family: "Jalnan";
}
</style>
