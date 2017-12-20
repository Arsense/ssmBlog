import VButton      from './components/button.vue'
import VSideBar     from './components/sidebar-menu.vue'
import VDashboard   from './components/dashboard.vue'
import VHeader      from './components/header.vue'
import VContent     from './components/content.vue'
import VBox         from './components/box.vue'
import VSelect2     from './components/select2.vue'
import VFooter      from './components/v-footer.vue'
import VTable       from './components/table.vue'
import VCheckBox    from './components/check-box.vue'
import VAlert       from './components/alert.vue'
import  VRow        from './grid/Row.vue'
import  VCallout    from './components/callout.vue'
import  VCustomTabs from './components/custom-tab.vue'
//import VCol         from './grid/Column/vue'
import  VText       from  './components/text.vue'
import  VLogin      from   './pages/login.vue'


Vue.component('v-login'     ,VLogin)
Vue.component('v-text'      ,VText)
Vue.component('v-custom'    ,VCustomTabs)
Vue.component('v-callout'   , VCallout)
Vue.component('v-row'       , VRow)
//Vue.component('v-col'       , VCol)
Vue.component('v-alert'     ,VAlert)
Vue.component('v-check'     ,VCheckBox)
Vue.component('v-button'    , VButton)
Vue.component('v-sidebar'   , VSideBar)
Vue.component('v-app'       , VDashboard)
Vue.component('v-header'    , VHeader)
Vue.component('v-content'   , VContent)
Vue.component('v-box'       , VBox)
Vue.component('v-select2'   , VSelect2)
Vue.component('v-footer'    , VFooter)
Vue.component('v-table'     , VTable)
