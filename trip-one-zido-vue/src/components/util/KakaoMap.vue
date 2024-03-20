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
    this.$zido.getAddress(this.$route.params.id)
    .then((res) => {
      this.getCoordinate(res)
      .then((res) => this.loadMap(res))
    })
  },
  methods: {
    loadMap(coordinate) {
      const container = document.getElementById("map");
      console.log(coordinate);
      const options = {
        center: new window.kakao.maps.LatLng(coordinate.y, coordinate.x),
        level: 4
      };

      this.map = new window.kakao.maps.Map(container, options);
    },

    async getCoordinate(address) {
      const res = await this.axios.get(
        `https://dapi.kakao.com/v2/local/search/address.json?query=${encodeURIComponent(
          address
        )}`,
        {
          headers: {
            Authorization: "KakaoAK 9f43f99ff692bc46c266ddd1b0118864",
          },
        }
      );
      if(res.data.documents[0].address != null){
        return res.data.documents[0].address
      } else{
        return res.data.documents[0].road_address
      }
    },
  },
};
</script>

<style scoped>
#map {
  height: 500px;
  width: 100%;
}
</style>
