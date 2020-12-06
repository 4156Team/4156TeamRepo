<template>
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%"
  >
    <el-table-column label="choose" width="100">
      <template scope="scope">
        <el-radio
          :label="scope.$index"
          v-model="radio"
          @change="getCurrentRow(scope.row)"
        ></el-radio>
      </template>
    </el-table-column>

    <el-table-column prop="eventName" label="Event Name" width="200"></el-table-column>
    <el-table-column prop="startTime" label="Satrt Time" width="200"></el-table-column>
    <el-table-column prop="eventLocation" label="eventLocation" width="200"></el-table-column>
    <el-table-column
      prop="appointmentId"
      label="appointmentId"
      width="250"></el-table-column>
  </el-table>
</template>
<script>
export default {
  data() {
    return {
      radio: "",
      tableData: [],
      templateSelection: [],
    };
  },
  methods: {
    getCurrentRow(row) {
      //   获取选中数据
      this.templateSelection = row;
      this.$emit("EventSelected", row);
      console.log("select", this.templateSelection);
    },
  },
  mounted: function() {
    // GET /someUrl
    this.$axios
      .get("/api/appointment/appointmentsRecord")
      .then((response) => {
        // console.log(response.data[0].facility_name);
        return response;
      })
      .then((jsonData) => {
        if (jsonData.data.status == "success") {
          this.tableData = jsonData.data.data;
          console.log(this.tableData);
        } else this.$msg("Failed");
      });
  },
  beforeDestroy() {
    window.console.log("before destroied");
  },
};
</script>