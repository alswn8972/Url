<template>
  <ul class="nav nav-mobile-menu">
    <div v-if="!this.isLogin">
      <li>
        <a href="/login" class="dropdown-toggle">
          <i class="material-icons">login_variant</i>
          <p>Sign In</p>
        </a>
      </li>
    <li>
      <a href="/join" class="dropdown-toggle">
        <i class="material-icons">person</i>
        <p>Sign Up</p>
      </a>
    </li>
    </div>
    <div v-else>
      <li>
        <a class="dropdown-toggle">
        <p><b>{{this.userName}}</b>님 안녕하세요 :D</p></a>

      </li>
    <li>
      <a class="dropdown-toggle" @click="clickLogout">
        <i class="material-icons">logout_variant</i>
        <p>Logout</p></a>
    </li>
    </div>
  </ul>

</template>
<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  data() {
    return {

    };
  },
    methods: {
    ...mapActions('user',["requestLogout"]),
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    clickLogout(){
      this.requestLogout();
      console.log(this.isLogin)
      this.$router.push('/')
    }
  },
  computed:{
    ...mapGetters('user', {userName:'getUserName', isLogin:'getAccessToken'})
  },
};
</script>
