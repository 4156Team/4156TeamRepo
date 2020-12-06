<template>
  <div>
    <div class="text">Register</div>
    <form class="title">
      <div>
        <el-input
          id="name"
          type="text"
          placeholder="Please enter your name"
          style="width: 30%;"
          v-model="name"
          required
          autofocus
        />
      </div>
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
          id="age"
          type="age"
          placeholder="Please enter your age"
          style="width: 30%;"
          v-model="age"
          required
        />
      </div>
      <div>
        <el-input
          id="gender"
          type="gender"
          placeholder="Please enter your gender"
          style="width: 30%;"
          v-model="gender"
          required
        />
      </div>
      <div>
        <el-input
          id="email"
          type="email"
          placeholder="Please enter your email"
          style="width: 30%;"
          v-model="email"
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
        <el-input
          id="password-confirm"
          type="password"
          placeholder="Confirm your password"
          style="width: 30%;"
          v-model="password_confirmation"
          required
        />
      </div>
      <div>
        <el-button class="button" type="warning" @click="login" round>
          Login
        </el-button>
        <el-button class="button" type="warning" @click="handleSubmit" round>
          Register
        </el-button>
      </div>
    </form>
  </div>
</template>
<script>
import qs from "qs";
export default {
  props: ["nextUrl"],
  data() {
    return {
      name: "",
      telephone: "",
      password: "",
      password_confirmation: "",
      gender: "",
      age: "",
      email:"",
      type: "",
    };
  },
  methods: {
    login() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    handleSubmit(e) {
      e.preventDefault();
      if (
        this.password === this.password_confirmation &&
        this.password.length > 0
      ) {
        var param = qs.stringify({
          name: this.name,
          telephone: this.telephone,
          password: this.password,
          gender: this.gender,
          age: this.age,
          email:this.email
        });
        this.$axios
          .post("http://localhost:8080/user/register", param, {
            headers: {
              "Access-Control-Allow-Credentials": "true",
              "Content-Type": "application/x-www-form-urlencoded",
            },
          })
          .then((response) => {
            if (response.data.status == "success") {
              this.type = "Vistor";
              window.sessionStorage.setItem("isLogin", true);
              window.sessionStorage.setItem("type", this.type);
              this.$router.push("/login");
            } else {
              this.$msg(response.data.data.errMsg);
              console.log(response.data.data.errMsg);
            }
          })
          .catch((error) => {
            this.$msg("Wrong!");
            console.error(error);
          });
      } else {
        this.password = "";
        this.passwordConfirm = "";
        this.$msg("Passwords do not match");
      }
    },
  },
};
</script>
<style lang="scss" scoped>
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
