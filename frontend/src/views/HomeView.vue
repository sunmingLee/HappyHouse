<template>
  <div class="home">
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
      <b-row>
        <b-col></b-col>
        <b-col cols="10">
          <b-jumbotron
            bg-variant="muted"
            text-variant="dark"
            border-variant="dark"
          >
            <template #header>Happy House</template>

            <template #lead> 홈입니다. </template>

            <hr class="my-4" />

            <p>뉴스</p>
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: "HomeView",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
