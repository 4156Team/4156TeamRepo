<template>
  <el-container class="bv-example-row">
    <el-container>
      <el-header>
        <div class="datepicker">
          <DatePicker v-on:DateSelected="date_se" />
        </div>
      </el-header>
      <el-main>
        <BookBox
          :currentEvent="event[index]"
          :next="next"
          :back="back"
          :selectedTime="date_select"
          v-on:register ="register"
        />
      </el-main>
      </el-container>
      <el-main>
        <div class="account">
        <AccountCard 
        v-if="isLogin"
        :refresh="register_status"/>
        </div>
        <div class="weather">
          <WeatherShow 
          :selectedTime="date_select"/>
        </div>
    </el-main>
  </el-container>
</template>

<script>
import AccountCard from "../components/UserPage/AccountCard"
import BookBox from "../components/BookBox.vue";
import DatePicker from "../components/DatePicker.vue";
import WeatherShow from "../components/UserPage/WeatherShow"
export default {
  components: {
    AccountCard,
    BookBox,
    DatePicker,
    WeatherShow,
  },
  name: "fetchdata",
  data() {
    return {
      event:[],
      index: 0,
      date_select: "",
      isLogin:false,
      register_status:0
    };
  },
  methods: {
    next() {
      this.index++;
    },
    back() {
      this.index--;
    },
    date_se(e) {
      this.date_select = e;
    },
    register(e){
      this.register_status = e
      console.log("ress",e)
    },
  },
  mounted: function () {
    // GET /someUrl
    this.isLogin = window.sessionStorage.getItem("isLogin");
    this.$axios
      .get("/api/ticketPrice/getAllTicketPrice")
      .then((response) => {
        return response;
      })
      .then((response) => {
        if (response.data.status == "success") {
          this.event = response.data.data;
          console.log(this.event);
        } else this.$msg("Failed");
      });
    }, 
  // mounted() {
  //   this.isLogin = window.sessionStorage.getItem("isLogin");
  // },
};
</script>
<style scoped>
.account{
  margin-top: 65px;
}
.weather{
  margin-top: 20px;
}
</style>
