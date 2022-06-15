<template>
  <b-container class="content bv-example-row mt-3" v-if="userInfo">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col
        class="text-right"
        v-if="
          userInfo.id === article.user_id ||
          userInfo.roles === 'ROLE_USER,ROLE_ADMIN'
        "
      >
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleno}.
          ${article.subject} </h3><div><h6>${article.user_id}</div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col
        class="text-right"
        v-if="
          userInfo.id === article.author ||
          userInfo.roles === 'ROLE_USER,ROLE_ADMIN'
        "
      >
        <b-button variant="outline-primary" @click="answerArticle"
          >댓글 쓰기</b-button
        >
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
/* eslint-disable */
// import moment from "moment";
import { getQna } from "@/api/qna";
import { mapState } from "vuex";

export default {
  name: "QnaDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    getQna(this.$route.params.articleno, ({ data }) => {
      this.article = data;
      console.log(this.article);
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "qnaList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "qnaModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      console.log(this.article.ref);
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "qnaDelete",
          params: { articleno: this.article.articleno, ref: this.article.ref },
        });
      }
    },
    answerArticle() {
      this.$router.replace({
        name: "replyRegister",
        params: { articleno: this.article.articleno },
      });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style></style>
