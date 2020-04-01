<template>

  <b-container>
      <b-col>
      <b-row align-content="center">
          <b-col class="logo">logo</b-col>
          <b-col class="menu">menu+description</b-col>
      </b-row>
      <b-row align-content="center">slider</b-row>
      <b-row align-content="center">
          <b-col class="calendar"><Calendar/></b-col>
          <b-col class="map">Map</b-col>
      </b-row>
      <b-row align-content="center">Events List</b-row>
      <b-row align-content="center">
          footer
<!--              <b-row> <span>1row</span> <Calendar/></b-row>-->
      </b-row>
      </b-col>
  </b-container>


</template>

<script>
import axios from "axios";
import Calendar from "./components/Calendar"

export default {
  name: 'App',
  data() {
    return {
    }
  },
  mounted() {

  },
  components: {
    Calendar,
    axios
  },
  methods: {

  }
}
</script>

<style lang="less">
    .logo {
        background-color: #4f4f4f;
    }

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
