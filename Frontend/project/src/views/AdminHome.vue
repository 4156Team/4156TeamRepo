<template>
  <el-container>
    <el-container>
      <h1 class="title">This is admin page </h1>
      <el-header height="100px">
      <span>
        <AdminTimePicker
        v-on:pickRange="pickRange"/>
      </span>
      </el-header>
      <el-main>
      <div>
        <PeopleMonth 
        :monthRange="monthRange"/>
      </div>
      </el-main>
    </el-container>
  </el-container>
  <!-- </div> -->
</template>

<script>
import PeopleMonth from "../components/AdminPage/PeopleMonth"
import AdminTimePicker from "../components/AdminPage/AdminTimePicker"
export default {
  name:"Adminhome",
  components:{
    PeopleMonth,
    AdminTimePicker,
    },
    data() {
    return {
      monthRange: "",
    }
  },
  methods: {
    pickRange(e) {
      this.monthRange = e
      console.log("range",e)
    },
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

