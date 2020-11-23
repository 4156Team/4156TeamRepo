<template>
  <div id="Admin">
    <router-view />
    <notifications group="foo" />
    <h1 class="title">This is admin page </h1>
    <!-- <EditEventform /> -->
  </div>
</template>

<script>
</script>
<style scoped>
.title{
  margin-top: 20px;
  margin-left: 20px;
  font-size: 20pt;
}
</style>

