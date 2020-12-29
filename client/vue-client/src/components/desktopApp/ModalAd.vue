<template>
  <b-modal v-model="adModal"  hide-footer size="lg" title="" scrollable centered>
    <template v-slot:modal-header="{ close }" >
      <b-button class="ml-auto"  variant="light" @click="closeAdModal"><b-icon icon="x" ></b-icon></b-button>
    </template>
    <div style="display: flex; justify-content: center; align-items: center; flex-direction: column">
    <p>Увага конкурс!</p>
      <p><b>МИКОЛАЙ ФЕСТ-ОНЛАЙН 2020</b></p>
    <a target="_blank" href="http://vodograi.ee/mykolay/mykolay.html">Дивитись</a>
    </div>
  </b-modal>
</template>

<script>
export default {
  name: "ModalAd",
  data() {
    return {
      adModal: false,
    }
  },
  methods: {
    closeAdModal() {
      this.adModal = false;
    },
    openModal() {
      this.adModal = true;
    }
  },
  mounted() {
    setTimeout(this.openModal, 5000)
  }
}
</script>

<style scoped>

</style>