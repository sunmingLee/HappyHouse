<template>
  <div class="map_wrap">
    <div
      id="map"
      style="width: 100%; height: 100%; position: relative; overflow: hidden"
    ></div>
    <ul id="category">
      <li id="BK9" data-order="0" @click="onClickCategory('BK9', 'bank')">
        <span class="category_bg bank"></span>
        은행
      </li>
      <li id="MT1" data-order="1" @click="onClickCategory('MT1', 'mart')">
        <span class="category_bg mart"></span>
        마트
      </li>
      <li id="PM9" data-order="2" @click="onClickCategory('PM9', 'pharmacy')">
        <span class="category_bg pharmacy"></span>
        약국
      </li>
      <li id="OL7" data-order="3" @click="onClickCategory('OL7', 'gasStation')">
        <span class="category_bg oil"></span>
        주유소
      </li>
      <li id="CE7" data-order="4" @click="onClickCategory('CE7', 'cafe')">
        <span class="category_bg cafe"></span>
        카페
      </li>
      <li
        id="CS2"
        data-order="5"
        @click="onClickCategory('CS2', 'convenienceStore')"
      >
        <span class="category_bg store"></span>
        편의점
      </li>
    </ul>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { numberToKorean } from "@/utils/filters";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      mapTypeControl: null,
      zoomControl: null,
      placeOverlay: null,
      contentNode: null,
      markers: [],
      aptMarkers: [],
      currCategory: "",
      infowindow: null,
      ps: null,
      overlay: null,
      categories: [
        "bank",
        "mart",
        "pharmacy",
        "gasStation",
        "cafe",
        "convenienceStore",
      ],
      geocoder: null,
      categoryStatus: {
        bank: "off",
        mart: "off",
        pharmacy: "off",
        gasStation: "off",
        cafe: "off",
        convenienceStore: "off",
      },
    };
  },
  computed: {
    ...mapGetters("houseStore", ["getHouseListPositions", "getHouseList"]),
  },
  created() {},
  watch: {
    getHouseListPositions: function () {
      this.searchPlaces();
      this.displayMarker(this.getHouseListPositions);
    },
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initKakaoMap();
    } else {
      const script = document.createElement("script");

      /* global kakao */
      script.onload = () => kakao.maps.load(this.initKakaoMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=578d9a95ba9f20564647a4a7abd052db&libraries=services";
      document.head.appendChild(script);
    }
  },

  methods: {
    initKakaoMap() {
      const mapContainer = document.getElementById("map");
      const mapOptions = {
        center: new kakao.maps.LatLng(37.5403169, 126.99526),
        level: 7,
      };

      this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
      this.map = new kakao.maps.Map(mapContainer, mapOptions);
      this.mapTypeControl = new kakao.maps.MapTypeControl();
      this.zoomControl = new kakao.maps.ZoomControl();
      this.ps = new kakao.maps.services.Places(this.map);
      this.contentNode = document.createElement("div");
      this.geocoder = new kakao.maps.services.Geocoder();
      kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
      this.map.addControl(
        this.mapTypeControl,
        kakao.maps.ControlPosition.TOPRIGHT,
      );
      this.map.addControl(this.zoomControl, kakao.maps.ControlPosition.RIGHT);

      this.contentNode.className = "placeinfo_wrap";
      this.addEventHandle(
        this.contentNode,
        "mousedown",
        kakao.maps.event.preventMap,
      );
      this.addEventHandle(
        this.contentNode,
        "touchstart",
        kakao.maps.event.preventMap,
      );
      this.placeOverlay.setContent(this.contentNode);
      this.displayMarker(this.getHouseListPositions);
    },
    addEventHandle(target, type, callback) {
      if (target.addEventListener) {
        target.addEventListener(type, callback);
      } else {
        target.attachEvent("on" + type, callback);
      }
    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      this.placeOverlay.setMap(null);
      this.removeMarker();
      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    placesSearchCB(data, status) {
      if (status === kakao.maps.services.Status.OK) {
        this.displayPlaces(data);
      }
    },
    displayPlaces(places) {
      const kakaoMap = this;
      const order = document
        .getElementById(this.currCategory)
        .getAttribute("data-order");

      for (let i = 0; i < places.length; i++) {
        const marker = this.addMarker(
          new kakao.maps.LatLng(places[i].y, places[i].x),
          order,
        );

        (function (marker, place) {
          kakao.maps.event.addListener(marker, "click", function () {
            kakaoMap.displayPlaceInfo(place);
          });
        })(marker, places[i]);
      }
    },
    addMarker(position, order) {
      const imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png",
        imageSize = new kakao.maps.Size(27, 28),
        imgOptions = {
          spriteSize: new kakao.maps.Size(72, 208),
          spriteOrigin: new kakao.maps.Point(46, order * 36),
          offset: new kakao.maps.Point(11, 28),
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions,
        ),
        marker = new kakao.maps.Marker({
          position: position,
          image: markerImage,
        });

      marker.setMap(this.map);
      this.markers.push(marker);

      return marker;
    },
    removeMarker() {
      for (let i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayPlaceInfo(place) {
      let content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
    onClickCategory(selectedId, key) {
      const id = selectedId;

      if (this.categoryStatus[key] === "off") {
        this.offAllCategoryStatus();
        this.categoryStatus[key] = "on";
      } else {
        this.categoryStatus[key] = "off";
      }

      this.placeOverlay.setMap(null);

      if (this.categoryStatus[key] === "on") {
        this.currCategory = id;
        this.searchPlaces();
      } else {
        this.currCategory = "";
        this.removeMarker();
      }
    },
    offAllCategoryStatus() {
      this.categories.forEach((category) => {
        this.categoryStatus[category] = "off";
      });
    },
    displayMarker(markerPositions) {
      if (this.aptMarkers.length > 0) {
        this.aptMarkers.forEach((marker) => marker.setMap(null));
        this.aptMarkers = [];
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position),
      );

      if (positions.length > 0) {
        this.aptMarkers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            }),
        );

        this.aptMarkers.forEach((marker, index) => {
          const convertedAptPrice = this.aptValueToWon(
            this.getHouseList[index].dealAmount,
          );
          const coord = new kakao.maps.LatLng(
            this.getHouseList[index].lat,
            this.getHouseList[index].lng,
          );
          let callback = (result, status) => {
            if (status === kakao.maps.services.Status.OK) {
              // if (result)

              const address = result[0].address.address_name;
              const content = `
						<div class="wrap">
							<div class="info">
								<div class="title">
									${this.getHouseList[index].aptName}
								</div>
								<div class="body">
									<div class="img">
										<img src="${require("../../assets/apt.png")}" width="73" height="70">
									</div>
									<div class="desc">
										<div class="price">
										${convertedAptPrice}
										</div>
										<div class="address">
											<span class="address__info">
												${address}
											</span>
											<span class="address__jibun">
												(지번) ${this.getHouseList[index].jibun}
											</span>
										</div>
									</div>
								</div>
							</div>
						</div>
					`;

              const overlay = new kakao.maps.CustomOverlay({
                content: content,
                map: this.map,
                position: marker.getPosition(),
              });

              kakao.maps.event.addListener(marker, "click", function () {
                overlay.setMap(this.map);
              });
            }
          };
          this.geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
        });

        console.log(this.aptMarkers);
        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds(),
        );

        this.map.setBounds(bounds);
      }
    },
    aptValueToWon(data) {
      return numberToKorean(parseInt(data.replaceAll(",", "")) * 10000);
    },
    searchAddrFromCoords(coords, callback) {
      // 좌표로 행정동 주소 정보를 요청합니다
      this.geocoder.coord2RegionCode(
        coords.getLng(),
        coords.getLat(),
        callback,
      );
    },

    searchDetailAddrFromCoords(coords, callback) {
      // 좌표로 법정동 상세 주소 정보를 요청합니다
      this.geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
    },
  },
};
</script>

<style lang="scss">
@import "scss/kakaoMap";
</style>
