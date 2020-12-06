<template>
<el-container>
<el-main>
  <el-row type="flex" class="row-bg">
    <el-col :span="5">
      <h>Select a ticket type to set price </h>
    </el-col>
    <el-col :span="5">
        <el-select v-model="value" clearable placeholder="Select a type">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
            </el-option>
        </el-select>
    </el-col>
    <el-col :span="5">
    <el-input-number v-model="price" @change="handleChange" :min="1" :max="100"></el-input-number>
    </el-col>
    <el-col :span="6">
    <div class="buttonitem"> 
        <el-button @click="handleTicket" type="success" icon="el-icon-check">Set Price</el-button>
    </div>
    </el-col>
  </el-row>
</el-main>
</el-container>
</template>

<script>
  export default {
    data() {
      return {
        options: [{
          value: 'adult',
          label: 'adult'
        }, {
          value: 'student',
          label: 'student'
        }, {
          value: 'child',
          label: 'child'
        }],
        value: '',
        price: 1
      }
    },
    methods: {
      handleChange(value) {
        this.price = value 
        console.log(value)
      },
      handleTicket(){
          if(!this.value){
              this.$msg("Please select a type");
          }else{
              var param = {ticketType:this.value,
                            ticketPrice:this.price}
                this.$axios
                .post("/api/manager/changeTicketPrice", JSON.stringify(param), {
                    headers: {
                    "Content-Type": "application/json",
                    },
                })
                .then((response) => {
                    if (response.data.status == "success") {
                    this.$notify({
                        group: "foo",
                        title: "Important message",
                        text: "Hello manager! Set Price for " + this.value + " success!",
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
      }
    };
</script>