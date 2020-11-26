<template>
  <div id="Admin">
    <router-view />
    <notifications group="foo" />
    <EditFacilityform />
  </div>
</template>
<script>
import EditFacilityform from "../components/EditFacilityform";
export default {
  components: {
      EditFacilityform,
  },
};
</script>
