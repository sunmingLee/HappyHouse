<template>
  <li class="list__item">
    <div class="item__info">
      <div
        class="info__desc"
        style="flex: 1; justify-content: space-evenly; flex-direction: row"
      >
        <div>
          <img
            :src="require('../../assets/apt.png')"
            style="width: 100px; height: 100px"
            :alt="house.aptName"
          />
        </div>
        <div>
          <h5>[{{ house.aptName }}]</h5>
          <p class="desc__sub-desc">건축연도: {{ house.buildYear }}년</p>
          <p class="desc__sub-desc">{{ house.dongName }}</p>
          <div class="desc__badges">
            <p class="desc__price">
              매매 {{ house.dealAmount | aptValueToWon }}
            </p>
          </div>
        </div>
      </div>
    </div>
  </li>
</template>

<script>
import { mapActions } from "vuex";
import { numberToKorean } from "@/utils/filters";
// import { postLikedItem, deleteLikedItem } from "@/api/user";

export default {
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  computed: {},

  methods: {
    ...mapActions("houseStore", ["detailHouse"]),
    selectHouse() {
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },
  filters: {
    aptValueToWon(data) {
      return numberToKorean(parseInt(data.replaceAll(",", "")) * 10000);
    },
  },
};
</script>

<style lang="scss" scoped>
@import "scss/aptItem";
</style>
