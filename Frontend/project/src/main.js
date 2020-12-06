import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import "./../node_modules/bulma/css/bulma.css";
import store from './store/index'
import Notifications from 'vue-notification'
require("./mock.js");


import { vsButton, vsSelect, vsPopup, vsRow, vsCol, vsCard } from 'vuesax'
import 'vuesax/dist/vuesax.css'
import 'material-icons/iconfont/material-icons.css';

import VueResource from "vue-resource";
import axios from 'axios';
import { Divider, Dropdown, DropdownItem, DropdownMenu, MessageBox, Message, Main, Aside, Footer, Dialog, Header, Popover, Rate } from 'element-ui'
Vue.prototype.$axios = axios //全局注册，使用方法为:this.$axios
Vue.prototype.$msgbox = MessageBox
Vue.prototype.$msg = Message

axios.defaults.withCredentials=true

Vue.use(vsButton).use(vsSelect).use(vsCol).use(vsRow).use(vsPopup).use(vsCard)

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
  Container,
  Image
} from "element-ui";
//element-ui样式还是需要全部加载的
import "element-ui/lib/theme-chalk/index.css";

import lang from "element-ui/lib/locale/lang/en";
import locale from "element-ui/lib/locale";
import GoogleAuth from 'vue-google-oauth'
import GSignInButton from 'vue-google-signin-button'

import { Loading } from 'element-ui';
Vue.use(Loading.directive);


// 设置语言
locale.use(lang);


//使用Button组件
Vue.use(Button).use(Alert).use(Divider)
  .use(TimeSelect)
  .use(Carousel)
  .use(CarouselItem)
  .use(Col)
  .use(Row).use(Dropdown).use(DropdownItem).use(DropdownMenu)
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
  .use(TimePicker)
  .use(Container)
  .use(Main)
  .use(Aside)
  .use(Footer)
  .use(Dialog)
  .use(Header).use(Popover).use(Rate).use(Image)

Vue.use(GoogleAuth, { client_id: '99250321813-sudhjlbktd5gvc13cf5medguum3oe3c5.apps.googleusercontent.com' })
Vue.use(GSignInButton)
Vue.googleAuth().load()
Vue.use(Notifications);
Vue.use(VueResource);
Vue.component(DatePicker.name, DatePicker);

// // Install the authentication plugin here
// Vue.use(Auth0Plugin, {
//   domain,
//   clientId,
//   onRedirectCallback: (appState) => {
//     router.push(
//       appState && appState.targetUrl
//         ? appState.targetUrl
//         : window.location.pathname
//     );
//   },
// });

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  render: (h) => h(App),
}).$mount("#app");
