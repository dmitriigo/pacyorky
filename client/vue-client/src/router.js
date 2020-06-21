import Vue from "vue";
import Router from 'vue-router'
import PacyorkyApplication from "./PacyorkyApplication";
import FirstStep from "./components/FirstStep";
import SecondStep from "./components/SecondStep";
import ThirdStep from "./components/ThirdStep";
import FourStep from "./components/FourStep";
import Admin from "./components/Admin";
import PrivacyPolicy from "./components/PrivacyPolicy";

Vue.use(Router)

export default new Router ({
    mode: 'history',
    routes: [
        {
            path: '',
            component: PacyorkyApplication
        },
        {
            path: '/firststep',
            component: FirstStep
        },
        {
            path: '/secondstep',
            component: SecondStep
        },
        {
            path: '/thirdstep',
            component: ThirdStep
        },
        {
            path: '/fourstep',
            component: FourStep
        },
        {
            path: "/groups",
            component: Admin
        },
        {
            path: "/privacy_policy",
            component: PrivacyPolicy
        }
    ]
})
