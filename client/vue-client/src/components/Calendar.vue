<template>
<b-row>
            <div v-if="!loading">
                <b-row>
                    <b-row>
                        <CalendarFull @trimToDate="trimToDate" v-bind:events="eventsForCalendar" />
                    </b-row>
                    <b-row>
                        <CalendarEvents v-bind:events="eventsForList"/>
                    </b-row>
                </b-row>
            </div>
            <div class="lds-dual-ring" v-else></div>
            <div class="error" v-if="apiError"><h1>ERRROOOROORORRRRRR!!!</h1></div>

</b-row>



</template>

<script>
    import axios from 'axios'
    import CalendarFull from "./CalendarFull";
    import CalendarEvents from "./CalendarEvents";

    export default {
        name: "Calendar.vue",
        components: {CalendarEvents, CalendarFull},
        data() {
            return {
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
        methods: {
            trimToDate(date) {
                if (date==="") this.eventsForList= this.events;
               else this.eventsForList = this.events.filter(event => event.date === date)
            },
            getEvents() {
                axios.get('/api/events')
                    .then(response => {
                        this.events = response.data;
                        this.loading = false;
                        this.eventsForList = this.events;
                        this.eventsForCalendar = this.events;
                    }).catch(error => (this.apiError = true));

            }
        }
    }
</script>


<style scoped>
    .lds-dual-ring {
        display: inline-block;
        width: 80px;
        height: 80px;
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