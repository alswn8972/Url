import Vue from 'vue'
import App from './App.vue'

import Vuetify from 'vuetify'		// <-- 추가
import 'vuetify/dist/vuetify.min.css'
import router from './router/router.js';
import 'material-design-icons-iconfont/dist/material-design-icons.css'
//ßimport store from './store'
Vue.config.productionTip = false
Vue.use(Vuetify);	

new Vue({
  router,
  //store,
  vuetify : new Vuetify(),
  render: h => h(App),
  icons: {
    iconfont: 'md',
  },
}).$mount('#app')
