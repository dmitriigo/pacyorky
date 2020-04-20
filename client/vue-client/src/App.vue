<template>
    <div class="all-content">

        <b-row>
            <PacyorkyHeader/>
        </b-row>
        <b-container>

            <b-row align-content="center" align-h="center" align-v="center">
                <b-col class="main-content" align-self="center">
                    <b-row>
                        <NavBar/>
                    </b-row>
                    <b-row class="present-pic">
                        <b-col cols="2"><img src="/img/birdy2.png"></b-col>
                        <b-col align-self="center" ><img src="/img/birdy0.png"></b-col>
                        <b-col cols="2"><img src="/img/birdy1.png"></b-col>
                    </b-row>
                    <b-row class="separator">
                        <b-col cols="3"><img src="/img/birdy10.png"></b-col>
                        <b-col class="separator-text" align-self="center"> <h1>{{$ml.get('separator1')}}</h1></b-col>
                        <b-col cols="3"><img src="/img/birdy11.png"></b-col>


                    </b-row>
                    <Slider :events="events"/>
                    <b-row class="app-data" ref="calendar">
                        <PacyorkyAppData @getSliderEvents="getSliderEvents"/>
                    </b-row>
                    <b-row>
                        <About/>
                    </b-row>
                </b-col>
            </b-row>


        </b-container>

    </div>
</template>

<script>

    import PacyorkyAppData from './components/PacyorkyAppData'
    import PacyorkyHeader from './components/PacyorkyHeader'
    import NavBar from './components/NavBar'
    import Slider from "./components/Slider";
    import About from './components/About';

    export default {
        name: 'App',
        components: {NavBar, PacyorkyHeader, PacyorkyAppData, Slider, About},
        data() {
            return {
                events: []
            }
        },
        mounted() {
            //please don't touch, some unknown bug...
            this.$ml.change('Russian');
            this.$ml.change('English');
        },
        methods: {
            getSliderEvents(events) {
                this.events = events;
            }
        }
    }
</script>

<style lang="less">
@import "styles/fonts.css";
    @primarycolor: #EBE1E4;
html {
    width: 100%;
}
body {
    width: 100%;
}

.all-content {
    width: 100%;
}


    .app-data {
        background-color: white;
        margin: 20px 0;
        border-radius: 5px;
    }

    .main-content {
        background: @primarycolor;
        border: 20px solid @primarycolor;
        border-radius: 26px;
    }

    .logo {
        background-color: #4f4f4f;
    }

    .separator {
        display: flex;
        justify-content: space-between;
        align-items: center;
        background-color: #BDD9DC;
        margin: 20px 0;
        padding: 10px;

        div {
            display: flex;
            align-items: center;
            justify-content: space-evenly;
            margin: 0px 0px;
            padding: 0;
            img {
                width: 70%;
                height: auto;
            }
            h1 {
                font-family: Amatic SC;
                font-style: normal;
                font-weight: bold;
                font-size: 48px;
                line-height: 14px;
            }
        }
    }

    .present-pic {
        margin: 0;
        padding: 10px;
        width: 100%;
        background-color: white;
        div {
            img {
                width: 100%;
                height: auto;
            }
        }

    }

    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }
</style>
