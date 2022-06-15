import axios from "axios";

// axios 객체 생성
const axiosService = axios.create({
  // export default axios.create({
  // .env.local에서 본인 서버 세팅
  baseURL: process.env.VUE_APP_API_URL,
  headers: {
    "Content-type": "application/json",
  },
});

/* ---사용자--- */

function registerUser(userData) {
  return axiosService.post("/", userData);
}

/* ---사용자--- */

/* ---게시판--- */
function getBoard(articleno) {
  return axiosService.get(`/${articleno}`);
}

function getBoards() {
  return axiosService.get("");
}

function deleteBoard(articleno) {
  return axiosService.delete(`/${articleno}`);
}

/* ---게시판--- */

/* ---Qna--- */
function getQna(articleno) {
  return axiosService.get(`/qna/detail/${articleno}`);
}

function getQnas() {
  return axiosService.get("/qna/list");
}

function registQna(data) {
  return axiosService.post("/qna/write", data);
}

function modifyQna(data) {
  return axiosService.put(`/qna/write/${data.articleno}`, data);
}

function replyQna(data) {
  return axiosService.post(`/qna/write/${data.articleno}`, data);
}

function deleteQna(articleno, ref) {
  return axiosService.delete(`/qna/write/${articleno}/${ref}`);
}

/* ---Qna--- */

export {
  registerUser,
  deleteBoard,
  getBoard,
  getBoards,
  getQna,
  getQnas,
  registQna,
  modifyQna,
  deleteQna,
  replyQna,
};
