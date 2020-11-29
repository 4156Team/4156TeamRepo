import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
    state: {
        user: null,
        type: null,
        isLogin: null
      },
     
      // 获取属性的状态
      getters: {
        //获取登录状态
        getStorage:function(state){
          if (!state.isLogin){
            state.user = window.sessionStorage.getItem('user');
            state.type = window.sessionStorage.getItem('type');
            state.isLogin = window.sessionStorage.getItem('isLogin');
          }
        }
        // isLogin: state => state.isLogin,
        // isAdmin: state => (state.type == "manager")? true :false
      },
     
      // 设置属性状态
      mutations: {
        GET_USER:(state,data) =>{
          state.user = data;
          window.sessionStorage.setItem("user",data);
        },
        GET_TYPE:(state,value) =>{
          state.type = value;
          window.sessionStorage.setItem('type',value)
        },
        SET_LOGIN:(state,status) =>{
          state.isLogin = status;
          window.sessionStorage.setItem("isLogin",status);
        }
      },
    
})

export default store