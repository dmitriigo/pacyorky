<template>
    <div>
        {{cords}}
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
        props: {
          events: {}
        },
        name: "Map.vue",
        data() {
            return {
                zoom: 7,
                center: [25.70090718284081, 58.640417759404755],
                rotation: 0,
                geolocPosition: undefined,
            }
        },
        computed: {
            cords() {
                return this.events.map(event => event.locationPoint)
            }

        }
    }
</script>

<style scoped>

</style>