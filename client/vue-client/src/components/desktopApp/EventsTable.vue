<template xmlns="http://www.w3.org/1999/html">

    <div class="table-main">
        <b-pagination class="pag" align="center"
                      v-model="currentPage"
                      :total-rows="rows"
                      :per-page="perPage"
                      aria-controls="my-table"
        ></b-pagination>
        <b-table
                id="my-table"
                :items="tableEvents"
                :per-page="perPage"
                :current-page="currentPage"
                :fields="$ml.get('fields')"
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

        <b-modal v-model="showModale" id="event-modal" hide-footer size="lg" :title="modalEvent.title" scrollable centered v-if="modalEvent">
            <div class="modal-event-item">
                <div class="modal-event-item-text d-flex flex-column">
                    <div class="modal-item-dates d-flex justify-content-around align-content-center">
                    <div class="modal-item-start-date">
                        <h5>{{$ml.get('date')}}: <span style="font-weight: normal">{{modalEvent.startDate}}</span></h5>
                <h5>{{$ml.get('time')}}: <span style="font-weight: normal">{{modalEvent.startTime}}</span></h5>
                    </div>
                    <div class="modal-item-end-date">
                <h5 v-if="modalEvent.endTime">{{$ml.get('enddate')}}: <span style="font-weight: normal">{{modalEvent.endDate}}</span></h5>
                <h5 v-if="modalEvent.endTime">{{$ml.get('time')}}: <span style="font-weight: normal">{{modalEvent.endTimeCoverted}}</span></h5>
                    </div>
                    </div>
                    <hr style="color: black; width: 100%; margin: 0 0 10px;"/>
                <h5 v-if="modalEvent.district">{{$ml.get('city')}}: <span style="font-weight: normal">{{$ml.get('district'+modalEvent.district.id)}}</span></h5>
                <h5>{{$ml.get('location')}}: <span style="font-weight: normal">{{modalEvent.location}}</span></h5>
                    <hr style="color: black; width: 100%; margin: 0 0 10px;"/>
                <h5>{{$ml.get('owner')}}: <span style="font-weight: normal">{{modalEvent.eventOwner}}</span></h5>
                    <hr style="color: black; width: 100%; margin: 0 0 10px;"/>
                <p style="text-align: justify">{{modalEvent.description}}</p>
                </div>
                <div class="modal-buttons">
                    <b-button :href=modalEvent.link target="_blank">{{$ml.get('seemoreinfo')}}</b-button>
                    <b-button @click="closeModal">{{$ml.get('close')}}</b-button>
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

    .modal-title {
        width: 100%;
        text-align: center;
        font-size: 30px;
    }
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
        background-color: @secondaryColor;
tr {
    background-color: white !important;
    &:hover {
        background-color: #eeeeee !important;
    }
}
        .page-item {
            border: none !important;
            outline: 0 !important;
        }

        .active {
            .page-link {
                color: red !important;
                border-radius: 20px;
                outline: 0 !important;
            }
        }
        .page-link {
            border: none;
        }
        li {
            background-color: @secondaryColor;
            border: none !important;

            span {
                background-color: @secondaryColor !important;
                color: black !important;

            }

            button {
                outline: 0 !important;
                background-color: @secondaryColor !important;
                color: black !important;
            }
        }
    }

    #my-table {
        width: 100%;
height: 80%;
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
