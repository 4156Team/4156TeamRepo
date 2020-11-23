<template>
  <div class="date">
  <el-row :gutter="10">
    <!-- <el-col :span="10" :offset="0"> -->
    <el-date-picker
      v-model="date_value"
      format="yyyy/MM/dd"
      value-format="yyyy-MM-dd"
      @change="dateChangebirthday1"
      type="date"
      placeholder="Select your day">
    </el-date-picker>
    
  <!-- </el-col> -->
  </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // pickerOptions: {
      //   disabledDate(time) {
      //     return time.getTime() > Date.now();
      //   },
      // },
      date_value: "",
    };
  },
  methods: {
    dateChangebirthday1(val) {
      // const date = new Date();
      // date.setTime(val.getTime() - 3600 * 1000 * 24 * 7);
      this.date_value = val;
      this.$emit("DateSelected", this.date_value);
      console.log("Newdate", this.date_value);
    },
  },
};
</script>
<style scoped>
.date{
  margin-left: 64px;
}

</style>
