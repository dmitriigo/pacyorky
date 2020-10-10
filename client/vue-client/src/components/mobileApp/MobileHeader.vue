<template>
  <b-col class="w-100">
    <b-row class="w-100 d-flex justify-content-center align-items-center">
      <b-col class="d-flex justify-content-center align-items-center">
        <b-dropdown id="mobile-menu" text="Menu">
          <b-dropdown-item class="mobile-menu-item">
            <router-link to="/">{{ $ml.get('main') }}</router-link>
          </b-dropdown-item>
        <b-dropdown-item>
            <router-link to="/calendar">{{ $ml.get('calendar') }}</router-link>
        </b-dropdown-item>
          <b-dropdown-item>
            <router-link to="/game">{{ $ml.get('game') }}</router-link>
          </b-dropdown-item>
            <b-dropdown-item>
            <router-link to="/project">{{ $ml.get('project') }}</router-link>
            </b-dropdown-item>

        </b-dropdown>
      </b-col>
      <b-col class="d-flex justify-content-center align-items-center">
        <img class="" style="width: 200%" :src="'/img/Illustration-'+$ml.current+'.png'"/>
      </b-col>
      <b-col class="d-flex justify-content-end align-items-center">
        <b-dropdown :text="$ml.current.substr(0,3)">
          <b-dropdown-item v-for="lang in $ml.list" v-if="lang!==$ml.current" :key="lang" @click="$ml.change(lang)">
            <img style="border: 2px solid black; border-radius: 50%" class="w-25 m-2" :src="'img/'+lang+'.svg'"/>
          </b-dropdown-item>
        </b-dropdown>
      </b-col>
    </b-row>
  </b-col>
</template>

<script>
export default {
  name: "MobileHeader"
}
</script>

<style lang="less">
.dropdown {
  .dropdown-toggle {
    background: #BDD9DC !important;
    color: black !important;
    border-radius: 46px;
    border: 1px solid #000000 !important;
  }
}
#mobile-menu {
  li {
    display: flex;
    justify-content: flex-start;
    margin: 0;
    a{
      display: flex;
      justify-content: center;
      margin-right: auto;
      .router-link-active {
        background: #ebe1e4;
        text-decoration: underline;
      }
      a{
        border: none;
        align-items: center;
        color: #000;
        width: 50px;
        height: 50px;
        border: 1px solid #000;
        border-radius: 50%;
        background: #bdd9dc;
      }

    }
  }
}
</style>