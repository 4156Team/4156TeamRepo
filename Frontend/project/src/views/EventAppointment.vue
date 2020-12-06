<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="18"> </el-col>
      <el-col :span="4">
        <el-button @click="deleteAppointment" type="primary" plain>Cancel</el-button>
      </el-col>
    </el-row>
    <TicketCheck v-if="redenComponet" v-on:EventSelected="appointment_se" />
  </div>
</template>
<script>
// import DatePicker from "../components/DatePicker.vue";
// import TimePicker from "../components/TimePicker.vue";
import TicketCheck from "../components/AppointmentShow.vue";
import qs from "qs";
export default {
  name: "appointment",
  inject:['reload'],
  data() {
    return {
      // date_select: "",
      // time_select: "",
      appoint_select: "",
      redenComponet: true,
    };
  },
  components: {
    // DatePicker,
    // TimePicker,
    TicketCheck,
  },
  methods: {
    appointment_se(t) {
      this.appoint_select = t;
      console.log("test",this.appoint_select.appointmentid);
    },

    deleteAppointment() {
      var book = this;
      if (this.appoint_select != null) {
        var param = qs.stringify({
          appointmentId: book.appoint_select.appointmentId,
        });
        this.$msgbox.confirm('This operation will permanently cancel the appointment, do you want to continue', 'Note', {
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Not sure',
        type: 'warning'
      }).then(() => {
          this.$axios
          .post("/api/appointment/deleteAppointment", param)
          .then((response) => {
            console.log(response.data);
            if (response.data.status == "success") {
              this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! You have dropped a appointment",
              });
              this.reload()
            } else {
              this.$notify({
                group: "foo",
                title: "Important message",
                type: "warn",
                text: "You have dropped that one, please refresh",
              });
            }
          })
          .catch(function(error) {
            console.error(error.response);
          });
      })
      }
    },
  },
};
</script>

<style>
.el-row {
  margin-bottom: 20px;
}
.el-col {
  border-radius: 10px;
  margin-top: 20px;
}
.bg-purple-dark {
  background: #94b6e6;
}
.bg-purple {
  background: #91d6b3;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 40px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
