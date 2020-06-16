<template>
    <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
        <b-row>
            <b-col class="d-flex justify-content-center align-items-center flex-column">
                <b-form v-on:submit.prevent="registerGroup">
                    <b-form-group
                            id="input-group-1"
                            label="You ID"
                            label-for="input-1"
                            description="you ID"
                    >
                        <b-form-input
                                id="input-1"
                                v-model="form.userId"
                                type="text"
                                required
                                placeholder="Id"
                        ></b-form-input>
                    </b-form-group>
                    <b-form-group id="input-group-3" label="Choose group" label-for="input-3">
                        <b-form-select
                                id="input-3"
                                v-model="form.groupId"
                                :options="groups"
                                required
                        ></b-form-select>
                    </b-form-group>
                    <div v-if="infoBlock" style="color: green">success</div>
                    <div v-if="error" style="color: red">ERRORR</div>
                    <div class="modal-buttons">
                        <b-button type="submit" variant="primary">Submit</b-button>
                    </div>
                </b-form>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    import axios from "axios";

    export default {
        name: "FirstStep",
        data() {
            return {
                form: {
                    userId: '',
                    groupId: '',
                    url: ''
                },
                groups: [],
                infoBlock: false,
                error: false,
            }
        },
        mounted() {
            axios.get('/join/thirdstep').then(response => {
                this.groups = response.data;
            })
            this.form.url = window.location.href;
        },
        methods: {
            registerGroup() {
                axios.post("/join/fourstep", {
                    userId: this.form.userId,
                    groupId: this.form.groupId,
                    url: this.form.url
                }).then(() => {this.infoBlock=true; window.location.replace("/fourstep")})
                .catch(() => this.error=true);
            },
        }
    }
</script>

<style scoped>

</style>