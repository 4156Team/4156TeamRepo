import Vue from "vue";
import Router from "vue-router";
import Home from "../views/Home.vue";
// import store from '../store'
import { authcheck } from "../auth/authcheck";
import { authcheck_admin } from "../auth/authcheck_admin";

Vue.use(Router);
let router = new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path:"/login",
      name:"Login",
      component: () => import("../views/Login.vue"),
    },
    {
      path:"/register",
      name:"Register",
      component: () => import("../views/Register.vue"),
    },
    {
      path: "/facility",
      name: "facility",
      component: () => import("../views/Facility.vue"),
    },
    {
      path: "/announcement",
      name: "announcement",
      component: () => import("../views/Announcement.vue"),
    },
    {
      path: "/event/:eventName",
      name: "eventName",  
      component: () => import("../views/EventSingle.vue"),
      beforeEnter: authcheck,
    },
    {
      path: "/myTicket",
      name: "myTicket",
      component: () => import("../views/TicketAppointment.vue"),
      beforeEnter: authcheck,
    },
    {
      path: "/myAppointment",
      name: "myAppointment",
      component: () => import("../views/EventAppointment.vue"),
      beforeEnter: authcheck,
    },
    {
      path: "/eventcheck",
      name: "event",
      component: () => import("../views/Event.vue"),
      // beforeEnter: authcheck,
    },
    {
      path: "/buyticket",
      name: "buyticket",
      component: () => import("../views/FetchDataList.vue"),
      // beforeEnter: authcheck,
    },
    {
      path: "/myAccount",
      name: "myAccount",
      component: () => import("../views/UserAccount.vue"),
      beforeEnter: authcheck,
    },
    {
      path: "/feedback",
      name: "feedback",
      component: () => import("../views/FeedbackPage.vue"),
      beforeEnter: authcheck,
    },
    {
      path: "/myquickpass",
      name: "myquickpassq",
      component: () => import("../views/QuickPassShow.vue"),
      beforeEnter: authcheck,
    },
    {
      path: "/adminhome",
      name: "adminhome",
      component: () => import("../views/AdminHome.vue"),
      beforeEnter: authcheck_admin,
    },
    {
      path: "/adminFacility",
      name: "adminhome",
      component: () => import("../views/FacilityUpdate.vue"),
      beforeEnter: authcheck_admin,
    },
    {
      path: "/adminCheckin",
      name: "adminhome",
      component: () => import("../views/AdminCheckin.vue"),
      beforeEnter: authcheck_admin,
    },
    {
      path: "/adminEvent",
      name: "adminhome",
      component: () => import("../views/EventUpdate.vue"),
      beforeEnter: authcheck_admin,
    },
    {
      path: "/admininfo",
      name: "adminhome",
      component: () => import("../views/AdminInfo.vue"),
      beforeEnter: authcheck_admin,
    },
    {
      path: "/adminprice",
      name: "adminhome",
      component: () => import("../views/AdminTicketAnnounce.vue"),
      beforeEnter: authcheck_admin,
    },
  ],
});

// router.beforeEach((to, from, next) => {
//   if (localStorage.getItem('user') == null){
//     store.state.isLogin = false
//     next({path:'/login'})

//   }else{next({path:'/login'})}
//   // if (localStorage.getItem('user') == null) {
//   //     next({ name: 'login'})
//   // } else {
//   //     let user = JSON.parse(localStorage.getItem('user'))
//   //     if(to.matched.some(record => record.meta.is_admin)) {
//   //         if(user == "manager"){
//   //             next()
//   //         }
//   //         else{
//   //             next({ name: 'home'})
//   //         }
//   //     }else {
//   //         next()
//   //     }
//   // }

//   // if (localStorage.getItem('user') == null) {
//   //   next({path:'/login'})
//   // }else{
//   //   store.state.isLogin = true
//   //   next()
//   //   }
  
// })

export default router