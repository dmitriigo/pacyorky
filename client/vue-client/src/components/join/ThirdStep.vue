<template>
  <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
    <b-row>
      <b-col class="d-flex justify-content-center align-items-center flex-column">
        Будь ласка оберіть свою групу:
        <b-form class="mt-5 mb-5">
          <b-form-group id="input-group-3" label="Обрати групу" label-for="input-3">
            <b-form-select
                id="input-3"
                v-model="groupId"
                :options="groups"
                required
            ></b-form-select>
          </b-form-group>
          <div v-if="infoBlock" style="color: green">Успішно!</div>
          <div v-if="error" style="color: red">Помилка!</div>
          <div class="modal-buttons">
            <b-button :disabled="groupId === {}" v-on:click="logInWithFacebook" variant="primary">Відправити</b-button>
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
          groupId: {},
          groups: [],
          infoBlock: false,
          error: false,
        }
      },
      mounted() {
        axios.get('/join/thirdstep').then(response => {
          this.groups = response.data;
        });
        this.loadFacebookSDK(document, "script", "facebook-jssdk");
        this.initFacebook();
      },
      methods: {

        async logInWithFacebook() {
          let self = this;
          window.FB.login(function (response) {
            if (response.status != 'connected') {
              self.error = true
            } else {
              axios.post("/join/fourstep", {
                userId: response.authResponse.userID,
                token: response.authResponse.accessToken,
                groupId: self.groupId
              }).then(() => {
                self.infoBlock = true;
                window.location.replace("/fourstep")
              })
                  .catch(() => self.error = true);
            }
          });
          return false;
        },
        async initFacebook() {
          window.fbAsyncInit = function () {
            window.FB.init({
              appId: "3559197890788331",
              version: "v13.0"
            });
          };
        },
        async loadFacebookSDK(d, s, id) {
          var js,
              fjs = d.getElementsByTagName(s)[0];
          if (d.getElementById(id)) {
            return;
          }
          js = d.createElement(s);
          js.id = id;
          js.src = "https://connect.facebook.net/en_US/sdk.js";
          fjs.parentNode.insertBefore(js, fjs);
        },
      }
    }
</script>

<style scoped>

</style>