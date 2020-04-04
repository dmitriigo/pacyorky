<template>
    <div class="p-map">
            <b-dropdown :text="$ml.get('city')">
                <b-dropdown-item v-for="district in districts" :key="district" @click="trimToDistrict(district)">
                    {{district}}
                </b-dropdown-item>
            </b-dropdown>
        <b-button @click="trimToDate">Show all</b-button>
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


        },
        name: "Map.vue",
        data() {
            return {
                center: [25.70090718284081, 58.640417759404755],
                mapCords: [0,0],
                rotation: 0,
                zoom: 7,
            }
        },
        methods: {
            trimToDate () {
              this.$emit("trimToDate", "")
            },
            trimToDistrict (district) {
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
            }

        }
    }
</script>

<style scoped>
.p-map {
    width: 100%;
}
</style>