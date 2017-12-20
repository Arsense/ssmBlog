<template>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            {{page_header_inter}}
            <small>{{page_desc}}</small>
        </h1>
        <ol class="breadcrumb">
            <li v-if="menu_lev1"><a href="#"><i class="fa fa-dashboard"></i> {{menu_lev1}}</a></li>
            <li v-if="menu_lev2" class="active">{{menu_lev2}}</li>
        </ol>
    </section>
    <!-- Main content -->
    <section class="content container-fluid">
        <slot></slot>
    </section>
</div>

</template>

<script>
import util from '../lib/util'
export default {
  name: 'v-content',
  props: {
    page_header: { type: String, default: '' },
    page_desc : { type: String, default: '' },
    data: Object
  } ,
  mounted : function() {
    console.log("content mounted!!")
  } ,
  computed : {
    menu_lev1 : function() {
        return util.get('menu_lev1')
    } ,
    menu_lev2 : function() {
        return util.get('menu_lev2')
    } ,
    page_header_inter : function() {
        return util.get('menu_lev1')
    }
  }
}
</script>