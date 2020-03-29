<template>

  <b-container>
      <b-row>
          <b-col cols="10">
              <b-row> <span>1row</span> <Calendar/></b-row>

          </b-col>
          <b-col cols="2"></b-col>
      </b-row>
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
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
