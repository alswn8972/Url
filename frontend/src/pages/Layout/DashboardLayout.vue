<template>
  <div class="wrapper" :class="{ 'nav-open': $sidebar.showSidebar }">
    <notifications></notifications>

    <side-bar

    >
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/">
        <md-icon>content_paste</md-icon>
        <p>SITE Checker</p>
      </sidebar-link>
      <sidebar-link to="/check">
        <md-icon>dashboard</md-icon>
        <p>Checker</p>
      </sidebar-link>
      <sidebar-link to="/table">
        <md-icon>content_paste</md-icon>
        <p>My SITE list</p>
      </sidebar-link>
      <sidebar-link to="/typography">
        <md-icon>library_books</md-icon>
        <p>Reservation</p>
      </sidebar-link>
    </side-bar>

    <div class="main-panel">
      <top-navbar></top-navbar>


      <dashboard-content> </dashboard-content>

      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
import TopNavbar from "./TopNavbar.vue";
import ContentFooter from "./ContentFooter.vue";
import DashboardContent from "./Content.vue";
import MobileMenu from "@/pages/Layout/MobileMenu.vue";

export default {
  components: {
    TopNavbar,
    DashboardContent,
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
