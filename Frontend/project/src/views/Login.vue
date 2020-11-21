<template>
  <div>
    <div class="text">Login</div>
    <form class="title">
      <div>
        <el-input
          id="telephone"
          type="telephone"
          placeholder="Please enter your telephone"
          style="width: 30%;"
          v-model="telephone"
          required
        />
      </div>
      <div>
        <el-input
          id="password"
          type="password"
          placeholder="Please enter your password"
          style="width: 30%;"
          v-model="password"
          required
        />
      </div>
      <div>
        <el-button class="button" type="warning" @click="handleSubmit" round>
          Login
        </el-button>
        <el-button class="button" type="warning" @click="signup" round>
          Sign Up
        </el-button>
        <a>
        <Googlelogin />
        </a>
      </div>
    </form>
  </div>
</template>
<script>
import qs from "qs";
import Googlelogin from "../components/Googlelogin"

export default {
  components: { Googlelogin },
  data() {
    return {
      telephone: "",
      password: "",
      type: "",
    };
  },
  // inject:['reload'],
  methods: {
    signup() {
      // window.sessionStorage.clear();
      this.$router.push("/register");
    },
    handleSubmit(e) {
      // window.sessionStorage.clear();
      e.preventDefault();
      if (this.password.length > 0) {
        // window.sessionStorage.clear();
        var param = qs.stringify({
          telephone: this.telephone,
          password: this.password,
        });
        // console.log(param);
        this.$axios
          .post("/api/user/login", param, {
            headers: {
              "Access-Control-Allow-Credentials": "true",
              "Content-Type": "application/x-www-form-urlencoded",
            },
          })
          .then((response) => {
            if (response.data.status == "success") {
              
              window.sessionStorage.clear();
              // this.$store.commit("SET_LOGIN", true);
              window.sessionStorage.setItem("isLogin", true);
              console.log("login", response);
              console.log(sessionStorage.getItem("isLogin"))
                // this.$store.commit("GET_USER", response.data.data);
              if (response.data.data == "manager") {
                window.sessionStorage.setItem("isAdmin", true);
                window.sessionStorage.setItem(
                  "user",
                  JSON.stringify(response.data.data)
                );
                window.sessionStorage.setItem("type", this.type);
                console.log("manager!")
                this.$router.push("/");}
               else {
                window.sessionStorage.setItem(
                  "user",
                  JSON.stringify(response.data.data)
                );
                window.sessionStorage.setItem("type", this.type);
                console.log("login", window.sessionStorage.getItem("user"));
                this.$router.push("/");
              }
          } else {
            window.alert(response.data.data.errMsg);
            console.log(response.data.data.errMsg);
          }
        })
        .catch(function(error) {
          console.error(error.response);
        });
      }
    },
  },
};
</script>
<style scoped>
.content {
  width: 300px;
}
.text {
  text-align: center;
  margin-top: 50px;
  font-size: 20px;
  text-shadow: 4px 4px 4px rgba(9, 39, 48, 0.7);
}
.title {
  text-align: center;
  margin-top: 25px;
  margin-bottom: 30px;
}
.button {
  text-align: center;
  margin-top: 10px;
}
</style>
