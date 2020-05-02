<template>
     <b-navbar fixed="top" class="buttons invert-btn">
        <b-button href="#" v-scroll-to="'#head'" variant="outline-dark">{{$ml.get('main')}}</b-button>
        <b-button href="#" v-scroll-to="'#calendar'" variant="outline-dark">{{$ml.get('calendar')}}</b-button>
        <b-button href="#" v-scroll-to="'#game'" variant="outline-dark">{{$ml.get('game')}}</b-button>
        <b-button href="#" v-scroll-to="'#project'" variant="outline-dark">{{$ml.get('project')}}</b-button>
        <b-dropdown :text="langNow">
            <b-dropdown-item v-for="lang in $ml.list" v-if="lang!==langNow" :key="lang" @click="$ml.change(lang), changeLang(lang)">
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
        justify-content: center;
        align-items: center;
        background-color: @primarycolor;
        opacity: 1;
        .btn {
            margin: 0 20px;
            border-radius: 20px !important;
            background-color:#BDD9DC ;
            color: black;
            font-family: Amatic SC;
            font-style: normal !important;
            font-weight: bold !important;
            font-size: 24px !important;
            line-height: 14px !important;
            &:hover {
                color: white !important;
                background-color: black !important;
            }
        }
        .show {
            .btn {
                background-color: #8EBABF;
                color: black;
                font-family: Amatic SC;
                font-style: normal;
                font-weight: bold;
                font-size: 24px;
                line-height: 14px;
            }
            .show {
                background-color: #BDD9DC;
                border: 1px solid #000000;
                box-sizing: border-box;
                border-radius: 20px;
                padding: 5px;
                li {
                    a {
                        font-family: Amatic SC;
                        font-style: normal;
                        font-weight: bold;
                        font-size: 24px;
                        line-height: 14px;
                        margin: 5px;
                        &:hover {
                            background: #8EBABF;
                            border: 1px solid #000000;
                            box-sizing: border-box;
                            border-radius: 20px;
                            font-family: Amatic SC;
                            font-style: normal;
                            font-weight: bold;
                            font-size: 24px;
                            line-height: 14px;
                        }
                    }
                }
            }
        }
    }




</style>
