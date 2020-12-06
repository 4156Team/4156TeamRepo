<template>
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%">
    <el-table-column prop="facilityName" label="facilityName" width="300"></el-table-column>
    <el-table-column prop="startTime" label="startTime" width="300"></el-table-column>
    <el-table-column prop="quickPassId" label="QuickPass Id" width="300"></el-table-column>
  </el-table>
</template>
<script>
export default {
  data() {
    return {
      radio: "",
      tableData: [],
    };
  },
  methods: {
  },
  mounted: function() {
    // GET /someUrl
    this.$axios
      .get("/api/quickpass/quickPassRecord")
      .then((response) => {
        return response;
      })
      .then((jsonData) => {
        if (jsonData.data.status == "success") {
          this.tableData = jsonData.data.data;
          console.log(this.tableData);
        } else this.$msg("Failed");
      });
  },
};
</script>