<template>

    <div class="table-main">

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
                <b-button size="sm" @click="showDetails(row.item.id)" class="mr-2">
                    More info
                </b-button>
            </template>
            <template v-slot:cell(location)="row">
                <b-button size="sm" @click="trimToLocation(row.item.id)" class="mr-2">
                    location
                </b-button>
            </template>

        </b-table>
        <b-pagination class="pag" align="center"
                v-model="currentPage"
                :total-rows="rows"
                :per-page="perPage"
                aria-controls="my-table"
        ></b-pagination>
        <b-modal v-model="showModale" id="event-modal" hide-footer title="" scrollable centered>
            <div class="modal-event-item">
            <h1>{{modalEvent.title}}</h1>
                <h3>{{$ml.get('date')}}: {{modalEvent.date}}</h3>
                <h3 v-if="modalEvent.endTime">End time: {{modalEvent.endTime}}</h3>
                <h3>Dictrict: {{modalEvent.district}}</h3>
                <h3>Location: {{modalEvent.location}}</h3>
                <h3>Owner: {{modalEvent.eventOwner}}</h3>
                <h4>{{modalEvent.description}}</h4>
                <div class="buttons">
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
                loading:true,
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
            fields () { return [
                {
                    key: 'title',
                    label: this.$ml.get('title')
                },
                {
                    key: 'date',
                    label: this.$ml.get('date')
                },
                {
                    key: 'description',
                    label: this.$ml.get('description')
                },
                {
                    key: 'more_info',
                    label: ''
                },
                {
                    key: 'location',
                    label: ''
                }
            ]}

        }
            ,
        props: {
            events: {}
        },
        methods: {
            showDetails (id) {
               this.modalEvent=this.events.filter(event => event.id === id)[0];
                this.showModale=true;
            },
            closeModal () {
                this.showModale=false;
            },
            trimToLocation (id) {
                this.$emit("trimToLocation", id);
            }
        }
    }
</script>

<style lang="less">
    @primarycolor: #F6F0E4;
    .pag {

    }
    .buttons {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .table-main {
        width: 100%;
        background-color: @primarycolor ;
    }
    #my-table {
        width: 100%;
    }
</style>