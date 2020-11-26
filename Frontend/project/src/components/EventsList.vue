<template>
  <div class="events container">
    <h2 class="subtitle is-3">Check out our upcoming events</h2>
    <div class="columns is-multiline">
      <div
        v-for="event in events"
        :event="event"
        :key="event.eventName"
        class="column is-one-quarter">
        <router-link :to="`/event/${event.eventName}`">
          <EventCard :event="event" />
        </router-link>
      </div>
    </div>
  </div>
</template>
<script>
import EventCard from "@/components/EventCard";
export default {
  name: "EventsList",
  components: {
    EventCard,
  },
  data() {
    return {
      event: {},
      events:[]
    };
  },
    mounted: function () {
    console.log("start!")
    this.$axios
      .get("/api/query/AllEvent")
      .then((response) => {
        if (response.data.status == "success") {
          this.events = response.data.data;
          console.log(this.events);
        } else window.alert("Failed");
      });
  }, 
};
</script>
<style lang="scss" scoped>
.events {
  margin-top: 100px;
  text-align: center;
}
</style>
