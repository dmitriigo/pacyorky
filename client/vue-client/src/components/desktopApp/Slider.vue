<template>
    <div class="slider">
        <h2 class="m-0 p-5">{{$ml.get('fevents')}}</h2>
        <carousel
                :autoplay="aplay"
                :autoplay-timeout="2000"
                :per-page="3"
                :scroll-per-page="true"
                :loop="true"
        >
            <slide v-for="event in sliderEvents" :key="event.id">
                <div class="one-slide">
               <img :src="event.cover"/>
                    <div class="slider-event-link d-flex justify-content-center align-content-center align-items-center">
                    <a style="color: black; font-size: 16px; font-weight: normal" :href="event.link" target="_blank">
                      {{event.title.length < 64 ? event.title : event.title.substr(0,64)+'...'}}</a>
                </div>
                </div>
            </slide>
        </carousel>
        <img src="/img/birdy0.png"/>
    </div>
</template>

<script>


    export default {
        name: "Slider.vue",
        props: {
            events: {}
        },
        computed: {
            sliderEvents () {
              let forShow = [];
              for (let event of this.events) {
                if (!event.cover){
                  event.cover = '../img/Illustration3.png';
                }
                forShow.push(event);
              }
              return forShow;
            },
        },
        data ()  {
            return {
              aplay: false
            }
        },
        mounted() {
            //slider css fix
            setTimeout(() => {
                this.aplay = true;
            }, 1000);
        }
    }
</script>

<style lang="less">
    @primarycolor: #EBE1E4;
    .VueCarousel-inner {
        max-height: 300px;
    }
    .one-slide {
        margin: 30px;
        background-color: #BDD9DC;
        border: 1px solid #D3D3D3;
        height: 90%;
    }
    .slider-event-link {
        height: 20%;
    }
    .slider {
        background-color: white;
        color: green;
        border: @primarycolor solid;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        img {
            width: 50%;
            height: 80%;
        }
        h2 {
            font-family: Amatic SC;
            font-style: normal;
            color: black;
            font-weight: bold;
            font-size: 54px;
            line-height: 14px;
        }
        &-button {
            height: 30%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

    }
    .VueCarousel {
        width: 100%;
    }
    .VueCarousel-slide {
        position: relative;
        background: white;
        color: #fff;
        font-family: Arial;
        font-size: 24px;
        text-align: center;
        min-height: 100px;
        img {
            width: 100%;
        }
    }

    .VueCarousel-pagination {
height: 30px;
        margin-bottom: 10px;
        .VueCarousel-dot-container {
            margin: 5px !important;
            height: 30px;
            align-items: center !important;
            align-content: center;
            align-self: center;
        }
    }

    .VueCarousel-dot {
        background-color: #D64D55 !important;
        margin: 5px !important;
        outline: 0 !important;
        border: 2px solid black !important;
        padding: 0 !important;
        width: 15px !important;
        height: 15px !important;
    }
    .VueCarousel-dot--active {
        background-color: #94C8D6 !important;
        width: 20px !important;
        height: 20px !important;
        margin: 5px !important;
    }



    .label {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
