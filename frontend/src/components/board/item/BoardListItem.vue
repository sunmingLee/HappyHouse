<template>
  <b-tr>
    <b-td>{{ announcementNo }}</b-td>
    <b-th class="text-left">
      <router-link
        :to="{
          name: 'boardDetail',
          params: { announcementNo: announcementNo },
        }"
        >{{ subject }}</router-link
      >
    </b-th>
    <b-td>{{ userid }}</b-td>
    <b-td>{{ regtime | dateFormat }}</b-td>
  </b-tr>
</template>

<script>
import moment from "moment";

export default {
  name: "BoardListItem",
  props: {
    announcementNo: Number,
    userid: String,
    subject: String,
    // hit: Number,
    regtime: String,
    // article: Object,
  },
  filters: {
    dateFormat(regtime) {
      return moment(new Date(regtime)).format("YY.MM.DD");
    },
  },
};
</script>

<style></style>
