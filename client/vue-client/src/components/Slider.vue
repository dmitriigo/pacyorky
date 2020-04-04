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

<style scoped lang="less">
    .slider {
        color: green;
        border: #42b983 solid;
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

    .label {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
    }
</style>