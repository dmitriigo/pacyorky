<template>
    <div class="header-container">
        <b-row lg="12" align-h="end" class="header-icons">
            <b-col cols="3" align-self="center"><h1>Icons</h1></b-col>
        </b-row>
        <b-row align-content="center" align-h="center" align-v="center" class="header-logo"><div class="header-logo-img"> <img src="/img/Illustration.png"/></div></b-row>
        <svg class="wave" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#C2E7E1" fill-opacity="1" d="M0,96L48,128C96,160,192,224,288,224C384,224,480,160,576,149.3C672,139,768,181,864,197.3C960,213,1056,203,1152,165.3C1248,128,1344,64,1392,32L1440,0L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"></path></svg>
        <b-row align-content="center" align-h="center" align-v="center" class="header-description">description</b-row>

    </div>
</template>

<script>
    export default {
        name: "PacyorkyHeader.vue"
    }
</script>

<style lang="less" scoped>
    .header- {
        &container {
            width: 100%;
        }

        &icons {
            background: #C2E7E1;
            border: 5px solid #C2E7E1;
            height: 100px;
        }

        &logo {
            height: 100px;
            background: #C2E7E1;
            z-index: 1;
            &-img {
                z-index: 1;
                img {
                    display: block;
                    height: auto;
                    max-width: 500px;
                    max-height: 500px;
                    z-index: 1;
                }
            }
        }

        &description {
            margin: 10px;
            height: 100px;
        }
    }
    .wave {
        z-index: -1;
    }
</style>