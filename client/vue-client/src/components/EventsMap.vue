<template>
    <div>
        <vl-map :load-tiles-while-animating="true" :load-tiles-while-interacting="true"
                data-projection="EPSG:4326" style="height: 400px">
            <vl-view :zoom.sync="zoom" :center.sync="center" :rotation.sync="rotation"></vl-view>

            <vl-geoloc @update:position="geolocPosition = $event">
                <template slot-scope="geoloc">
                    <vl-feature v-if="geoloc.position" id="position-feature">
                        <vl-geom-point :coordinates="geoloc.position"></vl-geom-point>
                        <vl-style-box>
                            <vl-style-circle :radius="20">
                                <vl-style-fill color="white"></vl-style-fill>
                                <vl-style-stroke color="red"></vl-style-stroke>
                            </vl-style-circle>
                            <vl-style-text text="Tartu" font="14px monospace"></vl-style-text>
                        </vl-style-box>
                    </vl-feature>
                </template>
            </vl-geoloc>

            <vl-layer-tile id="osm">
                <vl-source-osm></vl-source-osm>
            </vl-layer-tile>
        </vl-map>
        <div style="padding: 20px">
            Zoom: {{ zoom }}<br>
            Center: {{ center }}<br>
            Rotation: {{ rotation }}<br>
            My geolocation: {{ geolocPosition }}
        </div>

        <!-- --------------------------------------------------------------------------------------->
    </div>
</template>

<script>
    export default {
        name: "Map.vue",
        data() {
            return {
                zoom: 7,
                center: [25.70090718284081, 58.640417759404755],
                rotation: 0,
                geolocPosition: undefined,
            }
        },
    }
</script>

<style scoped>

</style>