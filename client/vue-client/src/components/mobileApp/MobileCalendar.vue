<template>
    <b-col style="background-color: white">
        <b-row>
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
      <b-row>

        <b-table
            id="my-table"
            :items="tableEvents"
            :per-page="perPage"
            :current-page="currentPage"
            :fields="$ml.get('minifields')"
            small
        >
          <template v-slot:cell(more_info)="row">
            <b-button size="sm" href=""  class="">
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
      },
      mounted() {
        axios.get('/api/events')
            .then(response => {
              this.events = response.data;
              this.events.sort((event1, event2) => new Date(event2.date)-new Date(event1.date));
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
            /*this.eventsForList = this.events;
            this.eventsForMap = this.events;
            this.eventsForCalendar = this.events;
            this.districtShow=false;*/
          } else {
            /*this.eventsForList = this.events.filter(event => new Date(event.date).toLocaleDateString() === date.toLocaleDateString());
            this.eventsForMap = this.events.filter(event => new Date(event.date).toLocaleDateString() === date.toLocaleDateString());
            this.districtShow=false;*/
          }
        },
      }
    }
</script>

<style lang="less">
@primarycolor: #EBE1E4;
.calendar-data {
  background-color: #BDD9DC;
  padding: 10px;

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