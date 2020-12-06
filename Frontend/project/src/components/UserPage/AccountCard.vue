<template>
  <div>
    <el-card class="box-card" >
      <el-container>
        <el-main>
            You have {{currentBalance}}$ in your acount
        </el-main>
        <el-button  type="text" @click="dialogFormVisible = true">Add More</el-button>
        <!-- <el-button  style="float: right" type="text">View details</el-button> -->     
      </el-container>
        <el-dialog title="Add balance into your account" :visible.sync="dialogFormVisible">
        <el-form>
            <el-form-item label="Add balance" :label-width="formLabelWidth">
                <el-input-number v-model="addBalance" @change="handleChange" :min="1" :max="1000"></el-input-number>
            </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="handleAddBalance">Confirm</el-button>
        </span>
        </el-dialog>
        </el-card>
  </div>

</template>
<script>
import qs from "qs";
export default {
    inject:['reload'],
    name:"AccountCard",
    props:{
      refresh:Number
    },
    data(){
        return{
            currentBalance: 0,
            addBalance:"",
            dialogFormVisible: false,
            formLabelWidth: '120px',
        }
    },
    watch:{
      refresh(){
        this.getCurrentBalance()
      }
    },
    methods:{
        handleChange(value) {
        // console.log(value)
        this.addBalance = value
      },
        handleAddBalance(){
        console.log("submit")
        this.dialogFormVisible = false
        var param = qs.stringify({
            amount: this.addBalance});
        console.log(param)
        this.$axios.get("/api/balance/addBalance?" + param)
        .then((response) => {
              this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! Your add " + this.addBalance + " $ into your account! ",
              });
            console.log("add",response);
            this.reload()
        }).catch((error) =>{
            console.log(error)
        })
        }, 
        getCurrentBalance(){
          this.$axios
            .get("/api/balance/queryBalance")
            .then((response) => {
              return response;
            })
            .then((jsonData) => {
              if (jsonData.data.status == "success") {
                this.currentBalance = jsonData.data.data.balance
              } else this.$msg("Failed");
            });
        }      
    },
  mounted(){
    this.dialogFormVisible = false
    this.getCurrentBalance()
  }
}
</script>
<style scoped>
.box-card{
width: 480px;
}
</style>