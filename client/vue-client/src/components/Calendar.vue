<template>
<b-row>
    <b-calendar class="calendar-data" :date-info-fn="light" @context="trimToDate" :start-weekday="1" today-variant="today-var">
    </b-calendar>

</b-row>



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
            light (ymd) {
                let table = false;
                this.events.forEach(event => {
                    if (event.date===ymd) table=true;
                });
                return table ? 'selected-date' : ''
            },
            trimToDate (context) {
                this.selectedDate = context.selectedYMD;
                this.$emit("trimToDate", this.selectedDate);
            }
        }
    }
</script>


<style lang="less">
    @primarycolor: #F6F0E4;
    .calendar-data {
        .selected-date {
            background-color: @primarycolor;
            border-radius: 20px;
        }

        .active {
            background-color: rgba(214, 77, 85, 0.54) !important;
        }
        .btn-outline-today-var {
          background-color: rgba(53, 131, 141, 0.61) !important;
        }
    }

</style>