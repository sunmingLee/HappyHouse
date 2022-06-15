import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(success, fail) {
  api.get(`/announcements/list`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/announcements/write`, article).then(success).catch(fail);
}

function getArticle(announcementNo, success, fail) {
  api
    .get(`/announcements/announcement/${announcementNo}`)
    .then(success)
    .catch(fail);
}

function modifyArticle(article, success, fail) {
  api
    .put(`/announcements/${article.announcementNo}`, article)
    .then(success)
    .catch(fail);
}

function deleteArticle(announcementNo, success, fail) {
  api.delete(`/announcements/${announcementNo}`).then(success).catch(fail);
}

export { listArticle, writeArticle, getArticle, modifyArticle, deleteArticle };
