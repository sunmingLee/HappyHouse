<template>
  <card v-if="userInfo">
    <h4 slot="header" class="card-title">프로필 수정</h4>
    <form>
      <div class="row">
        <div class="col-md-5">
          <base-input
            type="text"
            label="아이디"
            :disabled="true"
            placeholder="Light dashboard"
            v-model="userInfo.id"
          >
          </base-input>
        </div>
        <div class="col-md-3">
          <base-input
            type="text"
            label="사용자명"
            placeholder="Username"
            v-model="userInfo.user_name"
          >
          </base-input>
        </div>
        <div class="col-md-4">
          <base-input
            type="email"
            label="Email"
            placeholder="Email"
            v-model="userInfo.user_email"
          >
          </base-input>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <base-input
            type="text"
            label="주소"
            placeholder="Home Address"
            v-model="userInfo.address"
          >
          </base-input>
        </div>
      </div>

      <div class="row">
        <div class="col-md-12">
          <div class="form-group">
            <label>About Me</label>
            <textarea
              rows="5"
              class="form-control border-input"
              placeholder="Here can be your description"
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
          @click.prevent="updateProfile"
        >
          Update Profile
        </button>
      </div>
      <div class="clearfix"></div>
    </form>
  </card>
</template>
<script>
import Card from "@/components/Cards/Card.vue";
import { mapActions } from "vuex";

export default {
  components: {
    Card,
  },
  data() {
    return {
      userInfo: Object,
    };
  },
  methods: {
    ...mapActions("userStore", ["updateUserInfo"]),
    updateProfile() {
      this.updateUserInfo(this.userInfo);
    },
  },

  created() {
    this.userInfo = {
      id: this.$store.state.userStore.userInfo.id,
      user_name: this.$store.state.userStore.userInfo.user_name,
      user_email: this.$store.state.userStore.userInfo.user_email,
      address: this.$store.state.userStore.userInfo.address,
      aboutme: this.$store.state.userStore.userInfo.aboutme,
    };
  },
};
</script>
<style></style>
