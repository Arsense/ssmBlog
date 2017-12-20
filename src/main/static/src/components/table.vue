<template>
<table id="v-table1" class="table table-bordered table-striped v-table">
    <thead>
        <tr>
            <th v-for="col in columns">
                {{col['title']}}
            </th>
        </tr>
    </thead>
    <tbody>
        <tr v-for="item in dataItems">
            <td v-for="_col in columns">{{item[_col['key']]}}</td>
        </tr>
    </tbody>
</table>
</template>

<script>

export default {
  name: 'dataTable',
  props: {
    data : Object ,
    ajax_url: String

  } ,
  data: function () {

      return {
        columns: [],
        dataItems: []
      }
  },
  created : function() {
    if(this.ajax_url != undefined && this.ajax_url != "") {
        this.fetchData()
    } else if( this.data != undefined )  {
        this.columns    = this.data.columns
        this.dataItems  = this.data.dataItems
    }

  } ,
  computed: {

  } ,
  mounted : function() {
    console.log(" table mounted!!")
  } ,
  methods : {
    fetchData: function () {
        var self = this
        axios.get(this.ajax_url).then(function (response){
            self.columns = response.data.tableData.columns
            self.dataItems = response.data.tableData.dataItems
        })
    }
  }
}
</script>