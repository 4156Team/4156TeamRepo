<template>
  <div>
    <el-table
    :data="comments"
    style="width: 100%"
    height="600">
    <el-table-column>
      <div
        v-for="comment in comments"
        :comment="comment"
        :key="comment.commentId"
        >
         <CommentSingleCard 
         :comment="comment"
         />
      </div>
    </el-table-column>
    </el-table>
  </div>
</template>
<script>
import CommentSingleCard from "./CommentSingleCard"
export default {
  name: "CommentsList",
  components: {
    CommentSingleCard,
  },
  data() {
    return {
      comment: {},
      addAttraction:"",
      comments:[],
    };
  },
  methods:{
  },
  mounted: function () {
    // GET /someUrl
      this.$axios
        .get("/api/comment/showComments")
        .then((response) => {
          return response;
        })
        .then((response) => {
          if (response.data.status == "success") {
            this.comments = response.data.data;
            console.log(this.comments);
          } else window.alert("Failed");
    });
  }, 
};
</script>
<style scoped>
</style>