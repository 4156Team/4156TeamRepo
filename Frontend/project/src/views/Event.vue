<template>
  <div>
    <el-row :gutter="10">
      <el-col :span="8"
        ><div class="grid-content bg-purple">
          <DatePicker v-on:DateSelected="date_se" />
          <!-- <h2>{{ date_select }}</h2> -->
        </div></el-col
      >
      <el-col :span="8"
        ><div class="grid-content bg-purple-dark">
          <TimePicker v-on:TimeSelected="time_se" />
          <!-- <h2>{{ time_select }}</h2> -->
        </div></el-col
      >
      <el-col :span="4">
        <el-button @click="submitTicket" type="primary" plain>Submit</el-button>
      </el-col>
    </el-row>
    <EventCheck v-on:EventSelected="ticket_se" />
  </div>
</template>
<script>
import DatePicker from "../components/DatePicker.vue";
import TimePicker from "../components/TimePicker.vue";
import EventCheck from "../components/EventCheck.vue";
export default {
  name: "eventcheck",
  data() {
    return {
      date_select: "",
      time_select: "",
      event_select: "",
    };
  },
  components: {
    DatePicker,
    TimePicker,
    EventCheck,
  },
  methods: {
    login() {
      this.$auth.loginWithRedirect();
    },
    date_se(e) {
      this.date_select = e;
    },
    time_se(t) {
      this.time_select = t;
      console.log(this.time_select);
    },
    ticket_se(t) {
      this.ticket_select = t;
      console.log(this.event_select);
    },
    submitTicket() {
      var book = this;
      if (
        this.date_select.length > 0 &&
        this.time_select.length > 0 &&
        this.event_select.length > 0
      ) {
        var param = {
          auth_state: this.$auth.isAuthenticated,
          register_user: this.$auth.user.name,
          date_book: book.date_select,
          time_book: book.time_select,
          event_book: book.event_select,
        };
        // console.log(param);
        if (book.$auth.isAuthenticated) {
          book.$http
            .post("http://localhost:8081/test/", param)
            .then(console.log(param));
        } else {
          console.log("Not log in");
        }
        // if (!book.$auth.isAuthenticated) {
        //   book
        //     .$axios({
        //       url: "https://982517f7-7de9-4f21-b91b-fb184b613a4a.mock.pstmn.io",
        //       method: "post",
        //       data: param,
        //     })
        //     .then((ref) => {
        //       if (ref.result) {
        //         book.$message({
        //           message: book.$t("zsgl_zhgl.xxbccg"),
        //           type: "success",
        //         });
        //       } else {
        //         book.$message.error(ref.msg);
        //       }
        //     });
        // } else {
        //   this.$message.error();
        //   return false;
        // }
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
