<template>
     <b-navbar toggleable="md" fixed="top" class="buttons invert-btn">
         <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
         <b-collapse id="nav-collapse" is-nav>
             <b-navbar-nav align="center" class="w-100">
        <b-button href="#" v-scroll-to="{
            el: '#head'
        }" variant="outline-dark">{{$ml.get('main')}}</b-button>
        <b-button href="#" v-scroll-to="{
            el:'#calendar',
            offset: -30
        }" variant="outline-dark">{{$ml.get('calendar')}}</b-button>
        <b-button href="#" v-scroll-to="{
            el: '#game',
            offset: -30
        }" variant="outline-dark">{{$ml.get('game')}}</b-button>
        <b-button href="#" v-scroll-to="{
            el: '#project',
            offset: -30
        }" variant="outline-dark">{{$ml.get('project')}}</b-button>
        <b-dropdown :text="$ml.current">
            <b-dropdown-item v-for="lang in $ml.list" v-if="lang!==$ml.current" :key="lang" @click="$ml.change(lang)">
               <img class="w-25 m-2" :src="'img/'+lang+'.svg'"/>{{lang}}
            </b-dropdown-item>
        </b-dropdown>
                 <div class="ml-4">
                     <a href="https://www.facebook.com/vodograi.estonia/" target="_blank" class="ml-2 mr-2" style="color: black">  <font-awesome-icon   :icon="['fab', 'facebook-square']" /> </a>
                   <!--рассылка-->
                     <b-link @click="openModalWindow" class="ml-2 mr-2" style="color: black">  <font-awesome-icon  :icon="['far', 'bell']" /></b-link>
                     <a href="mailto:info@pacyorky.ee"  class="ml-2 mr-2" style="color: black"> <font-awesome-icon  :icon="['far', 'envelope']" /></a>
                     <a href="https://www.facebook.com/pacyorky.ee/" target="_blank" class="ml-2 mr-2" style="color: black"> <font-awesome-icon  :icon="['far', 'thumbs-up']" /></a>
                     <a href="https://www.facebook.com/sharer/sharer.php?kid_directed_site=0&u=https%3A%2F%2Fwww.facebook.com%2Fpacyorky.ee%2F&display=popup&ref=plugin&src=share_button" class="ml-2 mr-2" style="color: black"> <font-awesome-icon :icon="['fas', 'share-alt']" /></a>
                 </div>
             </b-navbar-nav>

         </b-collapse>

     </b-navbar>
</template>

<script>
    export default {
        name: "NavBar.vue",
        methods: {
            openModalWindow() {
                this.$emit("openModalWindow");
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
            @media (max-width: 1024px) {
                font-family: Amatic SC;
                font-style: normal !important;
                font-weight: bold !important;
                font-size: 24px !important;
                line-height: 14px !important;
            }
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
                font-style: normal !important;
                font-weight: bold !important;
                font-size: 24px !important;
                line-height: 14px !important;
                @media (max-width: 1024px) {
                    font-family: Amatic SC;
                    font-style: normal !important;
                    font-weight: bold !important;
                    font-size: 24px !important;
                    line-height: 14px !important;
                }
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
                        font-style: normal !important;
                        font-weight: bold !important;
                        font-size: 24px !important;
                        line-height: 14px !important;
                        @media (max-width: 1024px) {
                            font-family: Amatic SC;
                            font-style: normal !important;
                            font-weight: bold !important;
                            font-size: 24px !important;
                            line-height: 14px !important;
                        }
                        &:hover {
                            background: #8EBABF;
                            border: 1px solid #000000;
                            box-sizing: border-box;
                            border-radius: 20px;
                            font-family: Amatic SC;
                            font-style: normal !important;
                            font-weight: bold !important;
                            font-size: 24px !important;
                            line-height: 14px !important;
                            @media (max-width: 1024px) {
                                font-family: Amatic SC;
                                font-style: normal !important;
                                font-weight: bold !important;
                                font-size: 24px !important;
                                line-height: 14px !important;
                            }
                        }
                    }
                }
            }
        }
    }




</style>
