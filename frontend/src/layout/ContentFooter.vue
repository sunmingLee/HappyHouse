<template>
  <footer class="footer">
    <div class="container-fluid">
      <nav>
        <ul class="footer-menu">
          <li>
            <router-link :to="{ name: routeName }">{{ routeName }}</router-link>
          </li>
        </ul>
      </nav>
      <div class="copyright text-center">
        &copy; Thanks for
        <i class="fa fa-heart heart"></i>
        <a href="https://ssafy.com" target="_blank">ssafy</a>.
      </div>
    </div>
  </footer>
</template>
<script>
export default {
  computed: {
    routeName() {
      const { name } = this.$route;
      return name;
    },
  },
};
</script>
<style></style>
