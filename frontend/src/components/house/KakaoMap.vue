<template>
  <div>
    <div id="map"></div>
    <div v-show="false">
      {{ latitude }}
      {{ longitude }}
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      latitude: 0,
      longitude: 0,
      markers: [],
    };
  },

  created() {
    if (!("geolocation" in navigator)) {
      return;
    }
    // get curPosition
    navigator.geolocation.getCurrentPosition((pos) => {
      this.latitude = pos.coords.latitude;
      this.longitude = pos.coords.longitude;
    });
  },

  computed: {
    ...mapState(houseStore, ["houses", "isOK"]),
    // sidos() {
    //   return this.$store.state.sidos;
    // },
  },

  watch: {
    isOK() {
      // console.log("OK");
      this.setAptsOnMap();
    },
  },

  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },

  updated() {
    this.initMap();
  },

  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 내위치
      this.displayMarker([
        {
          title: "현재 위치",
          latlng: new kakao.maps.LatLng(this.latitude, this.longitude),
        },
      ]);

      this.map.setCenter(new kakao.maps.LatLng(this.latitude, this.longitude));
    },

    addScript() {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOMAP_KEY}`;
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      document.head.appendChild(script);
    },

    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      var imageSize = new kakao.maps.Size(24, 35);
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      if (markerPositions.length > 0) {
        this.markers = markerPositions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position: position.latlng,
              title: position.title,
              image: markerImage,
            }),
        );
      }
    },

    setAptsOnMap() {
      console.log("setApt: ", this.houses.length);
      var apts = [];
      if (this.houses.length > 0) {
        console.log("in");
        this.houses.forEach((house) => {
          apts.push({
            title: house.aptName,
            latlng: new kakao.maps.LatLng(house.lat, house.lng),
          });
        });
      }
      this.map.setCenter(apts[0].latlng);
      this.displayMarker(apts);
    },
  },
};
</script>

<style>
#map {
  width: 100%;
  height: 500px;
}
</style>
