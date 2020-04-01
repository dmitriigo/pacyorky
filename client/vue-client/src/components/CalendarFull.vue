<template>
    <b-calendar :date-info-fn="light" @context="trimToDate"></b-calendar>
</template>

<script>
    export default {
        props: {
            events: {}
        },
        name: "CalendarEvent",
        data() {
            return {
                selectedDate: null
            }
        }
        ,
        methods: {
            light (ymd) {
                let table = false;
                this.events.forEach(event => {
                    if (event.date===ymd) table=true;
                });
                return table ? 'table-info' : ''
            },
            trimToDate (context) {
                this.selectedDate = context.selectedYMD;
                this.$emit("trimToDate", this.selectedDate);
            }
        }
    }
</script>

<style scoped>

</style>