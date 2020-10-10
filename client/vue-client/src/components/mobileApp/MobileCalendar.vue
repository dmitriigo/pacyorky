<template>
  <b-col style="background-color: white">
    <b-row class="m-1">
      <b-calendar class="calendar-data"
                  block
                  :date-info-fn="light"
                  @context="trimToDate"
                  :start-weekday="1"
                  :value-as-date="true"
                  today-variant="today-var">
      </b-calendar>
    </b-row>
    <b-row></b-row>
    <b-row class="m-1 d-flex justify-content-center align-items-center flex-column">

      <b-table
          id="my-table"
          :items="tableEvents"
          :per-page="perPage"
          :current-page="currentPage"
          :fields="$ml.get('minifields')"
          small
      >
        <template v-slot:cell(more_info)="row">
          <b-button size="sm" @click="showDetails(row.item.id)" class="">
            ?
          </b-button>
        </template>
      </b-table>

      <b-pagination class="pag" align="center"
                    v-model="currentPage"
                    :total-rows="rows"
                    :per-page="perPage"
                    aria-controls="my-table"
      ></b-pagination>
    </b-row>
    <b-modal v-model="showModal" id="event-modal" hide-footer size="lg" :title="modalEvent.title" scrollable centered v-if="modalEvent">
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
  </b-col>
</template>

<script>
import axios from "axios";

export default {
  name: "MobileCalendar",
  data() {
    return {
      selectedDate: null,
      events: [],
      perPage: 5,
      currentPage: 1,
      tempEvents: [],
      showModal : false,
      modalEvent: {},

    }
  },
  computed: {
    rows() {
      return this.tempEvents.length
    },
    tableEvents() {
      let tevents = [...this.tempEvents];
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
  },
  mounted() {
    axios.get('/api/events')
        .then(response => {
          this.events = response.data;
          this.events.sort((event1, event2) => new Date(event2.date) - new Date(event1.date));
          this.tempEvents = [...this.events];
          this.loading = false;
        }).catch(error => {
      console.log(error);
      this.apiError = true
    });
  },
  methods: {
    light(ymd, date) {
      let table = false;
      this.events.forEach(event => {
        if (new Date(event.date).toDateString() === date.toDateString()) table = true;

      });
      return table ? 'selected-date' : ''
    },
    trimToDate(date) {
      if (date === null) {
        this.tempEvents = [...this.events];
      } else {
        this.tempEvents = [...this.events];
        this.tempEvents = this.tempEvents.filter(event => new Date(event.date)
            .toDateString() === new Date(date.activeYMD).toDateString());
      }
      if (this.tempEvents.length === 0) {
        this.tempEvents = [...this.events];
      }
    },
    showDetails(id) {
      this.modalEvent = this.events.filter(event => event.id === id)[0];
      this.showModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
  }
}
</script>

<style lang="less">
@primarycolor: #EBE1E4;
.calendar-data {
  background-color: #BDD9DC;
  padding: 10px;
  width: 100%;

  .btn {
    &:hover {
      background-color: #eeeeee !important;

    }
  }

  .selected-date {
    span {
      background-color: @primarycolor;
      border-radius: 50%;
      border: none !important;
    }
  }

  .active {
    background-color: rgba(214, 77, 85, 0.54) !important;
  }

  .btn-outline-today-var {
    background-color: rgba(53, 131, 141, 0.61) !important;
  }
}
</style>