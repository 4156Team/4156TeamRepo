<template>
  <el-table board
    :data="tableData"
    style="width: 100%">
    <el-table-column
      prop="facilityName"
      label="Name"
      width="300">
    </el-table-column>
    <el-table-column
      prop="facilityOpenTime"
      label="OpenTime"
      width="120">
    </el-table-column>
    <el-table-column
      prop="facilityCloseTime"
      label="CloseTime"
      width="120">
    </el-table-column>
    <el-table-column
      prop="facilityStatus"
      label="Status"
      width="120">
    </el-table-column>
        <el-table-column
      prop="queueStatus"
      label="queueStatus"
      width="120">
    </el-table-column>
    <el-table-column
      label="Operations"
      >
      <template slot-scope="scope">
        <el-button @click="handleClick(scope.row)" type="text" size="small">Detail</el-button>
        <el-button @click="handleJoinClick(scope.row)" type="text" size="small">Queue</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData:[]
      }
    },
    methods: {
      handleClick(row) {
      this.$notify({
      group: "info",
      title: "Facility Introduction " + row.facilityName,
      type: "success",
      text: row.facilityIntroduction,
    });
      console.log(JSON.stringify(row));
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
};

</script>
