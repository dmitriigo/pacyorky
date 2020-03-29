<template>
    <li>
        <h3>Event: {{event.title}}</h3>
        <h4>Location: {{event.location}}</h4>
        <h4>Date: {{event.date}}</h4>
        <h4>Description: {{event.description}}</h4>
    </li>
</template>

<script>
    export default {
        props: {
            event: {
                type: Object,
                required: true
            },
          index: Number
        },
        name: "CalendarEvent"
    }
</script>

<style scoped>

</style>