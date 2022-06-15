<template>
  <b-container class="bv-example-row mt-3 content">
    <b-row>
      <b-col>
        <card style="text-align: center"><h3>글작성</h3></card>
      </b-col>
    </b-row>
    <board-input-item type="register" />
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardWrite",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
