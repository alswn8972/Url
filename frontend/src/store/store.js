import Vue from "vue";
import Vuex from "vuex";

import user from "./modules/user";
import url from "./modules/url";
import createPersistedState from "vuex-persistedstate";
Vue.use(Vuex);

export default new Vuex.Store({
    modules: {
    user,
    url,
    },
    plugins: [
        //login 후 새로고침해도 vuex 값 저장
        createPersistedState({
          storage: window.localStorage,
        }),
      ],  
    
})
