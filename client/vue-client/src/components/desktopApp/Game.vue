
<template>
  <b-col class="game mt-0 mb-0 ml-3 mr-3">
    <b-row class="mb-3" style="background-color: white">
      <b-col>
        <b-row class="m-3">
          <b-col style="background-color: #BDD9DC">
            <b-row class="mt-3 mr-3 ml-3"><p style="text-align: justify" v-html="$ml.get('aboutgame')">
            </p></b-row>
            <b-row align-content="center" align-h="center" align-v="center"><img style="width: 70%; height: auto" src="/img/family.png"></b-row>
          </b-col>
          <b-col class="ml-3">
            <b-row style="background-color: #EBE1E4; padding: 10px; height: 70%"><iframe width="100%" src="https://www.youtube.com/embed/MRDI8jB7sq0" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></b-row>
            <b-row class="mt-3 p-3" style="background-color: #EBE1E4">
              <b-col class="d-flex flex-column justify-content-center align-items-center">
                <a href="/rules.pdf" target="_blank" style="color: black" download>
                <font-awesome-icon  :icon="['fas', 'file-pdf']" size="4x" />
                </a>
                <h4 class="game-block-download">{{$ml.get('rules')}}</h4>
              </b-col>
              <b-col class="d-flex flex-column justify-content-center align-items-center">
                <a href="/holidays.pdf" target="_blank" style="color: black" download>
                <font-awesome-icon  :icon="['fas', 'file-pdf']" size="4x" />
                </a>
                <h4 class="game-block-download">{{$ml.get('holidays')}}</h4>
              </b-col>
            </b-row>
          </b-col>
        </b-row>
        <b-row align-v="center" align-h="center">
          <h4 class="game-block-text">{{$ml.get('gallery')}}</h4></b-row>
        <b-row>
          <silent-box id="game-box" :gallery="gamePics"></silent-box>
        </b-row>
      </b-col>
    </b-row>
    <b-row class="" style="background-color: white">
      <b-col cols="4" class="m-3">
        <b-row align-v="center" align-h="center"><img style="width: 70%; height: auto" src="/img/birdy4.png"></b-row>
        <b-row style="background-color: #EBE1E4" class="p-3" align-h="center" align-v="center">
          <img style="width: 70%; height: auto" src="/img/kolyada.png">
          <p style="text-align: center" v-html="$ml.get('questions')"></p>
        </b-row>
      </b-col>
      <b-col class="justify-content-between align-items-center">
        <b-row align-h="center" align-v="center" style="height: 50%; background-color: #EBE1E4" class="m-3 p-2">
          <h4>{{$ml.get('howto')}}</h4>
        </b-row>
        <b-row style="background-color: #BDD9DC" class="m-3 p-3">
          <b-col cols="6"><p style="text-align: justify">{{$ml.get('gameinfo')}}</p></b-col>
          <b-col class="d-flex justify-content-center align-items-center">
            <img style="width: 60%" src="/img/emblema.png" /></b-col>
          <b-col class="d-flex justify-content-center align-items-center flex-column">
            <img style="width: 70%" src="/img/is_logo.png" alt="">
            <img style="width: 100%" src="/img/ee.png" alt="">
          </b-col>
        </b-row>
      </b-col>
    </b-row>
  </b-col>
</template>

<script>
export default {
  name: "Game",
  data() {
    return {
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
  }
}
</script>

<style lang="less">
#game-box {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  .silentbox-item {
      width: 30%;
    margin: 10px;
    img {
      width: 100%;
    }
  }
}
.game {
}
.game-block-download {
  margin: 0;
  font-family: Amatic SC;
  font-style: normal;
  font-weight: bold;
  font-size: 25px;
  line-height: 37px;
  text-align: justify;
}
.game-block-text {
  font-family: Amatic SC;
  font-style: normal;
  font-weight: bold;
  font-size: 35px;
  line-height: 44px;
  text-align: justify;

}
</style>
