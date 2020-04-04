<template>
    <div>
    <button
            v-for="lang in $ml.list"
            :key="lang"
            @click="$ml.change(lang)"
            v-text="lang"
    />
    </div>
</template>

<script>
    export default {
        name: "NavBar.vue"
    }
</script>

<style scoped>

</style>