import Vue from "vue";
import Router from 'vue-router'
import PacyorkyDesktopApplication from "./components/desktopApp/PacyorkyDesktopApplication";
import FirstStep from "./components/join/FirstStep";
import SecondStep from "./components/join/SecondStep";
import ThirdStep from "./components/join/ThirdStep";
import FourStep from "./components/join/FourStep";
import Admin from "./components/desktopApp/Admin";
import PrivacyPolicy from "./components/PrivacyPolicy";
import MailConfirmation from "./components/desktopApp/MailConfirmation";
import MobileMain from "./components/mobileApp/MobileMain";
import MobileCalendar from "./components/mobileApp/MobileCalendar";
import MobileGame from "./components/mobileApp/MobileGame";
import MobileProject from "./components/mobileApp/MobileProject";
import SecondStepPage from "@/components/join/SecondStepPage";

Vue.use(Router)

export default new Router ({
    mode: 'history',
    routes: [
        {
            path: '',
            components: {
               desktop:  PacyorkyDesktopApplication,
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
            path: '/secondsteppage',
            components: {
                desktop:  SecondStepPage,
                mobile:  SecondStepPage
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
        },
        {
            path: "/calendar",
            components: {
                desktop:  PacyorkyDesktopApplication,
                mobile:  MobileCalendar
            }
        },
        {
            path: "/game",
            components: {
                desktop:  PacyorkyDesktopApplication,
                mobile:  MobileGame
            }
        },
        {
            path: "/project",
            components: {
                desktop:  PacyorkyDesktopApplication,
                mobile:  MobileProject
            }
        },
    ]
})
