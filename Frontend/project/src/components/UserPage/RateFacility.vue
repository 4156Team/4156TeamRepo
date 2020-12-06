<template>
  <el-table
    :data="tableData"
    height="600"
    style="width: 100%">
    <el-table-column
      prop="facilityName"
      label="Name"
      width="300">
    </el-table-column>
    <el-table-column
      label="Rate Points"
      width="200">
      <template slot-scope="scope">
        <el-rate
          v-model="scope.row.rating"
          disabled
          text-color="#ff9900"
          show-score
          >
        </el-rate>
      </template>
    </el-table-column>
     <el-table-column
      label="Rating"
      width="120">
       <template slot-scope="scope">
        <!-- <el-button @click="handleClick" type="text" size="small">Detail</el-button> -->
        <el-popover
        placement="top"
        width="200"
        >
        <p>Give us a feedback</p>
        <el-rate
          v-model="value"
          show-score
          @change="handlechange(scope.row,value)"
          score-template="{value} points">
        </el-rate>
        <el-button type="text" slot="reference">Rate</el-button>
        </el-popover>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import qs from 'qs'
  export default {
    data() {
      return {
        tableData:[],
        value:0,
      }
    },
    methods:{
        handlechange(row,val){
          const number = val;
          this.value = number;
          this.$msg({
            message:"Rate with " + this.value + " points for " + row.facilityName,
            type: 'success'});
          var param = {facilityName:row.facilityName};
          var rate_number = qs.stringify({rate: this.value})
          this.$axios
            .post("/api/rating/postRate?" + rate_number,JSON.stringify(param),{
            headers: {
            "Content-Type": "application/json",
            },}).then( resp => {
              console.log(resp)
            })
            }
    },
    mounted: function () {
    // GET /someUrl
      this.$axios
        .get("/api/query/AllFacility")
        .then((response) => {
          return response;
        })
        .then((response) => {
          if (response.data.status == "success") {
            this.tableData = response.data.data;
            console.log(this.tableData);
          } else window.alert("Failed");
        });
    }, 
  }
</script>
