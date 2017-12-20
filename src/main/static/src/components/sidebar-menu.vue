<template>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu" data-widget="tree" >
            <li class="header"></li>
            <template v-for="item in data">
            <li v-if="item['children']" :class="append_active_class(item , 'treeview')"  >
                <a href="#"><i :class="item['icon']"></i> <span>{{item['desc']}}</span>
                    <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li v-for="citem in item['children']">
                        <a :href="citem['url']"><i :class="citem['icon']"></i>{{citem['desc']}}</a>
                    </li>
                </ul>

            </li>

            <li :class="append_active_class(item ,'')" v-else>
                <a :href="item['url']"><i :class="item['icon']"></i> <span>{{item['desc']}}</span></a>
            </li>
            </template>
        </ul>
    </section>
</aside>
</template>

<script>
import util from '../lib/util'
export default {
  name: 'sidebar-menu',
  props: {
    data: Array
  } ,
  mounted : function() {
    $('.sidebar-menu').tree()
  } ,
  methods : {
    is_cur_menu: function(item) {
        var pathname = window.location.pathname
        if(item['children'] !== null && item['children'] !== undefined ) {
            for(var c in item['children']) {
                if(item['children'][c].url === pathname) {
                    util.store('menu_lev2' , item['children'][c].desc)
                    return true
                }

            }
            return false
        }
        return item['url'] == pathname
    } ,
    append_active_class: function(item , cur_class) {
        if(this.is_cur_menu(item)) {
            util.store('menu_lev1' , item.desc)
            return cur_class + ' active'
        }
        return cur_class

    }


  }
}
</script>