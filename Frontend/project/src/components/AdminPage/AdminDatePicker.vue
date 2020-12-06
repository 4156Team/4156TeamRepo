<template>
<div class="block">
    <el-row>
    <span class="demonstration">Select Dates</span>
    </el-row>
    <el-row>
    <el-date-picker
      v-model="dates"
      value-format="MM-dd"
      type="dates"
      @change="dateChangeDate"
      placeholder="Pick one or more dates">
    </el-date-picker>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dates:""
    }
  },
  methods: {
    dateChangeDate(val){
      this.$emit('pickDates',val)
      console.log("dates",val)
    }  
  },
}
</script>