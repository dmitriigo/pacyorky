<template>
    <FullCalendar
            defaultView="dayGridMonth"
            :plugins="calendarPlugins"
            :events="events"
    :weekends="true"
    />
</template>


<script>

    import FullCalendar from '@fullcalendar/vue'
    import dayGridPlugin from '@fullcalendar/daygrid'

    export default {
        props: {
          events: {}
        },
        components: {
            FullCalendar // make the <FullCalendar> tag available
        },
        data() {
            return {
                calendarPlugins: [dayGridPlugin]
            }
        }
    }

</script>

<style lang='scss'>

    @import '~@fullcalendar/core/main.css';
    @import '~@fullcalendar/daygrid/main.css';

</style>