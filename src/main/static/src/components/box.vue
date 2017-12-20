<template>
  <div class="box box-success">
    <div class="box-header" :class="(isBorder || isSolid)?'with-border':''">
        <slot name="icon-title"></slot>
        <h3 class="box-title">{{ title }}</h3>
        <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" :data-widget="widgetType"><i :class="btnIcon"></i>
                </button>
              </div>
        <!-- /.box-tools -->
    </div>

      <div class="box-body">
            <slot></slot>
      </div>
      <!-- /.box-body -->
    <div class="box-footer clearfix">
        <slot name="footer"></slot>
    </div>
  </div>

</template>

<script>
export default {
  name: 'va-box',
  props: {
      widgetType:   { type: String, default: 'collapse' },
      title:        { type: String },
      isSolid:      { type: Boolean, default: false },
      isOpen:       { type: Boolean , default:true } ,
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
  created () {

  }
}
</script>
