<template>
    <div class="callout" :class="classObject">
        <h4>{{ title }}</h4>
        <p>{{ text }}</p>
    </div>
</template>



<script>
    const CalloutTypes=['success','info','danger','warning']
    export default {
        name: "v-callout",
        props:{
            type:{
                default:'info',
                validator:function(value){
                    return CalloutTypes.includes(value)
                }
            },
            title:{
                type:String
            },
            text:{
                type:String
            }
        },
        computed:{
            classObject () {
                return{
                    'callout-success': this.type === 'success',
                    'callout-info': this.type === 'info',
                    'callout-warning': this.type === 'warning',
                    'callout-danger': this.type === 'danger'
                }
            }
        }


    }
</script>

<style scoped>

</style>