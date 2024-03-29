import { createApp } from 'vue'
import { router } from './router/router.js';
import VueCookies from 'vue-cookies';
import App from './App.vue'
import axios from 'axios'
import zidoPlugin from './assets/zidoPlugin.js';

const app = createApp(App);
app.config.globalProperties.axios = axios
app.use(router)
app.use(VueCookies)
app.use(zidoPlugin)
app.mount('#app')