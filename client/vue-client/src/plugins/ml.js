import Vue from 'vue'
import {MLInstaller, MLCreate, MLanguage} from 'vue-multilanguage'

Vue.use(MLInstaller);

export default new MLCreate({
    initial: 'english',
    save: process.env.NODE_ENV === 'production',
    languages: [
        new MLanguage('english').create({
            title: 'Hello !',
            msg: 'You have {f} friends and {l} likes'
        }),
        new MLanguage('russian').create({
            title: 'Hello !',
            msg: 'You have {f} friends and {l} likes'
        }),
        new MLanguage('ukrainian').create({
            title: 'Hello!',
            msg: 'You have {f} friends and {l} likes'
        }),

        new MLanguage('eesti').create({
            title: 'Oi !',
            msg: 'Você tem {f} amigos e {l} curtidas'
        })
    ]
})