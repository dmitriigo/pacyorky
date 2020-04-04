import Vue from 'vue'
import {MLInstaller, MLCreate, MLanguage} from 'vue-multilanguage'

Vue.use(MLInstaller);

export default new MLCreate({
    initial: 'english',
    save: process.env.NODE_ENV === 'production',
    languages: [
        new MLanguage('english').create({
            title: 'Title',
            date: 'Date',
            description: 'Description',
        }),
        new MLanguage('russian').create({
            title: 'Название',
            date: 'Дата',
            description: 'Описание',
        }),
        new MLanguage('ukrainian').create({
            title: 'Название',
            date: 'Дата',
            description: 'Описание',
        }),

        new MLanguage('eesti').create({
            title: 'Oi !',
            date: 'Você tem {f} amigos e {l} curtidas',
            description: 'чячяч',
        })
    ]
})