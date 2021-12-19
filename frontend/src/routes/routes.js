import Vue from "vue";
import VueRouter from "vue-router";

import Main from "@/pages/MainPage.vue";
import UserJoin from "@/pages/UserJoin.vue";
import UserLogin from "@/pages/UserLogin.vue";
import First from "@/pages/FirstInput.vue";
import Status from "@/pages/StatusCode.vue";
import Reservation from "@/pages/Reservation.vue";
import UserTable from "@/pages/UserTable.vue";
import Profile from "@/pages/Profile.vue";
import VueSimpleAlert from "vue-simple-alert";
Vue.use(VueRouter);
const requireAuth = () => (to, from, next) => {
  if (localStorage.getItem('accessToken')) {
    return next();
  }
  VueSimpleAlert.fire({
    title:"서비스 권한 없음",
    text:"로그인이 필요한 서비스입니다.",
    type:"error",
  }).then(() => {
    next('/login');
  }); 
};

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
        path: "/join",
        component: UserJoin,
      },
      {
        path: "/login",
        component: UserLogin,
      },
      {
        path: "/my/url",
        component: UserTable,
        beforeEnter: requireAuth()
      },
      {
        path: "/status",
        component: Status,
      },
      {
        path: "/reservation",
        component: Reservation,
        beforeEnter: requireAuth()
      },
      {
        path: "/profile",
        component: Profile,
        beforeEnter: requireAuth()
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
