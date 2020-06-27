<template>
    <b-container class="min-vh-100">
        <b-row class="mt-5">
            <b-col>
        <b-form v-on:submit.prevent="showGroups">
            <b-form-group
                    id="input-group-1"
                    label="secret"
                    label-for="input-1"
                    description="secret"
            >
                <b-form-input
                        id="input-1"
                        v-model="secret"
                        type="text"
                        required
                        placeholder="secret"
                ></b-form-input>
            </b-form-group>
            <div class="modal-buttons">
                <b-button type="submit" variant="primary">Submit</b-button>
            </div>
        </b-form>
            </b-col>
        </b-row>
       <div v-if="allow" class="mt-5">
        <b-row class="w-100" v-for="group in groups" :key="group.id">
            <b-col class="w-100 d-flex justify-content-center align-items-center flex-column">
           <p>{{group.name}}</p>
                <p>{{group.id}}</p>
                <p>{{group.allowed}}</p>
                <b-form v-on:submit.prevent="changeAllowed(group.id)">
                    <b-form-group
                            id="input-group-2"
                            label="secret"
                            label-for="input-2"
                            description="secret"
                    >
                        <b-form-input
                                id="input-2"
                                v-model="secret"
                                type="text"
                                required
                                placeholder="secret"
                        ></b-form-input>
                    </b-form-group>
                    <div class="modal-buttons">
                        <b-button type="submit" variant="primary">changeAllowed</b-button>
                    </div>
                </b-form>
            </b-col>
        </b-row>
       </div>
    </b-container>
</template>

<script>
    import axios from "axios";
    export default {

        name: "Admin",
        data() {
            return {
                groups: [],
                allow: false,
                secret: ''
            }
        },
        methods: {
            showGroups () {
                axios.get("/admin/all", {
                    params: {
                        key: this.secret
                    }
                   }).then(response => {this.groups=response.data; console.log(this.groups); this.allow=true})
            },
            changeAllowed(groupId) {
                axios.get("admin/change", {
                    params: {
                        groupId: groupId,
                        key: this.secret
                    }
                }).then(() => this.showGroups())
            }

        }
    }
</script>

<style scoped>

</style>