import Vue from 'vue'
import {MLInstaller, MLCreate, MLanguage} from 'vue-multilanguage'

Vue.use(MLInstaller);

export default new MLCreate({
    initial: 'English',
    save: process.env.NODE_ENV === 'production',
    languages: [
        new MLanguage('English').create({
            title: 'Title',
            date: 'Date',
            description: 'Description',
            city: 'City',
            fields: [
                {
                    key: 'date',
                    label: 'Date'
                },
                {
                    key: 'title',
                    label: 'Title'
                },
                {
                    key: 'more_info',
                    label: ''
                },
                {
                    key: 'location',
                    label: ''
                }
            ],
            district0 : 'Without location',
            district1 : 'Tartu',
            district2 : 'Tallinn',
            district3 : 'Pärnu',
            district4 : 'Jõhvi',
            district5 : 'Kohtla-Järve',
            district6 : 'Haapsalu',
            district7 : 'Narva',
            district8 : 'Sillamäe',
            district9 : 'Harjumaa',
            district10 : 'Ida-Virumaa',
            district11 : 'Hiiumaa',
            district12 : 'Järvamaa',
            district13 : 'Jõgevamaa',
            district14 : 'Laanemaa',
            district15 : 'Polvamaa',
            district16 : 'Pärnumaa',
            district17 : 'Raplamaa',
            district18 : 'Rakvere',
            district19 : 'Saaremaa',
            district20 : 'Tartumaa',
            district21 : 'Valgamaa',
            district22 : 'Viljandimaa',
            district23 : 'Võrumaa',
            pevents: 'Past Events',
            fevents: 'Future Events',
            allevents: 'All Events'

        }),
        new MLanguage('Russian').create({
            title: 'Название',
            date: 'Дата',
            description: 'Описание',
            city: 'Город',
            fields: [
                {
                    key: 'date',
                    label: 'Дата'
                },
                {
                    key: 'title',
                    label: 'Название'
                },

                {
                    key: 'more_info',
                    label: ''
                },
                {
                    key: 'location',
                    label: ''
                }
            ],
            district0 : 'Без локации',
            district1 : 'Тарту',
            district2 : 'Таллинн',
            district3 : 'Пярну',
            district4 : 'Йыхви',
            district5 : 'Кохла-Ярве',
            district6 : 'Хаапсалу',
            district7 : 'Нарва',
            district8 : 'Силламяе',
            district9 : 'Харьюмаа',
            district10 : 'Ида-Вирумаа',
            district11 : 'Хииюма',
            district12 : 'Ярвамаа',
            district13 : 'Йыгевамаа',
            district14 : 'Ланемаа',
            district15 : 'Полвамаа',
            district16 : 'Пярнумаа',
            district17 : 'Рапламаа',
            district18 : 'Раквере',
            district19 : 'Саремаа',
            district20 : 'Тартумаа',
            district21 : 'Валгамаа',
            district22 : 'Вильяндимаа',
            district23 : 'Вырумаа',
            pevents: 'Прошедшие мероприятия',
            fevents: 'Будущие мероприятия',
            allevents: 'Все мероприятия'
        }),
        new MLanguage('Ukrainian').create({
            title: 'Название',
            date: 'Дата',
            description: 'Описание',
            city: 'Город',
            fields: [
                {
                    key: 'date',
                    label: 'Дата'
                },
                {
                    key: 'title',
                    label: 'Название'
                },

                {
                    key: 'more_info',
                    label: ''
                },
                {
                    key: 'location',
                    label: ''
                }
            ],
            district0 : 'Без локации',
            district1 : 'Тарту',
            district2 : 'Таллинн',
            district3 : 'Пярну',
            district4 : 'Йыхви',
            district5 : 'Кохла-Ярве',
            district6 : 'Хаапсалу',
            district7 : 'Нарва',
            district8 : 'Силламяе',
            district9 : 'Харьюмаа',
            district10 : 'Ида-Вирумаа',
            district11 : 'Хииюма',
            district12 : 'Ярвамаа',
            district13 : 'Йыгевамаа',
            district14 : 'Ланемаа',
            district15 : 'Полвамаа',
            district16 : 'Пярнумаа',
            district17 : 'Рапламаа',
            district18 : 'Раквере',
            district19 : 'Саремаа',
            district20 : 'Тартумаа',
            district21 : 'Валгамаа',
            district22 : 'Вильяндимаа',
            district23 : 'Вырумаа',
            pevents: 'Прошедшие мероприятия',
            fevents: 'Будущие мероприятия',
            allevents: 'Все мероприятия'
        }),

        new MLanguage('Eesti').create({
            title: 'Oi !',
            date: 'Você ',
            description: 'чячяч',
            city: 'City',
            fields: [
                {
                    key: 'date',
                    label: 'Date'
                },
                {
                    key: 'title',
                    label: 'Title'
                },
                {
                    key: 'more_info',
                    label: ''
                },
                {
                    key: 'location',
                    label: ''
                }
            ],
            district0 : 'Without location',
            district1 : 'Tartu',
            district2 : 'Tallinn',
            district3 : 'Pärnu',
            district4 : 'Jõhvi',
            district5 : 'Kohtla-Järve',
            district6 : 'Haapsalu',
            district7 : 'Narva',
            district8 : 'Sillamäe',
            district9 : 'Harjumaa',
            district10 : 'Ida-Virumaa',
            district11 : 'Hiiumaa',
            district12 : 'Järvamaa',
            district13 : 'Jõgevamaa',
            district14 : 'Laanemaa',
            district15 : 'Polvamaa',
            district16 : 'Pärnumaa',
            district17 : 'Raplamaa',
            district18 : 'Rakvere',
            district19 : 'Saaremaa',
            district20 : 'Tartumaa',
            district21 : 'Valgamaa',
            district22 : 'Viljandimaa',
            district23 : 'Võrumaa',
            pevents: 'Past Events',
            fevents: 'Future Events',
            allevents: 'All Events'
        })
    ]
})