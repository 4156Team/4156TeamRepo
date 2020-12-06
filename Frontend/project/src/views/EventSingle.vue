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
        <p class="is-size-5">
          <strong>Event Introduction:</strong>
          {{ event.eventIntroduction }}
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
        <div class="event-images">
          <img
            src="https://www.kiro7.com/resizer/nHPv7HlV0bPIDFRNOUq3dEg670c=/1200x675/cloudfront-us-east-1.images.arcpublishing.com/cmg/H3KH4Z5EKJBNHMMLYCRWZZ5PXU.jpg"
            :alt="`${event.eventName}`"
          />
        </div>
      </div>
    </section>
  </div>
</template>
<script>
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
      console.log(param)
      this.$http
        .post("/api/appointment/addAppointment", param,{
              headers: {
              "Content-Type": "application/json",
              },
          })
        .then((response) => {
            console.log(response.data)
            if (response.data.status == "success") {
              this.$notify({
                group: "foo",
                title: "Important message",
                text: "Hello user! You have register a event",
              });
              this.initData();
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
    initData () {
    var param =JSON.stringify({
          eventName: this.eventName,
        })
    this.$axios
      .post("/api/query/Event?", param,{
            headers: {
            "Content-Type": "application/json",
          },
        }).then((response) => {
        console.log("get",response);
        if (response.data.status == "success") {
          this.event = response.data.data;
          console.log(this.event);
        } else this.$msg("Failed");
      });
  }, 
  },
  created() {
    let selectevent = this.$route.path;
    let name = selectevent.replace(/\/event\//, "")
    // console.log(this.$route.name);
    this.eventName = decodeURI(name)
    console.log(this.eventName);
  },
    mounted() {
        this.initData();
    },
};
</script>
<style lang="scss" scoped>
.icon {
  margin-top: 100px;
  margin-bottom: 30px;
  padding: 40px 0 20px 0;
}
.event-images{
  margin-top: 20px;
  margin-bottom: 30px;
  padding: 40px 0 20px 0;

}
</style>
