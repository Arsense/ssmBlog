<template>
<header class="main-header">
    <!-- Logo -->
    <a :href="index_url" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>{{logo_short_name}}</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>{{logo_name}}</b></span>
    </a>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
         <!-- Sidebar toggle button-->
         <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
            <span class="sr-only">Toggle navigation</span>
         </a>
         <div class="navbar-custom-menu">
            <!-- Navbar Right Menu -->
            <ul class="nav navbar-nav">
                <slot></slot>
                <!-- Control Sidebar Toggle Button -->
                <li class="dropdown user user-menu">
                    <!-- Menu Toggle Button -->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                        <!-- The user image in the navbar-->
                        <img :src="user_img_url" class="user-image" alt="User Image">
                        <!-- hidden-xs hides the username on small devices so only the image appears. -->
                        <span class="hidden-xs">{{user_name}}</span>
                    </a>
                    <ul class="dropdown-menu">
                      <!-- The user image in the menu -->
                      <li class="user-header">
                        <img :src="user_img_url" class="img-circle" alt="User Image">
                        <p>{{user_name}}</p>
                      </li>
                      <!-- Menu Footer-->
                      <li class="user-footer">
                        <div class="pull-left">
                          <a :href="profile_url" class="btn btn-default btn-flat">Profile</a>
                        </div>
                        <div class="pull-right">
                          <a :href="sign_out_url" class="btn btn-default btn-flat">Sign out</a>
                        </div>
                      </li>
                    </ul>
                </li>
                <li>
                    <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                </li>
            </ul>
         </div>
    </nav>

</header>
</template>

<script>
import util from '../lib/util'
export default {
  name: 'dashboard',
  props: {
    data: Object
  } ,
  mounted : function() {
    console.log("mounted!!")
  } ,
  computed : {
    index_url : function() {
        return util.get_val(this.data['indexUrl'] ,  '#' )
    } ,
    logo_name :  function() {
        return util.get_val(this.data['logoName'] , '' )
    } ,
    logo_short_name : function() {
        return util.get_val(this.data['logoShortName'] , '' )
    } ,
    sign_out_url : function() {
        return util.get_val(this.data['signOutUrl'] , '#' )
    } ,
    user_img_url : function() {
        return util.get_val(this.data['userImgUrl'] , '/lib/vue-adminlte/dist/img/jd_def.gif' )
    } ,
    profile_url : function() {
        return util.get_val(this.data['profileUrl'] , '#')
    } ,
    user_name : function() {
        return util.get_val( this.data['userName'] , 'No Name')
    }
  }
}
</script>