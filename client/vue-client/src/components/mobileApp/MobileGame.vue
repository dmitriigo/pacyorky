<template>
    <b-col style="background-color: white">
        <b-row>
            <div class="w-100" style="text-align: center">
                <h1>{{$ml.get('game')}}</h1>
            </div>
        </b-row>
      <b-row class="p-3 m-3" style="background: #BDD9DC;">
        <b-col>
          <b-row>
            <b-col style="background: white">
              <b-row class="m-3"><p v-html="$ml.get('aboutgame').substr(0, 250)"></p></b-row>
              <b-row></b-row>
            </b-col>
          </b-row>
          <b-row></b-row>
        </b-col>
      </b-row>
      <b-row align-v="center" align-h="center"><b-img class="w-75" src="/img/Illustration1.png"></b-img></b-row>
      <b-row class="m-3"><iframe width="100%" src="https://www.youtube.com/embed/MRDI8jB7sq0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></b-row>
    </b-col>
</template>

<script>
    export default {
        name: "MobileGame"
    }
</script>

<style scoped>

</style>