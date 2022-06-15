<template>
  <div class="map__header">
    <div class="map__search">
      <div class="search__form">
        <div class="navbar-brand text-muted">검색 결과</div>
      </div>
    </div>
    <div class="map__filters">
      <div class="filters__select">
        <b-col>
          <b-form-select
            v-model="sidoName"
            :options="sidos"
            @change="gugunList"
          ></b-form-select>
        </b-col>
        <b-col>
          <b-form-select
            v-model="gugunName"
            :options="guguns"
            @change="dongList"
          ></b-form-select>
        </b-col>
        <b-col>
          <b-form-select v-model="dongName" :options="dongs"></b-form-select>
        </b-col>
        <b-col>
          <b-form-select v-model="year" :options="years"></b-form-select>
        </b-col>
        <b-col class="text-right">
          <b-button variant="outline-primary" @click="searchApt()"
            >검색</b-button
          >
        </b-col>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";

export default {
  data() {
    return {
      sidoName: null,
      gugunName: null,
      dongName: null,
      year: null,
      years: [
        { value: null, text: "거래연도를 선택하세요." },
        { value: 2018, text: "2018년" },
        { value: 2019, text: "2019년" },
        { value: 2020, text: "2020년" },
        { value: 2021, text: "2021년" },
      ],
    };
  },
  computed: {
    ...mapState("houseStore", ["sidos", "guguns", "dongs", "houses"]),
  },
  methods: {
    ...mapActions("houseStore", [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations("houseStore", [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
    gugunList() {
      // console.log("@change: " + this.sidoName);
      this.CLEAR_GUGUN_LIST();
      this.gugunName = null;
      if (this.sidoName) this.getGugun(this.sidoName);
    },
    dongList() {
      // console.log("@change: " + this.gugunName);
      this.CLEAR_DONG_LIST();
      this.dongName = null;
      if (this.gugunName) this.getDong([this.sidoName, this.gugunName]);
    },
    searchApt() {
      this.CLEAR_HOUSE_LIST();
      if (this.dongName && this.year)
        this.getHouseList([
          this.sidoName,
          this.gugunName,
          this.dongName,
          this.year,
        ]);
      else alert("모든 조건을 선택해주세요.");
    },
  },
  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
  },
};
</script>

<style lang="scss" scoped>
@import "scss/mapHeader";
</style>
