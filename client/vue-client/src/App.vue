<template>
<div>
    <b-row><PacyorkyHeader/></b-row>
    <b-container>

<b-row align-content="center" align-h="center" align-v="center">
    <b-col class="main-content" cols="11" align-self="center">
        <b-row><NavBar/></b-row>
        <b-row><h1>Some Picture</h1></b-row>
<Slider :events="events"/>
        <b-row><PacyorkyAppData @getSliderEvents="getSliderEvents"/></b-row>
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

    .main-content {
        background: #E1FCF8;
        border: 20px solid #E1FCF8;
        border-radius: 26px;
    }

    .logo {
        background-color: #4f4f4f;
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
