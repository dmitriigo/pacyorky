<template>
<b-row>
    <b-calendar :date-info-fn="light" @context="trimToDate" :start-weekday="1">
        <b-button @click="clearEvents" variant="outline-primary">ShowAll</b-button>
    </b-calendar>

</b-row>



</template>

<script>
    import axios from 'axios'

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
            },
            clearEvents () {
                this.$emit("trimToDate", "");
            }
        }
    }
</script>


<style scoped>



</style>