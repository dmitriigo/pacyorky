<template>
    <div class="slider">
        <h2>{{$ml.get('fevents')}}</h2>
        <carousel
                :autoplay="aplay"
                :autoplay-timeout="2000"
                :per-page="3"
                :scroll-per-page="false"
                :loop="true"
        >
            <slide v-for="event in events" :key="event.id">
                <div class="one-slide">
               <img src="/img/birdy4.png"/>
                <h4>{{event.title}}</h4>
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
    .one-slide {
        padding: 10px;
        margin: 10px;
        background-color: #42b983;
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
        }
        h2 {
            font-family: Amatic SC;
            font-style: normal;
            font-weight: bold;
            font-size: 48px;
            line-height: 14px;
            color: #000000;
            margin: 30px;
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
            margin: 0 !important;
            height: 30px;
        }
    }

    .VueCarousel-dot {
        background-color: #D64D55 !important;
        margin: 0 !important;
    }
    .VueCarousel-dot--active {
        background-color: #94C8D6 !important;
        width: 15px !important;
        height: 15px !important;
        margin: 0 !important;
    }



    .label {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>
