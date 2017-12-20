<template>
    <v-box>

    </v-box>
</template>

<script>
import VBox from './box.vue'
export default {
  name: 'va-box',
  props: {
      widgetType:   { type: String, default: 'collapse' },
      title:        { type: String },
      isSolid:      { type: Boolean, default: false },
      isBorder:     { type: Boolean, default: false }
  },
  mounted: function() {
     $('.box').boxWidget()
  },
  computed: {
      btnIcon () {
        if (this.widgetType === 'collapse') {
          return this.isOpen ? 'fa fa-minus' : 'fa fa-plus'
        } else if (this.widgetType === 'remove') {
          return 'fa fa-times'
        }
        return ''
      }
  },
  components: {
        'v-box': VBox
  } ,
  created () {

  }
}
</script>
