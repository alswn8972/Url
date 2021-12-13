<template>
  <div
    class="sidebar"
  >
    <div class="logo">
      <a href="/" class="simple-text logo-mini">
        <div class="logo-img">
          <img :src="imgLogo" alt="" />
        </div>
      </a>

      <a
        href="/"
        class="simple-text logo-normal"
      >
        SITE monitor
      </a>
    </div>
    <div class="sidebar-wrapper">
      <slot name="content"></slot>
      <md-list class="nav">
        <!--By default vue-router adds an active class to each route link. This way the links are colored when clicked-->
        <slot>
          <sidebar-link
            v-for="(link, index) in sidebarLinks"
            :key="link.name + index"
            :to="link.path"
            :link="link"
          >
          </sidebar-link>
        </slot>
      </md-list>
    </div>
  </div>
</template>
<script>
import SidebarLink from "./SidebarLink.vue";

export default {
  components: {
    SidebarLink,
  },
  props: {
    title: {
      type: String,
      default: "Vue MD",
    },
    imgLogo: {
      type: String,
      default: require("@/assets/img/vue-logo.png"),
    },
    sidebarLinks: {
      type: Array,
      default: () => [],
    },
    autoClose: {
      type: Boolean,
      default: true,
    },
  },
  provide() {
    return {
      autoClose: this.autoClose,
    };
  },
  computed: {

  },
};
</script>
<style>
@media screen and (min-width: 991px) {
  .nav-mobile-menu {
    display: none;
  }
}
</style>
