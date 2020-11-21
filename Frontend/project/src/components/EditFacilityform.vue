<template>
  <el-form ref="form" :model="form" label-width="120px">
    <el-form-item class="item" label="Facility Name">
      <el-input v-model="form.facilityName" style="width: 60%;"></el-input>
    </el-form-item>
    <el-form-item class="item" label="Facility location">
      <el-input v-model="form.facilityLocation" style="width: 60%;"></el-input>
    </el-form-item>
    <el-form-item label="Time">
      <el-col :span="5">
        <el-input v-model="form.facilityOpenTime" style="width: 60%;" placeholder="Open 00:00:00"></el-input>
      </el-col>
    <el-col class="line" :span="2">-</el-col>
      <el-col :span="5">
        <el-input v-model="form.facilityCloseTime" style="width: 60%;" placeholder="Close 00:00:00"></el-input>
      </el-col>
    </el-form-item>

    <el-form-item label="Open/Close">
      <el-switch v-model="form.FacilityStatus"></el-switch>
    </el-form-item>
    <el-form-item disabled label="Activity type">
      <el-checkbox-group v-model="form.type">
        <el-checkbox disabled label="Close activities" facilityName="type"></el-checkbox>
        <el-checkbox disabled label="Open activities" facilityName="type"></el-checkbox>
        <el-checkbox disabled label="Online activities" facilityName="type"></el-checkbox>
        <el-checkbox disabled label="offline activities" facilityName="type"></el-checkbox>
      </el-checkbox-group>
    </el-form-item>
    <el-form-item label="Facility Introduction">
      <el-input
        type="textarea"
        v-model="form.facilityIntroduction"
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
        facilityName: null,
        facilityLocation: null,
        facilityOpenTime: null,
        facilityCloseTime: null,
        FacilityStatus:null,
        queueStatus:null,
        facilityIntroduction: null,
      },
    };
  },

  methods: {
    onCreate() {
      console.log("create!", this.form);
      let param = this.form
      this.$axios
          .post("/api/manager/addFacility", JSON.stringify(param), {
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
                  text: "Hello manager! create facility successed",
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
          .post("/api/manager/updateFacility", JSON.stringify(param), {
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
                  text: "Hello manager! create facility successed",
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
          .post("/api/manager/deleteFacility", JSON.stringify(param), {
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
                  text: "Hello manager! create facility successed",
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