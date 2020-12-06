<template> 
  <el-rate
    v-model="value"
    :icon-classes="iconClasses"
    void-icon-class="icon-rate-face-off"
    :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
  </el-rate>
</template>
<script>
  export default {
    props:["value"],
    data() {
      return {
        iconClasses: ['icon-rate-face-1', 'icon-rate-face-2', 'icon-rate-face-3'] // same as { 2: 'icon-rate-face-1', 4: { value: 'icon-rate-face-2', excluded: true }, 5: 'icon-rate-face-3' }
      }
    }
  }
</script>