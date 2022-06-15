<template>
  <b-container v-if="houses && houses.length != 0" class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>검색 결과</b-alert></b-col>
    </b-row>
    <b-table-simple hover responsive>
      <b-thead head-variant="dark">
        <b-tr>
          <b-th class="col-sm-2">아파트명</b-th>
          <b-th>매매가</b-th>
        </b-tr>
      </b-thead>
      <tbody>
        <house-list-item
          v-for="(house, index) in pageItems"
          :key="index"
          :house="house"
        />
      </tbody>
    </b-table-simple>
    <b-pagination
      v-model="currentPage"
      :total-rows="houses.length"
      :per-page="perPage"
      align="center"
    >
    </b-pagination>
  </b-container>
  <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import HouseListItem from "@/components/house/HouseListItem.vue";
import { mapState, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListItem,
  },
  data() {
    return {
      perPage: 10,
      currentPage: 1,
      pageItems: [],
    };
  },
  created() {
    this.CLEAR_HOUSE_LIST();
  },
  computed: {
    ...mapState(houseStore, ["houses", "isOK"]),
  },
  watch: {
    currentPage() {
      this.setPage();
    },
    isOK() {
      this.setPage();
    },
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSE_LIST"]),
    setPage() {
      const { currentPage, perPage } = this;
      const start = (currentPage - 1) * perPage;
      const end = currentPage * perPage;

      this.pageItems = this.houses.slice(start, end);
    },
  },
};
</script>

<style></style>
