<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          id="userid-group"
          label="작성자:"
          label-for="userid"
          description="작성자를 입력하세요."
        >
          <b-form-input
            id="userid"
            :disabled="isUserid"
            v-model="article.userid"
            type="text"
            required
            placeholder="작성자 입력..."
            readonly
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="subject"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="subject"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register' || this.type === 'reply'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
/* eslint-disable */
import { registQna, getQna, modifyQna, replyQna } from "@/api/qna";
import { mapState } from "vuex";

const userStore = "userStore";

export default {
  name: "BoardInputItem",
  data() {
    return {
      article: {
        articleno: 0,
        userid: "",
        subject: "",
        content: "",
        ref: 0,
        step: 1,
        depth: 0,
      },
      isUserid: false,
      origin: {},
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      getQna(this.$route.params.articleno, ({ data }) => {
        this.article = data;
        this.article.userid = data.user_id;
        console.log(data);
      });
      this.isUserid = true;
    } else if (this.type === "reply") {
      this.article.userid = this.userInfo.id;
      getQna(this.$route.params.articleno, ({ data }) => {
        this.origin = data;
        console.log(origin);
        this.article.ref = data.articleno;
        this.article.step = data.step + 1;
        this.article.depth = data.depth + 1;
      });
    } else {
      this.article.userid = this.userInfo.id;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.article.userid &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else {
        // console.log("")
        if (this.type === "register") {
          this.registArticle();
        } else if (this.type === "modify") {
          this.modifyArticle();
        } else {
          this.replyArticle();
        }
      }
      // this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "qnaList" });
    },
    registArticle() {
      registQna(
        {
          user_id: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
          step: this.article.step,
          depth: this.article.depth,
        },
        () => {
          // let msg = "등록 처리시 문제가 발생했습니다.";
          // if (data === "success") {
          //   msg = "등록이 완료되었습니다.";
          // }
          alert("등록이 완료되었습니다.");
          this.moveList();
        },
      );
    },
    modifyArticle() {
      modifyQna(
        {
          articleno: this.article.articleno,
          user_id: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
          ref: this.article.ref,
          step: this.article.step,
          depth: this.article.depth,
        },
        () => {
          // let msg = "수정 처리시 문제가 발생했습니다.";
          // if (data === "success") {
          //   msg = "수정이 완료되었습니다.";
          // }
          alert("수정이 완료되었습니다.");
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "qnaList" });
        },
      );
    },
    replyArticle() {
      let s = "";
      for (let re = 0; re < this.article.depth; re++) {
        s += "Re:";
      }
      this.article.subject = s + this.article.subject;
      let datas = {
        origin: this.origin,
        current: {
          user_id: this.article.userid,
          subject: this.article.subject,
          content: this.article.content,
          ref: this.article.ref,
          step: this.article.step,
          depth: this.article.depth,
        },
      };
      console.log(this.origin);
      replyQna(datas, () => {
        // let msg = "댓글 등록 처리시 문제가 발생했습니다.";
        // if (data === "success") {
        //   msg = "댓글 등록이 완료되었습니다.";
        // }
        alert("댓글 등록이 완료되었습니다.");
        this.moveList();
      });
    },
    moveList() {
      this.$router.push({ name: "qnaList" });
    },
  },
};
</script>

<style></style>
