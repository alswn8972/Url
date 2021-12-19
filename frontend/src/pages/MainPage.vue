<!--메인페이지 사이드바-->
<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/">
        <md-icon>check_circle</md-icon>
        <p>Checker</p>
      </sidebar-link>
      <sidebar-link to="/my/url">
        <md-icon>list</md-icon>
        <p>Site list</p>
      </sidebar-link>
      <sidebar-link to="/status">
        <md-icon>code</md-icon>
        <p>Status Code</p>
      </sidebar-link>
      <sidebar-link to="/reservation">
        <md-icon>notifications</md-icon>
        <p>Reservation</p>
      </sidebar-link>

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
//import DashboardContent from "./Layout/Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";

export default {
  components: {
    TopNavbar,
    //DashboardContent,
    ContentFooter,
    MobileMenu,
  },
  data() {
    return {
      sidebarBackground: "green",
    };
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
