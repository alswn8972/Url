import Vue from 'vue'
import App from './App.vue'

import Vuetify from 'vuetify'		// <-- 추가
import 'vuetify/dist/vuetify.min.css'
import router from './routers/router.js';
import store from './store/store.js';
import MenuIcon from 'vue-material-design-icons/Menu.vue';
import "./plugins/fontAwesomeIcon";

//ßimport store from './store'
Vue.config.productionTip = false
Vue.use(Vuetify);	
Vue.component('menu-icon', MenuIcon);
new Vue({
  router,
  store,
  vuetify : new Vuetify(),
  render: h => h(App),
}).$mount('#app')
