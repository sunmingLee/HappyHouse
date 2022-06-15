import { apiInstance } from "./index.js";

const api = apiInstance();
// const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/apartment/sido`).then(success).catch(fail);
}

function gugunList(sido, success, fail) {
  api.get(`/apartment/gugun/${sido}`).then(success).catch(fail);
}

function dongList(sido, gugun, success, fail) {
  api.get(`/apartment/dong/${sido}/${gugun}`).then(success).catch(fail);
}

function houseList(sido, gugun, dong, year, success, fail) {
  api
    .get(`/apartment/list/${sido}/${gugun}/${dong}/${year}`)
    .then(success)
    .catch(fail);
}

function maxDeal(success, fail) {
  api.get("/apartment/maxdeal").then(success).catch(fail);
}

function dealCount(success, fail) {
  api.get("/apartment/dealcnt").then(success).catch(fail);
}

function mostDealApt(success, fail) {
  api.get("/apartment/mostdealapart").then(success).catch(fail);
}

function dealCountEachMonth(success, fail) {
  api.get("/apartment/dealcnt/month").then(success).catch(fail);
}

function mostDealAptTopX(top, success, fail) {
  api.get(`/apartment/mostdealapart/${top}`).then(success).catch(fail);
}

function rankBySquareFeet(success, fail) {
  api.get("/apartment/squarefeet/rank").then(success).catch(fail);
}

function newsList(success, fail) {
  api.get("/newscrawling").then(success).catch(fail);
}

export {
  sidoList,
  gugunList,
  dongList,
  houseList,
  maxDeal,
  dealCount,
  mostDealApt,
  dealCountEachMonth,
  mostDealAptTopX,
  rankBySquareFeet,
  newsList,
};
