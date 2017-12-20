<template>
<div id="app" class="wrapper">
    <v-header :data="data['app_info']"></v-header>
    <v-sidebar :data="data['menu_items']"  ></v-sidebar>
    <v-content>
        <slot></slot>
    </v-content>
    <v-control-sidebar></v-control-sidebar>
    <v-footer ></v-footer>
</div>
</template>

<script>
import VHeader      from './header.vue'
import VFooter      from './v-footer.vue'
import VSideBar     from './sidebar-menu.vue'
import VContent     from './content.vue'
import VControlSideBar from './control-sidebar.vue'

export default {
  name: 'dashboard',
  props: {
    data: Object
  } ,
  mounted : function() {
    console.log("dashboard mounted!!")
  } ,
  components: {
      'v-header': VHeader,
      'v-sidebar': VSideBar ,
      'v-content': VContent ,
      'v-control-sidebar': VControlSideBar ,
      'v-footer': VFooter,
  }
}
</script>