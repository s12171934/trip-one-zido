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
        level: 13,
      };
      
      this.map = new window.kakao.maps.Map(container, options);
      this.map.setDraggable(false);
      this.map.setZoomable(false);
      this.map.setMinLevel(13);
    },
  },
};
</script>

<style scoped>
#map {
  height: 650px;
}
</style>
