<template>
    <b-modal v-model="mailModal" id="jk"  hide-footer size="lg" title="" scrollable centered>
           <template v-slot:modal-header="{ close }" >
              <b-button class="ml-auto"  variant="light" @click="closeModalWindow"><b-icon icon="x" ></b-icon></b-button>
            </template>
        <div class="modal-event-item">
            <b-form v-on:submit.prevent="saveUser">
                <b-form-group
                        id="input-group-1"
                        :label="$ml.get('email')"
                        label-for="input-1"
                >
                    <b-form-input
                            id="input-1"
                            v-model="form.email"
                            type="email"
                            required
                            :placeholder="$ml.get('email')"
                    ></b-form-input>
                </b-form-group>
                <b-form-group id="input-group-2" :label="$ml.get('lang')" label-for="input-3">
                    <b-form-select
                            id="input-2"
                            v-model="form.lang"
                            :options="langs"
                            required
                    ></b-form-select>
                </b-form-group>
                <b-form-group id="input-group-3" :label="$ml.get('period')" label-for="input-3">
                    <b-form-select
                            id="input-3"
                            v-model="form.period"
                            :options="periods"
                            required
                    ></b-form-select>
                </b-form-group>
                <div v-if="infoBlock" style="color: green">{{ message }}</div>
                <div v-if="error" style="color: red">{{$ml.get('errormsg')}}</div>
                 <div class="modal-buttons">
                   <b-button type="submit" variant="primary">{{$ml.get('submit')}}</b-button>
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
                infoBlock : false,
                infoMessage : '',
                error : false,
                message: '',
            }
        },
        mounted() {
            axios.get('/api/mail-variant').then(response => {
                this.langs = response.data.mailLangs;
                let p = response.data.mailSendPeriods;
                for (const period of p) {
                    this.periods.push({text : this.$ml.get(period.toLowerCase()), value : period});
                }
            })
        },
        props: {
            mailModal: {}
        },
        methods: {
            closeModalWindow() {
                this.clearToDefault();
                this.$emit("closeModalWindow");
            },
            saveUser: function () {
                this.clearToDefault();
                axios.post("/api/add-mail", {
                    eMail : this.form.email,
                    mailLang : this.form.lang,
                    mailSendPeriod : this.form.period}
                ).then((response) => {
                    this.infoBlock = true;
                    this.message = response.data.message;
                    this.error = false;
                }).catch(error => {
                    console.log(error);
                    this.infoBlock = false;
                    this.error = true;
                });
            },
            clearToDefault () {
                this.infoBlock = false;
                this.infoMessage = '';
                this.error = false;
            }
        }
    }
</script>

<style scoped>

</style>