<template>
  <md-toolbar md-elevation="0" class="md-transparent">
    <div class="md-toolbar-row">
      <div class="md-toolbar-section-end">
        <md-button
          class="md-just-icon md-simple md-toolbar-toggle"
          :class="{ toggled: $sidebar.showSidebar }"
          @click="toggleSidebar"
        >
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </md-button>

        <div class="md-collapse">
          <md-list v-if="isLogin == null">
            <div class="btn">
              <md-button class="md-raised" href="/login">Sing In</md-button>
            </div>
            <div>
              <md-button class="md-raised" href="/join">Sign up</md-button>
            </div>
          </md-list>
          <md-list  v-else>
            <div class="btn">
              <b>{{this.userName}}</b>님
              <br>안녕하세요 :D
            </div>
            <div>
              <md-button class="md-raised" @click="clickLogout">Logout</md-button>
            </div>
          </md-list>
        </div>
      </div>
    </div>
  </md-toolbar>
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
    ...mapActions('url',["requestReset"]),
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    clickLogout(){
      this.requestLogout();
      this.requestReset();
      this.$router.push('/').catch(()=>{});
    }
  },
  computed:{
    ...mapGetters('user', {userName:'getUserName', isLogin:'getAccessToken'})
  },
  mounted() {

  },
  careate() {

  },
};
</script>

<style lang="css">
.btn{
  margin-right: 10px;
}
</style>
