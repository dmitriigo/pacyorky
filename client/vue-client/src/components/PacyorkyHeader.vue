<template>
    <b-row class="w-100 m-0 p-0" align-h="center" align-v="center">
        <b-col class="w-100 m-0 p-0">

            <b-row align-content="center" align-h="center" align-v="center" class="header-logo col-md-12 pt-md-5 pt-sm-0 m-md-0 col-sm-3 m-0 p-0">
                <b-col align-self="center" class="d-flex justify-content-center align-content-center pt-5 mt-4">
                <img class="mt-md-5 p-md-5" src="/img/Illustration.png"/>
                </b-col>
            </b-row>

            <svg class="wave" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 250">
                <path fill="#EBE1E4" fill-opacity="1"
                      d="M0,96L48,128C96,160,192,224,288,224C384,224,480,160,576,149.3C672,139,768,181,864,197.3C960,213,1056,203,1152,165.3C1248,128,1344,64,1392,32L1440,0L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"></path>
            </svg>
            <b-row align-content="center" align-h="center" align-v="center" class="align-self-center col-sm-12 col-xl-6 m-auto header-description">
                <b-col class="header-description-container">
                    <h4 class="p-sm-5"><span class="pacyorky">{{$ml.get('pacyorky')}} </span>{{$ml.get('headerdescriptionpt1')}}
                        <span class="pacyorky">{{$ml.get('pacyorky')}}</span> {{$ml.get('headerdescriptionpt2')}}</h4>
                </b-col>
            </b-row>
        </b-col>
    </b-row>

</template>

<script>
    export default {
        name: "PacyorkyHeader.vue"
    }
</script>

<style lang="less">
    @primarycolor: #EBE1E4;
    .header- {
        &container {
            width: 100%;
        }

        &logo {
            height: auto;
            max-height: 200px;
            background-color: @primarycolor;
            z-index: 1;

            img {
                display: inline-block;
                height: auto;
                max-width: 50%;
                z-index: 1;
            }
        }

        &description {
            margin-bottom: 20px;

            &-container {
                text-align: justify;

                h4 {
                    font-family: 'Open Sans', sans-serif;
                    font-style: normal;
                    font-size: 18px;
                    line-height: 26px;

                    .pacyorky {
                        color: #960F16;
                        font-family: Amatic SC;
                        font-style: normal;
                        font-weight: bold;
                        font-size: 24px;
                        line-height: 24px;
                    }
                }
            }
        }
    }

    .wave {
        z-index: -1;
    }
</style>
