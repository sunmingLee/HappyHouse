<template>
  <b-container class="bv-example-row mt-3 content">
    <b-row>
      <b-col>
        <card style="text-align: center"><h3>Q&A</h3></card>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="articles.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th class="col-sm-1">글번호</b-th>
              <b-th class="col-sm-4">제목</b-th>
              <b-th class="col-sm-1">원글번호</b-th>
              <b-th class="col-sm-2">작성자</b-th>
              <b-th>작성일</b-th>
            </b-tr>
          </b-thead>
          <tbody>
            <!-- 하위 component인 ListRow에 데이터 전달(props) -->
            <board-list-item
              v-for="article in pageItems"
              :key="article.articleno"
              v-bind:article="article"
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
import { getQnas } from "@/api/qna";
import BoardListItem from "@/components/qna/item/BoardListItem";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
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
    getQnas(({ data }) => {
      this.articles = data;
      for (let i = 0; i < data.length; i++) {
        this.articles[i].userid = data[i].user_id;
      }
      this.setPage();
      // console.log(data[0]);
      // console.log(this.articles[0]);
    });
  },
  watch: {
    currentPage() {
      this.setPage();
    },
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "qnaRegister" });
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
