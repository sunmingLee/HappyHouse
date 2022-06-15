/* eslint-disable */
import { sidoList, gugunList, dongList, houseList } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "시/도를 선택하세요" }],
    guguns: [{ value: null, text: "구/군을 선택하세요" }],
    dongs: [{ value: null, text: "동을 선택하세요" }],
    houses: [],
    house: null,
    isOK: false,
  },

  getters: {
    getHouseListPositions: function (state) {
      return state.houses.map((house) => [house.lat, house.lng]);
    },
    getHouseList: function (state) {
      return state.houses;
    },
  },

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sido, text: sido.sido });
      });
      // console.log(sidos);
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugun, text: gugun.gugun });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dong, text: dong.dong });
      });
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "시/도를 선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "구/군을 선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "동을 선택하세요" }];
    },
    SET_HOUSE_LIST: (state, houses) => {
      state.houses = houses;
      state.isOK = true;
      // console.log(houses);
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
      state.isOK = false;
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getGugun: ({ commit }, sidoName) => {
      // console.log(sidoName);
      gugunList(
        sidoName,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getDong: ({ commit }, [sidoName, gugunName]) => {
      // console.log(sidoName, gugunName);
      dongList(
        sidoName,
        gugunName,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    getHouseList: ({ commit }, [sidoName, gugunName, dongName, year]) => {
      houseList(
        sidoName,
        gugunName,
        dongName,
        year,
        ({ data }) => {
          //   console.log(response.data.response.body.items.item);
          commit("SET_HOUSE_LIST", data);
        },
        (error) => {
          console.log(error);
        },
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;
