<template>
  <div class="home">
    <section class="hero is-dark">
      <div class="hero-body">
        <div class="container">
          <h1 class="title">
            Welcome to the wonderland of Roller Coaster
          </h1>
          <h2 class="subtitle">
            Make sure you check out our upcoming events below
          </h2>
          <div class="button-block">
            <el-button
              v-if="!this.isLogin"
              @click="handleLogin"
              type = "success"
              icon="el-icon-user"
            >
              Sign Up to Browse Events
            </el-button>
            <h3 v-if="isLogin" class="is-size-3 welcome" border>
              Welcome 
              <el-button v-show="this.isAdimin"
              @click="handleAdmin">
              Admin page
              </el-button>
            </h3>
          <div>
            <el-divider></el-divider>
            <span>There little thoughts are the rustle of leaves; they have their whisper of joy in my mind. weather</span>
            
          </div>
          </div>
        </div>
      </div>
    </section>
    <section class="banner is-white">
      <h1 class="topic">
        Best Events
      </h1>
      <Carousel />
    </section>
      <el-divider></el-divider>
    <EventsList />
  </div>
</template>
<script>
import EventsList from "../components/EventsList";
import Carousel from "../components/Carousel";
export default {
  data() {
    return {
      isLogin: false,
      isAdmin: false,
    };
  },
  mounted() {
    this.isLogin = window.sessionStorage.getItem("isLogin");
    this.isAdimin = window.sessionStorage.getItem("isAdmin");

  },
  name: "home",
  components: {
    EventsList,
    Carousel,
  },
  inject:['reload'],
  methods: {
    // Log the user in
    handleLogin(e) {
      e.preventDefault();
      this.$router.push("/login");
    },
    handleAdmin(e) {
      e.preventDefault();
      this.$router.push("/adminhome");
    },
  },
};
</script>
<style lang="scss" scoped>
.hero {
  text-align: center;
  background-image: url("../assets/img/banner/cropped-banner.jpg");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 400px;
}
.hero-body .title {
  text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.6);
  padding: 40px 0 20px 0;
  font-size: 60px;
}
.subtitle {
  text-shadow: 4px 4px 4px rgba(45, 204, 106, 0.699);
  font-size: 30px;
}
.button-block {
  text-align: center;
  margin-left: auto;
  margin-right: auto;
  width: 100%;
  position: absolute;
  bottom: -150px;
  .button {
    margin-right: 50px;
    padding-left: 50px;
    padding-right: 50px;
  }
  .welcome {
    width: 400px;
    padding: 10px;
    margin-left: auto;
    margin-right: auto;
  }
}
.is-xl {
  font-size: 1.7rem;
}
</style>
<style lang="scss" scoped>
.banner {
  text-align: center;
}
.topic {
  margin-top: 50px;
  text-align: center;
  color: darkolivegreen;
  text-shadow: 4px 4px 4px rgba(9, 75, 9, 0.7);
  font-size: 40px;
  font-family: 'Lucida Sans';
}
</style>
