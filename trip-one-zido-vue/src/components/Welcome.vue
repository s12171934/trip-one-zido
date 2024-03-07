<template>
  <section id="banner" >
				<div class="content">
					<h1 style="color: beige;">오늘 봄,</h1>
					<h1 style="color: beige;">여기 괜찮을 지도</h1>
					<br>
					<br>
					<h1 style="color: beige;">쉽게 작성 할 수 있는 여행 리뷰</h1>
					<ul class="actions">
						<li><a href="/login" class="button scrolly">시작해보기</a></li>
					</ul>
				</div>
			</section>
			<!-- One -->
			<section id="one" class="wrapper"><div class="inner flex flex-3">
					<div class="flex-item left">
						<div>
							<h4>여행 계획, 더이상 고민없이</h4>
							<p>가벼운 터치로 누구나 알기 쉽게<br>나만의 여행 계획 세우기</p>
						</div>
						<div>
							<h4>여행 사진, 손쉽게 관리하기</h4>
							<p>간편한 업로드, 제한 없이<br>다른 사람의 사진도 제한없이</p>
						</div>
					</div>
					<div class="flex-item image fit round">
						<img src="/images/pic01.jpg" alt="" width="330" height="330"></div>
					<div class="flex-item right">
						<div>
							<h4>관광지를, 한눈에 알기쉽게</h4>
							<p>가고 싶었던 관광지를 한번에<br>다른 사람의 추천 맛집도 한번에</p>
						</div>
						<div>
							<h4>커뮤니티, 여행도 같이가자</h4>
							<p>때때로 혼자보다는 여럿이서<br>여러분의 친구와 함께 여행을</p>
						</div>
					</div>
				</div>
			</section>
      <section id="three" class="wrapper"><div class="inner flex flex-3">
					<div class="flex-item box">
						<div class="image fit">
							<img src="/images/pic02.jpg" alt="" width="418" height="200"></div>
						<div class="content">
							<h3>홀로 여행 계획</h3>
							<p>실패하는 여행계획은 이제 그만, <br>손 안에서 가볍게 그리고 알기 쉽게</p>
						</div>
					</div>
					<div class="flex-item box">
						<div class="image fit">
							<img src="/images/pic03.jpg" alt="" width="418" height="200"></div>
						<div class="content">
							<h3>찍은 사진 공유</h3>
							<p>가보지 않은 곳, 몰랐던 곳, <br>대한민국의 좋은 여행지를 한눈에</p>
						</div>
					</div>
					<div class="flex-item box">
						<div class="image fit">
							<img src="/images/pic04.jpg" alt="" width="418" height="200"></div>
						<div class="content">
							<h3>친구들과 함께</h3>
							<p>혼자만의 여행이 싫다면, <br>친구들과 함께 손쉽게 떠나는 여행</p>
						</div>
					</div>
				</div>
			</section>
</template>

<script>
export default {
	mounted(){
    this.$emit("meta",this.$route.matched[0].meta.isLogin);
  }
}
</script>

<style scoped>
h1,h3,h4,p,a {
  font-family: 'Jalnan';
}

</style>