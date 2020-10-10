<template>
  <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
    <b-row>
      <b-col class="d-flex justify-content-center align-items-center flex-column">
        <ol class="mt-5">Встановіть додаток у вашу групу на ФБ
          <li>Перейдіть до своєї групи</li>
          <li>Натисніть «Більше»</li>
          <li>Оберіть «Змінити налаштування групи»</li>
          <li>Знайдіть розділ «Додатки», Натисніть «Установити додатки»</li>
          <li>У пошуці наберіть Pacyorky і оберіть додаток</li>
          <li>Натисніть «Додати»</li>
        </ol>
        <silent-box id="join-box" :gallery="gamePics"></silent-box>
        <h3 class="mt-5">Після успішного встановлення додатку на ФБ, натисніть на кнопку внизу та дозвольте додатку
          виконати запитувані дії.</h3>
        <a class="btn btn-primary mb-5" href="/thirdstep">Долучитись!</a>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>

export default {
  name: "FirstStep",
  data() {
    return {
      gamePics: [
        {
          src: '/img/step1.png'
        }
        ,
        {
          src: '/img/step2.png',
        }
        ,
        {
          src: '/img/step3.png',
        }
        ,
        {
          src: '/img/step4.png',
        }
        ,
      ]
    }
  }
}
</script>

<style lang="less">
#join-box {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;

  .silentbox-item {
    width: 40%;
    margin: 10px;
    img {
      width: 100%;
    }
  }
}


</style>