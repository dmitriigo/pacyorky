import Vue from 'vue'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faBell, faEnvelope, faThumbsUp } from '@fortawesome/free-regular-svg-icons'
import {faShareAlt, faFilePdf} from "@fortawesome/free-solid-svg-icons";
import { faFacebookSquare } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faFacebookSquare, faBell, faEnvelope, faThumbsUp,faShareAlt, faFilePdf)

Vue.component('font-awesome-icon', FontAwesomeIcon)
