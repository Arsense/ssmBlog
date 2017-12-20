<template>

<footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
            Test
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="#">JD</a>.</strong> All rights reserved.
</footer>
</template>

<script>

export default {
  name: 'v-footer',
  props: {
    data: String
  } ,
  mounted : function() {
    console.log("footer + mounted!!")
  }
}
</script>