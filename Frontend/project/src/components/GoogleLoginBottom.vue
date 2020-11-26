<template>
  <g-signin-button
    :params="googleSignInParams"
    @success="onSignInSuccess"
    @error="onSignInError">
    Sign in with Google
  </g-signin-button>
</template>

<script>
export default {
  data () {
    return {
      /**
       * The Auth2 parameters, as seen on
       * https://developers.google.com/identity/sign-in/web/reference#gapiauth2initparams.
       * As the very least, a valid client_id must present.
       * @type {Object}
       */
      googleSignInParams: {
        client_id: '99250321813-sudhjlbktd5gvc13cf5medguum3oe3c5.apps.googleusercontent.com'
      }
    }
  },
  methods: {
    onSignInSuccess (googleUser) {
      // `googleUser` is the GoogleUser object that represents the just-signed-in user.
      // See https://developers.google.com/identity/sign-in/web/reference#users
      // const profile = googleUser.getBasicProfile() // etc etc
      // console.log(profile)
      const param = {token:googleUser.getAuthResponse().id_token}
      console.log(googleUser.getAuthResponse().id_token)
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
    onSignInError (error) {
      // `error` contains any error occurred.
      window.sessionStorage.clear();
      console.log('OH NOES', error)
    }
  }
}
</script>

<style>
.g-signin-button {
  /* This is where you control how the button looks. Be creative! */
  display: inline-block;
  padding: 4px 8px;
  border-radius: 3px;
  background-color: #3c82f7;
  color: #fff;
  box-shadow: 0 3px 0 #0f69ff;
}
</style>