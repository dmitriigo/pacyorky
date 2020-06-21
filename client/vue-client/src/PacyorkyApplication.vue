<template>
    <b-container fluid="true">
        <NavBar @openModalWindow="openModalWindow"/>
        <b-container id="head" fluid="true">
            <PacyorkyHeader/>
        </b-container>
        <b-container>
            <b-row align-content="center" align-h="center" align-v="center">
                <b-col class="main-content" align-self="center">
                    <b-row class="present-pic">
                        <b-col cols="2"><img src="/img/birdy2.png"></b-col>
                        <b-col align-self="center"><img src="/img/birdy0.png"></b-col>
                        <b-col cols="2"><img src="/img/birdy1.png"></b-col>
                    </b-row>
                    <b-row class="separator">
                        <b-col cols="3"><img src="/img/birdy10.png"></b-col>
                        <b-col class="separator-text" align-self="center"><h2>{{$ml.get('separator1')}}</h2></b-col>
                        <b-col cols="3"><img src="/img/birdy11.png"></b-col>
                    </b-row>
                    <Slider class="d-none d-xl-flex" :events="events"/>
                    <b-row class="app-data" ref="calendar">
                        <PacyorkyAppData @getSliderEvents="getSliderEvents"/>
                    </b-row>
                    <b-row class="separator" id="game">
                        <b-col cols="3"><img src="/img/birdy10.png"></b-col>
                        <b-col class="separator-text" align-self="center"><h2>{{$ml.get('game')}}
                            {{$ml.get('pacyorky')}}</h2></b-col>
                        <b-col cols="3"><img src="/img/birdy11.png"></b-col>
                    </b-row>
                    <b-row>
                        <!--<Game/>-->
                    </b-row>
                    <b-row class="separator" id="project">
                        <b-col cols="3"><img src="/img/birdy10.png"></b-col>
                        <b-col class="separator-text" align-self="center"><h2>{{$ml.get('aboutproject')}}</h2></b-col>
                        <b-col cols="3"><img src="/img/birdy11.png"></b-col>
                    </b-row>
                    <b-row>
                        <About/>
                    </b-row>
                </b-col>
            </b-row>
            <b-row class="copyright mt-3 d-flex" style="min-height: 100px" align-h="end" align-v="end">
                <b-col class="w-100 h-100 mh-100 d-flex justify-content-end align-content-end flex-column"><p class="h-100 m-0" style="font-size: 12px">{{$ml.get('copyright')}}</p></b-col>
                <b-col class="w-100 mh-100 d-flex justify-content-center align-content-center h-100"><h2>PACYORKY.EE</h2></b-col>
                <b-col class="w-100 d-flex align-bottom justify-content-end"><a class="p-0" href="/privacy_policy">Privacy policy</a></b-col>
            </b-row>

        </b-container>
        <ModalMailRegister @closeModalWindow="closeModalWindow" :mail-modal="mailModal"/>
    </b-container>
</template>

<script>
    import ModalMailRegister from "./components/ModalMailRegister";
    import Game from "./components/Game";
    import NavBar from "./components/NavBar";
    import PacyorkyHeader from "./components/PacyorkyHeader";
    import PacyorkyAppData from "./components/PacyorkyAppData";
    import Slider from "./components/Slider";
    import About from "./components/About";

    export default {
        name: "PacyorkyApplication",
        components: {ModalMailRegister, Game, NavBar, PacyorkyHeader, PacyorkyAppData, Slider, About},
        data() {
            return {
                events: [],
                mailModal: false
            }
        },
        mounted() {
            //please don't touch, some unknown bug...
            this.$ml.change('Russian');
            this.$ml.change('Ukrainian');
        },
        methods: {
            getSliderEvents(events) {
                this.events = events;
            },
            openModalWindow() {
                this.mailModal = true;
            },
            closeModalWindow() {
                this.mailModal = false;
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
        background-color: #E6EFF6;
    }

    .all-content {
        width: 100%;
    }

    .copyright {
        border: @primarycolor solid;
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

            h2 {
                font-family: Amatic SC;
                font-style: normal;
                font-weight: bold;
                font-size: 18px;
                line-height: 14px;
                @media (min-width: 768px) {
                    font-family: Amatic SC;
                    font-style: normal;
                    font-weight: bold;
                    font-size: 40px;
                    line-height: 14px;
                }
                @media (min-width: 1024px) {
                    font-family: Amatic SC;
                    font-style: normal;
                    font-weight: bold;
                    font-size: 60px;
                    line-height: 14px;
                }
            }
        }
    }

    h2 {
        font-family: Amatic SC;
        font-style: normal;
        font-weight: bold;
        font-size: 18px;
        line-height: 14px;
        @media (min-width: 768px) {
            font-family: Amatic SC;
            font-style: normal;
            font-weight: bold;
            font-size: 40px;
            line-height: 14px;
        }
        @media (min-width: 1024px) {
            font-family: Amatic SC;
            font-style: normal;
            font-weight: bold;
            font-size: 60px;
            line-height: 60px;
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

    .invert-btn {
        .btn {
            margin: 0 20px;
            border-radius: 20px !important;
            background-color: #BDD9DC;
            color: black;
            font-family: Amatic SC;
            font-style: normal !important;
            font-weight: bold !important;
            font-size: 24px !important;
            line-height: 14px !important;
            @media (max-width: 768px) {
                font-family: Amatic SC;
                font-style: normal !important;
                font-weight: bold !important;
                font-size: 18px !important;
                line-height: 14px !important;
            }

            &:hover {
                color: white;
                background-color: black;
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
