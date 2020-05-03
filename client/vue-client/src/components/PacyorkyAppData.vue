<template>
    <b-container id="calendar" class="ml-4">
        <b-row class="m-2 w-100" align-h="center"><h2 align="center">{{$ml.get("calendarandmap")}}</h2></b-row>
            <b-row class="calendar-table">
                <b-col class="p-0 mr-3 ml-1">
                    <Calendar align="center" class="w-100 ml-3" @trimToDate="trimToDate" v-bind:events="eventsForCalendar"/>
                </b-col>
                <b-col cols="8" sm="col">
                    <EventsTable class="p-3" @trimToLocation="trimToLocation" v-bind:events="eventsForList"/>
                </b-col>
            </b-row>
            <b-row class="calendar-data-all-map" v-if="!loading">
                <EventsMap @trimToDate="trimToDate" v-bind:events="eventsForMap" :districts="districts" :districtShow="districtShow" @trimToDistrict="trimToDistrict"
@futureEvents="futureEvents" @pastEvents="pastEvents"
/>
            </b-row>
            <b-row class="lds-dual-ring" v-else></b-row>
            <b-row class="error" v-if="apiError"><h1>{{$ml.get('errormsg')}}: info@pacyorky.ee</h1></b-row>
    </b-container>

</template>

<script>
    import EventsMap from "./EventsMap";
    import Calendar from "./Calendar";
    import axios from "axios";
    import EventsTable from "./EventsTable";

    export default {
        name: "PacyorkyAppData.vue",
        data() {
            return {
                eventsForMap: [],
                loading: true,
                events: [],
                apiError: false,
                eventsForList: [],
                eventsForCalendar: [],
                districts: {},
                districtShow: false
            }
        },
        mounted() {
            this.getEvents();
        },
        components: {
            EventsMap,
            Calendar,
            axios,
            EventsTable
        },
        methods: {
            futureEvents () {
                this.eventsForList = this.events.filter(event => new Date(event.date) >= Date.now());
                this.eventsForMap = this.events.filter(event => new Date(event.date) >= Date.now());
                this.eventsForCalendar = this.events.filter(event => new Date(event.date) >= Date.now());
                this.districtShow=false;
            },
            pastEvents () {
                this.eventsForList = this.events.filter(event => new Date(event.date) <= Date.now());
                this.eventsForMap = this.events.filter(event => new Date(event.date) <= Date.now());
                this.eventsForCalendar = this.events.filter(event => new Date(event.date) <= Date.now());
                this.districtShow=false;
            },
            getSliderEvents() {
                this.$emit("getSliderEvents", this.events);
            },
            trimToDistrict(district) {
                this.eventsForMap = this.events.filter(event => ('district'+event.district.id) === district);
                this.eventsForCalendar = this.events.filter(event => ('district'+event.district.id) === district);
                this.eventsForList = this.events.filter(event => ('district'+event.district.id) === district);
                this.districtShow = true;
            },
            trimToDate(date) {
                if (date === null) {
                    this.eventsForList = this.events;
                    this.eventsForMap = this.events;
                    this.eventsForCalendar = this.events;
                    this.districtShow=false;
                } else {
                    this.eventsForList = this.events.filter(event => new Date(event.date).toLocaleDateString() === date.toLocaleDateString());
                    this.eventsForMap = this.events.filter(event => new Date(event.date).toLocaleDateString() === date.toLocaleDateString());
                    this.districtShow=false;
                }
            },
            trimToLocation(id) {
                this.eventsForMap = this.events.filter(event => event.id === id);
            },
            getEvents() {
               axios.get('/api/events')
                    .then(response => {
                        this.events = response.data;
                        this.eventsForList = this.events;
                        this.eventsForCalendar = this.events;
                        this.eventsForMap = this.events;
                        this.districts = Array.from(new Set(this.events.map(event => event.district.id)));
                        this.districts= this.districts.map(district => "district"+district);
                        this.getSliderEvents();
                        this.loading = false;
                    }).catch(error => {
                    console.log(error);
                    this.apiError = true
                });

            }
        }
    }
</script>

<style lang="less" scoped>
.calendar-table {
    width: 100%;
}

    .calendar-data-all {
display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        padding: 10px;
        &-map{
    width: 100%;
         }
    }

    .lds-dual-ring {
        width: 250px;
        height: 250px;
        position: absolute;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        margin: auto;
        display: flex;
        align-content: center;
        align-items: center;
        justify-content: center;

    }

    .lds-dual-ring:after {
        content: " ";
        display: block;
        width: 64px;
        height: 64px;
        margin: 8px;
        border-radius: 50%;
        border: 6px solid #cef;
        border-color: #cef transparent #cef transparent;
        animation: lds-dual-ring 1.2s linear infinite;
    }

    @keyframes lds-dual-ring {
        0% {
            transform: rotate(0deg);
        }
        100% {
            transform: rotate(360deg);
        }
    }

    .error {
        width: 100%;
        background-color: red;
    }
</style>
