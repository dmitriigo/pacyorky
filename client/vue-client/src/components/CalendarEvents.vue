<template>



    <div class="table-main">
        <b-pagination
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                aria-controls="my-table"
        ></b-pagination>

        <p class="mt-3">Current Page: {{ currentPage }}</p>

        <b-table
                id="my-table"
                :items="events"
                :per-page="perPage"
                :current-page="currentPage"
                :fields="fields"
                :striped="true"
                small
        >
            <template v-slot:cell(more_info)="row">
                <b-button v-b-modal.event-modal size="sm" @click="showDetails(row.item.id)" class="mr-2">
                    More info
                </b-button>

            </template>

        </b-table>
        <b-modal id="event-modal">{{modalEvent}}</b-modal>
    </div>

  <!--  <div class="overflow-auto">
        <ul>
        <CalendarEvent
                v-for="(event, i) of events"
        v-bind:event="event"
                v-bind:key="event.id"
                v-bind:index="i"
        />
        </ul>
    </div>-->


</template>

<script>
    import CalendarEvent from "./CalendarEvent";
    import axios from "axios";
    export default {
        name: "CalendarEvents.vue",
        data() {
            return {
                loading:true,
                apiError: false,
                modalEvent: {},
                perPage: 3,
                currentPage: 1,
                fields: [
                    {
                        key: 'title'
                    },
                    {
                        key: 'date'
                    },
                    {
                        key: 'description'
                    },
                    {
                        key: 'more_info',
                        label: '',

                    }
                ]
            }
        },
        computed: {
            rows() {
                return this.events.length
            }

        }
            ,
        components: {CalendarEvent},
        props: {
            events: {}
        },
        methods: {
            showDetails (id) {
                axios.get('/api/events/'+id)
                    .then(response => {this.modalEvent = response.data; this.loading=false}).catch(error => (this.apiError = true));
            }
        }
    }
</script>

<style scoped>
    .table-main {
        width: 100%;
    }
    #my-table {
        width: 100%;
    }
</style>