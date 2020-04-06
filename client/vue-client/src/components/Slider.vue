<template>
    <div class="slider">
        <carousel
                :autoplay="true"
                :autoplay-timeout="2000"
                :per-page="3"
                :scroll-per-page="false"
                :loop="true"
        >
            <slide v-for="event in events" :key="event.id">
                {{event.title}}
            </slide>
        </carousel>
    </div>
</template>

<script>


    export default {
        name: "Slider.vue",
        props: {
            events: {}
        },
        computed: {
        }
    }
</script>

<style lang="less">
    @primarycolor: #F6F0E4;
    .slider {
        background-color: white;
        color: green;
        border: @primarycolor solid;
        border-radius: 10px;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    .VueCarousel-slide {
        position: relative;
        background: #42b983;
        color: #fff;
        font-family: Arial;
        font-size: 24px;
        text-align: center;
        min-height: 100px;
    }

    .VueCarousel-dot {
        background-color: @primarycolor !important;
    }
    .VueCarousel-dot--active {
        background-color: red !important;
    }



    .label {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>