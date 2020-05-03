<template>
    <b-col>
        <b-row class="m-0 mb-2 mt-2 w-100">
            <b-col align="center" align-self="center" class="p-2 ml-1 mr-1 map-btn invert-btn">
            <b-dropdown size="lg" class="w-75 p-3" :text="$ml.get(districtNowAtButton)">
                <b-dropdown-item v-for="district in districts" :key="district.id" @click="trimToDistrict(district)">
                    {{$ml.get(district)}}
                </b-dropdown-item>
            </b-dropdown>
            </b-col>
            <b-col cols="8" align="center" class="d-flex justify-content-around p-0 pl-2 w-100">
                <div class="map-btn w-100 m-0 p-3 d-flex justify-content-around invert-btn">
            <b-button @click="pastEvents"> {{$ml.get('pevents')}}</b-button>
                <b-button @click="trimToDate">{{$ml.get('allevents')}}</b-button>
            <b-button @click="futureEvents"> {{$ml.get('fevents')}}</b-button>
                </div>
            </b-col>
        </b-row>
        <vl-map class="m-1 pr-1" :load-tiles-while-animating="true" :load-tiles-while-interacting="true"
                data-projection="EPSG:4326" style="height: 400px">
            <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>
                    <vl-feature id="position-feature">
                        <vl-geom-multi-point :coordinates="cords"></vl-geom-multi-point>
                         <vl-style-box>
                            <vl-style-circle :radius="12">
                                <vl-style-fill color="#D64D55"></vl-style-fill>
                                <vl-style-stroke color="black"></vl-style-stroke>
                            </vl-style-circle>
                        </vl-style-box>
                    </vl-feature>
            <vl-layer-tile id="osm">
                <vl-source-osm></vl-source-osm>
            </vl-layer-tile>
        </vl-map>
        <b-row class=" m-1 w-100 mapa-desc">
            <b-col cols="4" align="center" align-self="center" class="invert-btn" ><b-button href="https://mapauaest.wordpress.com/" target="_blank">{{$ml.get('mapa')}}</b-button></b-col>
            <b-col cols="8" class="p-3"><h4>{{$ml.get('descformapa')}}</h4></b-col>
        </b-row>
        <b-row align-v="center" align-h="center"><b-img class="w-50" src="/img/birdy0.png"></b-img></b-row>
    </b-col>
</template>

<script>
    export default {
        props: {
          events: {},
            districts: {},
districtShow: {}

        },
        name: "Map.vue",
        data() {
            return {
                center: [25.70090718284081, 58.640417759404755],
                mapCords: [0,0],
                rotation: 0,
                zoom: 7,
                districtNow: undefined
            }
        },
        methods: {
            futureEvents () {
               this.$emit("futureEvents")
            },
            pastEvents () {
                this.$emit("pastEvents")
            },
            trimToDate () {
              this.$emit("trimToDate", null)
            },
            trimToDistrict (district) {
                this.districtNow = district;
                this.$emit("trimToDistrict", district);
            },
            centerMap () {
                if (this.mapCords[0]===0&&this.mapCords[1]===0) {
                    this.center= [25.70090718284081, 58.640417759404755];
                }
                else {
                    let x = 0;
                    let y = 0;
                    let cordx = this.mapCords.map(cord => cord[0]);
                    cordx.forEach(cord => x += cord);
                    x = x / cordx.length;
                    let cordy = this.mapCords.map(cord => cord[1]);
                    cordy.forEach(cord => y += cord);
                    y = y / cordy.length;
                    this.center = [x, y];
                }
            },
            zoomMap() {
                if (this.mapCords===[0,0]) this.zoom= 7;
               else if (this.mapCords.length===1) this.zoom= 12;
                else this.zoom=7;

            }
        },
        computed: {
            cords() {
                let mapCords = this.events.map(event => event.locationPoint).filter(cord => cord[0]!==0&&cord[1]!==0);
                if (mapCords.length ===0) {
                    this.mapCords=[0,0];
                    this.centerMap();
                    this.zoomMap();
                    return [0,0];
                }
                this.mapCords=mapCords;
                this.centerMap();
                this.zoomMap();
                return mapCords;
            },
            districtNowAtButton () {
                if (!this.districtShow) return "city";
                else return this.districtNow;
            }

        }
    }
</script>

<style lang="less">
    @primarycolor: #EBE1E4;

    .mapa-desc {
        background-color: @primarycolor;
    }
.map-btn {
    background-color: @primarycolor;


    .btn {
        margin: 0 20px;
        border-radius: 20px !important;
        background-color:#BDD9DC ;
        color: black;
        font-style: normal;
        font-size: 16px;
        line-height: 14px;

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
            &:hover {
                color: white !important;
                background-color: black !important;
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
