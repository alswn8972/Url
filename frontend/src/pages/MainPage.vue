<!--메인페이지 사이드바-->
<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <div>
        <sidebar-link to="/">
          <md-icon>check_circle</md-icon>
          <p>Checker</p>
        </sidebar-link>
      </div>
      <div @click="resetHistory()">
      <sidebar-link to="/my/url" >
        <md-icon>list</md-icon>
        <p >Site list</p>
      </sidebar-link>
      </div>
    <div>
      <sidebar-link to="/status">
        <md-icon>code</md-icon>
        <p>Status Code</p>
      </sidebar-link>
    </div>

      <div @click="resetEmail()"> 
      <sidebar-link to="/reservation">
        <md-icon>notifications</md-icon>
        <p>Reservation</p>
      </sidebar-link>
      </div>

      <div v-if="isLogin" @click="reset()"> 
      <sidebar-link to="/profile">
        <md-icon>face</md-icon>
        <p>Profile</p>
      </sidebar-link>
      </div>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>
        <transition name="fade" mode="out-in">
          <router-view></router-view>
        </transition>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>

<script>
import TopNavbar from "./Layout/TopNavbar.vue";
import ContentFooter from "./Layout/ContentFooter.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";
import {mapGetters, mapActions} from 'vuex';
export default {
  
  components: {
    TopNavbar,
    ContentFooter,
    MobileMenu,
  },
  data() {
    return {
      sidebarBackground: "green",
    };
  },
  methods : {
    ...mapActions('url',['requestResetHistory','requestResetEmail']),
    resetHistory(){
      this.requestResetHistory();
    },
   
     resetEmail(){
       this.requestResetEmail();
     },
     reset(){
       this.requestResetHistory();
       this.requestResetEmail();
     }
    
  },
    computed:{
    ...mapGetters('user', {isLogin:'getAccessToken'})
  },
};
</script>
<style>
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.1s;
}

.fade-enter,
  .fade-leave-to
    /* .fade-leave-active in <2.1.8 */ {
  opacity: 0;
}
</style>