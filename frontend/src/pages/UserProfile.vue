<template>
  <div class="content">
    <div class="container-fluid">
      <div class="col">
        <div>
          <user-card> </user-card>
        </div>
        <div>
          <edit-profile-form> </edit-profile-form>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EditProfileForm from "./UserProfile/EditProfileForm.vue";
import UserCard from "./UserProfile/UserCard.vue";

export default {
  components: {
    EditProfileForm,
    UserCard,
  },
};
</script>
<style></style>
