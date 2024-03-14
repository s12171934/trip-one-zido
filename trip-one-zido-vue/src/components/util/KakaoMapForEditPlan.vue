<template>
  <div id="map"></div>
</template>

<script>

export default {
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    this.loadMap();
  },
  methods: {
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(36, 128),
        level: 15,
      };

      this.map = new window.kakao.maps.Map(container, options);
    },
  },
};
</script>

<style scoped>
#map {
  height: 300px;
  width: 100%;
}
</style>
