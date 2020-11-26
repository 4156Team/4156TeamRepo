<template>
<a href="#" class="google-signup" @click.prevent="signIn">
    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 18 18" aria-hidden="true">
        <title>Google</title>
    </svg>
Google
</a>
</template>
<script>
import Vue from 'vue'
export default {
  name: 'Login',
  data: function () {
    return {
      section: 'Login',
      loading: '',
      response: ''
    }
  },
  methods: {
    signIn: function () {
      Vue.googleAuth().directAccess()
      Vue.googleAuth().signIn(this.onSignInSuccess, this.onSignInError)
    },
    onSignInSuccess: function (googleUser) {
      this.toggleLoading()
      this.resetResponse()
      const param = {token:googleUser.xc.id_token}
      console.log(googleUser.xc.id_token)
      this.$axios.post("/api/user/googleVerify", JSON.stringify(param),{
            headers: {
              "Access-Control-Allow-Credentials": "true",
              "Content-Type": "application/json",
            },}).then((response) => {
              window.sessionStorage.clear();
              if(response.status == "200"){
                window.sessionStorage.setItem("isLogin", true)
                window.sessionStorage.setItem("type", "vistor")
                 this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! You success login with Google account!",
              });
              this.$router.push("/");
              }else{
                window.alert("Failed to verfiy ")
              }
            }).catch(function(error) {
          console.error(error.response);
          window.alert("Backend service error!")
        });
    },
    onSignInError: function (error) {
      this.response = 'Failed to sign-in'
      console.log('GOOGLE SERVER - SIGN-IN ERROR', error)
    },
    toggleLoading: function () {
      this.loading = (this.loading === '') ? 'loading' : ''
    },
    resetResponse: function () {
      this.response = ''
    }
  }
}
</script>