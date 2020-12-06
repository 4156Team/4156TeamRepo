<template>
  <el-form ref="form" :model="form" label-width="200px">
    <el-form-item label="Annoucement Content">
      <el-input
        type="textarea"
        v-model="form.text"
        rows=5
        style="width: 80%;"
      ></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onCreate">Push</el-button>
    </el-form-item>
    <el-form-item label="Annoucement_id">
      <el-input
        type="text"
        maxlength="10"
        show-word-limit
        suffix-icon="el-icon-edit"
        v-model="form.announcementId"
        style="width: 50%;"
      ></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="danger" @click="onCancel">Delete</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      form: {
        date: null,
        text: null,
        announcementId: null,
      },
    };
  },
  methods:{
      onCreate(){
      let param = {text:this.form.text}
      this.$axios
          .post("/api/manager/pushAnnouncement", JSON.stringify(param), {
              headers: {
              "Content-Type": "application/json",
              },
          })
          .then((response) => {
              if (response.data.status == "success") {
              this.$notify({
                  group: "foo",
                  title: "Important message",
                  text: "Hello manager! You have pushed a new annoucement.",
              });
              console.log(response);
              } else {
              this.$msg(response.data.data);
              }
          })
          .catch(function(error) {
              console.error(error.response);
          });
      },
      onCancel(){
      let param = {announcementId:this.form.announcementId}
      this.$axios
          .post("/api/manager/deleteAnnouncement", JSON.stringify(param), {
              headers: {
              "Content-Type": "application/json",
              },
          })
          .then((response) => {
              if (response.data.status == "success") {
              this.$notify({
                  group: "foo",
                  title: "Important message",
                  text: "Hello manager! You have deleted an annoucement.",
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
  }
    
}
</script>