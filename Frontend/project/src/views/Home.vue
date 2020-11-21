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
            <button
              v-if="!this.isLogin"
              @click="handleLogin"
              class="button is-xl is-dark"
            >
              Sign Up to Browse Events
            </button>
            <h3 v-if="isLogin" class="is-size-3 has-background-dark welcome">
              Welcome 
              <el-button v-show="this.isAdimin"
              @click="handleAdmin">
              Admin page
              </el-button>
            </h3>
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
    console.log("this.isAdimin", this.isAdimin);
    // setTimeout(()=>{self.reload},1500)
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
  background-image: url("https://media.socastsrm.com/wordpress/wp-content/blogs.dir/684/files/2018/02/Wreck-It-Ralph-2-Ralph-Breaks-The-Internet-Trailer.jpg");
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
  text-shadow: 4px 4px 4px rgba(0, 0, 0, 0.7);
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
  text-shadow: 4px 4px 4px rgba(9, 39, 48, 0.7);
  font-size: 40px;
  font-family: "Helvetica Neue";
}
</style>
