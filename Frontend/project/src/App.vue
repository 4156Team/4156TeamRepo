<template>
  <div id="app">
    <!-- {{$route.name}} -->
    <div v-if="!($route.name === 'adminhome') && !($route.name === 'Login')">
      <Nav :name="$route.name" />
      <router-view v-if="isRouterAlive"/>
      <notifications group="foo" />
    </div>
    <div v-else-if="($route.name === 'Login')">
      <!-- <AdminNav /> -->
      <!-- <Nav :name="$route.name" /> -->
      <router-view />
      <notifications group="foo" />
    </div>
    <div v-else-if="($route.name === 'adminhome')">
      <AdminNav />
      <router-view />
      <notifications group="foo" />
    </div>
  </div>
  
</template>
<script>
import Nav from "./components/partials/Nav.vue";
import AdminNav from "./components/partials/AdminNav.vue";
export default {
  name: "app",
  provide() {
    return{
      reload:this.reload
    }
  },
  data(){
    return{
      isRouterAlive:true
    }
  },
  components: {
    Nav,
    AdminNav,
  },
  methods: {
    
    reload(){
      this.isRouterAlive = false;
      this.$nextTick(function(){
        this.isRouterAlive = true
      })
    }
  },
};
</script>
<style lang="scss" scoped>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
