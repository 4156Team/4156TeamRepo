<template>
  <el-table board
    :data="tableData"
    height="600"
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="props">
        <p>Introduction; {{ props.row.facilityIntroduction }}</p>
        <p>
          <el-image :src="props.row.facilityImage"
          :alt="props.row.facilityName">
          </el-image>
        </p>
      </template>
    </el-table-column>

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
        <!-- <el-button @click="handleClick(scope.row)" type="text" size="small">Favorites</el-button> -->
        <el-button @click="handleQuickPass(scope.row)" type="text" size="small">GetQuickPass</el-button>
          <el-dialog title="Select a quick pass time" :visible.sync="dialogFormVisible">
            <el-form>
                <el-time-select
                    v-model="quickPassTime"
                    :picker-options="{
                      start: '10:00',
                      step: '00:30',
                      end: '20:30'
                    }"
                    placeholder="Select time">
                  </el-time-select>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">Cancel</el-button>
                <el-button type="primary" @click="handleAddQuickPass">Confirm</el-button>
            </span>
          </el-dialog>

      </template>
    </el-table-column>
  </el-table>
</template>

<script>
  export default {
    data() {
      return {
        tableData:[],
        dialogFormVisible:false,
        facilityChosen:[],
        quickPassTime:''
      }
    },
    methods: {
      handleQuickPass(row){
        this.dialogFormVisible = true
        this.facilityChosen = row
        console.log("chosen",this.facilityChosen)
    },
      handleAddQuickPass(){
        var d = new Date()
        // var d2 = new Date(this.quickPassTime)
        var array = this.quickPassTime.split(":")
        d.setHours(array[0])
        d.setMinutes(array[1])
        // var d2 = [
        //   d.getFullYear(),
        //   ('0' + (d.getMonth() + 1)).slice(-2),
        //   ('0' + d.getDate()).slice(-2)
        // ].join('-')
        // var timestamp = String(d2).replace(/(?:[01]\d|2[0-3]):(?:[0-5]\d):(?:[0-5]\d)/,this.quickPassTime + ":00")
        console.log(d)
        // var timestamp = String(d2) + " "+ this.quickPassTime + ":00"
        this.dialogFormVisible = false
        var param = JSON.stringify({
            facilityName: this.facilityChosen.facilityName,
            startTime: d.toISOString()});
        console.log(param)
        this.$axios.post("/api/quickpass/appointQuickPass",param,{
            headers: {
            "Content-Type": "application/json",
            },}).then((response) => {
            console.log(response);
              this.$notify({
              group: "foo",
              title: "Important message",
              text: "Hello user! You success to add a quick pass!",
            });
        }).catch((error) =>{
            console.log(error)
        })
        },       
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
