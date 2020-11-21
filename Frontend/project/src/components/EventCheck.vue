<template>
  <el-table
    ref="multipleTable"
    :data="tableData"
    tooltip-effect="dark"
    style="width: 100%"
  >
    <el-table-column label="choose" width="80">
      <template scope="scope">
        <el-radio
          :label="scope.$index"
          v-model="radio"
          @change="getCurrentRow(scope.row)"
        ></el-radio>
      </template>
    </el-table-column>
    <el-table-column label="id" width="120">
      <template slot-scope="scope">{{ scope.row.id }}</template>
    </el-table-column>
    <el-table-column prop="name" label="name" width="120"></el-table-column>
    <el-table-column
      prop="description"
      label="description"
      show-overflow-tooltip
    ></el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      radio: "",
      tableData: [
        {
          id: "00001",
          name: "Roller1",
          description: "Best Roller Coaster",
        },
        {
          id: "00002",
          name: "Roller2",
          description: "Best Roller Coaster",
        },
        {
          id: "00003",
          name: "Roller3",
          description: "Best Roller Coaster",
        },
        {
          id: "00004",
          name: "Roller4",
          description: "Best Roller Coaster",
        },
        {
          id: "00005",
          name: "Roller5",
          description: "Best Roller Coaster",
        },
        {
          id: "00006",
          name: "Roller6",
          description: "Best Roller Coaster",
        },
        {
          id: "00007",
          name: "Roller7",
          description: "Best Roller Coaster",
        },
      ],
      templateSelection: [],
    };
  },
  methods: {
    getCurrentRow(row) {
      //   获取选中数据
      this.templateSelection = row;
      this.$emit("EventCheckSelected", row);
      console.log(this.templateSelection);
    },
  },
};
</script>
