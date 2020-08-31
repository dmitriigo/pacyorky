<template>
  <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
    <b-row>
      <b-col class="d-flex justify-content-center align-items-center flex-column">
        <p>please enter you ID</p>
        <b-form class="mt-5 mb-5" v-on:submit.prevent="registerPage">
          <b-form-group
              id="input-group-1"
              label="Ваш ID"
              label-for="input-1"
              description="Ваш ID"
          >
            <b-form-input
                id="input-1"
                v-model="form.userId"
                type="text"
                required
                placeholder="Id"
            ></b-form-input>
          </b-form-group>
          <div v-if="infoBlock" style="color: green">Удачно!</div>
          <div v-if="error" style="color: red">Ошибка!</div>
          <div class="modal-buttons">
            <b-button type="submit" variant="primary">Відправити</b-button>
          </div>
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "ThirdStepPage",
  data() {
    return {
      form: {
        userId: '',
        url: ''
      },
      infoBlock: false,
      error: false,
    }
  },
  mounted() {
    this.form.url = window.location.href;
  },

  methods: {
    registerPage() {
      axios.post("/join/foursteppage", {
        userId: this.form.userId,
        url: this.form.url
      }).then(() => {
        this.infoBlock = true;
        window.location.replace("/fourstep")
      })
          .catch(() => this.error = true);
    },
  }
}
</script>

<style scoped>

</style>