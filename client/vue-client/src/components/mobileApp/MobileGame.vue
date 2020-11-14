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
          <b-row class="d-flex justify-content-center align-items-center">
            <a style="color: blue; text-decoration: underline" v-html="$ml.get('gallery')" @click="openModal" class="m-3"></a></b-row>
        </b-col>
      </b-row>
      <b-row align-v="center" align-h="center"><b-img class="w-75" src="/img/Illustration1.png"></b-img></b-row>
      <b-row class="m-3"><iframe width="100%" src="https://www.youtube.com/embed/MRDI8jB7sq0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></b-row>
      <b-modal v-model="showModal" id="event-modal" hide-footer size="lg" :title="$ml.get('gallery')" scrollable centered>
        <div class="modal-event-item">
          <div class="modal-event-item-text d-flex flex-column">
            <silent-box id="game-box" :gallery="gamePics"></silent-box>
          </div>
          <div class="modal-buttons">
            <b-button @click="closeModal">{{$ml.get('close')}}</b-button>
          </div>

        </div>
      </b-modal>
    </b-col>
</template>

<script>
    export default {
        name: "MobileGame",
      data() {
          return {
            showModal : false,
            gamePics : [
              {
                src: '/img/pgame1-min.jpg'
              }
              ,
              {
                src: '/img/pgame2-min.jpg',
              }
              ,
              {
                src: '/img/pgame3-min.jpg',
              }
              ,
              {
                src: '/img/pgame4-min.jpg',
              }
              ,
              {
                src: '/img/pgame5-min.jpg',
              }
              ,
              {
                src: '/img/pgame6-min.jpg',
              }
              ,
            ]
          }
      },
      methods: {
          closeModal() {
            this.showModal = false;
          },
        openModal() {
            this.showModal = true;
        }
      }
    }
</script>

<style scoped>

</style>