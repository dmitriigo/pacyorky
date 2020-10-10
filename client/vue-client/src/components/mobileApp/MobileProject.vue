<template>
  <b-col style="background-color: white">
    <b-row>
      <div class="w-100" style="text-align: center">
        <h1>{{ $ml.get('aboutproject') }}</h1>
      </div>
    </b-row>
    <b-row>
      <b-col class="d-flex justify-content-center align-items-center flex-column p-3 m-3" style="background: #BDD9DC;">
        <b-row class="m-2"><h1>{{ $ml.get('pacyorky') }}</h1></b-row>
        <b-row class="p-3" style="background: white"><p v-html="$ml.get('aboutpt2').substr(0, 225)"></p></b-row>
      </b-col>
    </b-row>
    <b-row><b-col class="d-flex justify-content-center align-items-center flex-column p-3 m-3" style="background: #BDD9DC;">
      <b-row class="m-2"><h1>{{$ml.get('connectus')}}</h1></b-row>
      <b-row class="p-3" style="background: white"><p v-html="$ml.get('aboutpt3')"></p></b-row>
    </b-col></b-row>
    <b-row><b-col class="d-flex justify-content-center align-items-center flex-column p-3 m-3" style="background: #BDD9DC;">
      <b-row class="m-2"><h1>{{$ml.get('vodograi')}}</h1></b-row>
      <b-row class="p-3" style="background: white"><p v-html="$ml.get('aboutpt1')"></p></b-row>
    </b-col></b-row>
    <b-row>
      <b-col><p v-html="$ml.get('supporteddesc')"></p></b-col>
    </b-row>
    <b-row class="m-2">
      <b-col align="center"><img style="width: 60%" src="/img/is_logo.png" alt=""></b-col>
      <b-col align="center"><img style="width: 120%" src="/img/ee.png" alt=""></b-col>
    </b-row>
  </b-col>
</template>

<script>
export default {
  name: "MobileProject"
}
</script>

<style scoped>

</style>