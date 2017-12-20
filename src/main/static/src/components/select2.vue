<template>
  <select class="form-control select2" :multiple="multiple" data-placeholder="Select a State"
                               style="width: 100%;">
    <option v-for="opt in data">{{opt['text']}}</option>
    <slot></slot>
  </select>

</template>

<script>
export default {
  name: 'v-select2',
  props: {
      data: Array ,
      multiple: {type : Boolean , default: false }
  },
  mounted: function() {
     $('.select2').select2();
  },

  created () {

  }
}
</script>
