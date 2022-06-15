<template>
  <b-tr>
    <b-td>{{ article.articleno }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{ name: 'qnaDetail', params: { articleno: article.articleno } }"
        >{{ article.subject }}</router-link
      >
    </b-th>
    <b-td>{{ article.ref }}</b-td>
    <b-td>{{ article.userid }}</b-td>
    <b-td>{{ article.regdate }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    // articleno: Number,
    // userid: String,
    // subject: String,
    // ref: Number,
    // regtime: String,
    article: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
