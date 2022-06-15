<template>
  <div class="wrapper">
    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/overview">
        <i class="nc-icon nc-chart-pie-35"></i>
        <p>메인</p>
      </sidebar-link>
      <sidebar-link to="/user/mypage">
        <i class="nc-icon nc-circle-09"></i>
        <p>사용자 정보</p>
      </sidebar-link>
      <sidebar-link to="/board">
        <i class="nc-icon nc-notes"></i>
        <p>공지사항</p>
      </sidebar-link>
      <sidebar-link to="/house">
        <i class="nc-icon nc-paper-2"></i>
        <p>아파트 정보</p>
      </sidebar-link>
      <sidebar-link to="/qna">
        <i class="nc-icon nc-atom"></i>
        <p>Q&A</p>
      </sidebar-link>

      <template slot="bottom-links">
        <hr style="border: dashed 1px white" />
        <a class="active nav-link" href="http://ssafy.com">
          <i class="nc-icon nc-alien-33"></i>
          <p>SSAFY</p>
          <i>17반 배인수 - 이선민</i>
        </a>
        <hr style="border: dashed 1px white" />
      </template>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>

      <dashboard-content @click="toggleSidebar"> </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss"></style>
<script>
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./ContentView.vue";
import MobileMenu from "./MobileMenu.vue";
import TopNavbar from "@/layout/TopNavbar";
export default {
  components: {
    TopNavbar,
    ContentFooter,
    DashboardContent,
    MobileMenu,
  },
  methods: {
    toggleSidebar() {
      if (this.$sidebar.showSidebar) {
        this.$sidebar.displaySidebar(false);
      }
    },
  },
};
</script>
