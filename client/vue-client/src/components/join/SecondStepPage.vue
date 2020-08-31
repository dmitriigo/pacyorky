<template>
  <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
    <b-row>
      <b-col class="d-flex justify-content-center align-items-center flex-column">
        <h3 class="mt-5">Please give permissions</h3>
        <a class="btn btn-primary mb-5" href="https://www.facebook.com/v7.0/dialog/oauth?client_id=3559197890788331&redirect_uri=https://pacyorky.ee/thirdsteppage&response_type=token&scope=pages_read_engagement">Долучитись!</a>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "SecondStepPage"
}
</script>

<style scoped>

</style>