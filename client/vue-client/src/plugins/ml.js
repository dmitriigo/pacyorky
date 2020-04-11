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
            allevents: 'All Events',
            main: 'Main Page',
            calendar: 'Calendar',
            game: 'Game',
            project: 'Project',
            pacyorky: 'Pacyorky',
            headerdescriptionpt1: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias atque consequatur culpa earum quam qui quia totam?',
            headerdescriptionpt2: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias atque consequatur culpa earum quam qui quia totam?',
            moreinfo: 'Info',
            separator1: 'Calendar of a bright year',
            calendarandmap: 'Calendar and map of events',
            aboutproject: 'About Project',
            vodograi: 'Vodograi',
            connectus: 'Connect to us',
            aboutpt1: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt2: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt3: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            supportedby: 'Supported By',
            supporteddesc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi ',
            copyright: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis.',
            location: 'Location'


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
            allevents: 'Все мероприятия',
            main: 'Главная',
            calendar: 'Календарь',
            game: 'Игра',
            project: 'Проект',
            pacyorky: 'Пациорки',
            headerdescriptionpt1: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias atque consequatur culpa earum quam qui quia totam?',
            headerdescriptionpt2: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias atque consequatur culpa earum quam qui quia totam?',
            moreinfo: 'Инфо',
            separator1: 'Календарь яркого года',
            calendarandmap: 'Клаендарь и карта мероприятий',
            aboutproject: 'О проекте',
            vodograi: 'Водограй',
            connectus: 'Присоединиться к нам',
            aboutpt1: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt2: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt3: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            supportedby: 'При поддержке',
            supporteddesc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi ',
            copyright: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis.',
            location: 'Место'
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
            allevents: 'Все мероприятия',
            main: 'Главная',
            calendar: 'Календарь',
            game: 'Игра',
            project: 'Проект',
            pacyorky: 'Пацьoрки',
            headerdescriptionpt1: 'інтегрує події українського культурного простору Естонії із сегменту українських громад та земляцтв у соцмережах до єдиної платформи. ',
            headerdescriptionpt2: 'допоможе слідкувати за культурним життям української громади Естонії, вчасно дізнаватись і не пропустити важливі та цікаві івенти.',
            moreinfo: 'Инфо',
            separator1: 'Календарь яркого года',
            calendarandmap: 'Клаендарь и карта мероприятий',
            aboutproject: 'О проекте',
            vodograi: 'Водограй',
            connectus: 'Присоединиться к нам',
            aboutpt1: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt2: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt3: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            supportedby: 'При поддержке',
            supporteddesc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi ',
            copyright: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis.',
            location: 'Место'
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
            allevents: 'All Events',
            main: 'Main Page',
            calendar: 'Calendar',
            game: 'Game',
            project: 'Project',
            pacyorky: 'Pacyorky',
            headerdescriptionpt1: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias atque consequatur culpa earum quam qui quia totam?',
            headerdescriptionpt2: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Alias atque consequatur culpa earum quam qui quia totam?',
            moreinfo: 'Info',
            separator1: 'Calendar of a bright year',
            calendarandmap: 'Calendar and map of events',
            aboutproject: 'About Project',
            vodograi: 'Vodograi',
            connectus: 'Connect to us',
            aboutpt1: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt2: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            aboutpt3: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie. Orci sagittis viverra amet porta augue ut luctus dapibus. Pulvinar dui, sed id sodales amet tristique gravida nisl. Nibh quis id id cursus netus. Id integer id montes, morbi vitae ultrices metus tincidunt non. Volutpat id aliquet ipsum tristique tortor, sed aliquet augue massa. Blandit nunc augue praesent metus. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi cras molestie.',
            supportedby: 'Supported By',
            supporteddesc: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis. Elementum ac dolor imperdiet non faucibus facilisi ',
            copyright: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Turpis varius ridiculus mi risus quis.',
            location: 'Location'
        })
    ]
})