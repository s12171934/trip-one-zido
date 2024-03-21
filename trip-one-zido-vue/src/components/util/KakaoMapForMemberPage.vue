<template>
  <div id="map"></div>
</template>

<script>
import data from "@/assets/data.js";

export default {
  data() {
    return {
      map: null,
      accessToken: null,
    };
  },
  mounted() {
    this.loadMap();
    for (let locmap of data.locMap) {
      this.getPolygon(locmap);
    }
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

    async getAccessToken() {
      await this.axios
        .get(
          "https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json" +
            "?consumer_key=adbf653c1eab4c0dac2d" +
            "&consumer_secret=c1081304a9e44f95ac07"
        )
        .then((res) => (this.accessToken = res.data.result.accessToken));
    },

    async getPolygon(locmap) {
      await this.getAccessToken();
      this.axios
        .get(
          "https://sgisapi.kostat.go.kr/OpenAPI3/boundary/hadmarea.geojson" +
            `?accessToken=${this.accessToken}` +
            "&year=2023&low_search=0" +
            `&adm_cd=${locmap.code}`
        )
        .then(async (res) => {
          var coordinates = res.data.features[0].geometry.coordinates;
          const polygonPaths = [];
          for (let paths of coordinates) {
            const polygonPath = [];
            if(coordinates.length != 1 && locmap.code != 31){
              paths = paths[0]
            }
            for (let path of paths) {
              var proj4 = require("proj4");
              proj4 = require("proj4").default;
              const eps2097 =
                "+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs";
              const wgs84 = "+proj=longlat +ellps=WGS84 +datum=WGS84 +no_defs";
              const transPath = proj4(eps2097, wgs84, [path[0], path[1]]);
              polygonPath.push(
                new window.kakao.maps.LatLng(transPath[1], transPath[0])
              );
            }
            polygonPaths.push(polygonPath)
          }
          return polygonPaths;
        })
        .then((polygonPaths) => {
          for (let polygonPath of polygonPaths) {
            const polygon = new kakao.maps.Polygon({
              path: polygonPath,
              strokeWeight: 2,
              strokeColor: "#ff928e",
              strokeOpacity: 1,
              strokeStyle: "solid",
              fillColor: data.locFrequencyColor[Math.floor(locmap.count / 5)],
              fillOpacity: 1,
            });

            polygon.setMap(this.map);
          }
        });
    },
  },
};
</script>

<style scoped>
#map {
  height: 650px;
}
</style>
