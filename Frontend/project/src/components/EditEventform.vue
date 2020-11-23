<template>
  <el-form ref="form" :model="form" label-width="120px">
    <el-form-item class="item" label="Event Name">
      <el-input v-model="form.eventName" style="width: 60%;"></el-input>
    </el-form-item>
    <el-form-item class="item" label="Event location">
      <el-input v-model="form.eventLocation" style="width: 60%;"></el-input>
    </el-form-item>
    <!-- <el-form-item label="Start time">
      <el-col :span="8">
        <el-time-picker
          placeholder="Pick a  start time"
          v-model="form.startTime"
          style="width: 70%;"
        ></el-time-picker>
      </el-col>
      <el-col class="line" :span="2">-</el-col>
      <el-col :span="8">
        <el-time-picker
          placeholder="Pick a close time"
          v-model="form.endTime"
          style="width: 70%;"
        ></el-time-picker>
      </el-col>
    </el-form-item> -->
    <el-form-item label="Time">
      <el-col :span="5">
        <el-input v-model="form.startTime" style="width: 60%;" placeholder="Start 00:00:00"></el-input>
      </el-col>

    <el-col class="line" :span="2">-</el-col>
      <el-col :span="5">
        <el-input v-model="form.endTime" style="width: 60%;" placeholder="End 00:00:00"></el-input>
      </el-col>
    </el-form-item>
    <el-form-item label="Remain Position">
      <el-input-number v-model="form.eventRemainPositions" :min="1" :max="1000"></el-input-number>
    </el-form-item>
    <!-- <el-form-item label="Open/Close">
      <el-switch v-model="form.status"></el-switch>
    </el-form-item> -->
    <el-form-item disabled label="Activity type">
      <el-checkbox-group v-model="form.type">
        <el-checkbox disabled label="Close activities" eventName="type"></el-checkbox>
        <el-checkbox disabled label="Open activities" eventName="type"></el-checkbox>
        <el-checkbox disabled label="Online activities" eventName="type"></el-checkbox>
        <el-checkbox disabled label="offline activities" eventName="type"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="Resources">
      <el-radio-group v-model="form.resource">
        <el-radio disabled label="Event"></el-radio>
        <el-radio disabled label="Facility"></el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="Event Introduction">
      <el-input
        type="textarea"
        v-model="form.eventIntroduction"
        style="width: 60%;"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onCreate">Create</el-button>
      <el-button type="success" @click="onUpdate">Update</el-button>
      <el-button type="danger" @click="onCancel">Cancel</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      form: {
        eventName: null,
        eventLocation: null,
        startTime: null,
        endTime: null,
        eventRemainPositions:null,
        // status: false,
        // type: [],
        // resource: "",
        eventIntroduction: null,
      },
    };
  },

  methods: {
    onCreate() {
      console.log("create!", this.form);
      let param = this.form
      this.$axios
          .post("/api/manager/addEvent", JSON.stringify(param), {
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
                  text: "Hello manager! create event successed",
              });
              console.log(response);
              } else {
              window.alert(response.data.data);
              }
          })
          .catch(function(error) {
              console.error(error.response);
          });
    },
  
  onUpdate() {
      console.log("update!", this.form);
      let param = this.form
      this.$axios
          .post("/api/manager/updateEvent", JSON.stringify(param), {
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
                  text: "Hello manager! update event successed",
              });
              console.log(response);
              } else {
              window.alert(response.data.data);
              }
          })
          .catch(function(error) {
              console.error(error.response);
          });
    },
     onCancel() {
      console.log("Cancel!", this.form);
      let param = this.form
      this.$axios
          .post("/api/manager/deleteEvent", JSON.stringify(param), {
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
                  text: "Hello manager! delete event successed",
              });
              console.log(response);
              } else {
              window.alert(response.data.data);
              }
          })
          .catch(function(error) {
              console.error(error.response);
          });
    }
  },
};
</script>
<style scoped>
.item{
  margin-top: 10px;
}
</style>