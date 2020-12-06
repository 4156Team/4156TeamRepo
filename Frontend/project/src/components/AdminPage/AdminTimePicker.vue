<template>
  <div class="block">
    <el-row>
    <span class="demonstration">Select Month Range</span>
    </el-row>
    <el-row>
    <el-date-picker
      v-model="value2"
      size="large"
      type="monthrange"
      align="left"
      value-format="MM"
      unlink-panels
      @change="dateChangeMonth"
      start-placeholder="Start month"
      end-placeholder="End month"
      :picker-options="pickerOptions">
    </el-date-picker>
    </el-row>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        pickerOptions: {
          shortcuts: [{
            text: 'This month',
            onClick(picker) {
              picker.$emit('pick', [new Date(), new Date()]);
            }
          }, {
            text: 'This year',
            onClick(picker) {
              const end = new Date();
              const start = new Date(new Date().getFullYear(), 0);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: 'Last 6 months',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setMonth(start.getMonth() - 6);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value2: ''
      };
    },
    methods: {
      dateChangeMonth(val){
          this.$emit('pickRange',val)
      }  
    },
  };
</script>
