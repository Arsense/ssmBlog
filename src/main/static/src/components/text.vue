<template>
    <p :class="colorType">
    <slot></slot>
    </p>
</template>

<script>
    export default {
        name: "v-text",
        props:{
            type:{
                type:String,
                default:'green'
            }
        },
        computed:{
            colorType(){
                switch(this.type){
                    case 'green':
                    case 'aqua':
                    case 'light-blue':
                    case 'red':
                    case 'yellow':
                    case 'muted':
                        break
                    default:
                        return 'lead'
                }
                return 'text-' + this.type
            }
        },
        created () {

        }
    }
</script>

<style scoped>

</style>