<template>

    <div class="table-main">

        <b-table
                id="my-table"
                :items="tableEvents"
                :per-page="perPage"
                :current-page="currentPage"
                :fields="$ml.get('fields')"
                :striped="true"
                small
        >
            <template v-slot:cell(more_info)="row">
                <b-button size="sm" @click="showDetails(row.item.id)" class="mr-2">
                    {{$ml.get('moreinfo')}}
                </b-button>
            </template>
            <template v-slot:cell(location)="row">
                <b-button size="sm" @click="trimToLocation(row.item.id)" class="mr-2">
                    {{$ml.get('location')}}
                </b-button>
            </template>

        </b-table>
        <b-pagination class="pag" align="center"
                      v-model="currentPage"
                      :total-rows="rows"
                      :per-page="perPage"
                      aria-controls="my-table"
        ></b-pagination>
        <b-modal v-model="showModale" id="event-modal" hide-footer title="" scrollable centered v-if="modalEvent">
            <div class="modal-event-item">
                <h2>{{modalEvent.title}}</h2>
                <h4>{{$ml.get('date')}}: {{modalEvent.startDate}}</h4>
                <h4>{{$ml.get('time')}}: {{modalEvent.startTime}}</h4>
                <h4 v-if="modalEvent.endTime">{{$ml.get('enddate')}}: {{modalEvent.endDate}}</h4>
                <h4 v-if="modalEvent.endTime">{{$ml.get('time')}}: {{modalEvent.endTimeCoverted}}</h4>
                <h4 v-if="modalEvent.district">{{$ml.get('location')}}: {{$ml.get('district'+modalEvent.district.id)}}</h4>
                <h4>Location: {{modalEvent.location}}</h4>
                <h4>Owner: {{modalEvent.eventOwner}}</h4>
                <h4>{{modalEvent.description}}</h4>
                <div class="modal-buttons">
                    <b-button :href=modalEvent.link target="_blank">See more info</b-button>
                    <b-button @click="closeModal">Close</b-button>
                </div>

            </div>
        </b-modal>
    </div>


</template>

<script>

    export default {
        name: "CalendarEvents.vue",
        data() {
            return {
                showModale: false,
                loading: true,
                apiError: false,
                modalEvent: {},
                perPage: 5,
                currentPage: 1
            }
        },
        computed: {
            rows() {
                return this.events.length
            },
            tableEvents () {
                let tevents = [...this.events];
                tevents.forEach(event => {
                    let date = new Date(event.date);
                    event.startDate = date.toDateString();
                    event.startTime = date.toLocaleTimeString();
                    if (event.endTime) {
                        let endDate = new Date(event.endTime);
                        event.endDate = endDate.toDateString();
                        event.endTimeCoverted = endDate.toLocaleTimeString();
                    }
                })
                return tevents;
            }

        }
        ,
        props: {
            events: {}
        },
        methods: {
            showDetails(id) {
                this.modalEvent = this.events.filter(event => event.id === id)[0];
                this.showModale = true;
            },
            closeModal() {
                this.showModale = false;
            },
            trimToLocation(id) {
                this.$emit("trimToLocation", id);
            }
        }
    }
</script>

<style lang="less">
    @primarycolor: #EBE1E4;
    @secondaryColor: #BDD9DC;

    .modal-buttons {
        display: flex;
        justify-content: space-between;
        align-items: center;

        .btn {
            border-radius: 20px;
            background-color: #BDD9DC;
            color: black;
            &:hover {
                color: white !important;
                background-color: black !important;
            }
        }
    }

    .table-main {
        width: 100%;
        height: 100%;
        background-color: @primarycolor;

        .page-item {
            border: none;
        }

        .active {
            .page-link {
                color: rgba(53, 131, 141, 0.61) !important;
                border: rgba(53, 131, 141, 0.61) solid !important;
                border-radius: 20px;
            }
        }
        .page-link {
            border: none;
        }
        li {
            background-color: @primarycolor;
            border: none !important;

            span {
                background-color: @primarycolor !important;
                color: #D64D55 !important;

            }

            button {
                background-color: @primarycolor !important;
                color: #D64D55 !important;
            }
        }
    }

    #my-table {
        width: 100%;

        .btn {
            border-radius: 20px;
            background-color: #BDD9DC;
            color: black;
            &:hover {
                color: white !important;
                background-color: black !important;
            }
        }
    }
</style>
