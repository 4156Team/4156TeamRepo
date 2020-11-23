<template>
  <b-container class="bv-example-row">
    <!-- <el-row :gutter="10"> -->
      <!-- <el-col :span="8"> -->
        <div class="grid-content bg-purple">
          <DatePicker v-on:DateSelected="date_se" />
          <!-- <h2>{{ date_select }}</h2> -->
        </div>
      <!-- </el-col> -->
    <!-- </el-row> -->

    <b-row class="box"> 
      <b-col sm="6" offset="3">
        <BookBox
          :currentEvent="event[index]"
          :next="next"
          :back="back"
          :selectedTime="date_select"
        />
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import BookBox from "../components/BookBox.vue";
import DatePicker from "../components/DatePicker.vue";
export default {
  components: {
    BookBox,
    DatePicker,
  },
  name: "fetchdata",
  data() {
    return {
      event: [
        {
          Type: 0,
          category: "Adult",
          description: "One day ticket for adult",
          price: "20",
          imgSrc: require("../assets/img/ticket/adult.jpeg"),
        },
        {
          Type: 1,
          category: "Teenager",
          description: "One day ticket for teenager",
          price: "10",
          imgSrc: require("../assets/img/ticket/teen.jpeg"),
        },
      ],
      index: 0,
      date_select: "",
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
  },
};
</script>
<style scoped>
.box{
  margin-top: 20px;
}
</style>
