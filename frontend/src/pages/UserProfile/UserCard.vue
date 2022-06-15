<template>
  <card class="card-user" v-if="userInfo">
    <img
      slot="image"
      src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
      alt="..."
    />
    <div class="author">
      <img class="avatar border-gray" src="@/assets/ssafy_logo.png" alt="..." />

      <h4 class="title">
        {{ userInfo.user_name }}<br />
        <small>{{ userInfo.id }}</small>
      </h4>
    </div>
    <p
      class="description text-center"
      v-html="handleNewLine(userInfo.aboutme)"
    ></p>
    <div slot="footer" class="text-center d-flex justify-content-center">
      <button href="#" class="btn btn-simple">
        <i class="fa fa-facebook-square"></i>
      </button>
      <button href="#" class="btn btn-simple">
        <i class="fa fa-twitter"></i>
      </button>
      <button href="#" class="btn btn-simple">
        <i class="fa fa-google-plus-square"></i>
      </button>
    </div>
  </card>
</template>
<script>
import Card from "@/components/Cards/Card.vue";
import { mapState } from "vuex";
import { handleNewLine } from "@/utils/filters";

export default {
  components: {
    Card,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    getClasses(index) {
      var remainder = index % 3;
      if (remainder === 0) {
        return "col-md-3 col-md-offset-1";
      } else if (remainder === 2) {
        return "col-md-4";
      } else {
        return "col-md-3";
      }
    },
    handleNewLine(data) {
      return handleNewLine(data);
    },
  },
};
</script>
<style></style>
