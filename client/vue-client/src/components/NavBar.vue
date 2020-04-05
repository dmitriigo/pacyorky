<template>
    <div class="buttons">
        <b-button href="#head" variant="outline-dark">Main</b-button>
        <b-button href="#calendar" variant="outline-dark">Calendar</b-button>
        <b-button href="#game" variant="outline-dark">Game</b-button>
        <b-button href="#project" variant="outline-dark">Project</b-button>
        <b-dropdown variant="outline-dark" text="Lang">
            <b-dropdown-item v-for="lang in $ml.list" :key="lang" @click="$ml.change(lang)">
                {{lang}}
            </b-dropdown-item>
        </b-dropdown>
    </div>
</template>

<script>
    export default {
        name: "NavBar.vue"
    }
</script>

<style lang="less">
    .buttons {
        width: 100%;
        display: flex;
        justify-content: space-evenly;
        align-items: center;
        .btn {
            border-radius: 20px !important;
        }
    }




</style>