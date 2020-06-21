<template>
    <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
        <b-row>
            <b-col class="d-flex justify-content-center align-items-center flex-column">
                <h1 v-if="success">Успешно!</h1>
                <h1 v-else>Упс.... что-то пошло не так</h1>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import axios from "axios";

    export default {
        name: "MailConfirmation",
        data() {
            return {
                success: false
            }
        },
        mounted() {
              let browserLink = window.location.href;
             let params =  browserLink.split("?");
             let user = params[1].split("&")[0].split("=")[1];
             let token = params[1].split("&")[1].split("=")[1];
            axios.get('/api/confirm?user='+user+'&token='+token).then(response => {
                this.success = response.data;
            })
        }

    }
</script>

<style scoped>

</style>