<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <el-button @click="back" style="float: left; padding: 3px 3px" type="text">Back</el-button>
      <!-- <span> {{ currentEvent.category }}</span> -->
      <el-button @click="next" style="float: right; padding: 3px 0" type="text">Next</el-button>
    </div>
    <div class="text item">
      {{ "Type: " + currentEvent.ticketType}}
    </div>
    <div class="text item">
      {{ "Price: $" + currentEvent.ticketPrice}}
    </div>
    <div class="text item">
      {{ "Description: " + currentEvent.description }}
    </div>
    <div class="text item">
      <el-button
        @click.prevent="register"
        style="center; padding: 3px 0"
        type="text">Register
      </el-button>
    </div>
  <div class="img">
  <img :src="currentEvent.imageUrl">
  </div>
  </el-card>
</template>
<script>
export default {
  inject:['reload'],
  props: {
    selectedTime: String,
    currentEvent: Object,
    next: Function,
    back: Function,
  },
  data() {
    return { registerEvent: null,
    register_st: 0
     };
  },
  methods: {
    register() {
      if (!this.selectedTime) {
        this.$msg("Please select a date to register");
      } else {
        var param = {
          price: this.currentEvent.ticketPrice,
          validDate: this.selectedTime,
          status: "unused",
        };
        console.log("ticket!",param)
        this.$axios
          .post("/api/ticket/addTicket", JSON.stringify(param), {
            headers: {
              "Content-Type": "application/json",
            },
          })
          .then((response) => {
            if (response.data.status == "success") {
              this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! Your purchasement is successed",
              });
              console.log(response);
              // this.reload()
              this.register_st += 1
              console.log("re",this.register_st)
              this.$emit("register",this.register_st)
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
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  margin-left: 40px;
  width: 400px;
  text-align: center;
  padding: 14px 14px;
}
.img{
  display: flex;
  justify-content: center;
}
</style>
