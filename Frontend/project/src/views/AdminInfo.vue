<template>
  <el-container>
    <el-container>
      <h1 class="title">This is admin page </h1>
      <el-header height="100px">
      <span>
        <AdminDatePicker
        v-on:pickDates="pickDates"/>
      </span>
      </el-header>
      <el-main>
      <div>
        <PeopleDay 
        :dateArray="dateArray"/>
      </div>
      </el-main>    
  </el-container>
  </el-container>
  <!-- </div> -->
</template>

<script>
import PeopleDay from "../components/AdminPage/PeopleDay"
import AdminDatePicker from "../components/AdminPage/AdminDatePicker"
export default {
  name:"Admininfo",
  components:{
    PeopleDay,
    AdminDatePicker,
    },
    data() {
    return {
      dateArray:""
    }
  },
  methods: {
    pickDates(e){
      this.dateArray = e
      console.log("rangedate",e)
    }
}
};
</script>
<style scoped>
.title{
  margin-top: 20px;
  margin-left: 20px;
  font-size: 20pt;
}
</style>