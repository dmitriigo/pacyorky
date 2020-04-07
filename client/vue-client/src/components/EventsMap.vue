<template>
    <div class="p-map">
        <div class="buttons-on-map">
            <div class="buttons-on-map-district-selector">
            <b-dropdown :text="$ml.get(districtNowAtButton)">
                <b-dropdown-item v-for="district in districts" :key="district.id" @click="trimToDistrict(district)">
                    {{$ml.get(district)}}
                </b-dropdown-item>
            </b-dropdown>
            </div>
            <div class="buttons-on-map-events">
            <b-button @click="pastEvents"> {{$ml.get('pevents')}}</b-button>
                <b-button @click="trimToDate">{{$ml.get('allevents')}}</b-button>
            <b-button @click="futureEvents"> {{$ml.get('fevents')}}</b-button>

            </div>
        </div>
        <vl-map :load-tiles-while-animating="true" :load-tiles-while-interacting="true"
                data-projection="EPSG:4326" style="height: 400px">
            <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>
                    <vl-feature id="position-feature">
                        <vl-geom-multi-point :coordinates="cords"></vl-geom-multi-point>
                         <vl-style-box>
                            <vl-style-circle :radius="12">
                                <vl-style-fill color="#36D9C3"></vl-style-fill>
                                <vl-style-stroke color="black"></vl-style-stroke>
                            </vl-style-circle>
                        </vl-style-box>
                    </vl-feature>
            <vl-layer-tile id="osm">
                <vl-source-osm></vl-source-osm>
            </vl-layer-tile>
        </vl-map>
    </div>
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
              this.$emit("trimToDate", "")
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
.p-map {
    width: 100%;
    margin: 20px;
.btn {
    border-radius: 20px;
    background-color: #BDD9DC;
    color: black;
}

    .buttons-on-map {
        display: flex;
        align-items: center;
        justify-content: space-between;
        &-district-selector{
            width: 30%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: @primarycolor;
            padding: 10px;
        }
        &-events {
          display: flex;
            justify-content: space-evenly;
            align-items: center;
            width: 70%;
background-color: @primarycolor;
            margin: 10px -5px 10px 10px;
            padding: 10px;
         }
    } }

</style>