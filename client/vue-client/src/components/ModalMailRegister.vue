<template>
    <b-modal v-model="mailModal" id="jk"  hide-footer size="lg" title="" scrollable centered>
           <template v-slot:modal-header="{ close }" >
              <col>  <b-button  variant="light" @click="closeModalWindow"><b-icon icon="x" ></b-icon></b-button>  </col>
            </template>
        <div class="modal-event-item">
            <b-form v-on:submit.prevent="saveUser">
                <b-form-group
                        id="input-group-1"
                        label="Email address:"
                        label-for="input-1"
                        description="We'll never share your email with anyone else."
                >
                    <b-form-input
                            id="input-1"
                            v-model="form.email"
                            type="email"
                            required
                            placeholder="Enter email"
                    ></b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2" label="Lang" label-for="input-3">
                    <b-form-select
                            id="input-2"
                            v-model="form.lang"
                            :options="langs"
                            required
                    ></b-form-select>
                </b-form-group>
                <b-form-group id="input-group-3" label="Period:" label-for="input-3">
                    <b-form-select
                            id="input-3"
                            v-model="form.period"
                            :options="periods"
                            required
                    ></b-form-select>
                </b-form-group>
                 <div class="modal-buttons">
                   <b-button type="submit" variant="primary">Submit</b-button>
                   <b-button @click="closeModalWindow">{{$ml.get('close')}}</b-button>
                 </div>
            </b-form>
        </div>
    </b-modal>
</template>

<script>
    import axios from "axios";


    export default {
        name: "ModalMailRegister",
        data() {
            return {
                form: {
                    email: '',
                    lang: '',
                    period: ''
                },
                periods: [],
                langs: [],
            }
        },
        mounted() {
            axios.get('/api/mail-variant').then(response => {
                this.langs = response.data.mailLangs;
                this.periods = response.data.mailSendPeriods;
            })
        },
        props: {
            mailModal: {}
        },
        methods: {
            closeModalWindow() {
                this.$emit("closeModalWindow")
            },
            saveUser: function (event) {
                axios.post("/api/add-mail", {
                    eMail : this.form.email,
                    mailLang : this.form.lang,
                    mailSendPeriod : this.form.period}
                ).then(function (response) {
                    let data = JSON.parse( response.request.response );
                    alert (data.message);
                }).catch(() => {
                }).then(this.closeModalWindow());
            },
        }
    }
</script>

<style scoped>

</style>