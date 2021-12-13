import Vue from "vue";
import VueRouter from "vue-router";

import Dashboard from "@/pages/Dashboard.vue";


import Main from "@/pages/MainPage.vue";
import UserProfile from "@/pages/UserProfile.vue";
import UserLogin from "@/pages/UserLogin.vue";
import Check from "@/pages/UrlCheck.vue";
import First from "@/pages/FirstInput.vue";
import Status from "@/pages/StatusCode.vue";
import Reservation from "@/pages/Reservation.vue";
import UserTable from "@/pages/UserTable.vue";
import Typography from "@/pages/Typography.vue";
import Icons from "@/pages/Icons.vue";
import Notifications from "@/pages/Notifications.vue";

Vue.use(VueRouter);
const routes = [
  {
    path: "/",
    component: Main,
    children: [
      {
        path: "/",
        component:  First,
      },
      {
        path: "/check",
        component:  Check,
      },
      {
        path: "/dashboard",
        name: "Dashboard",
        component: Dashboard,
      },
      {
        path: "/join",
        component: UserProfile,
      },
      {
        path: "/login",
        component: UserLogin,
      },
      {
        path: "/my/url",
        component: UserTable,
      },
      {
        path: "typography",
        name: "Typography",
        component: Typography,
      },
      {
        path: "icons",
        name: "Icons",
        component: Icons,
      },
      {
        path: "notifications",
        name: "Notifications",
        component: Notifications,
      },
      {
        path: "/status",
        component: Status,
      },
      {
        path: "/reservation",
        component: Reservation,
      },
    ],
  },
];


const router = new VueRouter({
  routes,
  mode: 'history',
  base: "",
  linkExactActiveClass: "nav-item active",
})

export default router;
