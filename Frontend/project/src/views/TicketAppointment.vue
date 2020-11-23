<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="18"> </el-col>
      <el-col :span="4">
        <el-button @click="deleteTicket" type="primary" plain>Delete</el-button>
      </el-col>
    </el-row>
    <TicketCheck v-if="redenComponet" v-on:TicketSelected="ticket_se" />
  </div>
</template>
<script>
import TicketCheck from "../components/TicketShow.vue";
import qs from "qs";
export default {
  name: "appointment",
  inject:['reload'],
  data() {
    return {
      // date_select: "",
      // time_select: "",
      ticket_select: "",
      redenComponet: true,
    };
  },
  components: {
    // DatePicker,
    // TimePicker,
    TicketCheck,
  },
  methods: {
    // date_se(e) {
    //   this.date_select = e;
    // },
    // time_se(t) {
    //   this.time_select = t;
    //   console.log(this.time_select);
    // },
    ticket_se(t) {
      this.ticket_select = t;
      console.log(this.ticket_select);
    },
    deleteTicket() {
      var book = this;
      if (this.ticket_select != null) {
        var param = qs.stringify({
          ticketId: book.ticket_select.ticketId,
        });
        this.$axios
          .post("/api/ticket/deleteTicket", param, {
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
          })
          .then((response) => {
            console.log(response.data);
            if (response.data.status == "success") {
              this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! You have dropped a ticket",
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
      }
    },
    forceRerender() {
      this.renderComponent = false;
      this.$nextTick().then(() => {
        this.renderComponent = true;
      });
    },
  },
  mounted() {
    this.forceRerender();
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
