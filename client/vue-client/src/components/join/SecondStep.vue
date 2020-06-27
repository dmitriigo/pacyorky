<template>
    <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
        <b-row>
            <b-col class="d-flex justify-content-center align-items-center flex-column">
                <ul class="mt-5">Встановіть додаток у вашу групу на ФБ
                <li>Перейдіть до своєї групи</li>
                <li>Натисніть «Більше» </li>
                <li>Оберіть «Змінити налаштування групи»</li>
                </ul>
                <img style="max-width: 70%"  src="/img/step1.png"/>
                <p class="mt-3">Знайдіть розділ «Додатки», Натисніть «Установити додатки»</p>
                <img style="max-width: 70%"  src="/img/step2.png"/>
                <p class="mt-3">У пошуці наберіть Pacyorky і оберіть додаток</p>
                <img style="max-width: 70%"  src="/img/step3.png"/>
                <p class="mt-3">Натисніть «Додати»</p>
                <img style="max-width: 70%"  src="/img/step4.png"/>
                <h3 class="mt-5">Після успішного встановлення додатку на ФБ, натисніть на кнопку внизу та дозвольте додатку виконати запитувані дії.</h3>
                <a class="btn btn-primary mb-5" href="https://www.facebook.com/v7.0/dialog/oauth?client_id=3559197890788331&redirect_uri=https://pacyorky.ee/thirdstep&response_type=token">Долучитись!</a>
            </b-col>
        </b-row>
    </b-container>
</template>

<script>
    export default {
        name: "FirstStep"
    }
</script>

<style scoped>

</style>