<template>
  <div>
    <span class="demonstration">Select Your time</span>
    <el-time-select
      v-model="time_value"
      @change="timeChangebirthday1"
      :picker-options="{
        start: '08:30',
        step: '00:15',
        end: '18:30',
      }"
      placeholder="选择时间"
    >
    </el-time-select>
  </div>
</template>

<script>
export default {
  data() {
    return {
      time_value: "",
    };
  },
  methods: {
    timeChangebirthday1(val) {
      this.time_value = val;
      this.$emit("TimeSelected", val);
      console.log(this.time_value);
    },
  },
};
</script>
