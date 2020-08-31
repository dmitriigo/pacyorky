import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from "./router";
import axios from 'axios'
import './plugins/VCalendar-vue'
import './plugins/vuelayers'
import './plugins/VCarousel'
import './plugins/lightbox'
import './plugins/ml.js'
import './plugins/scrollto'
import './plugins/fa'
import VueAxios from 'vue-axios'
Vue.config.productionTip = false;
Vue.use(VueAxios, axios);
//Vue.config.devtools = true
new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
