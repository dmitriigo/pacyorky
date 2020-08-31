<template>
    <b-container fluid="true" class="min-vh-100 d-flex justify-content-center align-content-center align-items-center">
        <b-row>
            <b-col class="d-flex justify-content-center align-items-center flex-column">
                <h1>Установите приложение в свою группу:</h1>
                <p>Перейдите в свою группу, нажмите "еще" и выберете "Редактировать настройки группы"</p>
                <img style="max-width: 70%"  src="/img/step1.png"/>
                <p class="mt-3">Найдите раздел "Приложения" и нажмите на "Добавить приложение"</p>
                <img style="max-width: 70%"  src="/img/step2.png"/>
                <p class="mt-3">Введите в поиске "Pacyorky" и выберете приложение</p>
                <img style="max-width: 70%"  src="/img/step3.png"/>
                <p class="mt-3">Нажмите на "Добавить приложение"</p>
                <img style="max-width: 70%"  src="/img/step4.png"/>
                <h3 class="mt-5">После успешной установки нажмите на кнопку ниже и разрешите приложению запрошенные действия:</h3>
                <a class="btn btn-primary mb-5" href="https://www.facebook.com/v7.0/dialog/oauth?client_id=3559197890788331&redirect_uri=https://pacyorky.ee/thirdstep&response_type=token&scope=pages_read_engagement">Join!</a>
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