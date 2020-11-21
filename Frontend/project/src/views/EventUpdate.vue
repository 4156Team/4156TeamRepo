<template>
  <div id="Admin">
    <router-view />
    <notifications group="foo" />
    <EditEventform />
  </div>
</template>

<script>
import EditEventform from "../components/EditEventform";
export default {
  components: {
    EditEventform,
  },
};
</script>
