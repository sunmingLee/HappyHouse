<template>
  <b-container class="content bv-example-row mt-3">
    <b-row>
      <b-col>
        <card style="text-align: center"><h3>글작성</h3></card>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/qna/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
