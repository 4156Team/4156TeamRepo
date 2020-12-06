<template>
<el-container>
  <el-main>
    <el-row type="flex" class="row-bg">
    <el-col :span="6">
        <div class="item">
        <h1 class="item"> Ticket Id</h1>
            <el-input placeholder="Please input ticket ID" v-model="ticketId"></el-input> 
        </div>
        </el-col>
        <div class="buttonitem"> 
            <el-button @click="handleTicket" type="success" icon="el-icon-check">Checkin</el-button>
        </div>
        <el-col :span="6" :offset="4">
        <div class="item">
        <h1 class="item"> Appointment Id</h1>
            <el-input placeholder="Please input Appointment ID" v-model="appointmentId"></el-input> 
        </div>
        </el-col>
        <div class="buttonitem"> 
            <el-button @click="handleAppointment" type="success" icon="el-icon-check">Checkin</el-button>
        </div>
    </el-row>
    <el-row type="flex" class="row-bg">
    <el-col :span="6">
        <div class="item">
        <h1 class="item"> QuickPass Id</h1>
            <el-input placeholder="Please input QuickPass ID" v-model="quickPassId"></el-input> 
        </div>
        </el-col>
        <div class="buttonitem"> 
            <el-button @click="handleQuickpass" type="success" icon="el-icon-check">Checkin</el-button>
        </div>
    </el-row>
  </el-main>
</el-container>
</template>


<script>
export default {
  data() {
    return {
      ticketId: '',
      appointmentId:'',
      quickPassId:'',
    }
  },
  methods: {
      handleTicket(){
          if(!this.ticketId){
              this.$msg("Please input");
          }else{
              var param = {ticketId:this.ticketId}
                this.$axios
                .post("/api/manager/checkTicket", JSON.stringify(param), {
                    headers: {
                    "Content-Type": "application/json",
                    // "Access-Control-Allow-Credentials": "true",
                    },
                })
                .then((response) => {
                    if (response.data.status == "success") {
                    this.$notify({
                        group: "foo",
                        title: "Important message",
                        text: "Hello manager! Checkin successed",
                    });
                    console.log(response);
                    } else {
                    this.$msg(response.data.data);
                    }
                })
                .catch(function(error) {
                    console.error(error.response);
                });
          }
      },
        handleAppointment(){
          if(!this.appointmentId){
              window.alert("Please input");
          }else{
              var param = {appointmentId:this.appointmentId}
                this.$axios
                .post("/api/manager/checkAppointment", JSON.stringify(param), {
                    headers: {
                    "Content-Type": "application/json",
                    // "Access-Control-Allow-Credentials": "true",
                    },
                })
                .then((response) => {
                    if (response.data.status == "success") {
                    this.$notify({
                        group: "foo",
                        title: "Important message",
                        text: "Hello manager! Checkin successed",
                    });
                    console.log(response);
                    } else {
                    this.$msg(response.data.data);
                    }
                })
                .catch(function(error) {
                    console.error(error.response);
                });
          }
      },
        handleQuickpass(){
          if(!this.quickPassId){
              this.$msg("Please input");
          }else{
              var param = {quickpassId:this.quickPassId}
              console.log(param)
                this.$axios
                .post("/api/manager/checkQuickPass", JSON.stringify(param), {
                    headers: {
                    "Content-Type": "application/json",
                    // "Access-Control-Allow-Credentials": "true",
                    },
                })
                .then((response) => {
                    if (response.data.status == "success") {
                    this.$notify({
                        group: "foo",
                        title: "Important message",
                        text: "Hello manager! Checkin successed",
                    });
                    console.log(response);
                    } else {
                    this.$msg(response.data.data);
                    }
                })
                .catch(function(error) {
                    console.error(error.response);
                });
          }
      },
      
      
  },
}
</script>
<style scoped>
.item {
  margin-top: 10px;
  margin-left: 20px;
}
.buttonitem{
  margin-top: 30px;
  margin-left: 20px;
}

</style>