<template>
     <b-navbar fixed="top" class="buttons">
        <b-button href="#head" variant="outline-dark">Main</b-button>
        <b-button href="#calendar" variant="outline-dark">Calendar</b-button>
        <b-button href="#game" variant="outline-dark">Game</b-button>
        <b-button href="#project" variant="outline-dark">Project</b-button>
        <b-dropdown variant="outline-dark" :text="langNow">
            <b-dropdown-item v-for="lang in $ml.list" :key="lang" @click="$ml.change(lang), changeLang(lang)">
                {{lang}}
            </b-dropdown-item>
        </b-dropdown>
     </b-navbar>
</template>

<script>
    export default {
        name: "NavBar.vue",
        data () {
            return {
                langNow: "English"
        }
        },
        methods: {
            changeLang (lang) {
                this.langNow=lang;
            }
        }
    }
</script>

<style lang="less">

    @primarycolor: #EBE1E4;
    .buttons {
        width: 100%;
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        background-color: @primarycolor;
        opacity: 0.8;
        .btn {
            border-radius: 20px !important;
            background-color:#BDD9DC ;
            color: black;
        }
    }




</style>