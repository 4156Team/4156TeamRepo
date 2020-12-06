<template>
  <el-container>
    <el-header height="30px"> 
      <h1 class="header">Please give us feed back
      <vs-button color="#3dd495" gradient-color-secondary="rgb(130, 207, 23)" type="gradient" @click="dialogFormVisible = true" >Add a comment</vs-button>
      <el-dialog title="Give us a feedback" :visible.sync="dialogFormVisible">
        <el-form>
          <el-input
            type="textarea"
            :rows="3"
            placeholder="Please input your feedback"
            v-model="textarea">
          </el-input>
        </el-form>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">Cancel</el-button>
            <el-button type="primary" @click="handleAddComment">Confirm</el-button>
        </span>
      </el-dialog>

      </h1>
    </el-header>
    <el-container>
      <el-main>
      <RateFacility />
      </el-main>
      <el-main>
      <CommentList />
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import RateFacility from "../components/UserPage/RateFacility"
import CommentList from "../components/UserPage/CommentList"
export default {
    name:"FeedbackPage",
    inject:['reload'],
    components:{
        RateFacility,
        CommentList
    },
    data(){
      return{
        textarea:"",
        dialogFormVisible:false
      }
    },
   methods:{
     handleAddComment(){
      //  console.log(new Date().getTime());
       if(!this.textarea){
         this.$msgbox.alert('Please tell us your feelings today to help us become better!', 'Need your feedback', {
          confirmButtonText: 'OK',
        });
       }else{
         this.dialogFormVisible = false
         var param = JSON.stringify(
           {commentContent: this.textarea,
           commentTime: new Date()
         })
         this.$axios
        .post("/api/comment/postComment",param,{
              headers: {
              "Content-Type": "application/json",
              },
          })
        .then((response) => {
          return response;
        })
        .then((response) => {
          if (response.data.status == "success") {
            this.comments = response.data.data;
            this.$notify({
                group: "foo",
                title: "Important message",
                text: "Thanks for your feedback!",
              });
            this.reload();
          } else this.$msg("Failed");
    });

       }
     }
   }
    
}
</script>
<style scoped>
.header{
  text-align: center;
}

</style>>