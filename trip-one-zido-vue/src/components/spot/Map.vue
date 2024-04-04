<template>
  <main class="d-flex flex-column gap-5 p-2">
    <h1>약도&nbsp;[{{ spotData.title }}]</h1>
    <h3>주소: {{ spotData.address }}</h3>
    <KakaoMap/>
  </main>
</template>

<script>
import KakaoMap from '../util/KakaoMap.vue';

export default {
  components: {
    KakaoMap
  },
  data() {
    return {
      spotData: {
        title: null,
        address: null,
      },
    };
  },
  async mounted() {
    this.$emit("meta", this.$route.matched[0].meta.isLogin);
    //GET -- /api/spot/${id}
    this.spotData = await this.$zido.getSpotData(this.$route.params.id);
    document.body.className = "p-3"
  },
};
</script>

<style>
</style>
