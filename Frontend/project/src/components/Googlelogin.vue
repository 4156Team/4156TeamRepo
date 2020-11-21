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
import qs from 'qs'
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
      Vue.googleAuth().signIn(this.onSignInSuccess, this.onSignInError)
    },
    onSignInSuccess: function (authorizationCode) {
      this.toggleLoading()
      this.resetResponse()
      console.log(authorizationCode)
      this.$axios.post("/api/user/googleVerify", qs.stringify({idtokenstr: authorizationCode}), {
            headers: {
              "Access-Control-Allow-Credentials": "true",
              "Content-Type": "application/x-www-form-urlencoded",
            },
            })
      .then(function (response) {
        if (response.body) {
          var data = response.body
          // Save to vuex
          var token = data.token
          console.log(token)
          // Save to local storage as well 
          // ( or you can install the vuex-persistedstate plugin so that you won't have to do this step, only store to Vuex is sufficient )
          if (window.localStorage) {
            window.localStorage.setItem('user', JSON.stringify(data.user_data))
            window.localStorage.setItem('token', token)
          }

          // redirect to the dashboard
          this.$router.push({ name: 'home' })
        }
      }, function (response) {
        var data = response.body
        this.response = data.error
        console.log('BACKEND SERVER - SIGN-IN ERROR', data)
      })
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