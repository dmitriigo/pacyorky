<template>
  <b-container class="mobile-app" fluid="true">
    <b-container fluid="true" class="mb-5">
      <MobileHeader/>
    </b-container>
    <b-container class="mt-5">
      <b-col class="main-content" align-self="center">
        <b-row>
          <router-view name="mobile"></router-view>
        </b-row>
        <b-row align-v="center" align-content="center" align-h="center" class="mt-2">
          <a href="https://www.facebook.com/vodograi.estonia/" target="_blank" class="ml-2 mr-2"
             style="color: black">
            <font-awesome-icon :icon="['fab', 'facebook-square']"/>
          </a>
          <!--рассылка-->
          <b-link class="ml-2 mr-2" style="color: black" @click="openModalWindow">
            <font-awesome-icon :icon="['far', 'bell']"/>
          </b-link>
          <a href="mailto:info@pacyorky.ee" class="ml-2 mr-2" style="color: black">
            <font-awesome-icon :icon="['far', 'envelope']"/>
          </a>
          <a href="https://www.facebook.com/pacyorky.ee/" target="_blank" class="ml-2 mr-2"
             style="color: black">
            <font-awesome-icon :icon="['far', 'thumbs-up']"/>
          </a>
          <a href="https://www.facebook.com/sharer/sharer.php?kid_directed_site=0&u=https%3A%2F%2Fwww.facebook.com%2Fpacyorky.ee%2F&display=popup&ref=plugin&src=share_button"
             class="ml-2 mr-2" style="color: black">
            <font-awesome-icon :icon="['fas', 'share-alt']"/>
          </a>
        </b-row>
      </b-col>
    </b-container>
    <ModalMailRegister @closeModalWindow="closeModalWindow" :mail-modal="mailModal"/>
  </b-container>
</template>

<script>
import MobileHeader from "./MobileHeader";
import ModalMailRegister from "@/components/desktopApp/ModalMailRegister";

export default {
  name: "PacyorkyMobileApplication",
  components: {MobileHeader, ModalMailRegister},
  data() {
    return {
      mailModal: false
    }
  },
  methods: {
    openModalWindow() {
      this.mailModal = true;
    },
    closeModalWindow() {
      this.mailModal = false;
    }
  }
}
</script>

<style lang="less">
.mobile-app {
  font-family: Amatic SC;
  font-style: normal;
  font-weight: bold;
  font-size: 20px;
  line-height: 15px;
  p {
    font-family: Calibri;
    font-style: normal;
    font-weight: normal;
    font-size: 18px;
    line-height: 18px;
    text-align: justify;
  }
  .dropdown-menu {
    border: none;
    background-color: transparent;
    height: 100%;

    div {


      a {
        display: flex;
        justify-content: center;
        align-content: center;
        align-items: center;
        color: black;
        width: 50px;
        height: 50px;
        border: 1px solid #000000;
        border-radius: 50%;
        background: #BDD9DC;

        &.router-link-exact-active {
          background: #EBE1E4;
        }
      }
    }
  }
}
</style>