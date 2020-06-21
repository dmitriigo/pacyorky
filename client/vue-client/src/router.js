import Vue from "vue";
import Router from 'vue-router'
import PacyorkyApplication from "./PacyorkyApplication";
import FirstStep from "./components/FirstStep";
import SecondStep from "./components/SecondStep";
import ThirdStep from "./components/ThirdStep";
import FourStep from "./components/FourStep";
import Admin from "./components/Admin";
import PrivacyPolicy from "./components/PrivacyPolicy";
import MobileMain from "./components/MobileMain";
import MailConfirmation from "./components/MailConfirmation";

Vue.use(Router)

export default new Router ({
    mode: 'history',
    routes: [
        {
            path: '',
            components: {
               desktop:  PacyorkyApplication,
                mobile:  MobileMain
            }
        },
        {
            path: '/firststep',
            components: {
                desktop:  FirstStep,
                mobile:  FirstStep
            }
        },
        {
            path: '/secondstep',
            components: {
                desktop:  SecondStep,
                mobile:  SecondStep
            }
        },
        {
            path: '/thirdstep',
            components: {
                desktop:  ThirdStep,
                mobile:  ThirdStep
            }
        },
        {
            path: '/fourstep',
            components: {
                desktop:  FourStep,
                mobile:  FourStep
            }
        },
        {
            path: "/groups",
            components: {
                desktop:  Admin,
                mobile:  Admin
            }
        },
        {
            path: "/privacy_policy",
            components: {
                desktop:  PrivacyPolicy,
                mobile:  PrivacyPolicy
            }
        },
        {
            path: "/confirmmail",
            components: {
                desktop:  MailConfirmation,
                mobile:  MailConfirmation
            }
        }
    ]
})
