<template>
  <div class="content">
    <b-container fluid>
      <div class="row justify-content-md-center">
        <card class="col-md-6">
          <div class="text-primary" style="text-align: center">
            <h3>로그인</h3>
          </div>
        </card>
      </div>
      <div class="row justify-content-md-center">
        <card class="col-md-6">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isLoginError"
              >아이디 또는 비밀번호를 확인하세요.</b-alert
            >
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.id"
                required
                placeholder="아이디 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.user_password"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <div class="col-md-12 text-right">
              <b-button
                type="button"
                variant="primary"
                class="m-2"
                @click="confirm"
                >로그인</b-button
              >
              <b-button
                type="button"
                variant="success"
                class="m-1"
                @click="movePage"
                >회원가입</b-button
              >
            </div>
          </b-form>
        </card>
      </div>
    </b-container>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

const userStore = "userStore";
export default {
  name: "UserLogin",
  data() {
    return {
      type: ["", "info", "success", "warning", "danger"],
      user: {
        id: "",
        user_password: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.notifyVue(1, `<span> <b>로그인 완료</b> </span>`);
        this.$router.push({ name: "home" });
      }
    },
    movePage() {
      this.$router.push({ name: "signUp" });
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
