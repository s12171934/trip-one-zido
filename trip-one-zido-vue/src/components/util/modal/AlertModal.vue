<template>
<button type="button" @click="toggle" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModal">
				비번재설정 실패 모달
			</button>

  <div class="modal fade" :class="shown ? 'show' : ''" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel">
				<!-- Vertically centered scrollable modal -->
				<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
					<div class="modal-content">
						<div class="modal-header border border-0">
						<!-- <h1 class="modal-title fs-5" id="exampleModalLabel">타이틀 없애고</h1> -->
						<button @click="toggle" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>

						<div class="m-5 modal-body border rounded-3" id="modalBox">
						<h3>비밀번호와 </h3>
						<h3>비밀번호확인이 </h3>
						<h3>다릅니다</h3>
						</div>

						<div class="modal-footer border border-0">
						<button @click="toggle" type="button" class="rounded-3" data-bs-dismiss="modal" >비밀번호재설정하기</button>
						<!-- <button type="button" class="rounded-3" style="color: aliceblue; background-color:#ff928e;" data-bs-dismiss="modal">아이디찾기</button> -->
						</div>
					</div>
				</div>
			</div>
</template>

<script>
export default {
  data() {
    return{
      shown : false
    }
  },
  methods : {
    toggle(){
      this.shown = !this.shown;
    }
  }
}
</script>

<style scoped>
#modalButton, #button {
  color: rgb(255, 255, 255) !important;
  background-color: #ff928e !important;
}

#modalBox {
  background-color: #D9D9D9;
}

h4 {
  color: black !important;
}

.show {
  opacity: 100;
  display: block;
}


#button {
    font-family: 'Jalnan';
    border-radius: 30px;
    font-size: 17px;
}
button {
    color: rgb(255, 255, 255); 
    background-color:#ff928e;
}

#id {
    margin-left: 5%; margin-right: 5%;
}

h1 {
    color: #ff928e !important;
}
#actions {
    font-family: 'Jalnan';
    font-size: 20px;
}

#trHeader{
    background-color: white;
}


#changeBox {
    margin-left: 40%;
}

#centerPosition {
    text-align: center;
}

#header, h1, h3, section {
	font-family: 'Jalnan';
}

head {
	font-family: 'Jalnan';
}

#menu {
	font-family: 'Jalnan';
	font-size: 20px;
}

#footer {
	font-family: 'Jalnan';
	font-size: 10px;
}
</style>