<template>

    <b-container>
        <div v-if="!loading">
            <b-row>
                <Calendar @trimToDate="trimToDate" v-bind:events="eventsForCalendar"/>
            </b-row>
            <b-row>
                <EventsTable v-bind:events="eventsForList"/>
            </b-row>
            <EventsMap v-bind:events="eventsForMap"/>
        </div>
        <div class="lds-dual-ring" v-else></div>
        <div class="error" v-if="apiError"><h1>ERRROOOROORORRRRRR!!!</h1></div>
    </b-container>


</template>

<script>
    import axios from "axios";
    import Calendar from "./components/Calendar"
    import EventsTable from "./components/EventsTable";
    import EventsMap from "./components/EventsMap";


    export default {
        name: 'App',
        data() {
            return {
                eventsForMap: [],
                loading: true,
                events: [],
                apiError: false,
                eventsForList: [],
                eventsForCalendar: []
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
            trimToDate(date) {
                if (date === "") {
                    this.eventsForList = this.events;
                    this.eventsForMap = this.events;
                } else {
                    this.eventsForList = this.events.filter(event => event.date === date);
                    this.eventsForMap = this.events.filter(event => event.date === date);

                }
            },
            getEvents() {
                axios.get('/api/events')
                    .then(response => {
                        this.events = response.data;
                        this.loading = false;
                        this.eventsForList = this.events;
                        this.eventsForCalendar = this.events;
                        this.eventsForMap = this.events;
                    }).catch(error => {
                        console.log(error);
                        this.apiError = true
                });

            }
        }
    }
</script>

<style lang="less">
    .logo {
        background-color: #4f4f4f;
    }

    #app {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
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
        height: 100%;
        background-color: red;
    }
</style>
