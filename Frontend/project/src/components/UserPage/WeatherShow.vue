<template>
  <div v-loading="loading">
  <el-card class="box-card">
    <h1>Max temp in that day will be {{this.tableData.maxTemp}}</h1>
    <h2>Min temp in that day will be {{this.tableData.minTemp}}</h2>
    <h3>The weather in that day will be {{this.tableData.weather}}</h3>
  </el-card>
  </div>
</template>
<script>
import qs from "qs"

export default {
    props:{
        selectedTime: String,
    },
    data() {
        return {
            tableData:"",
            weather:"",
            loading:true
        }
    },
    watch:{
      selectedTime(newTime){
          console.log("NewTime",newTime)
          this.selectedTime = newTime
          var param = {date:this.selectedTime}
          this.$axios
            .post("/api/weather/query",qs.stringify(param))
            .then((response) => {
                console.log(response);
            if (response.data.status == "success") {
                this.tableData = response.data.data;
                console.log(this.tableData)
                this.loading = false
            } else this.$msg("Failed");
            });
      }
    },
}
</script>
<style scoped>
.box-card{
width: 480px;
}
</style>