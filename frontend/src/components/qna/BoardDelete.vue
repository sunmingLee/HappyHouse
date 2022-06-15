<template>
  <b-container class="bv-example-row mt-3 content">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger">삭제처리중...</b-alert>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
/* eslint-disable */
import { deleteQna } from "@/api/qna";

export default {
  name: "BoardDelete",
  created() {
    deleteQna(
      this.$route.params.articleno,
      parseInt(this.$route.params.ref),
      () => {
        // let msg = "삭제 처리시 문제가 발생했습니다.";
        // if (data === "success") {
        //   msg = "삭제가 완료되었습니다.";
        // }
        alert("삭제가 완료되었습니다.");
        // 현재 route를 /list로 변경.
        this.$router.push({ name: "qnaList" });
      },
    );
  },
};
</script>

<style></style>
