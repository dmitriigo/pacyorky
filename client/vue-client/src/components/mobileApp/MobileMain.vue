<template>
    <b-col style="background-color: white">
        <b-row>
            <div class="w-100" style="text-align: center">
            <h1 style="text-align: center">{{$ml.get('main')}}</h1>
            </div>
            <div style="text-align: center">
            <h4 class="p-sm-5"><span class="pacyorky">{{$ml.get('pacyorky')}} </span>{{$ml.get('headerdescriptionpt1')}}
                <span class="pacyorky">{{$ml.get('pacyorky')}}</span> {{$ml.get('headerdescriptionpt2')}}</h4>
            </div>
        </b-row>
        <b-row></b-row>
    </b-col>
</template>

<script>
    export default {
        name: "MobileMain"
    }
</script>

<style scoped>

</style>