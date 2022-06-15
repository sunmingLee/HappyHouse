<template>
  <div class="content">
    <div class="container-fluid">
      <div class="col">
        <card>
          <div class="text-primary" style="text-align: center">
            <h3>회원가입</h3>
          </div>
        </card>
        <card v-if="userInfo">
          <h4 slot="header" class="card-title">회원 정보 입력</h4>
          <form>
            <div class="row">
              <b-form-group class="col-md-6" label="아이디:" label-for="id">
                <b-form-input
                  type="text"
                  id="id"
                  required
                  placeholder="아이디 입력...."
                  v-model="userInfo.id"
                  :state="isIdValid"
                  @blur="checkDuplicateId"
                ></b-form-input>
                <!-- This will only be shown if the preceding input has an invalid state -->
                <b-form-invalid-feedback
                  id="input-live-feedback"
                  v-if="!isIdValid"
                >
                  아이디 중복
                </b-form-invalid-feedback>
              </b-form-group>
              <b-form-group
                class="col-md-6"
                label="비밀번호:"
                label-for="userpwd"
              >
                <b-form-input
                  type="password"
                  id="userpwd"
                  required
                  placeholder="비밀번호 입력...."
                  v-model="userInfo.user_password"
                ></b-form-input>
              </b-form-group>
            </div>
            <div class="row">
              <b-form-group
                class="col-md-6"
                label="사용자명:"
                label-for="username"
              >
                <b-form-input
                  type="text"
                  id="username"
                  required
                  placeholder="사용자명 입력...."
                  v-model="userInfo.user_name"
                ></b-form-input>
              </b-form-group>
              <b-form-group class="col-md-6" label="이메일:" label-for="email">
                <b-form-input
                  type="text"
                  id="email"
                  required
                  placeholder="이메일 입력...."
                  v-model="userInfo.user_email"
                ></b-form-input>
              </b-form-group>
            </div>
            <div class="row">
              <b-form-group class="col-md-6" label="전화번호:" label-for="tel">
                <b-form-input
                  type="text"
                  id="tel"
                  required
                  placeholder="전화번호 입력...."
                  v-model="userInfo.user_tel"
                ></b-form-input>
              </b-form-group>
              <b-form-group class="col-md-6" label="주소:" label-for="address">
                <b-form-input
                  type="text"
                  id="address"
                  required
                  placeholder="주소 입력...."
                  v-model="userInfo.address"
                ></b-form-input>
              </b-form-group>
            </div>
            <div class="row">
              <div class="col-md-12">
                <div class="form-group">
                  <label>About Me</label>
                  <textarea
                    rows="5"
                    class="form-control border-input"
                    placeholder="본인 소개를 입력해주세요..."
                    v-model="userInfo.aboutme"
                  >
                  </textarea>
                </div>
              </div>
            </div>
            <div class="text-center">
              <button
                type="submit"
                class="btn btn-info btn-fill float-right"
                @click.prevent="registerUser"
              >
                회원가입
              </button>
            </div>
            <div class="clearfix"></div>
          </form>
        </card>
      </div>
    </div>
  </div>
</template>

<script>
import { checkId, registerUser } from "@/api/user";

export default {
  name: "UserRegister",
  data() {
    return {
      type: ["", "info", "success", "warning", "danger"],
      isIdValid: null,
      userInfo: {
        id: "",
        user_password: "",
        user_name: "",
        user_email: "",
        user_tel: "",
        address: "",
        aboutme: "",
      },
    };
  },
  methods: {
    registerUser() {
      registerUser(this.userInfo, (response) => {
        if (response.data === 1) {
          this.notifyVue(
            2,
            `<span> <b>회원가입이 됐습니다!!</b> 로그인 해주세요.</span>`,
          );
          this.$router.push({ name: "signIn" });
        } else
          this.notifyVue(
            4,
            `<span> <b>회원가입이 실패했습니다!!</b> 다시 시도 해주세요.</span>`,
          );
      });
    },
    checkDuplicateId() {
      checkId(
        this.userInfo.id,
        (response) => {
          if (response.data.idcount === 1) this.isIdValid = false;
          else this.isIdValid = true;
        },
        (error) => {
          console.log(error);
        },
      );
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
