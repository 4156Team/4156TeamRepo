<template>
  <nav class="navbar container" role="navigation" aria-label="main navigation">
    <div class="navbar-brand">
      <a href="#" class="navbar-left">
        <img :src="logoSrc" style="max-width:80px;margin-top:-0px;">
      </a>
      <a class="navbar-item" href="/">
        <strong class="is-size-4">Roller Coaster</strong>
      </a>
      <a
        role="button"
        class="navbar-burger burger"
        aria-label="menu"
        aria-expanded="true"
      >
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
        <span aria-hidden="true"></span>
      </a>
    </div>
    <div id="navbar" class="navbar-menu">
      <div class="navbar-start">
        <router-link to="/" class="navbar-item">Home</router-link>
        <router-link to="/announcement" class="navbar-item">Announcement</router-link>
        <router-link to="/buyticket" class="navbar-item">Ticket</router-link>
        <router-link to="/facility" class="navbar-item">Facility</router-link>
        <!-- <router-link to="/myAppointment" class="navbar-item">My Appointment</router-link>
        <router-link to="/myTicket" class="navbar-item">My Ticket</router-link> -->
        <!-- <router-link to="/login" class="navbar-item">testlog</router-link> -->
        <div class="navbar-item">
          <el-dropdown>
          <span class="el-dropdown-link">
            My account<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><router-link to="/myAppointment" class="navbar-item">My Appointment</router-link></el-dropdown-item>
            <el-dropdown-item><router-link to="/myTicket" class="navbar-item">My Ticket</router-link></el-dropdown-item>
            <el-dropdown-item ><router-link to="/myquickpass" class="navbar-item">My Quickpass</router-link></el-dropdown-item>
            <el-dropdown-item divided><router-link to="/myAccount" class="navbar-item">My Balance</router-link></el-dropdown-item>
          </el-dropdown-menu>
          </el-dropdown>
        </div>
        <router-link to="/feedback" class="navbar-item">Feedback</router-link>
      </div>
    
      <div class="navbar-end">
      <div class="navbar-item">
        <div class="buttons">
          <a v-show="!this.isLogin" class="button is-primary" @click="login">Log in</a>
          <a v-show="this.isLogin" class="button is-primary" @click="logout">Log out</a>
        </div>
      </div>
    </div>
    </div> 
  </nav>
</template>
<script>

export default {
  data() {
    return {
      logoSrc: require("@/assets/img/logo/logo.png"),
      isLogin: false,
      isAdmin: false,
    };
  },
  name: "Nav",
  inject:['reload'],
  created() {
    this.isLogin = window.sessionStorage.getItem("isLogin");
    this.isAdmin = window.sessionStorage.getItem("isAdmin");
  },
  methods: {
    // Log the user in
    login() {
      this.$router.push("/login");  
    },
    // Log the user out
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");  
    },
  },
};
</script>
<style lang="scss" scoped>
nav {
  margin-top: 25px;
  margin-bottom: 30px;
  a {
    font-weight: bold;
    color: #2c3e50;
    &.router-link-exact-active {
      color: #d88d00;
    }
  }
}
</style>
