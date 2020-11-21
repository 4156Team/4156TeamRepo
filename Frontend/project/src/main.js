import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./../node_modules/bulma/css/bulma.css";
import store from './store/index'
import Notifications from 'vue-notification'




require("./mock.js");

// Import the Auth0 configuration
import { domain, clientId } from "../auth_config.json";

// Import the plugin here
import { Auth0Plugin } from "./auth";

import VueResource from "vue-resource";
import axios from 'axios';
Vue.prototype.$axios = axios //全局注册，使用方法为:this.$axios

axios.defaults.withCredentials=true




//从element-ui中引入需要的组件
import {
  Alert,
  Button,
  Card,
  Carousel,
  CarouselItem,
  Checkbox,
  CheckboxGroup,
  Col,
  DatePicker,
  Form,
  FormItem,
  Input,
  InputNumber,
  Menu,
  MenuItem,
  Option,
  Radio,
  RadioGroup,
  Row,
  Select,
  Submenu,
  Switch,
  Table,
  TableColumn,
  TimePicker,
  TimeSelect,
} from "element-ui";
//element-ui样式还是需要全部加载的
import "element-ui/lib/theme-chalk/index.css";

import lang from "element-ui/lib/locale/lang/en";
import locale from "element-ui/lib/locale";
import GoogleAuth from 'vue-google-oauth'


// 设置语言
locale.use(lang);


//使用Button组件
Vue.use(Button).use(Alert)
  .use(TimeSelect)
  .use(Carousel)
  .use(CarouselItem)
  .use(Col)
  .use(Row)
  .use(Select)
  .use(Table)
  .use(TableColumn)
  .use(Radio)
  .use(Card)
  .use(Form).use(Menu).use(MenuItem).use(Submenu)
  .use(Input)
  .use(Option)
  .use(FormItem)
  .use(Checkbox).use(CheckboxGroup).use(InputNumber)
  .use(RadioGroup)
  .use(Switch)
  .use(TimePicker);

Vue.use(GoogleAuth, { client_id: '99250321813-sudhjlbktd5gvc13cf5medguum3oe3c5.apps.googleusercontent.com' })
Vue.googleAuth().load()
Vue.use(Notifications);
Vue.use(VueResource);
Vue.component(DatePicker.name, DatePicker);

// Install the authentication plugin here
Vue.use(Auth0Plugin, {
  domain,
  clientId,
  onRedirectCallback: (appState) => {
    router.push(
      appState && appState.targetUrl
        ? appState.targetUrl
        : window.location.pathname
    );
  },
});

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: (h) => h(App),
}).$mount("#app");
