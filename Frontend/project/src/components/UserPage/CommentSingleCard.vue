<template lang="html">
    <vs-row vs-justify="center">
    <vs-col type="flex" vs-justify="center" vs-align="center" vs-w="10">
      <vs-card actionable class="comment-card">
        <div slot="header">
          <h3>
            {{comment.userName}}
          </h3>
        </div>
        <div slot="media">
          <!-- <img :src="$withBase('/card.png')"> -->
        </div>
        <div>
          <span>Lorem ipsum dolor sit amet, consectetur adipiscing elit
            {{ comment.commentContent}}
          </span>
        </div>
        <!-- <div slot="footer"> 
          <vs-row vs-justify="flex-end">
            <vs-button @click="popupActivo=true" color="primary" type="gradient">View</vs-button>
             <vs-popup title="fullscreen" :active.sync="popupActivo">
               <p>
                consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
              </p>
              </vs-popup>
            <vs-button @click="handleAdd" color="danger" type="gradient">Add</vs-button>
          </vs-row>
        </div> -->
      </vs-card>
    </vs-col>
  </vs-row>
</template>
<script>
export default {
  props:['comment'],
  data(){
    return{
    popupActivo:false,
    }
  },

}
</script>