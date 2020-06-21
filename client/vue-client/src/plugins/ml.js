import Vue from 'vue'
import {MLanguage, MLCreate, MLInstaller} from 'vue-multilanguage'

Vue.use(MLInstaller);

export default new MLCreate({
    initial: 'Ukrainian',
    save: process.env.NODE_ENV === 'production',
    languages: [
        new MLanguage('Ukrainian').create({
            title: 'Назва',
            date: 'Дата',
            enddate: 'Дата закінчення',
            time: 'Час',
            description: 'Опис',
            city: 'Місто',
            errormsg: 'Щось пішло не так, дайте нам знати',
            owner: 'Організатор',
            seemoreinfo: 'Дивитись більше інфо',
            close: 'Закрити',
            fields: [
                {
                    key: 'startDate',
                    label: 'Дата'
                },
                {
                    key: 'title',
                    label: 'Назва'
                },

                {
                    key: 'more_info',
                    label: 'Інфо'
                },
                {
                    key: 'location',
                    label: 'Локація'
                }
            ],
            district0: 'Без локації',
            district1: 'Тарту',
            district2: 'Таллінн',
            district3: 'Пярну',
            district4: 'Їхви',
            district5: 'Кохтла-Ярве',
            district6: 'Хаапсалу',
            district7: 'Нарва',
            district8: 'Сілламяе',
            district9: 'Хар\'юмаа',
            district10: 'Іда-Вірумаа',
            district11: 'Хііюмаа',
            district12: 'Ярвамаа',
            district13: 'Їгевамаа',
            district14: 'Лянемаа',
            district15: 'Пилвамаа',
            district16: 'Пярнумаа',
            district17: 'Рапламаа',
            district18: 'Раквере',
            district19: 'Саремаа',
            district20: 'Тартумаа',
            district21: 'Валгамаа',
            district22: 'Віл\'яндімаа',
            district23: 'Вирумаа',
            pevents: 'Минулі події',
            fevents: 'Майбутні події',
            allevents: 'Всі події',
            main: 'Головна',
            calendar: 'Календар',
            game: 'Гра',
            project: 'Проект',
            pacyorky: 'Пацьoрки',
            headerdescriptionpt1: 'інтегрує події українського культурного простору Естонії із сегменту українських громад та земляцтв у соцмережах до єдиної платформи. ',
            headerdescriptionpt2: 'допоможе слідкувати за культурним життям української громади Естонії, вчасно дізнаватись про заходи і не пропустити важливі та цікаві події.',
            moreinfo: 'Інфо',
            separator1: 'Календар яскравого року',
            calendarandmap: 'Календар та мапа подій',
            aboutproject: 'Про проект',
            vodograi: 'Водограй',
            connectus: 'Долучитись',
            aboutpt1: 'Українське земляцтво міста Сілламяе "Водограй" це громадська організація,  метою якої є збереження української культури, мови та традицій. <br> <br> "Водограй" проводить роботу у культурно-освітньому, молодіжному та інформаційному напрямках. <br> <br> При земляцтві також діє недільна школа, дитячий ансамбль “Едельвейс” та видається інфолист “Рідна хата”. <br> <br> Розсилку інфолиста на електронную адресу можна замовити, повідомив про своє бажання <a href="mailto:sillamae.vodograi@gmail.com">sillamae.vodograi@gmail.com</a>',
            aboutpt2: 'Проект Українського земляцтва Сілламяе "Водограй" «Пацьорки» розпочався у 2020 році. Мета проекту – створити вебдодаток-календар, який висвітлюватиме об’яви про події українських громад, земляцтв і товариств Естонії. <br> <br> ' +
                'Бути в курсі культурних подій іноді важко. Для того, щоб слідкувати за всіма заходами потрібно бути учасником чисельних груп у Фейсбуці. І всеодно часто трапляється так, що ми дізнаємось про цікаві концерти, семінари та заходи, які залюбки хотіли би відвідати, вже після їх завершення через новини і пости у соцмережах. <br> <br> Пацьорки допоможе не пропустити події української діаспори Естонії і прожити рік яскраво.',
            aboutpt3: 'Якщо ви керівник української громади/товариства/колективу/школи і бажаєте долучитись до Пацьорки у якості організатора українських культурних подій в Естонії, напишіть нам на адресу <a href="mailto:info@pacyorky.ee">info@pacyorky.ee</a> <br> <br> ' +
                'Пацьорки автоматично інтегрує події, які ви створюєте у Фейсбуці, і презентує їх відвідувачам платформи. <br> <br> Пацьорки допоможе поширювати інформацію про ваші заходи та залучати на ваші заходи більше гостей, які цікавляться українською культурою.' +
                ' <br> <br>Додаток Пацьорки є безкоштовним для користувачів та організаторів культурних подій.',
            supportedby: 'Нас підтримують:',
            supporteddesc: 'Проект здійснюється за фінансової підтримки Міністерства Культури Естонії та Фонду Інтеграції та Міграції Естонії.',
            copyright: '©2020 Українське Земляцтво Сілламяе «Водограй»',
            location: 'Адреса',
            mapa: 'Мапа українців в Естонії',
            descformapa: 'Не знайшли цікавої події або збираєтесь у мандрівку Естонією? Відвідайте українські місця в Естонії!',
            //NEW fields
            email: 'Електронна скринька',
            hourly: 'Найсвiжiшi',
            weekly: 'Щотижнево',
            lang: 'Мова',
            period: 'Перiодичнiсть',
            submit: 'Затвердити',
        }),

        new MLanguage('Estonian').create({
            title: 'Nimetus',
            date: 'Kuupäev',
            time: 'Aeg',
            enddate: 'Lõppkuupäev',
            description: 'Kirjeldus',
            city: 'Linn',
            errormsg: 'Midagi läks valesti, palun andke meile teada',
            owner: 'Korraldaja',
            seemoreinfo: 'Vaata rohkem infot',
            close: 'Sulgeda',
            fields: [
                {
                    key: 'startDate',
                    label: 'Kuupäev'
                },
                {
                    key: 'title',
                    label: 'Nimetus'
                },
                {
                    key: 'more_info',
                    label: 'Info'
                },
                {
                    key: 'location',
                    label: 'Koht'
                }
            ],
            district0: 'Pole kohta',
            district1: 'Tartu',
            district2: 'Tallinn',
            district3: 'Pärnu',
            district4: 'Jõhvi',
            district5: 'Kohtla-Järve',
            district6: 'Haapsalu',
            district7: 'Narva',
            district8: 'Sillamäe',
            district9: 'Harjumaa',
            district10: 'Ida-Virumaa',
            district11: 'Hiiumaa',
            district12: 'Järvamaa',
            district13: 'Jõgevamaa',
            district14: 'Läänemaa',
            district15: 'Põlvamaa',
            district16: 'Pärnumaa',
            district17: 'Raplamaa',
            district18: 'Rakvere',
            district19: 'Saaremaa',
            district20: 'Tartumaa',
            district21: 'Valgamaa',
            district22: 'Viljandimaa',
            district23: 'Võrumaa',
            pevents: 'Möödunud sündmused',
            fevents: 'Tulevikud sündmused',
            allevents: 'Kõik sündmused',
            main: 'Peamine',
            calendar: 'Kalender',
            game: 'Mäng',
            project: 'Projekt',
            pacyorky: 'Pacyorky',
            headerdescriptionpt1: 'integreerib ukraina kultuuri ruumi sündmusi Facebook eesti ukraina seltside segmendist ühisse  platvormisse.',
            headerdescriptionpt2: 'aitab jälgida ukraina seltskonna kultuurielu Eestis. Ära jätta vahele huvitavaid ja olulisi sündmusi!',
            moreinfo: 'Info',
            separator1: 'Särava aasta kalender',
            calendarandmap: 'Sündmuste kalender ja kaart',
            aboutproject: 'Projektist',
            vodograi: 'Vodograi',
            connectus: 'Liitu meiega',
            aboutpt1: 'Ukraina kaasmaalaskond Sillamäe "Vodograi" on mittetulundusühing, mille eesmärgiks on ukraina rahvuskultuuri, keele, kommete ja traditsioonide säilitamine. <br> <br> Kaasmaalaskonna töö suunad: kultuurhariduslik, noorsootöö,' +
                ' informatsiooniline. <br> <br> Ukraina kaasmaalaskonna "Vodograi" juures toimivad: pühapäevakool, laste lauluansambel "Edelveis", uudiskirja "Ridna hata" ilmumine. <br><br>  Kui soovite saada uudiskirja oma e-postile, andke meile teada <a href="mailto:sillamae.vodograi@gmail.com">sillamae.vodograi@gmail.com</a> aadressile.',
            aboutpt2: 'Ukraina kaasmaalaskond Sillamäe "Vodograi" projekt "Pacyorky" algas 2020.aastal. Projekti eesmäärgiks on luua veebirakendust-kalendrit, mis näitaks kuulutusi Eestis toimuvatest ukraina seltskonna üritustest.' +
                ' <br> <br> Mõnikord on nii raske jääda kõikide kutuuriüritustega kursis. Selleks on vaja osaleda hulkalistes Facebook gruppides. Ja ikka juhtub nii, et mõni huvitav kontsert, seminar või üritus jäi silma vahele, ning saame sellest teada juba kui see on lõppenud kas uudisteat või sotsiaalse võrgustiku postitustest. <br> <br> Pacyorky aitab olla kursis eesti ukraina diaaspora kultuurieluga ning veeta aasta säravalt.',
            aboutpt3: 'Kui te olete ukraina seltsi/ansambli/kooli juht ja soovite liituda Pacyorky\'ga, kirjutage meile <a href="mailto:info@pacyorky.ee">info@pacyorky.ee</a> aadressile.' +
                '<br> <br>Pacyorky rakendus automaatselt integreerib teie Facebook üritusi ja esitab teie üritusi platvormi külastajatele. <br> <br> Pacyorky aitab teid levitafa infot teie ürituste kohta ning meelitada oma sündmustele rohkem külalisi, kes tunnevad huvi ukraina kultuuri vastu.' +
                '<br> <br>Pacyorky rakendus on tasuta kasutajatele ja kultuuriürituste korraldajatele.',
            supportedby: 'Meid toetavad:',
            supporteddesc: 'Projekti läbiviimise rahaline toetus: Eesti Kultuuriministeerium ja Integratsiooni ja Migratsiooni Sihtasutus Eestis.',
            copyright: '©2020 Ukraina kaasmaalaskond Sillamäe «Vodograi».',
            location: 'Aadress',
            mapa: 'Ukrainlaste kaart Eestis',
            descformapa: 'Ei leidnud huvitavat sündmust või plaanid minna Eestis reisimas? Külasta ukraina kohti Eestis!',
            //NEW fields
            email: 'E-post',
            hourly: 'Värsked sündmused',
            weekly: 'Iga nädal',
            lang: 'Keel',
            period: 'Periood',
            submit: 'Kinnitada',
        }),

        new MLanguage('English').create({
            title: 'Title',
            date: 'Date',
            enddate: 'End date',
            time: 'Time',
            description: 'Description',
            city: 'City',
            errormsg: 'Something gone wrong, please notify me',
            owner: 'Owner',
            seemoreinfo: 'See more info',
            close: 'Close',
            fields: [
                {
                    key: 'startDate',
                    label: 'Date'
                },
                {
                    key: 'title',
                    label: 'Title'
                },
                {
                    key: 'more_info',
                    label: 'Info'
                },
                {
                    key: 'location',
                    label: 'Location'
                }
            ],
            district0: 'No location',
            district1: 'Tartu',
            district2: 'Tallinn',
            district3: 'Pärnu',
            district4: 'Jõhvi',
            district5: 'Kohtla-Järve',
            district6: 'Haapsalu',
            district7: 'Narva',
            district8: 'Sillamäe',
            district9: 'Harjumaa',
            district10: 'Ida-Virumaa',
            district11: 'Hiiumaa',
            district12: 'Järvamaa',
            district13: 'Jõgevamaa',
            district14: 'Läänemaa',
            district15: 'Põlvamaa',
            district16: 'Pärnumaa',
            district17: 'Raplamaa',
            district18: 'Rakvere',
            district19: 'Saaremaa',
            district20: 'Tartumaa',
            district21: 'Valgamaa',
            district22: 'Viljandimaa',
            district23: 'Võrumaa',
            pevents: 'Past Events',
            fevents: 'Future Events',
            allevents: 'All Events',
            main: 'Main Page',
            calendar: 'Calendar',
            game: 'Game',
            project: 'Project',
            pacyorky: 'Pacyorky',
            headerdescriptionpt1: 'integrates events of Ukrainian cultural space in Estonia from a segment of Ukrainian communities in social networks into a single platform',
            headerdescriptionpt2: 'helps to follow cultural life of Ukrainian community in Estonia. Stay notified o and do not miss any of important and interesting forthcoming events!',
            moreinfo: 'Info',
            separator1: 'Calendar of a bright year',
            calendarandmap: 'Calendar and map of events',
            aboutproject: 'About Our Project',
            vodograi: 'Vodograi',
            connectus: 'Join us',
            aboutpt1: 'Ukrainian community of Sillamäe «Vodograi» is a non-governmental organization, which aims to preserve Ukrainian culture, language and customs. <br> <br> «Vodograi» conducts work in cultural, educational, youth and informative fields. <br> <br> Community has Sunday school, kids vocal ansambel "Edelveis" and publishes newsletter "Ridna Hata". <br> <br> If you wish to receive the Vodograi\'s newsletter on your e-mail, please subscribe by letting us know on <a href="mailto:sillamae.vodograi@gmail.com">sillamae.vodograi@gmail.com</a>',
            aboutpt2: 'Project of Ukrainian community of Sillamäe «Vodograi» «Pacyorky» started in 2020. Main aim of the project is to create a webapplication-calendar, which would highlight events of Ukrainian communities of Estonia.' +
                '<br> <br>It could be hard to stay updated about all upcoming cultural events. In order to follow all events we have to sunscribe for numerous facebook groups. And anyway it sometimes happens that we get to know about an interesting concert, seminar or event, which we would love to go to, after its end from new or social network posts. <br> <br> Pacyorky will help not to miss interesting and important events of Ukrainian diaspora in Estonia and have a bright year!',
            aboutpt3: 'If you are a head or management personnel of Ukrainian community/ansamble/school and would like to join Pacyorky as an owner of Ukrainian events in Estonia, please let us know <a href="mailto:info@pacyorky.ee">info@pacyorky.ee</a>' +

                '<br> <br> Pacyorky application is free for use by guest users and event owners',
            supportedby: 'Supported By',
            supporteddesc: 'The project Pacyorky is supported by the Ministry of Culture of Estonia and Estonian Integration and Migration Foundation.',
            copyright: '©2020 Ukrainian community of Sillamäe «Vodograi»',
            location: 'Address',
            mapa: 'Map of ukrainians in Estonia',
            descformapa: 'Did not find an interesting event or planning a trip across Estonia? Visit Ukrainian locations in Estonia!',

//NEW fields
            email: 'E-mail',
            hourly: 'Recent events',
            weekly: 'Weekly',
            lang: 'Language',
            period: 'Period',
            submit: 'Submit',
        }),
        new MLanguage('Russian').create({
            title: 'Название',
            date: 'Дата',
            enddate: 'Дата окончания',
            time: 'Время',
            description: 'Описание',
            city: 'Город',
            errormsg: 'Что-то пошло не так, сообщите нам',
            owner: 'Орназитор',
            seemoreinfo: 'Смотреть больше инфо',
            close: 'Закрыть',
            fields: [
                {
                    key: 'startDate',
                    label: 'Дата'
                },
                {
                    key: 'title',
                    label: 'Название'
                },

                {
                    key: 'more_info',
                    label: 'Инфо'
                },
                {
                    key: 'location',
                    label: 'Локация'
                }
            ],
            district0: 'Без локации',
            district1: 'Тарту',
            district2: 'Таллинн',
            district3: 'Пярну',
            district4: 'Йыхви',
            district5: 'Кохтла-Ярве',
            district6: 'Хаапсалу',
            district7: 'Нарва',
            district8: 'Силламяэ',
            district9: 'Харьюмаа',
            district10: 'Ида-Вирумаа',
            district11: 'Хииюмаа',
            district12: 'Ярвамаа',
            district13: 'Йыгевамаа',
            district14: 'Лянемаа',
            district15: 'Пылвамаа',
            district16: 'Пярнумаа',
            district17: 'Рапламаа',
            district18: 'Раквере',
            district19: 'Сааремаа',
            district20: 'Тартумаа',
            district21: 'Валгамаа',
            district22: 'Вильяндимаа',
            district23: 'Вырумаа',
            pevents: 'Прошедшие мероприятия',
            fevents: 'Будущие мероприятия',
            allevents: 'Все мероприятия',
            main: 'Главная',
            calendar: 'Календарь',
            game: 'Игра',
            project: 'Проект',
            pacyorky: 'Пацёрки',
            headerdescriptionpt1: 'интегрирует события украинского культурного пространства Эстонии из сегмента украинских общин и землячеств в соцсетях в единую платформу.',
            headerdescriptionpt2: 'поможет следить за культурной жизнью украинской общины Эстонии, вовремя узнавать про мероприятия и не пропустить важные и интересные события.',
            moreinfo: 'Инфо',
            separator1: 'Календарь яркого года',
            calendarandmap: 'Календарь и карта событий',
            aboutproject: 'О проекте',
            vodograi: 'Водограй',
            connectus: 'Присоединиться',
            aboutpt1: 'Украинское землячество города Силламяэ "Водограй" это общественная организация, целью которой является сохранение украинской культуры, языка и традиций. <br> <br> "Водограй" проводит работу в культурно-образовательном, молодежном и информационном направлениях. <br> <br> При землячестве также действует воскресная школа, детский ансамбль "Эдельвейс" и выдается инфолист "Родной дом". <br> <br> Рассылку инфолиста на электронную почту можно заказать, написав о своем желании на электронную почту <a href="mailto:sillamae.vodograi@gmail.com">sillamae.vodograi@gmail.com</a>',
            aboutpt2: 'Проект Украинского землячества Силламяэ "Водограй" "Пацёрки" начался в 2020 году. Цель проекта - создать вебприложение-календарь, который будет объединять объявления о событиях украинских общин, землячеств и обществ Эстонии. ' +
                '<br> <br> Быть в курсе культурных мероприятий иногда сложно. Для того, чтобы следить за всеми мероприятиями, необходимо быть участником многочисленных групп в Фейсбуке. И все же случается так, что мы узнаем про интересные концерты, семинары и мероприятия, которые с удовольствием хотели бы посетить, уже после их завершения из новостей или постах в соцсетях. <br> <br> Пацёрки поможет не пропустить события украинской диаспоры Эстонии и прожить год ярко.',
            aboutpt3: 'Если вы руководитель украинской общины/общества/коллектива/школы и желаете присоедениться к Пацёркам в качестве организатора украинских культурных мероприятий в Эстонии, напишите нам на адрес <a href="mailto:info@pacyorky.ee">info@pacyorky.ee</a>' +
                '<br> <br>Пацёрки автоматически интегрирует события, которые вы создаете в Фейсбук, и представляет их посетителям платформы. <br> <br> Пацёрки поможет распространять информацию про ваши мероприятия и привлекать на ваши мероприятия больше гостей, которые интересуются украинской культурой.' +
                ' <br> <br>Приложение Пацёрки бесплатное для пользователей и орагнизаторов культурных событий.',
            supportedby: 'Нас поддерживают:',
            supporteddesc: 'Проект осуществляется с финансовой поддержкой Министерства Культуры эстонии и Фонда Интеграции и Миграции Эстонии.',
            copyright: '©2020 Украинское землячество Силламяэ «Водограй»',
            location: 'Адрес',
            mapa: 'Карта украинцев в Эстонии',
            descformapa: 'Не нашли интересного события или собираетесь в путешествие по Эстонии? Посетите украинские места Эстонии!',
            //NEW fields
            email: 'Электронная почта',
            hourly: 'Самые свежие',
            weekly: 'Еженедельно',
            lang: 'Язык',
            period: 'Периодичность',
            submit: 'Подтвердить',
        })

    ]
})
