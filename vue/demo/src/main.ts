import { createApp } from "vue";
import App from "./App.vue";
import router from './routes';
import 'ant-design-vue/dist/antd.css'
import Antd from 'ant-design-vue';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
const app = createApp(App);
app.config.productionTip = false;
createApp(App).use(router).use(Antd).mount("#app");
