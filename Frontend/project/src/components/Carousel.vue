<template>
  <el-carousel :interval="4000" type="card" height="300px">
    <el-carousel-item v-for="item in images" :key="item.id">
      <img :src="item.imgSrc" class="“carousel_image_type”" />
      <!-- <h3 class="medium">{{ item }}</h3> -->
    </el-carousel-item>
  </el-carousel>
</template>
<script>
export default {
  data: function() {
    return {
      images: [
        {id:0,
          imgSrc: require("../assets/img/banner/christmas.jpg"),
        },
        {id:1,
          imgSrc: require("../assets/img/banner/cooking.png"),
        },
        {id:2,
          imgSrc: require("../assets/img/banner/Fireworks.jpg"),
        },
        {id:3,
          imgSrc: require("../assets/img/banner/cooking-1.jpg"),
        },
        
      ],
    };
  },
};
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.carousel_image_type {
  width: 100%;
}
</style>
