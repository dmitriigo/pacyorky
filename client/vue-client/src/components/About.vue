<template>
    <b-col class="m-3">
        <b-row class="about-us pt-3">
            <b-col class="col-sm-12 mb-sm-3 mb-xl-0 col-xl-4">
                <div class="about-pane">
                    <h3>{{$ml.get('vodograi')}}</h3>
                    <p v-html="$ml.get('aboutpt1')"></p>
                    <div class="about-pane-img">
                    <img src="/img/emblema.png" />
                    </div>
                </div>
            </b-col>
            <b-col class="col-sm-12 mb-sm-3 mb-xl-0 col-xl-4">
                <div class="about-pane">
                    <h3>{{$ml.get('pacyorky')}}</h3>
                    <p v-html="$ml.get('aboutpt2')"></p>
                    <div class="about-pane-img">
                        <img style="height: auto; width: 33%" src="/favicon.png" />
                    </div>
                </div>
            </b-col>
            <b-col class="col-sm-12 col-xl-4">
                <div class="about-pane">
                    <h3>{{$ml.get('connectus')}}</h3>
                    <p v-html="$ml.get('aboutpt3')"></p>
                    <div class="about-pane-img">
                        <img src="/img/laptop-512.png" />
                    </div>
                </div>
            </b-col>
        </b-row>
        <b-row align-h="center" align-v="center" class="about-us pb-3"><img style="width: 70%" src="/img/Illustration12.png">
        </b-row>
        <b-row class="p-3 pt-5">
            <b-col align="center"><img style="width: 40%" src="/img/is_logo.png" alt=""></b-col>
            <b-col>
                <h3 style="font-family: Amatic SC; text-align: center; font-weight: bold;">{{$ml.get('supportedby')}}</h3>
                <p style="text-align: center">{{$ml.get('supporteddesc')}}</p>
            </b-col>
            <b-col align="center"><img style="width: 70%" src="/img/ee.png" alt=""></b-col>
        </b-row>
    </b-col>

</template>

<script>
    export default {
        name: "About.vue"
    }
</script>

<style lang="less">
    .about-us {
        background-color: white;
    }

    .about-pane {
        background-color: rgba(189, 217, 220, 0.47);
        border-radius: 5px;
        padding: 10px;
        display: flex;
        flex-direction: column;
        justify-content: normal;
        align-items: center;
&-img {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: flex-end;
    height: 100%;
    img {
        width: 25%;
    }
}
        height: 100%;
        h3 {
            text-align: center;
            font-family: Amatic SC;
            color: black;
            font-style: normal;
            font-weight: bold;
            font-size: 48px;
        }
        p {
            text-align: justify;
            padding: 1rem;
        }
    }
</style>
