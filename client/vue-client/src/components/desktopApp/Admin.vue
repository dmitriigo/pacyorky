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
            <b-col style="border: solid black; border-radius: 1%" class="m-3 p-3 w-100 d-flex justify-content-center align-items-center flex-column">
              <h3>name</h3> <p> {{group.name}}</p>
              <h3>id</h3><p> {{group.id}}</p>
              <h3>allowed</h3> <p :style="'color: ' + (group.allowed ? 'green' : 'red')"> {{group.allowed}}</p>
              <h3>hidden</h3><p :style="'color: ' + (group.hidden ? 'green' : 'red')">  {{group.hidden}}</p>
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
                      <button class="btn-primary btn" v-on:click="changeHidden(group.id)">Change hidden</button>
                        <b-button type="submit" variant="primary">changeAllowed</b-button>
                    </div>
                </b-form>
            </b-col>
        </b-row>
         <b-row><h1>PAGES</h1></b-row>
         <b-row class="w-100" v-for="page in pages" :key="page.id">
           <b-col style="border: solid black; border-radius: 1%" class="m-3 p-3 w-100 d-flex justify-content-center align-items-center flex-column">
             <h3>name</h3> <p> {{page.name}}</p>
             <h3>id</h3><p> {{page.id}}</p>
             <p :style="'color: ' + (page.allowed ? 'green' : 'red')"> {{page.allowed}}</p>
             <b-form v-on:submit.prevent="changeAllowedPage(page.id)">
               <b-form-group
                   id="input-page-2"
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
                secret: '',
                pages: []
            }
        },
        methods: {
          changeHidden(groupId) {
              axios.get("/admin/hide", {
                params: {
                  groupId: groupId,
                  key: this.secret
                }
              }).then(() => this.showGroups())
          },
            showGroups () {
                axios.get("/admin/all", {
                    params: {
                        key: this.secret
                    }
                   }).then(response => {this.groups=response.data; this.allow=true});
              axios.get("/admin/pages", {
                params: {
                  key: this.secret
                }
              }).then(response => {this.pages=response.data; this.allow=true})
            },
            changeAllowed(groupId) {
                axios.get("/admin/change", {
                    params: {
                        groupId: groupId,
                        key: this.secret
                    }
                }).then(() => this.showGroups())
            },
          changeAllowedPage(pageId) {
            axios.get("/admin/changepage", {
              params: {
                pageId: pageId,
                key: this.secret
              }
            }).then(() => this.showGroups())
          },

        }
    }
</script>

<style scoped>

</style>