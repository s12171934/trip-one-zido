<template>
  <div id="map"></div>
</template>

<script>
export default {
  data() {
    return {
      map: null,
      coordinates: [],
    };
  },
  async mounted() {
    const planData = await this.$zido.getPlanData(this.$route.params.id);

    if(planData.spots.length != 0){
      //일정 게시글에 딸린 장소가 있을 경우
      planData.spots.sort((a, b) => new Date(a.startDate) - new Date(b.startDate))
      for (let spot of planData.spots) {
        const coordinate = await this.getCoordinate(spot.address);
        this.coordinates.push(coordinate);
      }
      this.loadMap(this.coordinates);
      this.drawLine(this.coordinates);
    } else {
      //일정 게시글에 딸린 장소가 없을 경우 고정 좌표 표시
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(36, 128),
        level: 13,
      };
      this.map = new window.kakao.maps.Map(container, options);
    }
  },
  methods: {
    loadMap(coordinates) {
      console.log(coordinates[0]);
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(
          coordinates[0].y,
          coordinates[0].x
        ),
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
      if (res.data.documents[0].address != null) {
        return res.data.documents[0].address;
      } else {
        return res.data.documents[0].road_address;
      }
    },

    drawLine(coordinates) {
      console.log(coordinates);
      let points = coordinates.map(
        (coordinate) => new kakao.maps.LatLng(coordinate.y, coordinate.x)
      );

      // 지도에 표시할 선을 생성합니다
      var polyline = new kakao.maps.Polyline({
        path: points, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: "#ff928e", // 선의 색깔입니다
        strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });
      polyline.setMap(this.map);

      var imageSrc = "/images/sakura.png", // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imageOption
      );

      var bounds = new kakao.maps.LatLngBounds();
      for (let i = 0; i < points.length; i++) {
        // 배열의 좌표들이 잘 보이게 마커를 지도에 추가합니다
        let marker = new kakao.maps.Marker({
          position: points[i],
          image: markerImage,
        });
        marker.setMap(this.map);

        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(points[i]);

        this.map.setBounds(bounds);
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
