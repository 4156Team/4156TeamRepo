<template>
  <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <p>Text: {{ props.row.text }}</p>
      </template>
    </el-table-column>
    <el-table-column
      label="Date"
      prop="date">
    </el-table-column>
    <el-table-column
      label="Id"
      prop="announcementId">
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData: ""
      }
    },
    mounted: function() {
    // GET /someUrl
    this.$axios
      .get("/api/announcement/getAnnouncements")
      .then((response) => {
        // console.log(response.data[0].facility_name);
        return response;
      })
      .then((jsonData) => {
        if (jsonData.data.status == "success") {
          this.tableData = jsonData.data.data;
          console.log(this.tableData);
        } else window.alert("Failed");
      });
  },
};
</script>