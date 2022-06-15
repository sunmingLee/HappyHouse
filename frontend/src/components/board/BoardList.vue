<template>
  <b-container class="content bv-example-row mt-3">
    <b-row>
      <b-col>
        <card style="text-align: center"><h3>공지사항</h3></card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="outline-primary"
          @click="moveWrite()"
          v-if="userInfo && userInfo.roles === 'ROLE_USER,ROLE_ADMIN'"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th class="col-sm-2">글번호</b-th>
              <b-th class="col-sm-4">제목</b-th>
              <b-th class="col-sm-2">작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <board-list-item
              v-for="article in pageItems"
              :key="article.announcementNo"
              v-bind="article"
            />
          </tbody>
        </b-table-simple>
        <b-pagination
          v-model="currentPage"
          :total-rows="articles.length"
          :per-page="perPage"
          align="center"
        ></b-pagination>
      </b-col>
      <b-col v-else class="text-center">등록된 글이 없습니다.</b-col>
    </b-row>
  </b-container>
</template>

<script>
/* eslint-disable */
import { listArticle } from "@/api/board.js";
import BoardListItem from "@/components/board/item/BoardListItem";
import { mapState } from "vuex";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      // 전체 글목록
      articles: [],
      currentPage: 1,
      perPage: 5,
      // 페이지 별 글목록
      pageItems: [],
    };
  },
  created() {
    listArticle(
      ({ data }) => {
        this.articles = data;
        this.setPage();
      },
      (error) => {
        console.log(error);
      },
    );
  },
  watch: {
    currentPage() {
      this.setPage();
    },
  },
  methods: {
    isAdmin: () => {
      return this.userInfo.includes("ROLE_ADMIN");
    },
    // isAdmin() {
    //   console.log(this.userInfo);
    // },
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    setPage() {
      const { currentPage, perPage } = this;
      const start = (currentPage - 1) * perPage;
      const end = currentPage * perPage;

      this.pageItems = this.articles.slice(start, end);
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
