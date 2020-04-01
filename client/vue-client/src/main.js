import '@babel/polyfill'
import 'mutationobserver-shim'
import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from "./router";
import axios from 'axios'
import './plugins/VCalendar-vue'
import './plugins/vuelayers'
import VueAxios from 'vue-axios'
import { LMap, LTileLayer, LMarker } from 'vue2-leaflet';
import 'leaflet/dist/leaflet.css';
Vue.config.productionTip = false;
Vue.use(VueAxios, axios);
Vue.component('l-map', LMap);
Vue.component('l-tile-layer', LTileLayer);
Vue.component('l-marker', LMarker);
new Vue({
  router,
  render: h => h(App),
}).$mount('#app');
