<template>
    <b-col class="w-100">
        <b-row class="w-100">
            <b-col><b-dropdown text="Menu">

                    <div>
                    <router-link to="/">{{$ml.get('main')}}</router-link>
                    </div>
                <div>
                    <router-link to="/calendar">{{$ml.get('calendar')}}</router-link>
                </div>
                    <div>
                    <router-link to="/map">Map</router-link>
                    </div>
                        <div>
                    <router-link to="/game">{{$ml.get('game')}}</router-link>
                        </div>
                            <div>
                    <router-link to="/project">{{$ml.get('project')}}</router-link>
                            </div>

            </b-dropdown></b-col>
            <b-col>picture</b-col>
            <b-col><b-dropdown :text="$ml.current.substr(0,3)">
                <b-dropdown-item  v-for="lang in $ml.list" v-if="lang!==$ml.current" :key="lang" @click="$ml.change(lang)">
                    <img class="w-25 m-2" :src="'img/'+lang+'.svg'"/>
                </b-dropdown-item>
            </b-dropdown></b-col>
        </b-row>
    </b-col>
</template>

<script>
    export default {
        name: "MobileHeader"
    }
</script>

<style lang="less" scoped>

</style>