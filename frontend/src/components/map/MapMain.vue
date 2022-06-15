<template>
  <div class="content">
    <MapHeader />
    <div class="map__main">
      <div class="main__side-bar no-padding">
        <div class="side-bar__header">
          <span v-if="houses && houses.length !== 0"
            >검색된 아파트 {{ getHouseList.length }}개</span
          >
        </div>
        <div class="side-bar__content">
          <template v-if="!(houses && houses.length !== 0)">
            <div key="app-list" class="content_no-list">
              <h1>검색된 결과가 없습니다.</h1>
            </div>
          </template>
          <template v-else>
            <div v-if="houses && houses.length !== 0">
              <ul
                class="content__list"
                v-if="getHouseList.length !== 0"
                key="app-list"
              >
                <AptItem
                  v-for="(house, index) in getHouseList"
                  :key="index"
                  :house="house"
                  :index="index"
                />
              </ul>
            </div>
          </template>
        </div>
      </div>
      <div class="main__map">
        <KakaoMap ref="kakaoMap" />
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapGetters } from "vuex";
import KakaoMap from "@/components/map/KakaoMap.vue";
import AptItem from "@/components/map/AptItem.vue";
import MapHeader from "@/components/map/MapHeader";
import { numberToKorean } from "@/utils/filters";
export default {
  components: {
    KakaoMap,
    AptItem,
    MapHeader,
  },
  created() {
    this.CLEAR_HOUSE_LIST();
  },
  data() {
    return {
      filled: false,
      likedStatus: [],
      status: {
        aptStatus: false,
      },
    };
  },
  computed: {
    ...mapGetters("houseStore", ["getHouseList"]),
    ...mapState("houseStore", ["houses"]),
  },
  methods: {
    ...mapMutations("houseStore", ["CLEAR_HOUSE_LIST"]),

    initKakaoRoadview(latitude, longitude) {
      const roadviewContainer = document.getElementById("roadview");
      const roadview = new kakao.maps.Roadview(roadviewContainer);
      const roadviewClient = new kakao.maps.RoadviewClient();
      const position = new kakao.maps.LatLng(latitude, longitude);

      roadviewClient.getNearestPanoId(position, 50, function (panoId) {
        roadview.setPanoId(panoId, position);
      });
    },
    OnRoadView(latitude, longitude) {
      if (window.kakao && window.kakao.maps) {
        this.initKakaoRoadview(latitude, longitude);
      } else {
        const script = document.createElement("script");

        /* global kakao */
        script.onload = () => kakao.maps.load(this.initKakaoRoadview);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=0f4c22d8cdfcb5038140db4f10d9fdcd";
        document.head.appendChild(script);
      }

      this.ON_ROAD_VIEW();
    },
    OffRoadView() {
      this.OFF_ROAD_VIEW();
    },
    aptValueToWon(data) {
      return numberToKorean(parseInt(data.replaceAll(",", "")) * 10000);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "scss/mapMain";
</style>
