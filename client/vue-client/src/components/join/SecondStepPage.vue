<template>
  <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
    <b-row class="d-flex justify-content-center align-items-center flex-column">
      <ol>
        <li>      Натисніть на "Долучитись"
        </li>
        <li>      Увійдіть на Фейсбук
        </li>
        <li>      Оберіть сторінку громади, від якої бажаєте долучитись
        </li>
      </ol>
      <img style="max-width: 70%"  src="/img/step8.png"/>
      <button class="btn btn-primary m-3" v-on:click="logInWithFacebook">Долучитись!</button>
      <div v-if="infoBlock" style="color: green">Успішно!</div>
      <div v-if="error" style="color: red">Помилка!</div>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "SecondStepPage",
  mounted() {
    axios.get('/join/notify').then(response => {

    });
    this.loadFacebookSDK(document, "script", "facebook-jssdk")
    this.initFacebook();
  },
  data() {
    return {
      infoBlock: false,
      error: false,
    }
  },
  methods: {
    async logInWithFacebook() {
      let self = this;
      window.FB.login(function (response) {
        if (response.status != 'connected') {
          self.error = true
        } else {
          axios.post("/join/foursteppage", {
            userId: response.authResponse.userID,
            token: response.authResponse.accessToken
          }).then(() => {
            self.infoBlock = true;
            window.location.replace("/fourstep")
          })
              .catch(() => self.error = true);
        }
      }, {scope: 'pages_read_engagement'});
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
    }
  }
}
</script>

<style scoped>

</style>