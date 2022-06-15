import { apiInstance } from "./index.js";

const api = apiInstance();

function getQna(articleno, data) {
  api.get(`/qna/detail/${articleno}`).then(data);
}

function getQnas(data) {
  api.get("/qna/list").then(data);
}

function registQna(data, success) {
  api.post("/qna/write", data).then(success);
}

function modifyQna(data, success) {
  api.put(`/qna/write/${data.articleno}`, data).then(success);
}

function replyQna(data, success) {
  api.post(`/qna/write/${data.articleno}`, data).then(success);
}

function deleteQna(articleno, ref, success) {
  api.delete(`/qna/write/${articleno}/${ref}`).then(success);
}

export { getQna, getQnas, registQna, modifyQna, deleteQna, replyQna };
