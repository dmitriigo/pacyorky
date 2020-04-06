<template>
<div>

    <b-row><PacyorkyHeader/></b-row>
    <b-container>

<b-row align-content="center" align-h="center" align-v="center">
    <b-col class="main-content" align-self="center">
        <b-row><NavBar/></b-row>
        <b-row class="present-pic"><h1>Some Picture</h1></b-row>
        <b-row class="separator"><h1>Calendar for you!!!</h1></b-row>
<Slider :events="events"/>
        <b-row class="app-data"><PacyorkyAppData @getSliderEvents="getSliderEvents"/></b-row>
        <b-row><About/></b-row>
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
        methods: {
            getSliderEvents(events) {
                this.events=events;
            }
        }
    }
</script>

<style lang="less">

    @primarycolor: #F6F0E4;


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
        justify-content: center;
        align-items: center;
        background-color: #FF7373;
        border-radius: 5px;
        margin: 20px 0;
    }

    .present-pic {
        width: 100%;
        height: 300px;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: white;
        margin: 20px 0;
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
