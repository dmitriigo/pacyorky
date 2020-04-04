<template>
<b-row>
    <b-calendar :date-info-fn="light" @context="trimToDate" :start-weekday="1">
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