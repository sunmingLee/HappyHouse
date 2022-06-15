<template>
  <component :is="tag" @click.native="hideSidebar" v-bind="$attrs" tag="li">
    <a class="nav-link" v-bind="$attrs">
      <slot>
        <i v-if="link.icon" :class="link.icon"></i>
        <p>{{ userInfo.user_name }}</p>
      </slot>
    </a>
  </component>
</template>
<script>
import { mapState } from "vuex";

export default {
  inheritAttrs: false,
  inject: {
    autoClose: {
      default: true,
    },
  },
  props: {
    link: {
      type: [String, Object],
      default: () => {
        return {
          name: "",
          path: "",
          icon: "",
        };
      },
    },
    tag: {
      type: String,
      default: "router-link",
    },
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    hideSidebar() {
      if (
        this.autoClose &&
        this.$sidebar &&
        this.$sidebar.showSidebar === true
      ) {
        this.$sidebar.displaySidebar(false);
      }
    },
  },
};
</script>
<style></style>
