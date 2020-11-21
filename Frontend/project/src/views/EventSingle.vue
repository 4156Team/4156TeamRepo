<template>
  <div class="event-single">
    <section class="hero is-primary is-bold">
      <el-row :gutter="20">
        <el-col :span="20">
          <div class="hero-body">
            <div class="hero-body">
              <div class="container">
                <h1 class="title">{{ event.eventName }}</h1>
                <h2 class="subtitle">
                  <strong>Start Time</strong>
                  {{ event.startTime }}
                  <br />
                  <strong>End Time:</strong>
                  {{ event.endTime }}
                  <br />
                  <strong>Remain Position:</strong>
                  {{ event.eventRemainPositions}}
                  <br />
                </h2>
              </div>
            </div>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="icon">
            <p class="icon" />
            <el-button
              @click="RegisterEvent"
              type="success"
              icon="el-icon-check"
              plain>
              Register
            </el-button>
            <el-button type="success" icon="el-icon-s-home" circle>
              <router-link to="/" class="navbar-item">Home</router-link>
            </el-button>
          </div>
        </el-col>
      </el-row>
    </section>
    <section class="event-content">
      <div class="container">
        <p class="is-size-4 description">{{ event.eventName }}</p>
        <p class="is-size-5" style="margin-top:20px; margin-bottom:30px">
          <strong>Location:</strong>
          {{ event.eventLocation }}
        </p>
        <p class="is-size-5" style="margin-top:20px; margin-bottom:30px">
          <strong>StartTime:</strong>
          {{ event.startTime }}
        </p>
        <p class="is-size-5" style="margin-top:20px; margin-bottom:30px">
          <strong>EndTime:</strong>
          {{ event.endTime }}
        </p>
        <p class="is-size-5">
          <strong>RemainPositions:</strong>
          {{ event.eventRemainPositions }}
        </p>
        <!-- <div class="event-images columns is-multiline has-text-centered">
          <img
            :src="`${event[0].Event_image}`"
            :alt="`${event[0].Event_name}`"
          />
        </div> -->
      </div>
    </section>
  </div>
</template>
<script>
import qs from "qs";
export default {
  name: "EventSingle",
  data() {
    return {
      event: [],
      eventName: "",
    };
  },
  methods: {
    RegisterEvent() {
      var param = JSON.stringify({
        eventName: this.eventName,
        status: "unused"
      });
      this.$http
        .post("/api/appointment/addAppointment", param)
        .then((response) => {
            console.log(response.data);
            if (response.data.status == "success") {
              this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! You have register a event",
              });
            } else {
              this.$notify({
                group: "foo",
                title: "Important message",
                type: "warn",
                text: "Something wrong",
              });}
        }).catch(function(error) {
            console.error(error.response);
          });
    },
  },
  created() {
    let selectevent = this.$route.path;
    // console.log(this.$route.name);
    this.eventName = selectevent.replace(/\/event\//, "")
    console.log(this.eventName );
    return selectevent;
  },
  mounted: function () {

    var param =qs.stringify({
          eventName: this.eventName,
        })
    console.log(param);
    this.$axios
      .get("/api/query/Event?" + param)
      .then((response) => {
        console.log(response);
        if (response.data.status == "success") {
          this.event = response.data.data;
          console.log(this.event);
        } else window.alert("Failed");
      });
  }, 
};
</script>
<style lang="scss" scoped>
.icon {
  margin-top: 100px;
  margin-bottom: 30px;
  padding: 40px 0 20px 0;
}
</style>
