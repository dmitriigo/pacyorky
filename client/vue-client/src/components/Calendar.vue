<template>
    <b-calendar class="calendar-data"
                :date-info-fn="light"
                @context="trimToDate"
                :start-weekday="1"
                :value-as-date="true"
                today-variant="today-var">
    </b-calendar>




</template>

<script>

    export default {
        name: "Calendar.vue",
        props: {
            events: {}
        },
        data() {
            return {
                selectedDate: null
            }
        },
        mounted() {

        },
        methods: {
            light (ymd, date) {
                let table = false;
                this.events.forEach(event => {
                   if (new Date(event.date).toDateString()===date.toDateString()) table=true;

                });
                return table ? 'selected-date' : ''
            },
            trimToDate (context) {
                this.selectedDate = context.selectedDate;
                this.$emit("trimToDate", this.selectedDate);
            }
        }
    }
</script>


<style lang="less">
    @primarycolor: #EBE1E4;
    .calendar-data {
        background-color: #BDD9DC;
        padding: 10px;
        .btn {
            &:hover{
                background-color: #eeeeee !important;

            }
        }
        .selected-date {
            background-color: @primarycolor;
            border-radius: 50%;
            border: none !important;
        }

        .active {
            background-color: rgba(214, 77, 85, 0.54) !important;
        }
        .btn-outline-today-var {
          background-color: rgba(53, 131, 141, 0.61) !important;
        }
    }

</style>
