import Vue from "vue";
import VueRouter from "vue-router";

import Main from "../views/MainPage.vue";
import Join from "../views/JoinPage.vue";
import List from "../views/ListPage.vue";

Vue.use(VueRouter);


const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  {
    path: "/join",
    name: "Join",
    component: Join,
  },
  {
    path: "/list",
    name: "List",
    component: List,
  },
 
];

const router = new VueRouter({
  mode: "history",
  base: "",
  routes,
});


export default router;
