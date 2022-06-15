<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <div class="navbar-brand">{{ routeName }}</div>
      <!--      <button-->
      <!--        type="button"-->
      <!--        class="navbar-toggler navbar-toggler-right"-->
      <!--        :class="{ toggled: $sidebar.showSidebar }"-->
      <!--        aria-controls="navigation-index"-->
      <!--        aria-expanded="true"-->
      <!--        aria-label="Toggle navigation"-->
      <!--        @click="toggleSidebar"-->
      <!--      >-->
      <!--      </button>-->
      <div class="collapse navbar-collapse justify-content-end">
        <ul class="nav navbar-nav mr-auto">
          <!--          <li class="nav-item">-->
          <!--            <a-->
          <!--              class="nav-link navbar-toggler navbar-toggler-right"-->
          <!--              :class="{ toggled: $sidebar.showSidebar }"-->
          <!--              @click="toggleSidebar"-->
          <!--              data-toggle="dropdown"-->
          <!--            >-->
          <!--              <i class="nc-icon nc-palette"></i>-->
          <!--            </a>-->
          <!--          </li>-->
          <!--          <base-dropdown tag="li">-->
          <!--            <template slot="title">-->
          <!--              <i class="nc-icon nc-planet"></i>-->
          <!--              <b class="caret"></b>-->
          <!--              <span class="notification">5</span>-->
          <!--            </template>-->
          <!--            <a class="dropdown-item" href="#">Notification 1</a>-->
          <!--            <a class="dropdown-item" href="#">Notification 2</a>-->
          <!--            <a class="dropdown-item" href="#">Notification 3</a>-->
          <!--            <a class="dropdown-item" href="#">Notification 4</a>-->
          <!--            <a class="dropdown-item" href="#">Another notification</a>-->
          <!--          </base-dropdown>-->
          <!--          <li class="nav-item">-->
          <!--            <a href="#" class="nav-link">-->
          <!--              <i class="nc-icon nc-zoom-split"></i>-->
          <!--              <span class="d-lg-block">&nbsp;Search</span>-->
          <!--            </a>-->
          <!--          </li>-->
        </ul>
        <ul class="navbar-nav ml-auto" v-if="userInfo">
          <!--          <li class="nav-item">-->
          <!--            <a class="nav-link" href="#"> Account </a>-->
          <!--          </li>-->
          <!--          <base-dropdown title="Dropdown">-->
          <!--            <a class="dropdown-item" href="#">Action</a>-->
          <!--            <a class="dropdown-item" href="#">Another action</a>-->
          <!--            <a class="dropdown-item" href="#">Something</a>-->
          <!--            <a class="dropdown-item" href="#">Another action</a>-->
          <!--            <a class="dropdown-item" href="#">Something</a>-->
          <!--            <div class="divider"></div>-->
          <!--            <a class="dropdown-item" href="#">Separated link</a>-->
          <!--          </base-dropdown>-->
          <li class="nav-item">
            <span
              >{{ userInfo.user_name }}({{ userInfo.id }})님 환영합니다.</span
            >
          </li>

          <li class="nav-item">
            <a @click.prevent="onClickLogout" class="nav-link" href="">
              로그아웃
            </a>
          </li>
        </ul>
        <ul class="navbar-nav ml-auto" v-else>
          <li class="nav-item">
            <router-link :to="{ name: 'signUp' }" class="nav-link">
              회원가입
            </router-link>
            <router-link :to="{ name: 'signIn' }" class="nav-link">
              로그인
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>
<script>
import { mapMutations, mapState } from "vuex";

const userStore = "userStore";

export default {
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
  },
  data() {
    return {
      activeNotifications: false,
      type: ["", "info", "success", "warning", "danger"],
    };
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // console.log("memberStore : ", ms);
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      this.notifyVue(1, `<span> <b>로그아웃 하셨습니다.</b> </span>`);
      if (this.$route.path != "/overview") this.$router.push({ name: "home" });
    },
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    notifyVue(color, message) {
      this.$notifications.notify({
        message: message,
        icon: "nc-icon nc-app",
        horizontalAlign: "right",
        verticalAlign: "top",
        type: this.type[color],
      });
    },
  },
};
</script>
<style></style>
